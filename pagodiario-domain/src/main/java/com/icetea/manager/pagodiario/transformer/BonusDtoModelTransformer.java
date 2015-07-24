package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.BonusDto;
import com.icetea.manager.pagodiario.model.Bonus;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class BonusDtoModelTransformer extends AbstractDtoModelTransformer<BonusDto, Bonus> {

	@Override
	protected BonusDto doTransform(Bonus e, int depth) {
		BonusDto d = new BonusDto();
		d.setAmount(NumberUtils.toString(e.getAmount()));
		d.setBillId(e.getBill().getId());
		d.setDate(DateUtils.toDate(e.getDate()));
		d.setObservations(e.getObservations());
		
		return d;
	}

}
