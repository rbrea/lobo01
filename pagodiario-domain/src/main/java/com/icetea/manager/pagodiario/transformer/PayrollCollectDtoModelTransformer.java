package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.PayrollCollectDto;
import com.icetea.manager.pagodiario.model.PayrollCollect;

@Named
public class PayrollCollectDtoModelTransformer extends
		AbstractDtoModelTransformer<PayrollCollectDto, PayrollCollect> {

	@Override
	protected PayrollCollectDto doTransform(PayrollCollect e, int depth) {
		PayrollCollectDto d = new PayrollCollectDto();
		
		return d;
	}

}
