package com.icetea.manager.pagodiario.dao;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.Product;

@Named
public class ProductDaoImpl extends BasicIdentificableDaoImpl<Product> implements
		ProductDao {

	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}

	@Override
	public Product findByCode(String code){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.eq("code", code));
		
		return (Product) criteria.uniqueResult();
	}
	
}
