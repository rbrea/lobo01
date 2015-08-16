package com.icetea.manager.pagodiario.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.NotImplementedException;

import com.icetea.manager.pagodiario.api.dto.SupervisorPayrollItemDto;
import com.icetea.manager.pagodiario.dao.SupervisorPayrollItemDao;
import com.icetea.manager.pagodiario.model.SupervisorPayrollItem;
import com.icetea.manager.pagodiario.transformer.SupervisorPayrollItemDtoModelTransformer;

@Named
public class SupervisorPayrollItemServiceImpl extends
		BasicIdentifiableServiceImpl<SupervisorPayrollItem, SupervisorPayrollItemDao, SupervisorPayrollItemDto, SupervisorPayrollItemDtoModelTransformer> 
			implements SupervisorPayrollItemService {

	@Inject
	public SupervisorPayrollItemServiceImpl(SupervisorPayrollItemDao dao,
			SupervisorPayrollItemDtoModelTransformer transformer) {
		super(dao, transformer);
	}

	@Override
	public SupervisorPayrollItemDto insert(SupervisorPayrollItemDto o) {
		throw new NotImplementedException("not implemented");
	}

	@Override
	public SupervisorPayrollItemDto update(SupervisorPayrollItemDto o) {
		throw new NotImplementedException("not implemented");
	}
	
	@Override
	public SupervisorPayrollItemDto searchDetail(Long id){
		return this.getTransformer().transform(this.getDao().findById(id));
	}

}
