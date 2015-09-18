package com.icetea.manager.pagodiario.dao;

import java.util.Date;

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
		criteria.add(Restrictions.eq("payrollDate", date));
		
		return (PayrollCollect) criteria.uniqueResult();
	}
	
}
