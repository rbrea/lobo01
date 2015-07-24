package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.DiscountDto;
import com.icetea.manager.pagodiario.model.Discount;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class DiscountDtoModelTransformer extends
		AbstractDtoModelTransformer<DiscountDto, Discount> {

	@Override
	protected DiscountDto doTransform(Discount e, int depth) {
		DiscountDto d = new DiscountDto();
		d.setAmount(NumberUtils.toString(e.getAmount()));
		d.setBillId(e.getBill().getId());
		d.setDate(DateUtils.toDate(e.getDate()));
		d.setObservations(e.getObservations());
		
		return d;
	}

}
