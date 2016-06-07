package com.icetea.manager.pagodiario.dao;

import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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

	@Override
	public Payroll findLast(){
		Criteria criteria = super.createCriteria();
		criteria.setProjection(Projections.max("dateFrom"));
		
		Date maxDate = (Date)criteria.uniqueResult();
		
		criteria = super.createCriteria();
		criteria.add(Restrictions.eq("dateFrom", maxDate));
		
		return (Payroll) criteria.uniqueResult();				
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Payroll> findLast(Integer maxResults){
		Criteria criteria = super.createCriteria();
		criteria.setMaxResults(maxResults);
		criteria.addOrder(Order.desc("dateFrom"));
		
		return criteria.list();
	}

	@Override
	public Payroll findByPaymentId(Long paymentId){
		Criteria criteria = super.createCriteria();
		criteria.createAlias("payrollItemList", "payrollItem");
		criteria.createAlias("payrollItem.items", "item");
		criteria.createAlias("item.bill", "bill");
		criteria.createAlias("bill.payments", "payment");
		criteria.add(Restrictions.eq("payment.id", paymentId));
		
		List<?> list = criteria.list();
		
		return (Payroll) ((list != null && !list.isEmpty()) ? list.get(0) : null);
	}
	
}
