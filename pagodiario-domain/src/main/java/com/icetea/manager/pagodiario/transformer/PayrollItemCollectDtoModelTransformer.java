package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.ConciliationItemCollectDto;
import com.icetea.manager.pagodiario.api.dto.PayrollItemCollectDto;
import com.icetea.manager.pagodiario.model.ConciliationItemCollect;
import com.icetea.manager.pagodiario.model.PayrollItemCollect;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class PayrollItemCollectDtoModelTransformer extends
		AbstractDtoModelTransformer<PayrollItemCollectDto, PayrollItemCollect> {

	@Override
	protected PayrollItemCollectDto doTransform(PayrollItemCollect e, int depth) {
		PayrollItemCollectDto d = new PayrollItemCollectDto();
		
		d.setId(e.getId());
		d.setAmountToPay(NumberUtils.toString(e.getAmountToPay()));
		d.setCardsCount(e.getCardsCount());
		d.setCardsCountReal(e.getCardsCountReal());
		d.setCollectorDescription(e.getCollector().getDescription());
		d.setCollectorZone(e.getCollector().getZone());
		d.setCommission(NumberUtils.toString(e.getCommission()));
		d.setCommissionPercentage(NumberUtils.toString(e.getCommissionPercentage()));
		d.setTotalAmount(NumberUtils.toString(e.getTotalAmount()));
		d.setTotalPayment(NumberUtils.toString(e.getTotalPayment()));
		d.setTotalToCollect(NumberUtils.toString(NumberUtils.subtract(e.getTotalPayment(), e.getAmountToPay())));
		
		for (ConciliationItemCollect c : e.getConciliationItemCollectList()) {
			ConciliationItemCollectDto i = new ConciliationItemCollectDto();
			i.setAmount(NumberUtils.toString(c.getAmount()));
			i.setCreditNumber(c.getBill().getCreditNumber());
			i.setDescription(c.getDescription());
			i.setId(c.getId());
			i.setClientCompanyType(c.getBill().getClient().getCompanyType());
			i.setClientName(c.getBill().getClient().getName());
			
			d.getItems().add(i);
		}
		
		return d;
	}

}
