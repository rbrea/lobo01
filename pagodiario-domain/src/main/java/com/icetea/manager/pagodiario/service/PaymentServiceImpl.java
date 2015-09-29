package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.PaymentDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.CollectorDao;
import com.icetea.manager.pagodiario.dao.PaymentDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bill.Status;
import com.icetea.manager.pagodiario.model.Collector;
import com.icetea.manager.pagodiario.model.Payment;
import com.icetea.manager.pagodiario.transformer.PaymentDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class PaymentServiceImpl 
	extends BasicIdentifiableServiceImpl<Payment, PaymentDao, PaymentDto, PaymentDtoModelTransformer> 
		implements PaymentService {

	private final BillDao billDao;
	private final CollectorDao collectorDao;
	
	@Inject
	public PaymentServiceImpl(PaymentDao dao,
			PaymentDtoModelTransformer transformer,
			BillDao billDao,
			CollectorDao collectorDao) {
		super(dao, transformer);
		this.billDao = billDao;
		this.collectorDao = collectorDao;
	}
	
	@Override
	public PaymentDto insert(PaymentDto d) {
		Payment e = new Payment();
		
		ErrorTypedConditions.checkArgument(d.getAmount() != null, "importe es requerido", ErrorType.VALIDATION_ERRORS);
		ErrorTypedConditions.checkArgument(d.getCollectorId() != null, "id de cobrador/zona es requerido", ErrorType.VALIDATION_ERRORS);
		ErrorTypedConditions.checkArgument(d.getDate() != null, "fecha de pago es requerida", ErrorType.VALIDATION_ERRORS);
		

		BigDecimal amount = NumberUtils.toBigDecimal(d.getAmount());
		e.setAmount(amount);
		
		ErrorTypedConditions.checkArgument(d.getBillId() != null || d.getCreditNumber() != null, ErrorType.BILL_REQUIRED);
		
		Bill bill = null;
		if(d.getBillId() != null){
			bill = this.billDao.findById(d.getBillId());
		} else if(d.getCreditNumber() != null){
			bill = this.billDao.findByCreditNumber(d.getCreditNumber());
		}
		
		ErrorTypedConditions.checkArgument(bill != null, "Número de crédito inexistente.", ErrorType.BILL_NOT_FOUND);
		
		ErrorTypedConditions.checkArgument(amount.compareTo(bill.getTotalDailyInstallment()) >= 0, 
				String.format("El monto a pagar no puede ser menor que la cuota diaria: $%s", 
						NumberUtils.toString(bill.getTotalDailyInstallment())), ErrorType.VALIDATION_ERRORS);
		
		ErrorTypedConditions.checkArgument(amount.compareTo(bill.getRemainingAmount()) <= 0, 
				String.format("El monto ingresado: $%s no puede ser mayor al monto restante a pagar de la factura: $%s", 
						d.getAmount(), NumberUtils.toString(bill.getRemainingAmount())), ErrorType.VALIDATION_ERRORS);
		
		e.setBill(bill);
		
		ErrorTypedConditions.checkArgument(d.getCollectorId() != null, "nro de zona de cobrador es requerido", ErrorType.VALIDATION_ERRORS);
		
		Collector collector = this.collectorDao.findByZone(d.getCollectorId());
		
		ErrorTypedConditions.checkArgument(collector != null, 
				String.format("Cobrador no encontrado con nro de zona: %s", d.getCollectorId()), ErrorType.VALIDATION_ERRORS);
		
		e.setCollector(collector);
		e.setDate(DateUtils.parseDate(d.getDate(), "dd/MM/yyyy"));
		this.getDao().saveOrUpdate(e);
		
		bill.addPayment(e);
		bill.setRemainingAmount(NumberUtils.subtract(bill.getRemainingAmount(), e.getAmount()));
		// tengo q restar el monto (calculando los dias de atraso)
		
		int overdueDays = e.getAmount().divide(
				bill.getTotalDailyInstallment(), RoundingMode.DOWN).intValue();
		
		bill.decrementOverdueDays(overdueDays);
		
		if(bill.getRemainingAmount().compareTo(BigDecimal.ZERO) <= 0){
			bill.setStatus(Status.COMPLETED);
			bill.setCompletedDate(new Date());
		}
		
		this.billDao.saveOrUpdate(bill);
		
		return d;
	}
	
	@SuppressWarnings("unused")
	@Override
	public PaymentDto update(PaymentDto d) {
		Payment e = new Payment();
		
		return d;
	}

	@Override
	public List<PaymentDto> search(Long billId){
		
		ErrorTypedConditions.checkArgument(billId != null, ErrorType.BILL_REQUIRED);
		
		return this.getTransformer().transformAllTo(this.getDao().findByBillId(billId));
	}

	@Override
	public List<PaymentDto> transform(List<BillDto> bills){
		List<PaymentDto> list = Lists.newArrayList();

		if(bills == null){
			return list;
		}
		
		for(BillDto b : bills){
			PaymentDto p = new PaymentDto();
			p.setDate(DateUtils.currentDate());
			p.setAmount(b.getTotalDailyInstallment());
			p.setBillId(b.getId());
			p.setCollectorId(b.getCollectorId());
			list.add(p);
		}
		
		return list;
	}

}
