package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.NotImplementedException;

import com.icetea.manager.pagodiario.api.dto.PayrollDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.PayrollDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Payroll;
import com.icetea.manager.pagodiario.model.PayrollTrader;
import com.icetea.manager.pagodiario.transformer.PayrollDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class PayrollServiceImpl extends
		BasicIdentifiableServiceImpl<Payroll, PayrollDao, PayrollDto, PayrollDtoModelTransformer>
  		implements PayrollService {

	private final BillDao billDao;
	
	@Inject
	public PayrollServiceImpl(PayrollDao dao,
			PayrollDtoModelTransformer transformer,
			BillDao billDao) {
		super(dao, transformer);
		this.billDao = billDao;
	}
	
	
	public PayrollDto processPeriod(Date dateFrom, Date dateTo){
		List<Bill> bills = this.billDao.find(dateFrom, dateTo);
		
		ErrorTypedConditions.checkArgument(bills != null && !bills.isEmpty(), 
				String.format("No se encontraron facturas para el periodo %s al %s", 
						DateUtils.toDate(dateFrom), DateUtils.toDate(dateTo)), 
				ErrorType.BILL_NOT_FOUND);

		Payroll payroll = new Payroll();
		payroll.setDateFrom(dateFrom);
		payroll.setDateTo(dateTo);
		
		BigDecimal totalAmount = BigDecimal.ZERO;
		
		for(Bill bill : bills){
			PayrollTrader payrollTrader = new PayrollTrader();
			payrollTrader.setBill(bill);
			//payrollTrader.setBonus(bonus);
			
			BigDecimal commission = bill.getTotalAmount().multiply(BigDecimal.TEN).divide(
					NumberUtils._100, MathContext.DECIMAL128); 
			
			payrollTrader.setCommission(commission);
			//payrollTrader.setDiscount(discount);
			payrollTrader.setPayroll(payroll);
			payrollTrader.setTrader(bill.getTrader());
			
			totalAmount = NumberUtils.add(totalAmount, commission);
			
			payroll.addPayrollTrader(payrollTrader);
		}
		
		payroll.setTotalAmount(totalAmount);
		//payroll.setTotalDiscount(totalDiscount);
		//payroll.setTotalBonus(totalBonus);
		
		
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
