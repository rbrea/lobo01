package com.icetea.manager.pagodiario.dao;

import com.icetea.manager.pagodiario.model.Client;

public interface ClientDao extends BasicIdentificableDao<Client> {

	Client find(Long documentNumber);
 
}
