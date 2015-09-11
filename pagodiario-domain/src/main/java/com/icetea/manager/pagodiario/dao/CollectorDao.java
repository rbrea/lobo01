package com.icetea.manager.pagodiario.dao;

import com.icetea.manager.pagodiario.model.Collector;

public interface CollectorDao extends BasicIdentificableDao<Collector> {

	Collector findByZone(Long zone);

}
