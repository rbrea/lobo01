package com.icetea.manager.pagodiario.service;

import javax.inject.Inject;
import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.PaymentDto;
import com.icetea.manager.pagodiario.dao.PaymentDao;
import com.icetea.manager.pagodiario.model.Payment;
import com.icetea.manager.pagodiario.transformer.PaymentDtoModelTransformer;

@Named
public class PaymentServiceImpl 
	extends BasicIdentifiableServiceImpl<Payment, PaymentDao, PaymentDto, PaymentDtoModelTransformer> 
		implements PaymentService {

	@Inject
	public PaymentServiceImpl(PaymentDao dao,
			PaymentDtoModelTransformer transformer) {
		super(dao, transformer);
	}

}
