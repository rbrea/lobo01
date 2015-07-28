package com.icetea.manager.pagodiario.dao;

import java.util.Date;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.Payroll;

@Named
public class PayrollDaoImpl extends BasicIdentificableDaoImpl<Payroll>
		implements PayrollDao {

	@Override
	protected Class<Payroll> getEntityClass() {
		return Payroll.class;
	}
	
	@Override
	public Payroll find(Date from, Date to){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.eq("dateFrom", from));
		criteria.add(Restrictions.eq("dateTo", to));
		
		return (Payroll) criteria.uniqueResult();
	}

}
