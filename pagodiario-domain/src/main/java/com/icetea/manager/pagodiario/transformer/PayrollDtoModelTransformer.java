package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.PayrollDto;
import com.icetea.manager.pagodiario.model.Payroll;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class PayrollDtoModelTransformer extends AbstractDtoModelTransformer<PayrollDto, Payroll> {

	@Override
	protected PayrollDto doTransform(Payroll e, int depth) {
		PayrollDto d = new PayrollDto();
		d.setFromDate(DateUtils.toDate(e.getDateFrom(), "dd/MM/yyyy"));
		d.setToDate(DateUtils.toDate(e.getDateTo(), "dd/MM/yyyy"));
		d.setId(e.getId());
		d.setStatus(e.getStatus().name());
		d.setTotalAmount(NumberUtils.toString(e.getTotalAmount()));
		d.setTotalDiscount(NumberUtils.toString(e.getTotalDiscount()));
		d.setTotal(NumberUtils.toString(e.getTotal()));
		
		return d;
	}

}
