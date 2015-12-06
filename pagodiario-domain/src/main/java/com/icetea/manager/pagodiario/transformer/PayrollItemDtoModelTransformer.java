package com.icetea.manager.pagodiario.transformer;

import javax.inject.Inject;
import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.ConciliationItemDto;
import com.icetea.manager.pagodiario.api.dto.PayrollItemDto;
import com.icetea.manager.pagodiario.model.AbstractConciliationItem;
import com.icetea.manager.pagodiario.model.BonusConciliationItem;
import com.icetea.manager.pagodiario.model.ConciliationItem;
import com.icetea.manager.pagodiario.model.Payroll;
import com.icetea.manager.pagodiario.model.PayrollItem;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class PayrollItemDtoModelTransformer extends
		AbstractDtoModelTransformer<PayrollItemDto, PayrollItem> {

	private final ConciliationItemDtoModelTransformer conciliationItemDtoModelTransformer;
	
	@Inject
	public PayrollItemDtoModelTransformer(
			ConciliationItemDtoModelTransformer conciliationItemDtoModelTransformer) {
		super();
		this.conciliationItemDtoModelTransformer = conciliationItemDtoModelTransformer;
	}

	@Override
	protected PayrollItemDto doTransform(PayrollItem e, int depth) {
		PayrollItemDto d = new PayrollItemDto();
		d.setId(e.getId());
		
		Payroll payroll = e.getPayroll();
		
		d.setPayrollDateFrom(DateUtils.toDate(payroll.getDateFrom()));
		d.setPayrollDateTo(DateUtils.toDate(payroll.getDateTo()));
		d.setSubtotalCollect(NumberUtils.toString(e.getSubtotalCollect()));
		d.setSubtotalDiscount(NumberUtils.toString(e.getSubtotalDiscount()));
		d.setTotalAmount(NumberUtils.toString(e.getTotalAmount()));
		d.setTraderName(e.getTrader().getName());
		
		// primero van todos los items que no sea baja
		for(ConciliationItem c : e.getItems()){
			if(c.getType() != AbstractConciliationItem.Type.REDUCTION){
				d.addConciliationItem(this.conciliationItemDtoModelTransformer.transform(c));
			}
		}
		
		BonusConciliationItem bonusItem = e.getBonusItem();
		// luego el premio
		if(bonusItem != null && bonusItem.getCollectAmount() != null){
			ConciliationItemDto b = new ConciliationItemDto();
			b.setId(bonusItem.getId());
			b.setCollectAmount(NumberUtils.toString(bonusItem.getCollectAmount()));
			b.setDate(DateUtils.toDate(bonusItem.getDate()));
			b.setDescription(bonusItem.getDescription());
			b.setDiscountAmount(NumberUtils.toString(bonusItem.getDiscountAmount()));
			b.setType(bonusItem.getType().name());
			
			d.addConciliationItem(b);
		}
		// x ultimo las bajas
		for(ConciliationItem c : e.getItems()){
			if(c.getType() == AbstractConciliationItem.Type.REDUCTION){
				d.addConciliationItem(this.conciliationItemDtoModelTransformer.transform(c));
			}
		}
		
		return d;
	}

}
