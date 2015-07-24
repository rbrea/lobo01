package com.icetea.manager.pagodiario.service;

import java.util.List;

import com.icetea.manager.pagodiario.api.dto.ProductReductionDto;
import com.icetea.manager.pagodiario.model.ProductReduction;

public interface ProductReductionService extends
		BasicIdentifiableService<ProductReduction, ProductReductionDto> {

	List<ProductReductionDto> search(Long billId);

}
