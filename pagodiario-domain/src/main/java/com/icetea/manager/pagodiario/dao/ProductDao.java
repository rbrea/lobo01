package com.icetea.manager.pagodiario.dao;

import java.util.List;

import com.icetea.manager.pagodiario.model.Product;
import com.icetea.manager.pagodiario.model.type.ProductType;

public interface ProductDao extends BasicIdentificableDao<Product> {

	Product findByCode(String code);

	List<Product> find(String code, String description, ProductType productType);

}
