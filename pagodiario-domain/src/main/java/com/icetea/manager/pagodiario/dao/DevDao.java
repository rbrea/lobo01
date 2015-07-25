package com.icetea.manager.pagodiario.dao;

import java.util.Date;
import java.util.List;

import com.icetea.manager.pagodiario.model.Dev;

public interface DevDao extends BasicIdentificableDao<Dev> {

	List<Dev> findByBillId(Long billId);

	List<Dev> find(Date dateFrom, Date dateTo);

}
