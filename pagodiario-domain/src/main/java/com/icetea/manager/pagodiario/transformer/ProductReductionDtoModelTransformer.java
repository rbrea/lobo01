package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.ProductReductionDto;
import com.icetea.manager.pagodiario.model.ProductReduction;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class ProductReductionDtoModelTransformer extends
		AbstractDtoModelTransformer<ProductReductionDto, ProductReduction> {

	@Override
	protected ProductReductionDto doTransform(ProductReduction e, int depth) {
		ProductReductionDto d = new ProductReductionDto();
		d.setAmount(NumberUtils.toString(e.getAmount()));
		d.setBillId(e.getBill().getId());
		d.setDate(DateUtils.toDate(e.getDate()));
		d.setObservations(e.getObservations());
		d.setBillStatus(e.getBill().getStatus().name());
		
		return d;
	}

}
