package com.icetea.manager.pagodiario.service;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.PaymentDto;
import com.icetea.manager.pagodiario.model.Payment;

public interface PaymentService extends BasicIdentifiableService<Payment, PaymentDto>{

	PaymentDto update(PaymentDto input);

	PaymentDto insert(PaymentDto input, boolean bulk);

	List<PaymentDto> search(Long billId);

	List<PaymentDto> transform(List<BillDto> bills);

	List<PaymentDto> search(Long billId, String paymentDate);

	List<PaymentDto> searchByDate(String paymentDate);

	List<PaymentDto> searchByCreditNumber(Long creditNumber, String paymentDate);

}
