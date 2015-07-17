package com.icetea.manager.pagodiario.dao;

import java.util.List;

import com.icetea.manager.pagodiario.model.Payment;

public interface PaymentDao extends BasicIdentificableDao<Payment> {

	List<Payment> findByBillId(Long billId);

}
