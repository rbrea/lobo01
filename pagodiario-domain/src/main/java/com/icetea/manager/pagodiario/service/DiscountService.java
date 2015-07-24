package com.icetea.manager.pagodiario.service;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.DiscountDto;
import com.icetea.manager.pagodiario.model.Discount;

public interface DiscountService extends
		BasicIdentifiableService<Discount, DiscountDto> {

	List<DiscountDto> search(Long billId);

}
