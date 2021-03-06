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
		d.setCollectorId((e.getCollector() != null) ? e.getCollector().getId() : null);
		d.setDate(DateUtils.toDate(e.getDate()));
		d.setId(e.getId());
		d.setCreditNumber(e.getBill().getCreditNumber());
		d.setBillStatus(e.getBill().getStatus().name());
		d.setRemainingAmount(NumberUtils.toString(e.getBill().getRemainingAmount()));
		d.setOverdueDays(e.getBill().getOverdueDays());
		d.setInstallmentAmount(NumberUtils.toString(e.getBill().getTotalDailyInstallment()));
		d.setTotalAmount(NumberUtils.toString(e.getBill().getTotalAmount()));
		d.setCollectorId((e.getBill().getCollector() != null) ? e.getBill().getCollector().getId() : null);
		d.setTraderPayment(e.isTraderPayment());
		d.setClientName(e.getBill().getClient().getName());
		d.setClientCompanyType(e.getBill().getClient().getCompanyType());
		d.setCollectorDescription(e.getCollector().getDescription());
		d.setCollectorZone(e.getCollector().getZone());
		
		return d;
	}

}
