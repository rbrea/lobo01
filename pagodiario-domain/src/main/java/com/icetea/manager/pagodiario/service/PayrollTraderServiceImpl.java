package com.icetea.manager.pagodiario.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.PayrollTraderDto;
import com.icetea.manager.pagodiario.dao.PayrollTraderDao;
import com.icetea.manager.pagodiario.model.PayrollTrader;
import com.icetea.manager.pagodiario.transformer.PayrollTraderDtoModelTransformer;

@Named
public class PayrollTraderServiceImpl 
	extends BasicIdentifiableServiceImpl<PayrollTrader, PayrollTraderDao, PayrollTraderDto, PayrollTraderDtoModelTransformer>
		implements PayrollTraderService {

	@Inject
	public PayrollTraderServiceImpl(PayrollTraderDao dao,
			PayrollTraderDtoModelTransformer transformer) {
		super(dao, transformer);
	}


}
