package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.ConciliationItemDto;
import com.icetea.manager.pagodiario.model.ConciliationItem;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class ConciliationItemDtoModelTransformer extends
		AbstractDtoModelTransformer<ConciliationItemDto, ConciliationItem> {

	@Override
	protected ConciliationItemDto doTransform(ConciliationItem e, int depth) {
		ConciliationItemDto d = new ConciliationItemDto();
		d.setId(e.getId());
		d.setCollectAmount(NumberUtils.toString(e.getCollectAmount()));
		d.setDate(DateUtils.toDate(e.getDate()));
		d.setDescription(e.getDescription());
		d.setDiscountAmount(NumberUtils.toString(e.getDiscountAmount()));
		d.setType((e.getType() != null) ? e.getType().name() : null);
		
		return d;
	}

}
