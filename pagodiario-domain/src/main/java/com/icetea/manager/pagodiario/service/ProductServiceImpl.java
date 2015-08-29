package com.icetea.manager.pagodiario.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.common.base.Preconditions;
import com.icetea.manager.pagodiario.api.dto.ProductDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillProductDao;
import com.icetea.manager.pagodiario.dao.ProductDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;
import com.icetea.manager.pagodiario.model.BillProduct;
import com.icetea.manager.pagodiario.model.Product;
import com.icetea.manager.pagodiario.transformer.ProductDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class ProductServiceImpl extends
		BasicIdentifiableServiceImpl<Product, ProductDao, ProductDto, ProductDtoModelTransformer> implements
		ProductService {

	private final BillProductDao billProductDao;
	
	@Inject
	public ProductServiceImpl(ProductDao dao,
			ProductDtoModelTransformer transformer,
			BillProductDao billProductDao) {
		super(dao, transformer);
		this.billProductDao = billProductDao;
	}

	@Override
	public ProductDto insert(ProductDto input) {
		
		Product e = new Product();
		e.setCode(input.getCode());
		e.setDescription(input.getDescription());
		e.setPrice(NumberUtils.toBigDecimal(input.getPrice()));
		e.setDailyInstallment(NumberUtils.toBigDecimal(input.getDailyInstallment()));
		
		this.getDao().saveOrUpdate(e);
		
		return this.getTransformer().transform(e);
	}

	@Override
	public ProductDto update(ProductDto d) {

		Preconditions.checkArgument(d.getId() != null, "id required");
		
		Product e = this.getDao().findById(d.getId());
		e.setDescription(d.getDescription());
		e.setPrice(NumberUtils.toBigDecimal(d.getPrice()));
		e.setDailyInstallment(NumberUtils.toBigDecimal(d.getDailyInstallment()));
		
		this.getDao().saveOrUpdate(e);
		
		return this.getTransformer().transform(e);
	}
	
	@Override
	public ProductDto searchByCode(String code){
		
		return this.getTransformer().transform(this.getDao().findByCode(code));
	}

	@Override
	public boolean remove(Long id) {
		Product e = this.getDao().findById(id);
		if(e == null){
			throw new RuntimeException(String.format("La entidad con id %s no existe en la bd", id));
		}
		List<BillProduct> billProducts = this.billProductDao.findByProductId(id);
		if(billProducts != null && !billProducts.isEmpty()){
			throw new ErrorTypedException("No se puede borrar este producto debido a que esta asociado a facturas", 
					ErrorType.VALIDATION_ERRORS);
		}
		
		return this.getDao().delete(e);
	}
	
}
