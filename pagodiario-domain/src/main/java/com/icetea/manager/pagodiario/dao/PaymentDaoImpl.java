package com.icetea.manager.pagodiario.dao;

import javax.inject.Named;

import com.icetea.manager.pagodiario.model.Payment;

@Named
public class PaymentDaoImpl extends BasicIdentificableDaoImpl<Payment>
		implements PaymentDao {

	@Override
	protected Class<Payment> getEntityClass() {
		return Payment.class;
	}

}
