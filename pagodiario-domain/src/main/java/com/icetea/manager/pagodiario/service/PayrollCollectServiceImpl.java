package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.NotImplementedException;

import com.icetea.manager.pagodiario.api.dto.PayrollCollectDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.PayrollCollectDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.ConciliationItemCollect;
import com.icetea.manager.pagodiario.model.Payment;
import com.icetea.manager.pagodiario.model.PayrollCollect;
import com.icetea.manager.pagodiario.model.PayrollCollect.Status;
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
	private final PaymentService paymentService;
	
	@Inject
	public PayrollCollectServiceImpl(PayrollCollectDao dao,
			PayrollCollectDtoModelTransformer transformer,
			BillDao billDao, PaymentService paymentService) {
		super(dao, transformer);
		this.billDao = billDao;
		this.paymentService = paymentService;
	}

	@Override
	public PayrollCollectDto processPayroll(final String inputDate){
		
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(inputDate), 
				"La Fecha de liquidacion de cobrador es requerida", ErrorType.VALIDATION_ERRORS);
		
		final Date date = DateUtils.parseDate(inputDate);
		
		PayrollCollect payrollCollect = this.getDao().findByDate(date);
		
		ErrorTypedConditions.checkArgument(payrollCollect == null,
				String.format("Ya existe una liquidación de cobrador con fecha: %s", inputDate), 
				ErrorType.VALIDATION_ERRORS);
		
		List<Bill> bills = this.billDao.findActivesByDateWithoutDayOfWeek(date);
		
		if(bills == null || bills.isEmpty()){
			
			return null;
		}
		
		payrollCollect = new PayrollCollect();
		payrollCollect.setPayrollDate(date);
		
		List<PayrollItemCollect> payrollItemCollectList = payrollCollect.getPayrollItemCollectList();
		
		List<Bill> billsActiveByDate = this.billDao.findActivesByDate(date);
		int billsCount = 0;
		if(billsActiveByDate != null){
			billsCount = billsActiveByDate.size();
		}
		
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
			// x cada factura incremento una tarjeta a cobrar, q no quiere decir, q esa tarj haya sido cobrada ...
			payrollItemCollect.incrementCards();
			
			BigDecimal amount = bill.getTotalDailyInstallment();
			
			payrollItemCollect.acumTotalAmount(amount);
			
//			payrollItemCollect.incrementCards();
//			payrollCollect.incrementCards();
			
			List<Payment> payments = ListUtils.select(bill.getPayments(), new Predicate<Payment>() {
				@Override
				public boolean evaluate(Payment p) {
					
					return DateUtils.truncate(p.getDate()).compareTo(DateUtils.truncate(date)) == 0;
				}
			});
			
			if(payments != null && !payments.isEmpty()){
				for(Payment p : payments){
					payrollItemCollect.acumTotalPayment(p.getAmount());
					payrollCollect.acumTotalPayment(p.getAmount());
					payrollItemCollect.incrementCardsReal();
					payrollCollect.incrementCardsReal();

					ConciliationItemCollect cic = new ConciliationItemCollect();
					cic.setAmount(p.getAmount());
					cic.setBill(bill);
					cic.setDescription("");
					cic.setPayrollItemCollect(payrollItemCollect);
					payrollItemCollect.addConciliationItemCollect(cic);
				}
			}
			payrollCollect.acumTotalAmount(amount);
		}
		
		// finalmente calculo las comisiones de cada cobrador ...
		for(PayrollItemCollect c : payrollItemCollectList){
			BigDecimal commissionPercentage = c.commissionPercentage();
			BigDecimal totalPayment = c.getTotalPayment();
			
			BigDecimal amountToPay = NumberUtils.calculatePercentage(totalPayment, commissionPercentage);
			c.setAmountToPay(amountToPay);
			payrollCollect.acumTotalAmountToPay(amountToPay);
		}
		payrollCollect.setStatus(Status.FINISHED);
		
		payrollCollect.setTotalCardsCount(billsCount); // agrego el total de tarjetas a cobrar en un dia ...
		
		this.getDao().saveOrUpdate(payrollCollect);
		
		return this.getTransformer().transform(payrollCollect);
	}

	@Override
	public PayrollCollectDto insert(PayrollCollectDto o) {
		throw new NotImplementedException("not implemented");
	}

	@Override
	public PayrollCollectDto update(PayrollCollectDto o) {
		throw new NotImplementedException("not implemented");
	}

	@Override
	public PayrollCollectDto commitPayroll(Long id){
		PayrollCollect payrollCollect = this.getDao().findById(id);
		
		ErrorTypedConditions.checkArgument(payrollCollect != null, "Liquidacion de cobrador seleccionada para confirmar no existe.", 
				ErrorType.VALIDATION_ERRORS);
		
		payrollCollect.setStatus(Status.COMMITED);
		
		this.getDao().saveOrUpdate(payrollCollect);
		
		return this.getTransformer().transform(payrollCollect);
	}
	
}
