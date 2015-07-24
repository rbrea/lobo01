package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.PayrollItemDto;
import com.icetea.manager.pagodiario.model.PayrollItem;

@Named
public class PayrollItemDtoModelTransformer extends
		AbstractDtoModelTransformer<PayrollItemDto, PayrollItem> {

	@Override
	protected PayrollItemDto doTransform(PayrollItem e, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

}
