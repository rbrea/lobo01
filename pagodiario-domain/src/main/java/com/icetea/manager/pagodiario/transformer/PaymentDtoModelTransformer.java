package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.PaymentDto;
import com.icetea.manager.pagodiario.model.Payment;

@Named
public class PaymentDtoModelTransformer extends AbstractDtoModelTransformer<PaymentDto, Payment> {

	@Override
	protected PaymentDto doTransform(Payment e, int depth) {
		PaymentDto d = new PaymentDto();
		
		return d;
	}


}
