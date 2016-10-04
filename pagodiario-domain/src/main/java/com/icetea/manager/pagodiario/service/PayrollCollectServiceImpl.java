package com.icetea.manager.pagodiario.service;

import static org.slf4j.LoggerFactory.getLogger;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;

import com.icetea.manager.pagodiario.api.dto.PayrollCollectDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.PaymentDao;
import com.icetea.manager.pagodiario.dao.PayrollCollectDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Collector;
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

	private static final Logger LOGGER = getLogger(PayrollCollectServiceImpl.class);
	
	private final BillDao billDao;
	private final PaymentDao paymentDao;
	
	@Inject
	public PayrollCollectServiceImpl(PayrollCollectDao dao,
			PayrollCollectDtoModelTransformer transformer,
			BillDao billDao, PaymentDao paymentDao) {
		super(dao, transformer);
		this.billDao = billDao;
		this.paymentDao = paymentDao;
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
	
	/**
	 * PARA CALCULAR LA LIQ DE COBRADORES, TENGO Q BUSCAR TODOS LOS PAGOS PARA LA FEC A LIQ,
	 * TAMB TENGO Q TNER EN CUENTA TODAS LAS FACTURAS POR COBRADOR QUE DEBERIAN SER COBRADAS
	 * TAMB TENGO Q TNER EN CUENTA CTAS FACTURAS FUERON COBRADAR REALMENTE ...
	 */
	@Override
	public PayrollCollectDto processPayrollAlternative(final String inputDate){
		
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(inputDate), 
				"La Fecha de liquidacion de cobrador es requerida", ErrorType.VALIDATION_ERRORS);
		
		final Date date = DateUtils.parseDate(inputDate);
		
		PayrollCollect payrollCollect = this.getDao().findByDate(date);
		
		ErrorTypedConditions.checkArgument(payrollCollect == null,
				String.format("Ya existe una liquidación de cobrador con fecha: %s", inputDate), 
				ErrorType.VALIDATION_ERRORS);

		payrollCollect = new PayrollCollect();
		payrollCollect.setPayrollDate(date);
		
		List<PayrollItemCollect> payrollItemCollectList = payrollCollect.getPayrollItemCollectList();
		
		// primero deberia generar la lista de payrollcollectitems teniendo en cuenta las facturas a cobrar ...
		// luego una vez q genere todo esto,,, actualizo montos y valores tniendo en cuenta los pagos reales ...
		// busco el total de facturas q corresponde liquidarse en esta fecha
		int billsToCollectCount = this.fillPaymentCollectItemList(date, payrollCollect, payrollItemCollectList);
		
		List<Payment> payments = this.getPaymentsToCollect(inputDate);
		
		if(!payments.isEmpty()){
			for (Payment payment : payments) {
				final Bill bill = payment.getBill();
				final Collector collector = payment.getCollector();

				PayrollItemCollect payrollItemCollect = CollectionUtils.find(payrollItemCollectList, 
						new Predicate<PayrollItemCollect>() {
							@Override
							public boolean evaluate(PayrollItemCollect pic) {
								return pic.getCollector().getId().equals(collector.getId());
							}
				});
				
				// entonces x cada pago,, hago lo q falta ...
				payrollItemCollect.acumTotalPayment(payment.getAmount());
				payrollCollect.acumTotalPayment(payment.getAmount());
				payrollItemCollect.incrementCardsReal();
				payrollCollect.incrementCardsReal();

				ConciliationItemCollect cic = new ConciliationItemCollect();
				cic.setAmount(payment.getAmount());
				cic.setBill(bill);
				cic.setDescription(StringUtils.EMPTY);
				cic.setPayrollItemCollect(payrollItemCollect);
				payrollItemCollect.addConciliationItemCollect(cic);
			}
		}
		// finalmente calculo las comisiones de cada cobrador ...
		for(PayrollItemCollect c : payrollItemCollectList){
			LOGGER.info(String.format("LIQ COBRADOR: Cobrador: %s, Cant Tarj Real: %s, Monto a pagar: %s, Comision: %s", 
					c.getCollector().getDescription(), c.getCardsCountReal(), c.getAmountToPay(), c.getCommission()));
			BigDecimal commissionPercentage = c.commissionPercentage();
			BigDecimal totalPayment = c.getTotalPayment();
			
			BigDecimal amountToPay = NumberUtils.calculatePercentage(totalPayment, commissionPercentage);
			c.setAmountToPay(amountToPay);
			payrollCollect.acumTotalAmountToPay(amountToPay);
		}
		payrollCollect.setStatus(Status.FINISHED);
		
		payrollCollect.setTotalCardsCount(billsToCollectCount); // agrego el total de tarjetas a cobrar en un dia ...
		
		this.getDao().saveOrUpdate(payrollCollect);
		
		return this.getTransformer().transform(payrollCollect);
	}

	private int fillPaymentCollectItemList(final Date date, PayrollCollect payrollCollect,
			List<PayrollItemCollect> payrollItemCollectList) {
		
		int billsToCollectCount = 0;

		List<Bill> billsActiveByDate = this.billDao.findActivesByDate(date);
		
		
		if(billsActiveByDate != null){
			billsToCollectCount = billsActiveByDate.size();
			LOGGER.info(String.format("LIQ COBRADOR: Cantidad de facturas activas: %s", billsToCollectCount));
			for (final Bill bill : billsActiveByDate) {
				// busco si ya tengo creada una liq para el cobrador de este pago ...
				PayrollItemCollect payrollItemCollect = CollectionUtils.find(payrollItemCollectList, new Predicate<PayrollItemCollect>() {
					@Override
					public boolean evaluate(PayrollItemCollect item) {
						
						return item.getCollector().getId().equals(bill.getCollector().getId());
					}
				});
				// si no la tngo creada,, entonces la genero
				if(payrollItemCollect == null){
					payrollItemCollect = new PayrollItemCollect();
					payrollItemCollect.setCollector(bill.getCollector());
					payrollItemCollect.setPayrollCollect(payrollCollect);
					payrollItemCollectList.add(payrollItemCollect);
					
					Collection<Bill> foundBills = CollectionUtils.select(billsActiveByDate, new Predicate<Bill>() {
						@Override
						public boolean evaluate(Bill b) {
							return b.getCollector().getId().equals(bill.getCollector().getId());
						}
					});
					if(foundBills != null){
						for (Bill b : foundBills) {
							// x cada factura incremento una tarjeta a cobrar, q no quiere decir, q esa tarj haya sido cobrada ...
							payrollItemCollect.incrementCards();
							BigDecimal amount = b.getTotalDailyInstallment();
							payrollItemCollect.acumTotalAmount(amount);
							payrollCollect.acumTotalAmount(amount);
						}
					}
				}
			}
		}
		
		return billsToCollectCount;
	}
	
	protected List<Payment> getPaymentsToCollect(String paymentDate){
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(paymentDate), ErrorType.VALIDATION_ERRORS);
		
		Date from = DateUtils.truncate(DateUtils.parseDate(paymentDate));
		Date to = DateUtils.normalizeTo(from);
		
		return this.paymentDao.findToCollect(from, to);
	}

	@Override
	public boolean existsByPaymentId(Long paymentId){
		return this.getDao().findByPaymentId(paymentId) != null;
	}
	
}
