package com.icetea.manager.pagodiario.dao;

import javax.inject.Named;

import com.icetea.manager.pagodiario.model.Client;

@Named
public class ClientDaoImpl extends BasicIdentificableDaoImpl<Client> 
	implements ClientDao {

	@Override
	protected Class<Client> getEntityClass() {
		return Client.class;
	}


}
