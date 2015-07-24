package com.icetea.manager.pagodiario.dao;

import java.util.List;

import com.icetea.manager.pagodiario.model.Bonus;

public interface BonusDao extends BasicIdentificableDao<Bonus> {

	List<Bonus> findByBillId(Long billId);

}
