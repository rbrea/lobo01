package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.common.base.Preconditions;
import com.icetea.manager.pagodiario.api.dto.BillDetailDevolutionDto;
import com.icetea.manager.pagodiario.api.dto.BillDetailDiscountDto;
import com.icetea.manager.pagodiario.api.dto.BillDetailDto;
import com.icetea.manager.pagodiario.api.dto.BillDetailPaymentDto;
import com.icetea.manager.pagodiario.api.dto.BillDetailReductionDto;
import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.BillInfoDto;
import com.icetea.manager.pagodiario.api.dto.BillProductDetailDto;
import com.icetea.manager.pagodiario.api.dto.BillProductDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.api.pojo.jasper.BillTicketPojo;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.ClientDao;
import com.icetea.manager.pagodiario.dao.CollectorDao;
import com.icetea.manager.pagodiario.dao.ConciliationItemCollectDao;
import com.icetea.manager.pagodiario.dao.ConciliationItemDao;
import com.icetea.manager.pagodiario.dao.ProductDao;
import com.icetea.manager.pagodiario.dao.TraderDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bill.Status;
import com.icetea.manager.pagodiario.model.BillProduct;
import com.icetea.manager.pagodiario.model.Client;
import com.icetea.manager.pagodiario.model.Collector;
import com.icetea.manager.pagodiario.model.ConciliationItem;
import com.icetea.manager.pagodiario.model.ConciliationItemCollect;
import com.icetea.manager.pagodiario.model.Dev;
import com.icetea.manager.pagodiario.model.Discount;
import com.icetea.manager.pagodiario.model.Payment;
import com.icetea.manager.pagodiario.model.Product;
import com.icetea.manager.pagodiario.model.ProductReduction;
import com.icetea.manager.pagodiario.model.Trader;
import com.icetea.manager.pagodiario.transformer.BillDtoModelTransformer;
import com.icetea.manager.pagodiario.transformer.BillTicketTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;
import com.icetea.manager.pagodiario.utils.StringUtils;

@Named
public class BillServiceImpl 
	extends BasicIdentifiableServiceImpl<Bill, BillDao, BillDto, BillDtoModelTransformer>
		implements BillService {

	private final ClientDao clientDao;
	private final TraderDao traderDao;
	private final ProductDao productDao;
	private final BillTicketTransformer billTicketTransformer;
	private final ConciliationItemDao conciliationItemDao;
	private final CollectorDao collectorDao;
	private final ConciliationItemCollectDao conciliationItemCollectDao;
	
	@Inject
	public BillServiceImpl(BillDao dao, BillDtoModelTransformer transformer,
			ClientDao clientDao, TraderDao traderDao, ProductDao productDao,
			BillTicketTransformer billTicketTransformer,
			ConciliationItemDao conciliationItemDao,
			CollectorDao collectorDao,
			ConciliationItemCollectDao conciliationItemCollectDao) {
		super(dao, transformer);
		this.clientDao = clientDao;
		this.traderDao = traderDao;
		this.productDao = productDao;
		this.billTicketTransformer = billTicketTransformer;
		this.conciliationItemDao = conciliationItemDao;
		this.collectorDao = collectorDao;
		this.conciliationItemCollectDao = conciliationItemCollectDao;
	}

	@Override
	public BillDto insert(BillDto d) {
		
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(d.getStartDate()), 
				"La fecha es requerida", ErrorType.VALIDATION_ERRORS);
		
		List<BillProductDto> billProducts = d.getBillProducts();
		
		ErrorTypedConditions.checkArgument(billProducts != null && !billProducts.isEmpty(), 
				String.format("No se registran productos asociados a la factura"), 
				ErrorType.PRODUCT_REQUIRED);
		
		ErrorTypedConditions.checkArgument(d.getClientId() != null, "id de cliente requerido", ErrorType.VALIDATION_ERRORS);
		
		Client client = this.clientDao.findById(d.getClientId());
		
		ErrorTypedConditions.checkArgument(client != null, String.format("Cliente no encontrado con id: %s", d.getClientId()), 
				ErrorType.CLIENT_NOT_FOUND);
		
		ErrorTypedConditions.checkArgument(d.getTraderId() != null, "id de vendedor requerido", ErrorType.VALIDATION_ERRORS);
		
		Trader trader = this.traderDao.findById(d.getTraderId());
		
		ErrorTypedConditions.checkArgument(trader != null, String.format("Vendedor no encontrado con id: %s", d.getTraderId()), 
				ErrorType.TRADER_NOT_FOUND);
		
		ErrorTypedConditions.checkArgument(d.getCreditNumber() != null, "nro de credito requerido", ErrorType.VALIDATION_ERRORS);
		
		Bill found = this.getDao().findByCreditNumber(d.getCreditNumber());
		
		ErrorTypedConditions.checkArgument(found == null, 
				String.format("Ya existe factura con nro. de credito %s", d.getCreditNumber()), ErrorType.VALIDATION_ERRORS);
		
		Bill e = new Bill();
		
		for(BillProductDto p : billProducts){
			BillProduct bp = new BillProduct();
			bp.setAmount(NumberUtils.toBigDecimal(p.getAmount()));
			bp.setBill(e);
			bp.setCount(p.getCount());
			bp.setDailyInstallment(NumberUtils.toBigDecimal(p.getDailyInstallment()));
			Product product = this.productDao.findById(p.getProductId());
			
			ErrorTypedConditions.checkArgument(product != null, String.format("Producto no encontrado con id: %s", p.getProductId()), 
					ErrorType.PRODUCT_NOT_FOUND);
			
			bp.setProduct(product);
			e.addBillProduct(bp);
		}
		e.setClient(client);
		Date startDate = DateUtils.parseDate(d.getStartDate());
		e.setStartDate(startDate);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);
		
		int weekOfYear = calendar.get(Calendar.WEEK_OF_YEAR);
		
		e.setWeekOfYear(weekOfYear);
		
		int month = calendar.get(Calendar.MONTH);
		e.setMonth(month);
		
		int year = calendar.get(Calendar.YEAR);
		e.setYear(year);
		
		BigDecimal calculatedTotalAmount = e.calculateTotalAmount();
