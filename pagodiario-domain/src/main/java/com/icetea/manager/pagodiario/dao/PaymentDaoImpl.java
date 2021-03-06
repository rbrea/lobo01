package com.icetea.manager.pagodiario.dao;

import java.util.Date;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> find(Long billId, Date paymentDateFrom, Date paymentDateTo){
		
		Criteria criteria = super.createCriteria();
		criteria.createAlias("bill", "bill");
		criteria.add(Restrictions.eq("bill.id", billId));
		criteria.add(Restrictions.between("date", paymentDateFrom, paymentDateTo));
		
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> findByCreditNumber(Long creditNumber, Date paymentDateFrom, Date paymentDateTo){
		
		Criteria criteria = super.createCriteria();
		criteria.createAlias("bill", "bill");
		criteria.add(Restrictions.eq("bill.creditNumber", creditNumber));
		criteria.add(Restrictions.between("date", paymentDateFrom, paymentDateTo));
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> find(Date paymentDateFrom, Date paymentDateTo) {
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.between("date", paymentDateFrom, paymentDateTo));
		
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> findToCollect(Date paymentDateFrom, Date paymentDateTo) {
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.between("date", paymentDateFrom, paymentDateTo));
		criteria.add(Restrictions.eq("traderPayment", false));
		
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Payment> find(Date paymentDateFrom, Date paymentDateTo, Long collectorId, Long clientId) {
		Criteria criteria = super.createCriteria();
		if(paymentDateFrom != null){
			criteria.add(Restrictions.ge("date", paymentDateFrom));
		}
		if(paymentDateTo != null){
			criteria.add(Restrictions.le("date", paymentDateTo));
		}
		if(collectorId != null){
			criteria.createAlias("collector", "collector");
			criteria.add(Restrictions.eq("collector.id", collectorId));
		}
		if(clientId != null){
			criteria.createAlias("bill", "bill");
			criteria.createAlias("bill.client", "client");
			criteria.add(Restrictions.eq("client.id", clientId));
		}
		
		return criteria.list();
	}

}
