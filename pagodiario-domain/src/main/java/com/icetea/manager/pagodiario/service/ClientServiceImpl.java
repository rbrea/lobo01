package com.icetea.manager.pagodiario.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.ClientDto;
import com.icetea.manager.pagodiario.dao.ClientDao;
import com.icetea.manager.pagodiario.model.Client;
import com.icetea.manager.pagodiario.transformer.ClientDtoModelTransformer;

@Named
public class ClientServiceImpl extends BasicIdentifiableServiceImpl<Client, ClientDao, ClientDto, ClientDtoModelTransformer> 
	implements ClientService {

	@Inject
	public ClientServiceImpl(ClientDao dao,
			ClientDtoModelTransformer transformer) {
		super(dao, transformer);
	}

	@Override
	public ClientDto insert(ClientDto input) {
		
		// TODO: [roher] buscar que no exista previamente cliente,, con las claves unicas
		
		Client e = new Client();
		e.setAddress(input.getAddress());
		e.setCity(input.getCity());
		e.setCompanyAddress(input.getCompanyAddress());
		e.setCompanyCity(input.getCompanyCity());
		e.setCompanyPhone(input.getCompanyPhone());
		e.setCompanyType(input.getCompanyType());
		e.setDocumentNumber(input.getDocumentNumber());
		e.setDocumentType(input.getDocumentType());
		e.setEmail(input.getEmail());
		e.setName(input.getName());
		e.setNearStreets(input.getNearStreets());
		e.setPhone(input.getPhone());
		
		this.getDao().saveOrUpdate(e);
		
		return this.getTransformer().transform(e);
	}

	@Override
	public ClientDto update(ClientDto d) {

		Client e = this.getDao().findById(d.getId());
		if(e == null){
			throw new RuntimeException("El cliente no existe");
		}
		e.setAddress(d.getAddress());
		e.setCity(d.getCity());
		e.setCompanyAddress(d.getCompanyAddress());
		e.setCompanyCity(d.getCompanyCity());
		e.setCompanyPhone(d.getCompanyPhone());
		e.setCompanyType(d.getCompanyType());
		e.setDocumentNumber(d.getDocumentNumber());
		e.setDocumentType(d.getDocumentType());
		e.setEmail(d.getEmail());
		e.setName(d.getName());
		e.setNearStreets(d.getNearStreets());
		e.setPhone(d.getPhone());
		
		this.getDao().saveOrUpdate(e);
		
		return this.getTransformer().transform(e);
	}
	
}
