package com.icetea.manager.pagodiario.dao;

import java.util.List;

import javax.inject.Named;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.Product;
import com.icetea.manager.pagodiario.model.type.ProductType;

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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> find(String code, String description, ProductType productType){
		Criteria criteria = super.createCriteria();
		if(StringUtils.isNotBlank(code)){
			criteria.add(Restrictions.eq("code", code));
		}
		if(StringUtils.isNotBlank(description)){
			criteria.add(Restrictions.eq("description", description));
		}
		if(productType != null){
			criteria.add(Restrictions.eq("productType", productType));
		}
		
		return criteria.list();
	}
	
}
