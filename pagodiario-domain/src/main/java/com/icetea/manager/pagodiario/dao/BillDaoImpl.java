package com.icetea.manager.pagodiario.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bill.Status;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.StringUtils;

@Named
public class BillDaoImpl extends BasicIdentificableDaoImpl<Bill> 
	implements BillDao {

	@Override
	protected Class<Bill> getEntityClass() {
		return Bill.class;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> find(Status status, Long collectorId){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.eq("status", status));
		if(collectorId != null){
			criteria.createAlias("collector", "collector");
			criteria.add(Restrictions.eq("collector.id", collectorId));
		}
		
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> find(Date dateFrom, Date dateTo){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.between("startDate", dateFrom, dateTo));
		criteria.add(
				Restrictions.or(
						Restrictions.eq("status", Bill.Status.ACTIVE),
						Restrictions.eq("status", Bill.Status.CANCELED)
				)
		);
		
		return criteria.list();
	}

	@Override
	public Bill findByCreditNumber(Long creditNumber){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.eq("creditNumber", creditNumber));
		
		return (Bill) criteria.uniqueResult();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> findByCollectorId(Long collectorId){
		Criteria criteria = super.createCriteria();
		criteria.createAlias("collector", "collector");
		criteria.add(Restrictions.eq("collector.id", collectorId));
		criteria.add(Restrictions.eq("status", Bill.Status.ACTIVE));
		
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bill> find(Long collectorZone, Date dateFrom, Date dateTo, int dayOfWeek){
		Criteria criteria = super.createCriteria();
		criteria.createAlias("collector", "collector");
		if(dateFrom != null && dateTo != null){
			criteria.add(Restrictions.between("startDate", dateFrom, dateTo));
		}
		criteria.add(Restrictions.eq("collector.zone", collectorZone));
		criteria.add(Restrictions.eq("status", Bill.Status.ACTIVE));
		
		switch(dayOfWeek){
			case 1:
				criteria.add(Restrictions.eq("weekSunday", StringUtils.S));
				break;
			case 2:
				criteria.add(Restrictions.eq("weekMonday", StringUtils.S));
				break;
			case 3:
				criteria.add(Restrictions.eq("weekTuesday", StringUtils.S));
				break;
			case 4:
				criteria.add(Restrictions.eq("weekWednesday", StringUtils.S));
				break;
			case 5:
				criteria.add(Restrictions.eq("weekThursday", StringUtils.S));
				break;
			case 6:
				criteria.add(Restrictions.eq("weekFriday", StringUtils.S));
				break;
			case 7:
				criteria.add(Restrictions.eq("weekSaturday", StringUtils.S));
				break;
			default:
				break;
		}
		
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> findExpires(){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.eq("status", Bill.Status.ACTIVE));
		criteria.add(Restrictions.lt("overdueDaysFlag", DateUtils.parseDate(DateUtils.currentDate())));
		
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> findByTraderId(Long traderId){
		Criteria criteria = super.createCriteria();
		criteria.createAlias("trader", "trader");
		criteria.add(Restrictions.eq("trader.id", traderId));
		
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> findByClientId(Long clientId){
		Criteria criteria = super.createCriteria();
		criteria.createAlias("client", "client");
		criteria.add(Restrictions.eq("client.id", clientId));
		
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> findActivesByDate(Date date){
		Criteria criteria = super.createCriteria();
		Date dateToVerify = DateUtils.lastSecondOfDay(date);
		criteria.add(Restrictions.eq("status", Status.ACTIVE));
//		criteria.add(Restrictions.le("completedDate", dateToVerify));
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateToVerify);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		
		switch(dayOfWeek){
		case 1:
			criteria.add(Restrictions.eq("weekSunday", StringUtils.S));
			break;
		case 2:
			criteria.add(Restrictions.eq("weekMonday", StringUtils.S));
			break;
		case 3:
			criteria.add(Restrictions.eq("weekTuesday", StringUtils.S));
			break;
		case 4:
			criteria.add(Restrictions.eq("weekWednesday", StringUtils.S));
			break;
		case 5:
			criteria.add(Restrictions.eq("weekThursday", StringUtils.S));
			break;
		case 6:
			criteria.add(Restrictions.eq("weekFriday", StringUtils.S));
			break;
		case 7:
			criteria.add(Restrictions.eq("weekSaturday", StringUtils.S));
			break;
		default:
			break;
		}
		
		return criteria.list();
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List findActivesGroupingByWeekAndTrader(){
		Criteria criteria = super.createCriteria();
		criteria.createAlias("trader", "trader");
		
		criteria.add(Restrictions.eq("status", Status.ACTIVE));
	
		Date now = new Date();
		Date from = DateUtils.truncate(DateUtils.addYears(now, -1), Calendar.DATE);
		
		criteria.add(Restrictions.between("startDate", from, now));
		
		ProjectionList projectionList = Projections.projectionList();
		projectionList.add(Projections.groupProperty("trader.id").as("TRADER_ID"));
//		projectionList.add(Projections.sqlGroupProjection(
//			    "week({alias}.START_DATE) as WEEK, month({alias}.START_DATE) as MONTH, year({alias}.START_DATE) as YEAR", 
//			    "week({alias}.START_DATE), month({alias}.START_DATE), year({alias}.START_DATE)", 
//			    new String[]{"WEEK", "MONTH", "YEAR"}, 
//			    new Type[] {DoubleType.INSTANCE}));
		projectionList.add(Projections.groupProperty("month").as("MONTH"));
		projectionList.add(Projections.groupProperty("year").as("YEAR"));
		projectionList.add(Projections.sum("totalAmount").as("TOTAL_AMOUNT"));
		
		criteria.setProjection(projectionList);
		
		List list = criteria.list();

		return list;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> findByFilter(Long creditNumber, Long collectorId, Bill.Status status, Long clientId){
		Criteria criteria = super.createCriteria();
		if(creditNumber != null){
			criteria.add(Restrictions.eq("creditNumber", creditNumber));
		}
		if(collectorId != null){
			criteria.createAlias("collector", "collector");
			criteria.add(Restrictions.eq("collector.id", collectorId));
		}
		if(status != null){
			criteria.add(Restrictions.eq("status", status));
		}
		if(clientId != null){
			criteria.createAlias("client", "client");
			criteria.add(Restrictions.eq("client.id", clientId));
		}
		
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> findToMakeVouchers(Date date){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.eq("status", Bill.Status.CANCELED));
		criteria.add(Restrictions.between("completedDate", date, DateUtils.addDays(date, 1)));
		criteria.add(Restrictions.le("overdueDays", 0));
		
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> findLastYear(){
		Criteria criteria = super.createCriteria();
		Date now = new Date();
		criteria.add(
				Restrictions.between("startDate", 
						DateUtils.addMonths(now, -12), DateUtils.addDays(now, 1)));
		
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> findBillsWithCollectors(Date from, Date to){
		Criteria criteria = super.createCriteria();
		//criteria.add(Restrictions.between("startDate", lo, hi))
		Date toVerify = DateUtils.lastSecondOfDay(to);
		criteria.add(Restrictions.eq("status", Status.ACTIVE));
		
		return criteria.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> findFinalizedInTime(){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.eq("status", Bill.Status.CANCELED));
		criteria.add(Restrictions.le("overdueDays", 0));
		
		return criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Bill> findCanceled(){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.eq("status", Bill.Status.CANCELED));
		
		return criteria.list();
	}
	
}
