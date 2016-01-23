package com.icetea.manager.pagodiario.transformer;

import java.util.List;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.DevAddDto;
import com.icetea.manager.pagodiario.api.dto.DevDto;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Dev;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class DevDtoModelTransformer extends AbstractDtoModelTransformer<DevDto, Dev> {

	@Override
	protected DevDto doTransform(Dev e, int depth) {
		DevDto d = new DevDto();
		d.setAmount(NumberUtils.toString(e.getAmount()));
		d.setBillId(e.getBill().getId());
		d.setDate(DateUtils.toDate(e.getDate()));
		d.setObservations(e.getObservations());
		d.setBillStatus(e.getBill().getStatus().name());
		d.setRemainingAmount(NumberUtils.toString(e.getBill().getRemainingAmount()));
		d.setOverdueDays(e.getBill().getOverdueDays());
		d.setInstallmentAmount(NumberUtils.toString(e.getBill().getTotalDailyInstallment()));
		d.setProductCount(e.getProductCount());
		
		return d;
	}

	public DevAddDto transform(List<Dev> devs, Bill bill){
		DevAddDto d = new DevAddDto();
		
		d.setBillId(bill.getId());
		d.setBillStatus(bill.getStatus().name());
		d.setRemainingAmount(NumberUtils.toString(bill.getRemainingAmount()));
		d.setOverdueDays(bill.getOverdueDays());
		d.setInstallmentAmount(NumberUtils.toString(bill.getTotalDailyInstallment()));
		d.setTotalAmount(NumberUtils.toString(bill.getTotalAmount()));
		
		return d;
	}
	
}