//		if(calculatedTotalAmount.compareTo(NumberUtils.toBigDecimal(d.getTotalAmount())) != 0){
//			throw new ErrorTypedException("Error de validacion de importe total", ErrorType.UNKNOWN_ERROR);
//		}
		e.setTotalAmount(calculatedTotalAmount);
		BigDecimal calculatedTotalDailyInstallment = e.calculateTotalDailyInstallment();
//		if(calculatedTotalDailyInstallment.compareTo(NumberUtils.toBigDecimal(d.getTotalDailyInstallment())) != 0){
//			throw new ErrorTypedException("Error de validacion de total de valor de cuota diaria", ErrorType.UNKNOWN_ERROR);
//		}
		e.setTotalDailyInstallment(calculatedTotalDailyInstallment);
		
		Date now = new Date();
		
		int days = DateUtils.daysBetween(startDate, now);
		
		e.setOverdueDays(days);
		e.setRemainingAmount(calculatedTotalAmount);
		e.setTrader(trader);
		e.setEndDate(e.calculateEndDate());
		
		ErrorTypedConditions.checkArgument(d.getCollectorId() != null, "Id de cobrador es requerido", ErrorType.VALIDATION_ERRORS);
		
		Collector collector = this.collectorDao.findById(d.getCollectorId());
		
		ErrorTypedConditions.checkArgument(collector != null, 
				String.format("Cobrador no encontrado con id: %s", d.getCollectorId()), ErrorType.VALIDATION_ERRORS);
		
		e.setCollector(collector);
		e.setCreditNumber(Long.valueOf(d.getCreditNumber()));
		this.getDao().saveOrUpdate(e);
		
		Payment payment = new Payment();
		payment.setAmount(calculatedTotalDailyInstallment);
		
		payment.setBill(e);
		payment.setCollector(collector);
		payment.setDate(startDate);
		
		e.addPayment(payment);
		// le resto el 1er pago ...
		e.setRemainingAmount(NumberUtils.subtract(calculatedTotalAmount, calculatedTotalDailyInstallment));
		
		e.setStatus(Status.ACTIVE);
		
		this.getDao().saveOrUpdate(e);
		
		return this.getTransformer().transform(e);
	}
	
	@Override
	public BillDto update(BillDto d) {

		Preconditions.checkArgument(d.getId() != null, "id required");
		
		Bill e = this.getDao().findById(d.getId());
		
		ErrorTypedConditions.checkArgument(e != null, ErrorType.BILL_NOT_FOUND);
		
		this.getDao().saveOrUpdate(e);
		
		return this.getTransformer().transform(e);
	}
	
	@Override
	public List<BillDto> searchActives(){
	
		return this.searchActives(null);
	}
	
	@Override
	public List<BillDto> searchActives(Long collectorId){
	
		return this.getTransformer().transformAllTo(this.getDao().find(Status.ACTIVE, collectorId));
	}
	
	@Override
	public boolean updateOverdueDays(Long billId){
		
		Bill bill = this.getDao().findById(billId);

		ErrorTypedConditions.checkArgument(bill != null, ErrorType.BILL_NOT_FOUND);
		
		bill.incrementOverdueDays();
		bill.audit();
		
		return this.getDao().saveOrUpdate(bill);
	}

	@Override
	public List<BillDto> searchByCollectorId(Long collectorId){
		return this.getTransformer().transformAllTo(this.getDao().findByCollectorId(collectorId));
	}

	@Override
	public BillDetailDto searchDetail(Long billId){
		BillDetailDto d = new BillDetailDto();
		
		Bill bill = this.getDao().findById(billId);
		
		ErrorTypedConditions.checkArgument(bill != null, ErrorType.BILL_NOT_FOUND);
		
		d.setClientAddress(bill.getClient().getCompanyAddress());
		d.setClientName(bill.getClient().getName());
		d.setCreditAmount(NumberUtils.toString(bill.getTotalAmount()));
		d.setCreditDate(DateUtils.toDate(bill.getStartDate()));
		d.setFirstInstallmentAmount(
				NumberUtils.toString(bill.getPayments().get(0).getAmount()));
		d.setInstallmentAmount(NumberUtils.toString(bill.getTotalDailyInstallment()));
		d.setRemainingAmount(NumberUtils.toString(bill.getRemainingAmount()));
		d.setTraderName(bill.getTrader().getName());
		d.setCreditNumber(StringUtils.toString(bill.getCreditNumber()));
		d.setCompletedDate((bill.getCompletedDate() != null) ? DateUtils.toDate(bill.getCompletedDate()) : StringUtils.EMPTY);
		d.setStatus(bill.getStatus().name());
		
		for(Payment p : bill.getPayments()){
			BillDetailPaymentDto r = new BillDetailPaymentDto();
			r.setAmount(NumberUtils.toString(p.getAmount()));
			r.setCollector(String.valueOf((p.getCollector() != null) ? p.getCollector().getZone() + " / " + p.getCollector().getDescription() : ""));
			r.setDate(DateUtils.toDate(p.getDate()));
			
			d.getPayments().add(r);
		}
		
		for (Dev p : bill.getDevList()) {
			BillDetailDevolutionDto r = new BillDetailDevolutionDto();
			r.setAmount(NumberUtils.toString(p.getAmount()));
			r.setDate(DateUtils.toDate(p.getDate()));
			r.setInstallmentAmount(NumberUtils.toString(p.getInstallmentAmount()));
			r.setProductDescription(p.getProduct().getDescription());
			
			d.getDevolutions().add(r);
		}
		for (BillProduct p : bill.getBillProducts()) {
			BillProductDetailDto r = new BillProductDetailDto();
			r.setCount((p.getCount() != null) ? String.valueOf(p.getCount()) : "0");
			r.setCodProducto(p.getProduct().getCode());
			r.setDescription(p.getProduct().getDescription() + " / " + NumberUtils.toString(p.getProduct().getPrice()));
			r.setInstallmentAmount(NumberUtils.toString(p.getDailyInstallment()));
			r.setTotalAmount(NumberUtils.toString(p.getAmount()));
			
			d.getProducts().add(r);
		}
		for(Discount e : bill.getDiscounts()){
			BillDetailDiscountDto r = new BillDetailDiscountDto();
			r.setAmount(NumberUtils.toString(e.getAmount()));
			r.setDate(DateUtils.toDate(e.getDate()));
			
			d.getDiscounts().add(r);
		}
		for(ProductReduction e : bill.getProductReductionList()){
			BillDetailReductionDto r = new BillDetailReductionDto();
			r.setAmount(NumberUtils.toString(e.getAmount()));
			r.setDate(DateUtils.toDate(e.getDate()));
			r.setObservations(e.getObservations());
			
			d.getReductions().add(r);
		}
		
		return d;
	}
	
	@Override
	public List<BillTicketPojo> searchBillsByCollectorZone(String ticketDateValue, Long collectorZone, String fromDate, String toDate){
		
		Date dateFrom = null;
		if(StringUtils.isNotBlank(fromDate)){
			dateFrom = DateUtils.parseDate(fromDate);
		}
		Date dateTo = null;
		if(StringUtils.isNotBlank(toDate)){
			dateTo = DateUtils.parseDate(toDate);
		}
	
		List<Bill> bills = this.getDao().find(collectorZone, dateFrom, dateTo);
		
		return this.billTicketTransformer.transform(ticketDateValue, bills);
	}

	@Override
	public List<BillDto> searchExpires(){
		return this.getTransformer().transformAllTo(this.getDao().findExpires());
	}

	@Override
	public boolean remove(Long id) {
		
		ErrorTypedConditions.checkArgument(id != null, "Id de factura es requerido", ErrorType.VALIDATION_ERRORS);
		
		Bill bill = this.getDao().findById(id);
		
		ErrorTypedConditions.checkArgument(bill != null, 
				String.format("Factura %s no encontrada. No puede ser borrada.", id), ErrorType.VALIDATION_ERRORS);
		
		List<ConciliationItem> conciliationItemList = this.conciliationItemDao.findByBillId(id);
		
		ErrorTypedConditions.checkArgument(conciliationItemList == null || conciliationItemList.isEmpty(), 
				String.format("La factura con nro de ticket: %s esta asociada a una liquidacion. No puede ser borrada.", id), 
				ErrorType.VALIDATION_ERRORS);
		
		List<ConciliationItemCollect> conciliationItemCollectList = this.conciliationItemCollectDao.findByBillId(id);
		
		ErrorTypedConditions.checkArgument(conciliationItemCollectList == null || conciliationItemCollectList.isEmpty(), 
				String.format("La factura con nro de ticket: %s esta asociada a una liquidacion de cobrador. No puede ser borrada.", id), 
				ErrorType.VALIDATION_ERRORS);
		
		return this.getDao().delete(bill);
	}

	@Override
	public BillDto searchByCreditNumber(Long creditNumber){

		return this.getTransformer().transform(this.getDao().findByCreditNumber(creditNumber));
	}

	@Override
	public List<BillDto> searchByFilter(Long creditNumber, Long collectorId, String statusArg){
	
		Bill.Status status = Bill.Status.getValueOf(statusArg);
		
		return this.getTransformer().transformAllTo(this.getDao().findByFilter(creditNumber, collectorId, status));
	}

	@Override
	public List<BillDto> searchToMakeVouchers(Date date){
	
		ErrorTypedConditions.checkArgument(date != null, 
				"La fecha de finalizado es requerida.", ErrorType.VALIDATION_ERRORS);
		
		return this.getTransformer().transformAllTo(this.getDao().findToMakeVouchers(date));
	}

	@Override
	public List<BillDto> searchFinalizedInTime(){
		return this.getTransformer().transformAllTo(this.getDao().findFinalizedInTime());
	}

	@Override
	public BillInfoDto setCancelDiscount(Long id){

		Bill bill = this.getDao().findById(id);
		ErrorTypedConditions.checkArgument(bill != null, 
				String.format("Factura no encontrada con id: %s", id), ErrorType.VALIDATION_ERRORS);

		bill.setStatus(Status.CANCELED_DISCOUNT);
		bill.setUpdatedDate(DateUtils.now());
		
		BillInfoDto r = new BillInfoDto();
		r.setBillId(id);
		r.setBillStatus(bill.getStatus().name());
		r.setRemainingAmount(NumberUtils.toString(bill.getRemainingAmount()));
		r.setOverdueDays(bill.getOverdueDays());
		r.setInstallmentAmount(NumberUtils.toString(bill.getTotalDailyInstallment()));
		
		return r;
	}

	@Override
	public List<BillDto> searchCanceled(){
		return this.getTransformer().transformAllTo(this.getDao().findCanceled());
	}
	
}
