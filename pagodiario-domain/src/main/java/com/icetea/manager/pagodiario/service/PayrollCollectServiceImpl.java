package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.NotImplementedException;

import com.icetea.manager.pagodiario.api.dto.PaymentDto;
import com.icetea.manager.pagodiario.api.dto.PayrollCollectDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.CollectorDao;
import com.icetea.manager.pagodiario.dao.PayrollCollectDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Collector;
import com.icetea.manager.pagodiario.model.ConciliationItemCollect;
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
	private final CollectorDao collectorDao;
	
	@Inject
	public PayrollCollectServiceImpl(PayrollCollectDao dao,
			PayrollCollectDtoModelTransformer transformer,
			BillDao billDao, PaymentService paymentService,
			CollectorDao collectorDao) {
		super(dao, transformer);
		this.billDao = billDao;
		this.paymentService = paymentService;
		this.collectorDao = collectorDao;
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
		
		List<PaymentDto> payments = this.paymentService.searchByDate(inputDate);
		
		payrollCollect = new PayrollCollect();
		payrollCollect.setPayrollDate(date);
		
		List<PayrollItemCollect> payrollItemCollectList = payrollCollect.getPayrollItemCollectList();
		
		for(final PaymentDto payment : payments){
			
			PayrollItemCollect payrollItemCollect = CollectionUtils.find(payrollItemCollectList, new Predicate<PayrollItemCollect>() {
				@Override
				public boolean evaluate(PayrollItemCollect item) {
					
					return item.getCollector().getId().equals(payment.getCollectorId());
				}
			});
			
			if(payrollItemCollect == null){
				payrollItemCollect = new PayrollItemCollect();
				
				Collector collector = this.collectorDao.findById(payment.getCollectorId());
				payrollItemCollect.setCollector(collector);
				payrollItemCollect.setPayrollCollect(payrollCollect);
				payrollItemCollectList.add(payrollItemCollect);
			}
			
			BigDecimal amount = NumberUtils.toBigDecimal(payment.getAmount());
			
			payrollItemCollect.acumTotalAmount(amount);
			
			payrollItemCollect.incrementCards();
			payrollCollect.incrementCards();
			
//			List<Payment> payments = ListUtils.select(bill.getPayments(), new Predicate<Payment>() {
//				@Override
//				public boolean evaluate(Payment p) {
//					
//					return p.getDate().compareTo(date) == 0;
//				}
//			});
			
			payrollItemCollect.acumTotalPayment(amount);
			payrollCollect.acumTotalPayment(amount);
			
			ConciliationItemCollect cic = new ConciliationItemCollect();
			cic.setAmount(amount);
			
			Bill bill = this.billDao.findById(payment.getBillId());
			
			cic.setBill(bill);
			cic.setDescription(StringUtils.EMPTY);
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
			payrollCollect.acumTotalAmountToPay(amountToPay);
		}
		payrollCollect.setStatus(Status.FINISHED);

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
