package com.icetea.manager.pagodiario.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.common.base.Preconditions;
import com.icetea.manager.pagodiario.api.dto.TraderDto;
import com.icetea.manager.pagodiario.dao.TraderDao;
import com.icetea.manager.pagodiario.model.Trader;
import com.icetea.manager.pagodiario.transformer.TraderDtoModelTransformer;

@Named
public class TraderServiceImpl 
	extends BasicIdentifiableServiceImpl<Trader, TraderDao, TraderDto, TraderDtoModelTransformer>
		implements TraderService {

	@Inject
	public TraderServiceImpl(TraderDao dao,
			TraderDtoModelTransformer transformer) {
		super(dao, transformer);
	}

	@Override
	public TraderDto insert(TraderDto input) {
		Trader e = new Trader();
		e.setAddress(input.getAddress());
		e.setCity(input.getCity());
		e.setDocumentNumber(input.getDocumentNumber());
		e.setDocumentType(input.getDocumentType());
		e.setEmail(input.getEmail());
		e.setName(input.getName());
		e.setNearStreets(input.getNearStreets());
		e.setPhone(input.getPhone());
		e.setSupervisor(input.isSupervisor());
		
		if(input.getParentId() != null){
			Trader parent = this.getDao().findById(input.getParentId());
			e.setParent(parent);
		}
		
		this.getDao().saveOrUpdate(e);
		
		return this.getTransformer().transform(e);
	}

	@Override
	public TraderDto update(TraderDto d) {
		Preconditions.checkArgument(d.getId() != null, "id required");
		
		Trader e = this.getDao().findById(d.getId());
		e.setAddress(d.getAddress());
		e.setCity(d.getCity());
		e.setDocumentNumber(d.getDocumentNumber());
		e.setDocumentType(d.getDocumentType());
		e.setEmail(d.getEmail());
		e.setName(d.getName());
		e.setNearStreets(d.getNearStreets());
		e.setPhone(d.getPhone());
		e.setSupervisor(d.isSupervisor());
		
		if(d.getParentId() != null){
			Trader parent = this.getDao().findById(d.getParentId());
			e.setParent(parent);
		}
		
		this.getDao().saveOrUpdate(e);
		
		return this.getTransformer().transform(e);
	}

	@Override
	public boolean deleteChild(Long parentId, Long childId){
	
		Trader parent = this.getDao().findById(parentId);
		if(parent != null){
			parent.removeTrader(childId);
		}
		this.getDao().save(parent);
		Trader child = this.getDao().findById(childId);
		if(child != null){
			child.setParent(null);
		}
		this.getDao().saveOrUpdate(child);
		
		return true;
	}
	
}
