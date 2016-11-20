package com.icetea.manager.pagodiario.dao;

import java.util.Date;
import java.util.List;

import com.icetea.manager.pagodiario.model.Payment;

public interface PaymentDao extends BasicIdentificableDao<Payment> {

	List<Payment> findByBillId(Long billId);

	List<Payment> find(Long billId, Date paymentDateFrom, Date paymentDateTo);
	
	List<Payment> find(Date paymentDateFrom, Date paymentDateTo);
	
	List<Payment> findToCollect(Date paymentDateFrom, Date paymentDateTo);

	List<Payment> findByCreditNumber(Long creditNumber, Date paymentDateFrom, Date paymentDateTo);

}
