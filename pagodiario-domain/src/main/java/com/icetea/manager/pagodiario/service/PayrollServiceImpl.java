package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.NotImplementedException;

import com.icetea.manager.pagodiario.api.dto.PayrollDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.BonusDao;
import com.icetea.manager.pagodiario.dao.DiscountDao;
import com.icetea.manager.pagodiario.dao.PayrollDao;
import com.icetea.manager.pagodiario.dao.ProductReductionDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bonus;
import com.icetea.manager.pagodiario.model.ConciliationItem;
import com.icetea.manager.pagodiario.model.Discount;
import com.icetea.manager.pagodiario.model.Payroll;
import com.icetea.manager.pagodiario.model.Payroll.Status;
import com.icetea.manager.pagodiario.model.PayrollItem;
import com.icetea.manager.pagodiario.model.ProductReduction;
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
	
	@Inject
	public PayrollServiceImpl(PayrollDao dao,
			PayrollDtoModelTransformer transformer,
			BillDao billDao,
			DiscountDao discountDao,
			BonusDao bonusDao,
			ProductReductionDao productReductionDao) {
		super(dao, transformer);
		this.billDao = billDao;
		this.discountDao = discountDao;
		this.bonusDao = bonusDao;
		this.productReductionDao = productReductionDao;
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
		
		Payroll payroll = new Payroll();
		payroll.setDateFrom(dateFrom);
		payroll.setDateTo(dateTo);
		
		BigDecimal totalAmount = BigDecimal.ZERO;
		
		if(bills != null){
			for(Bill bill : bills){
				PayrollItem payrollItem = new PayrollItem();
				
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
				
				payroll.addPayrollItem(payrollItem);
			}
		}
		List<Discount> discounts = this.discountDao.find(dateFrom, dateTo);
		BigDecimal totalDiscount = BigDecimal.ZERO;
		if(discounts != null){
			for(final Discount e : discounts){
				
				PayrollItem item = null;
				ConciliationItem conciliationItem = null;
				
				List<PayrollItem> payrollItemList = payroll.getPayrollItemList();
				
				for(PayrollItem p : payrollItemList){
					conciliationItem = CollectionUtils.find(p.getItems(), new Predicate<ConciliationItem>() {
						@Override
						public boolean evaluate(ConciliationItem o) {
							return o.getBill().getId().equals(e.getBill().getId());
						}
					});
				}
				if(conciliationItem == null){
					item = new PayrollItem();
					item.setItemDate(e.getDate());
					
					item.setTrader(e.getBill().getTrader());
					
					item.setPayroll(payroll);
					payroll.addPayrollItem(item);
				} else {
					item = conciliationItem.getPayrollItem();
				}
				
				if(item.getItems() != null && !item.getItems().isEmpty()){
					// pongo el 0 pq a este punto solo voy a tener 1 siempre porque solo tengo el de la factura ...
					conciliationItem = item.getItems().get(0);
				} else {
					conciliationItem = new ConciliationItem(ConciliationItem.Type.CREDIT);
					conciliationItem.setPayrollItem(item);
					conciliationItem.setDate(e.getBill().getStartDate());
					conciliationItem.setBill(e.getBill());
					conciliationItem.setDescription("'CREDITO' " 
							+ conciliationItem.getBill().getCreditNumber() + " FECHA " 
							+ DateUtils.toDate(conciliationItem.getBill().getStartDate(), "dd/MM/yyyy"));
					item.addItem(conciliationItem);
				}
				conciliationItem.setDiscountAmount(NumberUtils.add(conciliationItem.getDiscountAmount(), e.getAmount()));
				
				totalDiscount = NumberUtils.add(totalDiscount, e.getAmount());
			}
		}
		List<Bonus> bonusList = this.bonusDao.find(dateFrom, dateTo);
		if(bonusList != null){
			for(final Bonus e : bonusList){
				// TODO: completar logica de premios
				
				totalAmount = NumberUtils.add(totalAmount, e.getAmount());
			}
		}
		List<ProductReduction> productReductionList = this.productReductionDao.find(dateFrom, dateTo);
		if(productReductionList != null){
			for(final ProductReduction e : productReductionList){
				
				PayrollItem item = null;
				ConciliationItem conciliationItem = null;
				
				List<PayrollItem> payrollItemList = payroll.getPayrollItemList();
				
				for(PayrollItem p : payrollItemList){
					conciliationItem = CollectionUtils.find(p.getItems(), new Predicate<ConciliationItem>() {
						@Override
						public boolean evaluate(ConciliationItem o) {
							return o.getBill().getId().equals(e.getBill().getId()) 
									&& o.getType() == ConciliationItem.Type.REDUCTION;
						}
					});
				}
				if(conciliationItem == null){
					item = new PayrollItem();
					item.setItemDate(e.getDate());
					
					item.setTrader(e.getBill().getTrader());
					
					item.setPayroll(payroll);
					payroll.addPayrollItem(item);
					
					conciliationItem = new ConciliationItem(ConciliationItem.Type.REDUCTION);
					conciliationItem.setBill(e.getBill());
					conciliationItem.setPayrollItem(item);
					
					conciliationItem.setDescription("'1/2 BAJA CRED' " 
							+ conciliationItem.getBill().getCreditNumber() + " FEC " 
							+ DateUtils.toDate(conciliationItem.getBill().getStartDate(), "dd/MM/yyyy"));
					
					item.addItem(conciliationItem);
					
				} else {
					item = conciliationItem.getPayrollItem();
				}
				
				BigDecimal amount = e.getAmount().negate();
				
				conciliationItem.setCollectAmount(NumberUtils.add(conciliationItem.getCollectAmount(), amount));
				
				totalAmount = NumberUtils.add(totalAmount, amount);
			}
		}
		// finalmente seteo el total de la liquidacion (comisiones + premios - bajas)
		payroll.setTotalAmount(totalAmount);
		// finalmente seteo el total de descuentos de la liquidacion
		payroll.setTotalDiscount(totalDiscount);
		payroll.setStatus(Status.FINISHED);
		payroll.setTotal(NumberUtils.subtract(totalAmount, totalDiscount));
		this.getDao().saveOrUpdate(payroll);
		
		return this.getTransformer().transform(payroll);
	}

	@Override
	public PayrollDto insert(PayrollDto o) {
		throw new NotImplementedException("not implemented");
	}


	@Override
	public PayrollDto update(PayrollDto o) {
		throw new NotImplementedException("not implemented");
	}
	
}
