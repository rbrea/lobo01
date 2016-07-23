package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.PaymentDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.CollectorDao;
import com.icetea.manager.pagodiario.dao.PaymentDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Collector;
import com.icetea.manager.pagodiario.model.Payment;
import com.icetea.manager.pagodiario.transformer.PaymentDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.BillUtils;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class PaymentServiceImpl 
	extends BasicIdentifiableServiceImpl<Payment, PaymentDao, PaymentDto, PaymentDtoModelTransformer> 
		implements PaymentService {

	private final BillDao billDao;
	private final CollectorDao collectorDao;
	private final BillUtils billUtils;
	
	@Inject
	public PaymentServiceImpl(PaymentDao dao,
			PaymentDtoModelTransformer transformer,
			BillDao billDao,
			CollectorDao collectorDao,
			BillUtils billUtils) {
		super(dao, transformer);
		this.billDao = billDao;
		this.collectorDao = collectorDao;
		this.billUtils = billUtils;
	}
	
	@Override
	public PaymentDto insert(PaymentDto d, boolean bulk) {
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
		
		ErrorTypedConditions.checkArgument(amount.compareTo(bill.getRemainingAmount()) <= 0, 
				String.format("El monto ingresado: $%s no puede ser mayor al monto restante a pagar de la factura: $%s", 
						d.getAmount(), NumberUtils.toString(bill.getRemainingAmount())), ErrorType.VALIDATION_ERRORS);
		
		Date selectedDate = DateUtils.parseDate(d.getDate(), "dd/MM/yyyy");
		
		ErrorTypedConditions.checkArgument(!selectedDate.before(bill.getStartDate()), 
				"La fecha elegida no puede ser menor a la fecha de inicio de Factura");

		e.setBill(bill);
		
		ErrorTypedConditions.checkArgument(d.getCollectorId() != null, "nro de zona de cobrador es requerido", ErrorType.VALIDATION_ERRORS);
		
		Collector collector = this.collectorDao.findByZone(d.getCollectorId());
		
		ErrorTypedConditions.checkArgument(collector != null, 
				String.format("Cobrador no encontrado con nro de zona: %s", d.getCollectorId()), ErrorType.VALIDATION_ERRORS);
		
		e.setCollector(collector);
		e.setDate(selectedDate);
		this.getDao().saveOrUpdate(e);
		
		bill.addPayment(e);
		bill.setRemainingAmount(NumberUtils.subtract(bill.getRemainingAmount(), e.getAmount()));
		// tengo q restar el monto (calculando los dias de atraso)
		if(!NumberUtils.isNullOrZero(bill.getTotalDailyInstallment())){
			int overdueDays = e.getAmount().divide(
					bill.getTotalDailyInstallment(), RoundingMode.DOWN).intValue();
			
			bill.decrementOverdueDays(overdueDays);
		}
		this.billUtils.doBillCancelation(bill);
		
		this.billDao.saveOrUpdate(bill);
		
		d.setBillStatus(bill.getStatus().name());
		d.setRemainingAmount(NumberUtils.toString(bill.getRemainingAmount()));
		d.setOverdueDays(bill.getOverdueDays());
		d.setInstallmentAmount(NumberUtils.toString(bill.getTotalDailyInstallment()));
		
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
			
			p.setWeekFriday(b.isWeekFriday());
			p.setWeekMonday(b.isWeekMonday());
			p.setWeekSaturday(b.isWeekSaturday());
			p.setWeekSunday(b.isWeekSunday());
			p.setWeekThursday(b.isWeekThursday());
			p.setWeekTuesday(b.isWeekTuesday());
			p.setWeekWednesday(b.isWeekWednesday());
			
			list.add(p);
		}
		
		return list;
	}

	@Override
	public List<PaymentDto> search(Long billId, String paymentDate){
		
		ErrorTypedConditions.checkArgument(billId != null, ErrorType.BILL_REQUIRED);
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(paymentDate), ErrorType.VALIDATION_ERRORS);
		
		Date from = DateUtils.truncate(DateUtils.parseDate(paymentDate));
		Date to = DateUtils.normalizeTo(from);
		
		return this.getTransformer().transformAllTo(this.getDao().find(billId, from, to));
	}

	@Override
	public PaymentDto insert(PaymentDto o) {
		throw new ErrorTypedException("ERROR NO DEBE USARSE");
	}

	@Override
	public List<PaymentDto> searchByDate(String paymentDate){
		
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(paymentDate), ErrorType.VALIDATION_ERRORS);
		
		Date from = DateUtils.truncate(DateUtils.parseDate(paymentDate));
		Date to = DateUtils.normalizeTo(from);
		
		return this.getTransformer().transformAllTo(this.getDao().find(from, to));
	}

	@Override
	public boolean remove(Long id) {
		
		Payment payment = super.getDao().findById(id);
		if(payment != null){
			Date now = DateUtils.now();
			Bill bill = payment.getBill();
			bill.getPayments().remove(payment);
			bill.setUpdatedDate(now);
			// como estoy quitando el pago tengo q sumarle el pago q elimino a saldo restante para q se incremente...
			bill.setRemainingAmount(NumberUtils.add(bill.getRemainingAmount(), payment.getAmount()));
			if(!NumberUtils.isNullOrZero(bill.getTotalDailyInstallment())){
				int overdueDays = payment.getAmount().divide(
						bill.getTotalDailyInstallment(), RoundingMode.DOWN).intValue();
				// como estoy quitando el pago entonces incremento los dias d atraso para q vuelvan a estar como si el pago no hubiera sido hecho ...
				bill.setOverdueDays(overdueDays + bill.getOverdueDays());
				bill.setOverdueDaysFlag(now);
			}
			
			
			this.billDao.saveOrUpdate(bill);
		}
		
		return super.remove(id);
	}
	
}
