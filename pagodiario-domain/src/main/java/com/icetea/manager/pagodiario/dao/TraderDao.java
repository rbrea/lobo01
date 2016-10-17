package com.icetea.manager.pagodiario.dao;

import java.util.List;

import com.icetea.manager.pagodiario.model.Trader;

public interface TraderDao extends BasicIdentificableDao<Trader> {

	Trader find(Long documentNumber);

	List<Trader> findSupervisors();

	List<Trader> find(Trader.Status status);

}
