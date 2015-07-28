package com.icetea.manager.pagodiario.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.NotImplementedException;

import com.icetea.manager.pagodiario.api.dto.ConciliationItemDto;
import com.icetea.manager.pagodiario.dao.ConciliationItemDao;
import com.icetea.manager.pagodiario.model.ConciliationItem;
import com.icetea.manager.pagodiario.transformer.ConciliationItemDtoModelTransformer;

@Named
public class ConciliationItemServiceImpl extends
		BasicIdentifiableServiceImpl<ConciliationItem, ConciliationItemDao, ConciliationItemDto, ConciliationItemDtoModelTransformer> 
			implements ConciliationItemService {

	@Inject
	public ConciliationItemServiceImpl(ConciliationItemDao dao,
			ConciliationItemDtoModelTransformer transformer) {
		super(dao, transformer);
	}

	@Override
	public ConciliationItemDto insert(ConciliationItemDto o) {
		throw new NotImplementedException();
	}

	@Override
	public ConciliationItemDto update(ConciliationItemDto o) {
		throw new NotImplementedException();
	}
	
	@Override
	public List<ConciliationItemDto> searchByPayrollItemId(Long id){
		
		return this.getTransformer().transformAllTo(this.getDao().findByPayrollItemId(id));
	}

}
