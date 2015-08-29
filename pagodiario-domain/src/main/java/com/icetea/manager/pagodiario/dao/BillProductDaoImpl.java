package com.icetea.manager.pagodiario.dao;

import java.util.List;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.BillProduct;

@Named
public class BillProductDaoImpl extends BasicIdentificableDaoImpl<BillProduct> 
	implements BillProductDao {

	@Override
	protected Class<BillProduct> getEntityClass() {
		return BillProduct.class;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BillProduct> findByProductId(Long productId){
		Criteria criteria = super.createCriteria();
		criteria.createAlias("product", "product");
		criteria.add(Restrictions.eq("product.id", productId));
		
		return criteria.list();
	}
	
}
