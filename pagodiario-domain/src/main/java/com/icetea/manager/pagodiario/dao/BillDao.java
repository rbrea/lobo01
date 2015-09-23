package com.icetea.manager.pagodiario.dao;

import java.util.Date;
import java.util.List;

import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bill.Status;

public interface BillDao extends BasicIdentificableDao<Bill> {

	List<Bill> find(Status status);

	List<Bill> find(Date dateFrom, Date dateTo);

	Bill findByCreditNumber(Long creditNumber);

	List<Bill> findByCollectorId(Long collectorId);

	List<Bill> find(Long collectorId, Date dateFrom, Date dateTo);

	List<Bill> findExpires();

	List<Bill> findByTraderId(Long traderId);

	List<Bill> findByClientId(Long clientId);

	List<Bill> findActivesByDate(Date date);

}
