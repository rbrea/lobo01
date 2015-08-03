package com.icetea.manager.pagodiario.dao;

import com.icetea.manager.pagodiario.model.Product;

public interface ProductDao extends BasicIdentificableDao<Product> {

	Product findByCode(String code);

}
