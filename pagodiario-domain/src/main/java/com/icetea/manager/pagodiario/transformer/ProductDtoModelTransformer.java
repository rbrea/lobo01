package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.ProductDto;
import com.icetea.manager.pagodiario.model.Product;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class ProductDtoModelTransformer extends AbstractDtoModelTransformer<ProductDto, Product> {

	@Override
	protected ProductDto doTransform(Product e, int depth) {
		ProductDto dto = new ProductDto();
		dto.setId(e.getId());
		dto.setCode(e.getCode());
		dto.setDescription(e.getDescription());
		dto.setPrice(NumberUtils.toString(e.getPrice()));
		
		return dto;
	}

}