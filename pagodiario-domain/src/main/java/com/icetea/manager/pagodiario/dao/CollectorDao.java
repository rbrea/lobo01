package com.icetea.manager.pagodiario.dao;

import java.util.List;

import com.icetea.manager.pagodiario.model.Collector;

public interface CollectorDao extends BasicIdentificableDao<Collector> {

	Collector findByZone(Long zone);

	List<Collector> findByDescription(String q);

}
