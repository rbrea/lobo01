package com.icetea.manager.pagodiario.service;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.NotImplementedException;

import com.icetea.manager.pagodiario.api.dto.PayrollItemCollectDto;
import com.icetea.manager.pagodiario.dao.PayrollItemCollectDao;
import com.icetea.manager.pagodiario.model.PayrollItemCollect;
import com.icetea.manager.pagodiario.transformer.PayrollItemCollectDtoModelTransformer;

@Named
public class PayrollItemCollectServiceImpl 
	extends BasicIdentifiableServiceImpl<PayrollItemCollect, PayrollItemCollectDao, PayrollItemCollectDto, PayrollItemCollectDtoModelTransformer>
		implements PayrollItemCollectService {

	@Inject
	public PayrollItemCollectServiceImpl(PayrollItemCollectDao dao,
			PayrollItemCollectDtoModelTransformer transformer) {
		super(dao, transformer);
	}

	@Override
	public PayrollItemCollectDto insert(PayrollItemCollectDto o) {
		throw new NotImplementedException();
	}

	@Override
	public PayrollItemCollectDto update(PayrollItemCollectDto o) {
		throw new NotImplementedException();
	}

}
