package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.PaymentDto;
import com.icetea.manager.pagodiario.model.Payment;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class PaymentDtoModelTransformer extends AbstractDtoModelTransformer<PaymentDto, Payment> {

	@Override
	protected PaymentDto doTransform(Payment e, int depth) {
		PaymentDto d = new PaymentDto();
		d.setAmount(NumberUtils.toString(e.getAmount()));
		d.setBillId(e.getBill().getId());
		d.setCollectorId(e.getCollectorId());
		d.setDate(DateUtils.toDate(e.getDate(), "dd/MM/yyyy"));
		d.setId(e.getId());
		d.setCreditNumber(e.getBill().getCreditNumber());
		
		return d;
	}


}
