package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.SupervisorConciliationItemDto;
import com.icetea.manager.pagodiario.api.dto.SupervisorPayrollItemDto;
import com.icetea.manager.pagodiario.model.SupervisorConciliationItem;
import com.icetea.manager.pagodiario.model.SupervisorPayrollItem;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class SupervisorPayrollItemDtoModelTransformer 
	extends AbstractDtoModelTransformer<SupervisorPayrollItemDto, SupervisorPayrollItem> {

	@Override
	protected SupervisorPayrollItemDto doTransform(SupervisorPayrollItem e,
			int depth) {
		
		SupervisorPayrollItemDto d = new SupervisorPayrollItemDto();
		d.setId(e.getId());
		for (SupervisorConciliationItem supervisorConciliationItem : e.getSupervisorConciliationItems()) {
			SupervisorConciliationItemDto i = new SupervisorConciliationItemDto();
			i.setBonusAmount(NumberUtils.toString(supervisorConciliationItem.getBonusAmount()));
			i.setCreditAmount(NumberUtils.toString(supervisorConciliationItem.getCreditAmount()));
			i.setDevAmount(NumberUtils.toString(supervisorConciliationItem.getDevAmount()));
			i.setReductionAmount(NumberUtils.toString(supervisorConciliationItem.getReductionAmount()));
			i.setTotalTrader(NumberUtils.toString(supervisorConciliationItem.getTotalTrader()));
			i.setTraderName(supervisorConciliationItem.getTrader().getName());
			
			d.addSupervisorConciliationItem(i);
		}
		d.setSupervisorName(e.getSupervisor().getName());
		d.setTotalAmount(NumberUtils.toString(e.getTotalAmount()));
		if(e.getBonusItem() != null){
			d.setTotalBonusAmount(NumberUtils.toString(e.getBonusItem().getCollectAmount()));
		}
		d.setTotalCreditAmount(
				NumberUtils.toString(
						NumberUtils.subtract(e.getSubtotalCollect(), e.getSubtotalDiscount())));
		
		return d;
	}

}
