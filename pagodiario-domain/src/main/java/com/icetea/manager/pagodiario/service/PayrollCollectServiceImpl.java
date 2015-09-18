package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;

import com.icetea.manager.pagodiario.api.dto.PayrollCollectDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.PayrollCollectDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.ConciliationItemCollect;
import com.icetea.manager.pagodiario.model.Payment;
import com.icetea.manager.pagodiario.model.PayrollCollect;
import com.icetea.manager.pagodiario.model.PayrollItemCollect;
import com.icetea.manager.pagodiario.transformer.PayrollCollectDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;
import com.icetea.manager.pagodiario.utils.StringUtils;

@Named
public class PayrollCollectServiceImpl extends
		BasicIdentifiableServiceImpl<PayrollCollect, PayrollCollectDao, PayrollCollectDto, PayrollCollectDtoModelTransformer> 
			implements PayrollCollectService {

	private final BillDao billDao;
	
	@Inject
	public PayrollCollectServiceImpl(PayrollCollectDao dao,
			PayrollCollectDtoModelTransformer transformer,
			BillDao billDao) {
		super(dao, transformer);
		this.billDao = billDao;
	}

	@Override
	public void processPayroll(final String inputDate){
		
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(inputDate), 
				"La Fecha de liquidacion de cobrador es requerida", ErrorType.VALIDATION_ERRORS);
		
		final Date date = DateUtils.parseDate(inputDate);
		
		PayrollCollect payrollCollect = this.getDao().findByDate(date);
		
		ErrorTypedConditions.checkArgument(payrollCollect != null,
				String.format("Ya existe una liquidación de cobrador con fecha: ", inputDate), 
				ErrorType.VALIDATION_ERRORS);
		
		List<Bill> bills = this.billDao.findActivesByDate(date);

		if(bills == null || bills.isEmpty()){
			
			return;
		}
		
		payrollCollect = new PayrollCollect();
		payrollCollect.setPayrollDate(date);
		
		List<PayrollItemCollect> payrollItemCollectList = payrollCollect.getPayrollItemCollectList();
		
		for(final Bill bill : bills){
			
			PayrollItemCollect payrollItemCollect = CollectionUtils.find(payrollItemCollectList, new Predicate<PayrollItemCollect>() {
				@Override
				public boolean evaluate(PayrollItemCollect item) {
					
					return item.getCollector().getId().equals(bill.getCollector().getId());
				}
			});
			
			if(payrollItemCollect == null){
				payrollItemCollect = new PayrollItemCollect();
				payrollItemCollect.setCollector(bill.getCollector());
				payrollItemCollect.setPayrollCollect(payrollCollect);
				payrollItemCollectList.add(payrollItemCollect);
			}
			
			BigDecimal amount = bill.getTotalDailyInstallment();
			
			payrollItemCollect.acumTotalAmount(amount);
			
			payrollItemCollect.incrementCards();
			
			List<Payment> payments = ListUtils.select(bill.getPayments(), new Predicate<Payment>() {
				@Override
				public boolean evaluate(Payment p) {
					return p.getDate().equals(date);
				}
			});
			
			if(payments != null && !payments.isEmpty()){
				for(Payment p : payments){
					payrollItemCollect.acumTotalPayment(p.getAmount());
					payrollCollect.acumTotalPayment(amount);
				}
			}
			
			ConciliationItemCollect cic = new ConciliationItemCollect();
			cic.setAmount(amount);
			cic.setBill(bill);
			cic.setDescription("");
			cic.setPayrollItemCollect(payrollItemCollect);
			payrollItemCollect.addConciliationItemCollect(cic);
			
			payrollCollect.acumTotalAmount(amount);
		}
		
		// finalmente calculo las comisiones de cada cobrador ...
		for(PayrollItemCollect c : payrollItemCollectList){
			BigDecimal commissionPercentage = c.commissionPercentage();
			BigDecimal totalPayment = c.getTotalPayment();
			
			BigDecimal amountToPay = NumberUtils.calculatePercentage(totalPayment, commissionPercentage);
			c.setAmountToPay(amountToPay);
		}

		this.getDao().saveOrUpdate(payrollCollect);
	}

	@Override
	public PayrollCollectDto insert(PayrollCollectDto o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PayrollCollectDto update(PayrollCollectDto o) {
		// TODO Auto-generated method stub
		return null;
	}

}