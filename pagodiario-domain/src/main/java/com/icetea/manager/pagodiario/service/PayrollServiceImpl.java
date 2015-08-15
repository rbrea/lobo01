package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.NotImplementedException;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.icetea.manager.pagodiario.api.dto.PayrollDetailDto;
import com.icetea.manager.pagodiario.api.dto.PayrollDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.BonusDao;
import com.icetea.manager.pagodiario.dao.DevDao;
import com.icetea.manager.pagodiario.dao.DiscountDao;
import com.icetea.manager.pagodiario.dao.PayrollDao;
import com.icetea.manager.pagodiario.dao.ProductReductionDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.AbstractConciliationItem;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bonus;
import com.icetea.manager.pagodiario.model.ConciliationItem;
import com.icetea.manager.pagodiario.model.Dev;
import com.icetea.manager.pagodiario.model.Discount;
import com.icetea.manager.pagodiario.model.Payroll;
import com.icetea.manager.pagodiario.model.Payroll.Status;
import com.icetea.manager.pagodiario.model.PayrollItem;
import com.icetea.manager.pagodiario.model.ProductReduction;
import com.icetea.manager.pagodiario.model.SupervisorConciliationItem;
import com.icetea.manager.pagodiario.model.SupervisorPayrollItem;
import com.icetea.manager.pagodiario.model.Trader;
import com.icetea.manager.pagodiario.transformer.PayrollDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class PayrollServiceImpl extends
		BasicIdentifiableServiceImpl<Payroll, PayrollDao, PayrollDto, PayrollDtoModelTransformer>
  		implements PayrollService {

	private final BillDao billDao;
	private final DiscountDao discountDao;
	private final BonusDao bonusDao;
	private final ProductReductionDao productReductionDao;
	private final DevDao devDao;
	
	@Inject
	public PayrollServiceImpl(PayrollDao dao,
			PayrollDtoModelTransformer transformer,
			BillDao billDao,
			DiscountDao discountDao,
			BonusDao bonusDao,
			ProductReductionDao productReductionDao,
			DevDao devDao) {
		super(dao, transformer);
		this.billDao = billDao;
		this.discountDao = discountDao;
		this.bonusDao = bonusDao;
		this.productReductionDao = productReductionDao;
		this.devDao = devDao;
	}
	
	@Override
	public PayrollDto processPeriod(Date dateFrom, Date dateTo){
		List<Bill> bills = this.billDao.find(dateFrom, dateTo);
		
		Payroll found = this.getDao().find(dateFrom, dateTo);
		
		ErrorTypedConditions.checkArgument(found == null, 
				String.format("Ya se hizo la liquidacion para el periodo: %s a %s", 
						DateUtils.toDate(dateFrom), 
						DateUtils.toDate(dateTo)), 
				ErrorType.VALIDATION_ERRORS);
		
		final Payroll payroll = new Payroll();
		payroll.setDateFrom(dateFrom);
		payroll.setDateTo(dateTo);
		
		BigDecimal totalAmount = BigDecimal.ZERO;
		
		if(bills != null){
			for(final Bill bill : bills){
				PayrollItem payrollItem = CollectionUtils.find(payroll.getPayrollItemList(), new Predicate<PayrollItem>() {
					@Override
					public boolean evaluate(PayrollItem item) {
						return item.getTrader().getId().equals(bill.getTrader().getId())
								&& item.getPayroll().getId().equals(payroll.getId());
					}
				});
				
				if(payrollItem == null){
					payrollItem = new PayrollItem();
				}
				
				BigDecimal commission = bill.getTotalAmount().multiply(BigDecimal.TEN).divide(
						NumberUtils._100, MathContext.DECIMAL128); 
				
				ConciliationItem conciliationItem = new ConciliationItem(ConciliationItem.Type.CREDIT);
				conciliationItem.setCollectAmount(commission);
				conciliationItem.setDescription("'CREDITO' " 
						+ bill.getCreditNumber() + " FECHA " + DateUtils.toDate(bill.getStartDate(), "dd/MM/yyyy"));
				conciliationItem.setDate(bill.getStartDate());
				conciliationItem.setPayrollItem(payrollItem);
				conciliationItem.setBill(bill);
				payrollItem.addItem(conciliationItem);
				
				payrollItem.setPayroll(payroll);
				payrollItem.setTrader(bill.getTrader());
				
				totalAmount = NumberUtils.add(totalAmount, commission);
				
				payrollItem.setItemDate(bill.getStartDate());
				payrollItem.setSubtotalCollect(NumberUtils.add(payrollItem.getSubtotalCollect(), commission));
				payrollItem.setTotalAmount(NumberUtils.add(payrollItem.getTotalAmount(), payrollItem.getSubtotalCollect()));
				
				payroll.addPayrollItem(payrollItem);
			}
		}
		List<Discount> discounts = this.discountDao.find(dateFrom, dateTo);
		BigDecimal totalDiscount = BigDecimal.ZERO;
		if(discounts != null){
			for(final Discount e : discounts){
				
				ConciliationItem conciliationItem = null;
				
				PayrollItem payrollItem = CollectionUtils.find(payroll.getPayrollItemList(), new Predicate<PayrollItem>() {
					@Override
					public boolean evaluate(PayrollItem item) {
						return item.getTrader().getId().equals(e.getBill().getTrader().getId());
					}
				});
				
				if(payrollItem == null){
					payrollItem = new PayrollItem();
					payrollItem.setItemDate(e.getDate());
					
					payrollItem.setTrader(e.getBill().getTrader());
					
					payrollItem.setPayroll(payroll);
					payroll.addPayrollItem(payrollItem);
				} else {
					conciliationItem = CollectionUtils.find(payrollItem.getItems(), new Predicate<ConciliationItem>() {
						@Override
						public boolean evaluate(ConciliationItem o) {
							return o.getBill().getId().equals(e.getBill().getId());
						}
					});
				}
				
				if(payrollItem.getItems() != null && !payrollItem.getItems().isEmpty()){
					// pongo el 0 pq a este punto solo voy a tener 1 siempre porque solo tengo el de la factura ...
					conciliationItem = payrollItem.getItems().get(0);
				} else {
					conciliationItem = new ConciliationItem(ConciliationItem.Type.CREDIT);
					conciliationItem.setPayrollItem(payrollItem);
					conciliationItem.setDate(e.getBill().getStartDate());
					conciliationItem.setBill(e.getBill());
					conciliationItem.setDescription("'CREDITO' " 
							+ conciliationItem.getBill().getCreditNumber() + " FECHA " 
							+ DateUtils.toDate(conciliationItem.getBill().getStartDate(), "dd/MM/yyyy"));
					payrollItem.addItem(conciliationItem);
				}
				BigDecimal realAmount = NumberUtils.calculatePercentage(e.getAmount(), BigDecimal.TEN);
				
				conciliationItem.setDiscountAmount(NumberUtils.add(conciliationItem.getDiscountAmount(), 
						realAmount));
				payrollItem.setSubtotalDiscount(NumberUtils.add(payrollItem.getSubtotalDiscount(), realAmount));
				payrollItem.setTotalAmount(NumberUtils.subtract(payrollItem.getTotalAmount(), payrollItem.getSubtotalDiscount()));
				totalDiscount = NumberUtils.add(totalDiscount, realAmount);
			}
		}
		List<Bonus> bonusList = this.bonusDao.find(dateFrom, dateTo);
		if(bonusList != null){
			for(final Bonus e : bonusList){
				// TODO: completar logica de premios
				
				totalAmount = NumberUtils.add(totalAmount, e.getAmount());
			}
		}
		
		List<Dev> devList = this.devDao.find(dateFrom, dateTo);
		if(devList != null){
			for(final Dev e : devList){
				
				ConciliationItem conciliationItem = null;
				
				PayrollItem payrollItem = CollectionUtils.find(payroll.getPayrollItemList(), new Predicate<PayrollItem>() {
					@Override
					public boolean evaluate(PayrollItem item) {
						return item.getTrader().getId().equals(e.getBill().getTrader().getId());
					}
				});
				
				if(payrollItem == null){
					payrollItem = new PayrollItem();
					payrollItem.setItemDate(e.getDate());
					
					payrollItem.setTrader(e.getBill().getTrader());
					
					payrollItem.setPayroll(payroll);
					payroll.addPayrollItem(payrollItem);
				} else {
					conciliationItem = CollectionUtils.find(payrollItem.getItems(), new Predicate<ConciliationItem>() {
						@Override
						public boolean evaluate(ConciliationItem o) {
							return o.getBill().getId().equals(e.getBill().getId())
									&& o.getType() == ConciliationItem.Type.DEVOLUTION;
						}
					});
				}
				
				if(conciliationItem == null){
					
					conciliationItem = new ConciliationItem(ConciliationItem.Type.DEVOLUTION);
					conciliationItem.setBill(e.getBill());
					conciliationItem.setPayrollItem(payrollItem);
					
					conciliationItem.setDescription("'DEV CRED' " 
							+ conciliationItem.getBill().getCreditNumber() + " FEC " 
							+ DateUtils.toDate(e.getDate(), "dd/MM/yyyy"));
					
					conciliationItem.setDate(e.getDate());
					payrollItem.addItem(conciliationItem);
					
				}
				
				BigDecimal amount = e.getAmount().negate();
				
				BigDecimal realAmount = NumberUtils.calculatePercentage(amount, BigDecimal.TEN);
				conciliationItem.setCollectAmount(NumberUtils.add(conciliationItem.getCollectAmount(), 
						realAmount));
				payrollItem.setSubtotalCollect(NumberUtils.add(payrollItem.getSubtotalCollect(), realAmount));
				payrollItem.setTotalAmount( 
						NumberUtils.subtract(payrollItem.getSubtotalCollect(), payrollItem.getSubtotalDiscount()));
				
				totalAmount = NumberUtils.add(totalAmount, realAmount);
			}
		}
		
		List<ProductReduction> productReductionList = this.productReductionDao.find(dateFrom, dateTo);
		if(productReductionList != null){
			for(final ProductReduction e : productReductionList){
				
				ConciliationItem conciliationItem = null;
				
				PayrollItem payrollItem = CollectionUtils.find(payroll.getPayrollItemList(), new Predicate<PayrollItem>() {
					@Override
					public boolean evaluate(PayrollItem item) {
						return item.getTrader().getId().equals(e.getBill().getTrader().getId());
					}
				});
				
				if(payrollItem == null){
					payrollItem = new PayrollItem();
					payrollItem.setItemDate(e.getDate());
					
					payrollItem.setTrader(e.getBill().getTrader());
					
					payrollItem.setPayroll(payroll);
					payroll.addPayrollItem(payrollItem);
				} else {
					conciliationItem = CollectionUtils.find(payrollItem.getItems(), new Predicate<ConciliationItem>() {
						@Override
						public boolean evaluate(ConciliationItem o) {
							return o.getBill().getId().equals(e.getBill().getId())
									&& o.getType() == ConciliationItem.Type.REDUCTION;
						}
					});
				}
				
				if(conciliationItem == null){
					conciliationItem = new ConciliationItem(ConciliationItem.Type.REDUCTION);
					conciliationItem.setBill(e.getBill());
					conciliationItem.setPayrollItem(payrollItem);
					
					conciliationItem.setDescription("'1/2 BAJA CRED' " 
							+ conciliationItem.getBill().getCreditNumber() + " FEC " 
							+ DateUtils.toDate(e.getDate(), "dd/MM/yyyy"));
					
					conciliationItem.setDate(e.getDate());
					payrollItem.addItem(conciliationItem);
					
				} else {
					payrollItem = conciliationItem.getPayrollItem();
				}
				
				BigDecimal amount = e.getAmount().negate();
				
				BigDecimal realAmount = NumberUtils.calculatePercentage(amount, BigDecimal.TEN);
				conciliationItem.setCollectAmount(NumberUtils.add(conciliationItem.getCollectAmount(), 
						realAmount));
				payrollItem.setSubtotalCollect(NumberUtils.add(payrollItem.getSubtotalCollect(), realAmount));
				payrollItem.setTotalAmount( 
						NumberUtils.subtract(payrollItem.getSubtotalCollect(), payrollItem.getSubtotalDiscount()));
				
				totalAmount = NumberUtils.add(totalAmount, realAmount);
			}
		}
		// finalmente seteo el total de la liquidacion (comisiones + premios - bajas)
		payroll.setTotalAmount(totalAmount);
		// finalmente seteo el total de descuentos de la liquidacion
		payroll.setTotalDiscount(totalDiscount);
		payroll.setStatus(Status.FINISHED);
		payroll.setTotal(NumberUtils.subtract(totalAmount, totalDiscount));
		
		this.processPeriodSupervisor(payroll);

		this.getDao().saveOrUpdate(payroll);

		PayrollDto payrollDto = this.getTransformer().transform(payroll);
		
		return payrollDto;
	}

	@Override
	public PayrollDto insert(PayrollDto o) {
		throw new NotImplementedException("not implemented");
	}


	@Override
	public PayrollDto update(PayrollDto o) {
		throw new NotImplementedException("not implemented");
	}

	@Override
	public List<PayrollDetailDto> searchDetail(Long payrollId){
		List<PayrollDetailDto> list = Lists.newArrayList();
		
		Payroll payroll = this.getDao().findById(payrollId);
		
		Map<Long, PayrollDetailDto> map = Maps.newHashMap();
		
		for(PayrollItem i : payroll.getPayrollItemList()){
			PayrollDetailDto d = map.get(i.getTrader().getId());
			
			if(d == null){
				d = new PayrollDetailDto();
				d.setId(i.getId());
				d.setName(i.getTrader().getName());
				d.setPhone(i.getTrader().getPhone());
				d.setTraderId(i.getTrader().getId());
			}
			d.setTotalAmount(NumberUtils.toString(i.getSubtotalCollect()));
			d.setTotalDiscount(NumberUtils.toString(i.getSubtotalDiscount()));
			d.setTotal(NumberUtils.toString(i.getTotalAmount()));
			
			map.put(i.getTrader().getId(), d);
		}
		list.addAll(map.values());

		return list;
	}
	
	public boolean processPeriodSupervisor(Payroll payroll){
		List<PayrollItem> payrollItemList = payroll.getPayrollItemList();
		
		for (PayrollItem p : payrollItemList) {
			final Trader trader = p.getTrader();
			
			final Trader supervisor = trader.getParent();
			if(supervisor != null){
				SupervisorPayrollItem item = CollectionUtils.find(payroll.getSupervisorPayrollItemList(), new Predicate<SupervisorPayrollItem>() {
					@Override
					public boolean evaluate(SupervisorPayrollItem s) {
						
						return s.getId().equals(supervisor.getId());
					}
				});
				if(item == null){
					item = new SupervisorPayrollItem();
					item.setPayroll(payroll);
					item.setSupervisor(supervisor);
					item.setItemDate(new Date());
					payroll.getSupervisorPayrollItemList().add(item);
				}
				// tengo que busc si en la lista de items tngo el vendedor q estoy procesando ...
				List<SupervisorConciliationItem> supervisorConciliationItems = item.getSupervisorConciliationItems();
				
				SupervisorConciliationItem supervisorConciliationItem = CollectionUtils.find(supervisorConciliationItems, new Predicate<SupervisorConciliationItem>() {
					@Override
					public boolean evaluate(SupervisorConciliationItem o) {
						return o.getTrader().getId().equals(trader.getId());
					}
				});
				if(supervisorConciliationItem == null){
					supervisorConciliationItem = new SupervisorConciliationItem(trader);
					supervisorConciliationItem.setSupervisorPayrollItem(item);
					item.addItem(supervisorConciliationItem);
				}
				for (ConciliationItem conciliationItem : p.getItems()) {
					if(conciliationItem.getType() == AbstractConciliationItem.Type.CREDIT){
						BigDecimal creditAmount = NumberUtils.subtract(conciliationItem.getCollectAmount(), conciliationItem.getDiscountAmount());
						creditAmount = NumberUtils.calculatePercentage(creditAmount, new BigDecimal(50));
						supervisorConciliationItem.setCreditAmount(NumberUtils.add(supervisorConciliationItem.getCreditAmount(), creditAmount));
						supervisorConciliationItem.setTotalTrader(
								NumberUtils.add(supervisorConciliationItem.getTotalTrader(), creditAmount));
					}
					if(conciliationItem.getType() == AbstractConciliationItem.Type.BONUS){
						BigDecimal bonusAmount = NumberUtils.calculatePercentage(conciliationItem.getCollectAmount(), new BigDecimal(50));
						supervisorConciliationItem.setBonusAmount(
								NumberUtils.add(
										supervisorConciliationItem.getBonusAmount(), bonusAmount));
						supervisorConciliationItem.setTotalTrader(
								NumberUtils.add(supervisorConciliationItem.getTotalTrader(), bonusAmount));
					}
					if(conciliationItem.getType() == AbstractConciliationItem.Type.DEVOLUTION){
						BigDecimal devAmount = NumberUtils.calculatePercentage(conciliationItem.getCollectAmount(), new BigDecimal(50));
						supervisorConciliationItem.setDevAmount(
								NumberUtils.add(
										supervisorConciliationItem.getDevAmount(), devAmount));
						supervisorConciliationItem.setTotalTrader(
								NumberUtils.subtract(supervisorConciliationItem.getTotalTrader(), devAmount));
					}
					if(conciliationItem.getType() == AbstractConciliationItem.Type.REDUCTION){
						BigDecimal reductionAmount = NumberUtils.calculatePercentage(conciliationItem.getCollectAmount(), new BigDecimal(50));
						supervisorConciliationItem.setReductionAmount(
								NumberUtils.add(
										supervisorConciliationItem.getReductionAmount(), reductionAmount));
						supervisorConciliationItem.setTotalTrader(
								NumberUtils.subtract(supervisorConciliationItem.getTotalTrader(), reductionAmount));
					}
					payroll.setTotalSupervisor(NumberUtils.add(
							payroll.getTotalSupervisor(), supervisorConciliationItem.getTotalTrader()));
					item.setTotalAmount(NumberUtils.add(item.getTotalAmount(), supervisorConciliationItem.getTotalTrader()));
				}
			}
			
		}
		
		return true;
	}

	@Override
	public List<PayrollDetailDto> searchSupervisorDetail(Long payrollId){
		List<PayrollDetailDto> list = Lists.newArrayList();
		
		Payroll payroll = this.getDao().findById(payrollId);
		
		Map<Long, PayrollDetailDto> map = Maps.newHashMap();
		
		for(SupervisorPayrollItem i : payroll.getSupervisorPayrollItemList()){
			PayrollDetailDto d = map.get(i.getSupervisor().getId());
			
			if(d == null){
				d = new PayrollDetailDto();
				d.setId(i.getId());
				d.setName(i.getSupervisor().getName());
				d.setPhone(i.getSupervisor().getPhone());
				d.setTraderId(i.getSupervisor().getId());
			}
			d.setTotal(NumberUtils.toString(i.getTotalAmount()));
			
			map.put(i.getSupervisor().getId(), d);
		}
		list.addAll(map.values());

		return list;
	}
	
}
