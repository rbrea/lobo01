package com.icetea.manager.pagodiario.dao;

import java.util.List;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.Payment;

@Named
public class PaymentDaoImpl extends BasicIdentificableDaoImpl<Payment>
		implements PaymentDao {

	@Override
	protected Class<Payment> getEntityClass() {
		return Payment.class;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> findByBillId(Long billId){
		
		Criteria criteria = super.createCriteria();
		criteria.createAlias("bill", "bill");
		criteria.add(Restrictions.eq("bill.id", billId));
		
		return criteria.list();
	}
	
}
