package com.icetea.manager.pagodiario.service;

import com.icetea.manager.pagodiario.api.dto.ProductDto;
import com.icetea.manager.pagodiario.model.Product;

public interface ProductService extends
		BasicIdentifiableService<Product, ProductDto> {

	ProductDto insert(ProductDto input);

	ProductDto update(ProductDto d);
	
}
