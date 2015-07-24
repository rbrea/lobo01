package com.icetea.manager.pagodiario.dao;

import java.util.List;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.ProductReduction;

@Named
public class ProductReductionDaoImpl extends
		BasicIdentificableDaoImpl<ProductReduction> implements
		ProductReductionDao {

	@Override
	protected Class<ProductReduction> getEntityClass() {
		return ProductReduction.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductReduction> findByBillId(Long billId){
		
		Criteria criteria = super.createCriteria();
		criteria.createAlias("bill", "bill");
		criteria.add(Restrictions.eq("bill.id", billId));
		
		return criteria.list();
	}
	
}
