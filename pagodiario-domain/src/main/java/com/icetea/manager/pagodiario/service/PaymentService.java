package com.icetea.manager.pagodiario.service;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.PaymentDto;
import com.icetea.manager.pagodiario.model.Payment;

public interface PaymentService extends BasicIdentifiableService<Payment, PaymentDto>{

	PaymentDto update(PaymentDto input);

	PaymentDto insert(PaymentDto input);

	List<PaymentDto> search(Long billId);

}
