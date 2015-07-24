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
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.BonusDao;
import com.icetea.manager.pagodiario.dao.DiscountDao;
import com.icetea.manager.pagodiario.dao.PayrollDao;
import com.icetea.manager.pagodiario.dao.ProductReductionDao;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bonus;
import com.icetea.manager.pagodiario.model.Discount;
import com.icetea.manager.pagodiario.model.Payroll;
import com.icetea.manager.pagodiario.model.PayrollItem;
import com.icetea.manager.pagodiario.model.ProductReduction;
import com.icetea.manager.pagodiario.transformer.PayrollDtoModelTransformer;
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
		
		Payroll payroll = new Payroll();
		payroll.setDateFrom(dateFrom);
		payroll.setDateTo(dateTo);
		
		BigDecimal totalAmount = BigDecimal.ZERO;
		
		if(bills != null){
			for(Bill bill : bills){
				PayrollItem payrollItem = new PayrollItem();
				payrollItem.setBill(bill);
				
				BigDecimal commission = bill.getTotalAmount().multiply(BigDecimal.TEN).divide(
						NumberUtils._100, MathContext.DECIMAL128); 
				
				payrollItem.setCommission(commission);
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
				
				PayrollItem item = CollectionUtils.find(payroll.getPayrollItemList(), new Predicate<PayrollItem>() {
					@Override
					public boolean evaluate(PayrollItem o) {
						return o.getBill().getId().equals(e.getBill().getId());
					}
				});
				if(item == null){
					item = new PayrollItem();
					item.setItemDate(e.getDate());
					
					item.setBill(e.getBill());
					
					item.setTrader(e.getBill().getTrader());
					
					item.setPayroll(payroll);
					payroll.addPayrollItem(item);
				}
				item.setDiscountAmount(e.getAmount());
				
				totalDiscount = NumberUtils.add(totalDiscount, e.getAmount());
			}
		}
		List<Bonus> bonusList = this.bonusDao.find(dateFrom, dateTo);
		if(bonusList != null){
			for(final Bonus e : bonusList){
				
				PayrollItem item = CollectionUtils.find(payroll.getPayrollItemList(), new Predicate<PayrollItem>() {
					@Override
					public boolean evaluate(PayrollItem o) {
						return o.getBill().getId().equals(e.getBill().getId());
					}
				});
				if(item == null){
					item = new PayrollItem();
					item.setItemDate(e.getDate());
					
					item.setBill(e.getBill());
					
					item.setTrader(e.getBill().getTrader());
					
					item.setPayroll(payroll);
					payroll.addPayrollItem(item);
				}
				item.setCommission(e.getAmount());
				
				totalAmount = NumberUtils.add(totalAmount, e.getAmount());
			}
		}
		List<ProductReduction> productReductionList = this.productReductionDao.find(dateFrom, dateTo);
		if(productReductionList != null){
			for(final ProductReduction e : productReductionList){
				
				PayrollItem item = CollectionUtils.find(payroll.getPayrollItemList(), new Predicate<PayrollItem>() {
					@Override
					public boolean evaluate(PayrollItem o) {
						return o.getBill().getId().equals(e.getBill().getId());
					}
				});
				if(item == null){
					item = new PayrollItem();
					item.setItemDate(e.getDate());
					
					item.setBill(e.getBill());
					
					item.setTrader(e.getBill().getTrader());
					
					item.setPayroll(payroll);
					payroll.addPayrollItem(item);
				}
				item.setCommission(e.getAmount());
				
				totalAmount = NumberUtils.add(totalAmount, e.getAmount());
			}
		}
		// finalmente seteo el total de la liquidacion (comisiones + premios - bajas)
		payroll.setTotalAmount(totalAmount);
		// finalmente seteo el total de descuentos de la liquidacion
		payroll.setTotalDiscount(totalDiscount);
		
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
