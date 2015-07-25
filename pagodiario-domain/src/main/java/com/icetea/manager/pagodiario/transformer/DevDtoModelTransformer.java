package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.DevDto;
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
		
		return d;
	}

}
