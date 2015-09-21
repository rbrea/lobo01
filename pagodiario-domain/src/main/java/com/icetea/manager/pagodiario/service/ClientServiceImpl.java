package com.icetea.manager.pagodiario.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.ClientDto;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.dao.BillDao;
import com.icetea.manager.pagodiario.dao.ClientDao;
import com.icetea.manager.pagodiario.exception.ErrorTypedConditions;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Client;
import com.icetea.manager.pagodiario.transformer.ClientDtoModelTransformer;

@Named
public class ClientServiceImpl extends BasicIdentifiableServiceImpl<Client, ClientDao, ClientDto, ClientDtoModelTransformer> 
	implements ClientService {

	private final BillDao billDao;
	
	@Inject
	public ClientServiceImpl(ClientDao dao,
			ClientDtoModelTransformer transformer,
			BillDao billDao) {
		super(dao, transformer);
		this.billDao = billDao;
	}

	@Override
	public ClientDto insert(ClientDto input) {

		Client e = this.getDao().find(input.getDocumentNumber());

		ErrorTypedConditions.checkArgument(e == null, "Cliente ya existe dni: " + input.getDocumentNumber(),
				ErrorType.VALIDATION_ERRORS);
		
		e = new Client();
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

	@Override
	public boolean remove(Long id) {
		Client e = this.getDao().findById(id);
		if(e == null){
			throw new RuntimeException(String.format("La entidad con id %s no existe en la bd", id));
		}

		List<Bill> bills = this.billDao.findByClientId(id);
		
		if(bills != null && !bills.isEmpty()){
			throw new ErrorTypedException("No se puede borrar el cliente seleccionado debido a que esta asociado a facturas", 
					ErrorType.VALIDATION_ERRORS);
		}
		
		return this.getDao().delete(e);
	}

	@Override
	public List<ClientDto> searchByName(String q){
		return this.getTransformer().transformAllTo(this.getDao().findByName(q));
	}
	
}
