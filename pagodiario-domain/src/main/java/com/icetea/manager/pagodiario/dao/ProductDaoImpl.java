package com.icetea.manager.pagodiario.dao;

import javax.inject.Named;

import com.icetea.manager.pagodiario.model.Product;

@Named
public class ProductDaoImpl extends BasicIdentificableDaoImpl<Product> implements
		ProductDao {

	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}

}
