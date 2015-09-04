package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.PayrollItemDto;
import com.icetea.manager.pagodiario.model.Payroll;
import com.icetea.manager.pagodiario.model.PayrollItem;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class PayrollItemDtoModelTransformer extends
		AbstractDtoModelTransformer<PayrollItemDto, PayrollItem> {

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
		
		return d;
	}

}
