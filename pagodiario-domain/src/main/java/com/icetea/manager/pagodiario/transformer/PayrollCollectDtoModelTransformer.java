package com.icetea.manager.pagodiario.transformer;

import javax.inject.Inject;
import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.PayrollCollectDto;
import com.icetea.manager.pagodiario.model.PayrollCollect;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class PayrollCollectDtoModelTransformer extends
		AbstractDtoModelTransformer<PayrollCollectDto, PayrollCollect> {

	private final PayrollItemCollectDtoModelTransformer payrollItemCollectDtoModelTransformer;
	
	@Inject
	public PayrollCollectDtoModelTransformer(
			PayrollItemCollectDtoModelTransformer payrollItemCollectDtoModelTransformer) {
		super();
		this.payrollItemCollectDtoModelTransformer = payrollItemCollectDtoModelTransformer;
	}

	@Override
	protected PayrollCollectDto doTransform(PayrollCollect e, int depth) {
		PayrollCollectDto d = new PayrollCollectDto();
		
		d.setPayrollDate(DateUtils.toDate(e.getPayrollDate()));
		d.setTotalCards(e.getTotalCardsCount());
		d.setTotalAmount(NumberUtils.toString(e.getTotalAmount()));
		d.setTotalPayment(NumberUtils.toString(e.getTotalPayment()));
		d.setTotalAmountToPay(NumberUtils.toString(e.getTotalAmountToPay()));
		
		d.getItems().addAll(
				this.payrollItemCollectDtoModelTransformer.transformAllTo(
						e.getPayrollItemCollectList()));
		
		return d;
	}

}
