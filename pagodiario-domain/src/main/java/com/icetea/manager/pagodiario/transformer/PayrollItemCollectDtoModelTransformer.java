package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.PayrollItemCollectDto;
import com.icetea.manager.pagodiario.model.PayrollItemCollect;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class PayrollItemCollectDtoModelTransformer extends
		AbstractDtoModelTransformer<PayrollItemCollectDto, PayrollItemCollect> {

	@Override
	protected PayrollItemCollectDto doTransform(PayrollItemCollect e, int depth) {
		PayrollItemCollectDto d = new PayrollItemCollectDto();
		
		d.setAmountToPay(NumberUtils.toString(e.getAmountToPay()));
		d.setCardsCount(e.getCardsCount());
		d.setCollectorDescription(e.getCollector().getDescription());
		d.setCollectorZone(e.getCollector().getZone());
		d.setCommission(NumberUtils.toString(e.getCommission()));
		d.setCommissionPercentage(NumberUtils.toString(e.getCommissionPercentage()));
		d.setTotalAmount(NumberUtils.toString(e.getTotalAmount()));
		d.setTotalPayment(NumberUtils.toString(e.getTotalPayment()));
		
		return d;
	}

}
