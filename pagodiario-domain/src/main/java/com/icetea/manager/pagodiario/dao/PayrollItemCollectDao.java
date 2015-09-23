package com.icetea.manager.pagodiario.dao;

import java.util.Date;
import java.util.List;

import com.icetea.manager.pagodiario.model.PayrollItemCollect;

public interface PayrollItemCollectDao extends
		BasicIdentificableDao<PayrollItemCollect> {

	List<?> findPeriod(Date date);

}
