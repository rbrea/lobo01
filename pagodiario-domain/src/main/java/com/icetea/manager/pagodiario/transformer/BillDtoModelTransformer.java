package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.model.Bill;

@Named
public class BillDtoModelTransformer extends AbstractDtoModelTransformer<BillDto, Bill> {

	@Override
	protected BillDto doTransform(Bill e, int depth) {
		return null;
	}


}
