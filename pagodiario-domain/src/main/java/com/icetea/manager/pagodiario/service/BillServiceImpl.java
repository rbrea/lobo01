package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.common.base.Preconditions;
import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.BillProductDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.ClientDao;
import com.icetea.manager.pagodiario.dao.ProductDao;
import com.icetea.manager.pagodiario.dao.TraderDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bill.Status;
import com.icetea.manager.pagodiario.model.BillProduct;
import com.icetea.manager.pagodiario.model.Client;
import com.icetea.manager.pagodiario.model.Payment;
import com.icetea.manager.pagodiario.model.Product;
import com.icetea.manager.pagodiario.model.Trader;
import com.icetea.manager.pagodiario.transformer.BillDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class BillServiceImpl 
	extends BasicIdentifiableServiceImpl<Bill, BillDao, BillDto, BillDtoModelTransformer>
		implements BillService {

	private final ClientDao clientDao;
	private final TraderDao traderDao;
	private final ProductDao productDao;
	
	@Inject
	public BillServiceImpl(BillDao dao, BillDtoModelTransformer transformer,
			ClientDao clientDao, TraderDao traderDao, ProductDao productDao) {
		super(dao, transformer);
		this.clientDao = clientDao;
		this.traderDao = traderDao;
		this.productDao = productDao;
	}

	@Override
	public BillDto insert(BillDto d) {
		
		List<BillProductDto> billProducts = d.getBillProducts();
		
		ErrorTypedConditions.checkArgument(billProducts != null && !billProducts.isEmpty(), 
				String.format("No se registran productos asociados a la factura"), 
				ErrorType.PRODUCT_REQUIRED);
		
		Client client = this.clientDao.findById(d.getClientId());
		
		ErrorTypedConditions.checkArgument(client != null, String.format("Cliente no encontrado con id: %s", d.getClientId()), 
				ErrorType.CLIENT_NOT_FOUND);
		
		Trader trader = this.traderDao.findById(d.getTraderId());
		
		ErrorTypedConditions.checkArgument(trader != null, String.format("Vendedor no encontrado con id: %s", d.getTraderId()), 
				ErrorType.TRADER_NOT_FOUND);
		
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
		e.setStartDate(DateUtils.parseDate(d.getStartDate()));
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
		e.setOverdueDays(0);
		e.setRemainingAmount(calculatedTotalAmount);
		e.setTrader(trader);
		e.setEndDate(e.calculateEndDate());
		e.setCollectorId(d.getCollectorId());
		e.setCreditNumber(Long.valueOf(d.getCreditNumber()));
		this.getDao().saveOrUpdate(e);
		
		Payment payment = new Payment();
		payment.setAmount(calculatedTotalDailyInstallment);
		
		payment.setBill(e);
		payment.setCollectorId(d.getCollectorId());
		payment.setDate(DateUtils.parseDate(d.getStartDate()));
		
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
	
		return this.getTransformer().transformAllTo(this.getDao().find(Status.ACTIVE));
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
	
}
