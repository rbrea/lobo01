package com.icetea.manager.pagodiario.dao;

import java.util.List;

import com.icetea.manager.pagodiario.model.Client;

public interface ClientDao extends BasicIdentificableDao<Client> {

	Client find(Long documentNumber);

	List<Client> findByName(String name);
 
}
