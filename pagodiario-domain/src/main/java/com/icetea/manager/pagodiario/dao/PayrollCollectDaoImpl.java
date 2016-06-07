package com.icetea.manager.pagodiario.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.PayrollCollect;

@Named
public class PayrollCollectDaoImpl extends
		BasicIdentificableDaoImpl<PayrollCollect> implements PayrollCollectDao {

	@Override
	protected Class<PayrollCollect> getEntityClass() {
		return PayrollCollect.class;
	}

	@Override
	public PayrollCollect findByDate(Date date){
		Criteria criteria = super.createCriteria();
//		criteria.createAlias("payrollItemCollectList", "payrollItemCollectList");
//		criteria.createAlias("payrollItemCollectList.conciliationItemCollectList", "conciliationItemCollectList");
//		criteria.createAlias("conciliationItemCollectList.bill", "bill");
//		criteria.add(Restrictions.eq("bill.status", Bill.Status.ACTIVE));
		criteria.add(Restrictions.eq("payrollDate", date));
		
		return (PayrollCollect) criteria.uniqueResult();
	}

	@Override
	public PayrollCollect findByPaymentId(Long paymentId){
		Criteria criteria = super.createCriteria();
		criteria.createAlias("payrollItemCollectList", "payrollItemCollect");
		criteria.createAlias("payrollItemCollect.conciliationItemCollectList", "conciliationItemCollect");
		criteria.createAlias("conciliationItemCollect.bill", "bill");
		criteria.createAlias("bill.payments", "payment");
		criteria.add(Restrictions.eq("payment.id", paymentId));
		
		List<?> list = criteria.list();
		
		return (PayrollCollect) ((list != null && !list.isEmpty()) ? list.get(0) : null);
	}
	
}
