package com.icetea.manager.pagodiario.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.lang3.time.DateUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.PayrollItemCollect;

@Named
public class PayrollItemCollectDaoImpl extends
		BasicIdentificableDaoImpl<PayrollItemCollect> implements
		PayrollItemCollectDao {

	@Override
	protected Class<PayrollItemCollect> getEntityClass() {
		return PayrollItemCollect.class;
	}
	
	@Override
	public List<?> findPeriod(Date date){
		Criteria criteria = super.createCriteria();
		criteria.createAlias("payrollCollect", "payrollCollect");
		criteria.createAlias("collector", "collector");
		criteria.add(
				Restrictions.between("payrollCollect.payrollDate", 
						DateUtils.addDays(date, -90), date));
		criteria.setProjection(
				Projections.projectionList()
					.add(Projections.groupProperty("collector.zone"))
					.add(Projections.property("collector.description"))
					.add(Projections.sum("totalPayment")));
		criteria.addOrder(Order.asc("collector.id"));
		
		return criteria.list();
	}

}
