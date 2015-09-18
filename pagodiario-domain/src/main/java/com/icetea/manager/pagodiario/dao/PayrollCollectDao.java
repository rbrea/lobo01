package com.icetea.manager.pagodiario.dao;

import java.util.Date;

import com.icetea.manager.pagodiario.model.PayrollCollect;

public interface PayrollCollectDao extends
		BasicIdentificableDao<PayrollCollect> {

	PayrollCollect findByDate(Date date);

}
