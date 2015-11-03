package com.icetea.manager.pagodiario.dao;

import java.util.Date;
import java.util.List;

import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bill.Status;

public interface BillDao extends BasicIdentificableDao<Bill> {

	List<Bill> find(Status status, Long collectorId);

	List<Bill> find(Date dateFrom, Date dateTo);

	Bill findByCreditNumber(Long creditNumber);

	List<Bill> findByCollectorId(Long collectorId);

	List<Bill> find(Long collectorZone, Date dateFrom, Date dateTo);

	List<Bill> findExpires();

	List<Bill> findByTraderId(Long traderId);

	List<Bill> findByClientId(Long clientId);

	List<Bill> findActivesByDate(Date date);

	@SuppressWarnings("rawtypes")
	List findActivesGroupingByWeekAndTrader();

	List<Bill> findByFilter(Long creditNumber, Long collectorId, Status status);

	List<Bill> findToMakeVouchers(Date date);

	List<Bill> findLastYear();

	List<Bill> findBillsWithCollectors(Date from, Date to);

	List<Bill> findFinalizedInTime();

	List<Bill> findCanceled();

}
