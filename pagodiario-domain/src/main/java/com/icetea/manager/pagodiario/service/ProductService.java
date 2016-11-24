package com.icetea.manager.pagodiario.service;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.ProductDto;
import com.icetea.manager.pagodiario.model.Product;

public interface ProductService extends
		BasicIdentifiableService<Product, ProductDto> {

	ProductDto insert(ProductDto input);

	ProductDto update(ProductDto d);

	ProductDto searchByCode(String code);

	List<ProductDto> search(String code, String description, String productType);
	
}
