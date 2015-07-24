package com.icetea.manager.pagodiario.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.NotImplementedException;

import com.icetea.manager.pagodiario.api.dto.ProductReductionDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.ProductReductionDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.ProductReduction;
import com.icetea.manager.pagodiario.transformer.ProductReductionDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class ProductReductionServiceImpl
		extends BasicIdentifiableServiceImpl<ProductReduction, ProductReductionDao, 
			ProductReductionDto, ProductReductionDtoModelTransformer> implements ProductReductionService {
	
	private BillDao billDao;
	
	@Inject
	public ProductReductionServiceImpl(ProductReductionDao dao,
			ProductReductionDtoModelTransformer transformer,
			BillDao billDao) {
		super(dao, transformer);
		this.billDao = billDao;
	}

	@Override
	public ProductReductionDto insert(ProductReductionDto o) {
		Bill bill = this.billDao.findById(o.getBillId());
		
		ErrorTypedConditions.checkArgument(bill != null, ErrorType.BILL_NOT_FOUND);
		
		ProductReduction e = new ProductReduction();
		e.setAmount(NumberUtils.toBigDecimal(o.getAmount()));
		e.setDate(DateUtils.parseDate(o.getDate(), "dd/MM/yyyy"));
		e.setObservations(o.getObservations());
		e.setBill(bill);
		
		this.getDao().saveOrUpdate(e);
		
		bill.getProductReductionList().add(e);
		
		this.billDao.saveOrUpdate(bill);
		
		return this.getTransformer().transform(e);
	}

	@Override
	public ProductReductionDto update(ProductReductionDto o) {
		throw new NotImplementedException("not implemented");
	}

	@Override
	public List<ProductReductionDto> search(Long billId){
		
		ErrorTypedConditions.checkArgument(billId != null, ErrorType.BILL_REQUIRED);
		
		return this.getTransformer().transformAllTo(this.getDao().findByBillId(billId));
	}
	
}
