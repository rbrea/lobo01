package com.icetea.manager.pagodiario.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.NotImplementedException;

import com.icetea.manager.pagodiario.api.dto.PayrollItemDto;
import com.icetea.manager.pagodiario.dao.PayrollItemDao;
import com.icetea.manager.pagodiario.model.PayrollItem;
import com.icetea.manager.pagodiario.transformer.PayrollItemDtoModelTransformer;

@Named
public class PayrollItemServiceImpl 
	extends BasicIdentifiableServiceImpl<PayrollItem, PayrollItemDao, PayrollItemDto, PayrollItemDtoModelTransformer>
		implements PayrollItemService {

	@Inject
	public PayrollItemServiceImpl(PayrollItemDao dao,
			PayrollItemDtoModelTransformer transformer) {
		super(dao, transformer);
	}
	
	@Override
	public PayrollItemDto insert(PayrollItemDto o) {
		throw new NotImplementedException("not implemented");
	}
	
	@Override
	public PayrollItemDto update(PayrollItemDto o) {
		throw new NotImplementedException("not implemented");
	}
	
}
