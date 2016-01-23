package com.icetea.manager.pagodiario.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BillProductDto;
import com.icetea.manager.pagodiario.api.dto.DevAddDto;
import com.icetea.manager.pagodiario.api.dto.DevDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.BillProductDao;
import com.icetea.manager.pagodiario.dao.DevDao;
import com.icetea.manager.pagodiario.dao.ProductDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.BillProduct;
import com.icetea.manager.pagodiario.model.Dev;
import com.icetea.manager.pagodiario.model.Product;
import com.icetea.manager.pagodiario.transformer.DevDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.BillUtils;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class DevServiceImpl extends BasicIdentifiableServiceImpl<Dev, DevDao, DevDto, DevDtoModelTransformer> implements
		DevService {
	
	private final BillDao billDao;
	private final ProductDao productDao;
	private final BillUtils billUtils;
	private final BillProductDao billProductDao;
	
	@Inject
	public DevServiceImpl(DevDao dao, DevDtoModelTransformer transformer,
			BillDao billDao, ProductDao productDao,
			BillUtils billUtils,
			BillProductDao billProductDao) {
		super(dao, transformer);
		this.billDao = billDao;
		this.productDao = productDao;
		this.billUtils = billUtils;
		this.billProductDao = billProductDao;
	}

	@Deprecated
	@Override
	public DevDto insert(DevDto o) {
		Bill bill = this.billDao.findById(o.getBillId());
		
		ErrorTypedConditions.checkArgument(bill != null, ErrorType.BILL_NOT_FOUND);
		
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(o.getAmount()), 
				"El monto de devolucion es requerido", ErrorType.VALIDATION_ERRORS);
		
		BigDecimal amount = NumberUtils.toBigDecimal(o.getAmount());

		ErrorTypedConditions.checkArgument(!NumberUtils.isNegative(amount), 
				"El monto de devolucion no puede ser menor a 0.", ErrorType.VALIDATION_ERRORS);
		
		ErrorTypedConditions.checkArgument(amount.compareTo(bill.getTotalAmount()) <= 0, 
				String.format("El monto ingresado %s no puede ser mayor al total facturado %s", 
						o.getAmount(), NumberUtils.toString(bill.getTotalAmount())), ErrorType.VALIDATION_ERRORS);
		
		boolean isDevAmountGreaterThanRemainingAmount = amount.compareTo(bill.getRemainingAmount()) > 0;
		
		ErrorTypedConditions.checkArgument(!isDevAmountGreaterThanRemainingAmount, 
				String.format("No se puede devolver un monto mayor al monto a pagar restante: %s", 
						NumberUtils.toString(bill.getRemainingAmount())), 
				ErrorType.VALIDATION_ERRORS);
		
		ErrorTypedConditions.checkArgument(o.getProductCount() != null, 
				"La cantidad de productos es requerido", ErrorType.VALIDATION_ERRORS);
		ErrorTypedConditions.checkArgument(o.getProductId() != null, 
				"El producto es requerido", ErrorType.VALIDATION_ERRORS);
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(o.getAmount()), 
				"El monto es requerido", ErrorType.VALIDATION_ERRORS);
		
		Product product = this.productDao.findById(o.getProductId());
		
		ErrorTypedConditions.checkArgument(product != null, 
				"El producto seleccionado no fue encontrado en el sistema", ErrorType.VALIDATION_ERRORS);
		
		BillProduct billProductFound = null;
		
		for (BillProduct billProduct : bill.getBillProducts()) {
			Long productId = billProduct.getProduct().getId();
			if(productId.equals(product.getId())){
				billProductFound = billProduct;
				break;
			}
		}
		
		ErrorTypedConditions.checkArgument(billProductFound != null, 
				"El producto seleccionado no pertenece a la factura seleccionada", ErrorType.VALIDATION_ERRORS);
		
		Dev e = new Dev();
		e.setAmount(amount);
		e.setDate(DateUtils.parseDate(o.getDate(), "dd/MM/yyyy"));
		e.setObservations(StringUtils.trim(o.getObservations()));
		e.setBill(bill);
		e.setProduct(product);
		e.setProductCount(o.getProductCount());

		BigDecimal devInstallmentAmount = NumberUtils.toBigDecimal(o.getProductInstallment());/*NumberUtils.multiply(
				new BigDecimal(o.getProductCount()), billProductFound.getDailyInstallment());*/
		
		ErrorTypedConditions.checkArgument(!NumberUtils.isNegative(devInstallmentAmount), 
				String.format("El monto de la cuota diaria no puede ser negativo: %s", 
						NumberUtils.toString(devInstallmentAmount)), ErrorType.VALIDATION_ERRORS);
		
		e.setInstallmentAmount(devInstallmentAmount);
		
		this.getDao().saveOrUpdate(e);
		
		bill.getDevList().add(e);
		
		bill.setTotalAmount(NumberUtils.subtract(bill.getTotalAmount(), e.getAmount()));
		bill.setRemainingAmount(NumberUtils.subtract(bill.getRemainingAmount(), e.getAmount()));
		bill.setTotalDailyInstallment(NumberUtils.subtract(bill.getTotalDailyInstallment(), devInstallmentAmount));

		// [roher] actualizo los dias de atraso segun el nuevo monto de cuota diaria y el nuevo remanente
		if(!NumberUtils.isNullOrZero(bill.getTotalDailyInstallment())){
			int overdueDays = e.getAmount().divide(
					bill.getTotalDailyInstallment(), RoundingMode.DOWN).intValue();
			bill.decrementOverdueDays(overdueDays);
		}
		
		this.billUtils.doBillCancelation(bill);
		
		this.billDao.saveOrUpdate(bill);
		
		return this.getTransformer().transform(e);
	}
	
	protected BigDecimal doNewInstallmentAmountCalculation(BigDecimal totalAmount, BigDecimal totalRemaining){
		return null;
	}

	@Override
	public DevDto update(DevDto o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DevDto> search(Long billId){
		
		ErrorTypedConditions.checkArgument(billId != null, ErrorType.BILL_REQUIRED);
		
		return this.getTransformer().transformAllTo(this.getDao().findByBillId(billId));
	}

	@Override
	public DevAddDto insert(DevAddDto o) {
		Bill bill = this.billDao.findById(o.getBillId());
		
		ErrorTypedConditions.checkArgument(bill != null, ErrorType.BILL_NOT_FOUND);
		ErrorTypedConditions.checkArgument(StringUtils.isNotBlank(o.getSelectedDate()), "Fecha de Descuento es requerida");
		
		Date selectedDate = DateUtils.parseDate(o.getSelectedDate(), "dd/MM/yyyy");
		
		ErrorTypedConditions.checkArgument(!selectedDate.before(bill.getStartDate()), 
				"La fecha elegida no puede ser menor a la fecha de inicio de Factura");
		
		List<BillProduct> billProducts = bill.getBillProducts();
		
		List<Dev> devs = Lists.newArrayList();
		
		List<BillProduct> toRemove = Lists.newArrayList();
		
		for (final BillProduct bp : billProducts) {
			BillProductDto bpd = CollectionUtils.find(o.getBillProducts(), new Predicate<BillProductDto>() {
				@Override
				public boolean evaluate(BillProductDto e) {
					return bp.getId().equals(e.getId());
				}
			});
			
			Dev dev = null;
			if(bpd != null){
				
				ErrorTypedConditions.checkArgument(bpd.getCount() > 0 && bpd.getCount() <= bp.getCount(), 
						"La cantidad seleccionada en el descuento no puede ser mayor a la cantidad original o negativa.");
				
				if(bpd.getCount() != bp.getCount()){
					BigDecimal origAmount = bp.getAmount();
					BigDecimal origInstallmentAmount = bp.getDailyInstallment();
					
					BigDecimal originalPrice = NumberUtils.divide(bp.getAmount(), new BigDecimal(bp.getCount()));
					BigDecimal originalInstallmentAmount = NumberUtils.divide(bp.getDailyInstallment(), new BigDecimal(bp.getCount()));
					
					bp.setAmount(NumberUtils.multiply(originalPrice, new BigDecimal(bpd.getCount())));
					bp.setDailyInstallment(NumberUtils.multiply(originalInstallmentAmount, new BigDecimal(bpd.getCount())));
					
					Integer originalCount = bp.getCount();
					
					bp.setCount(bpd.getCount());
					bp.setUpdatedDate(new Date());
					
					// creo una devolucion por los productos que bajaron la cantidad ...
					dev = new Dev();
					
					int count = originalCount - bpd.getCount();
					BigDecimal diffAmount = NumberUtils.subtract(origAmount, bp.getAmount());
					BigDecimal diffInstallmentAmount = NumberUtils.subtract(origInstallmentAmount, bp.getDailyInstallment());
					
					dev.setAmount(diffAmount);
					dev.setDate(selectedDate);
					dev.setObservations(StringUtils.abbreviate(StringUtils.trim(o.getObservations()), 250));
					dev.setBill(bill);
					dev.setProduct(bp.getProduct());
					dev.setProductCount(count);
					dev.setInstallmentAmount(diffInstallmentAmount);
					
					this.getDao().saveOrUpdate(dev);
					
					bill.getDevList().add(dev);
				}
				
			} else {
//				bill.setTotalDailyInstallment(NumberUtils.subtract(bill.getTotalDailyInstallment(), bp.getDailyInstallment()));
//				bill.setTotalAmount(NumberUtils.subtract(bill.getTotalAmount(), bp.getAmount()));
				// creo una devolucion por los productos quitados de la factura ...
				dev = new Dev();
				dev.setAmount(bp.getAmount());
				dev.setDate(DateUtils.parseDate(o.getSelectedDate(), "dd/MM/yyyy"));
				dev.setObservations(StringUtils.abbreviate(StringUtils.trim(o.getObservations()), 250));
				dev.setBill(bill);
				dev.setProduct(bp.getProduct());
				dev.setProductCount((bp.getCount() != null) ? bp.getCount() : 0);
				dev.setInstallmentAmount(bp.getDailyInstallment());
				
				this.getDao().saveOrUpdate(dev);
				
				bill.getDevList().add(dev);
				
				toRemove.add(bp);
			}
			if(dev != null){
				bill.setTotalAmount(NumberUtils.subtract(bill.getTotalAmount(), dev.getAmount()));
				bill.setRemainingAmount(NumberUtils.subtract(bill.getRemainingAmount(), dev.getAmount()));
				bill.setTotalDailyInstallment(NumberUtils.subtract(bill.getTotalDailyInstallment(), dev.getInstallmentAmount()));
				// [roher] actualizo los dias de atraso segun el nuevo monto de cuota diaria y el nuevo remanente
				if(!NumberUtils.isNullOrZero(bill.getTotalDailyInstallment())){
					int overdueDays = dev.getAmount().divide(
							bill.getTotalDailyInstallment(), RoundingMode.DOWN).intValue();
					bill.decrementOverdueDays(overdueDays);
				}
				devs.add(dev);
			}
		}
		
		for(BillProduct bp : toRemove){
			bp.setBill(null);
			this.billProductDao.saveOrUpdate(bp);
			this.billProductDao.delete(bp);
		}
		
		billProducts.removeAll(toRemove);
		
		this.billUtils.doBillCancelation(bill);
		
		this.billDao.saveOrUpdate(bill);

		return this.getTransformer().transform(devs, bill);
	}
	
}
