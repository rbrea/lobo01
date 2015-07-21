package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.PayrollDto;
import com.icetea.manager.pagodiario.model.Payroll;

@Named
public class PayrollDtoModelTransformer extends AbstractDtoModelTransformer<PayrollDto, Payroll> {

	@Override
	protected PayrollDto doTransform(Payroll e, int depth) {
		// TODO Auto-generated method stub
		return null;
	}

}
