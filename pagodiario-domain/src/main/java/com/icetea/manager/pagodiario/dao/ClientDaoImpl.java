package com.icetea.manager.pagodiario.dao;

import javax.inject.Named;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.icetea.manager.pagodiario.model.Client;

@Named
public class ClientDaoImpl extends BasicIdentificableDaoImpl<Client> 
	implements ClientDao {

	@Override
	protected Class<Client> getEntityClass() {
		return Client.class;
	}

	@Override
	public Client find(Long documentNumber){
		Criteria criteria = super.createCriteria();
		criteria.add(Restrictions.eq("documentNumber", documentNumber));
		
		return (Client) criteria.uniqueResult();
	}

}
