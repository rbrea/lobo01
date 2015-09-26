package com.icetea.manager.pagodiario.dao;

import java.util.List;

import com.icetea.manager.pagodiario.model.ConciliationItemCollect;

public interface ConciliationItemCollectDao extends
		BasicIdentificableDao<ConciliationItemCollect> {

	List<ConciliationItemCollect> findByBillId(Long billId);

}
