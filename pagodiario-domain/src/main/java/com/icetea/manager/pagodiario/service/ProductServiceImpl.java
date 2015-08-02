package com.icetea.manager.pagodiario.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.common.base.Preconditions;
import com.icetea.manager.pagodiario.api.dto.ProductDto;
import com.icetea.manager.pagodiario.dao.ProductDao;
import com.icetea.manager.pagodiario.model.Product;
import com.icetea.manager.pagodiario.transformer.ProductDtoModelTransformer;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class ProductServiceImpl extends
		BasicIdentifiableServiceImpl<Product, ProductDao, ProductDto, ProductDtoModelTransformer> implements
		ProductService {

	@Inject
	public ProductServiceImpl(ProductDao dao,
			ProductDtoModelTransformer transformer) {
		super(dao, transformer);
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

}
