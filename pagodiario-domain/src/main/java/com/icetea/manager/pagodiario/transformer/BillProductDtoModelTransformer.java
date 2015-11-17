package com.icetea.manager.pagodiario.transformer;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.BillProductDto;
import com.icetea.manager.pagodiario.model.BillProduct;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class BillProductDtoModelTransformer extends AbstractDtoModelTransformer<BillProductDto, BillProduct> {

	@Override
	protected BillProductDto doTransform(BillProduct e, int depth) {
		if(e == null){
			return null;
		}
		BillProductDto d = new BillProductDto();
		d.setAmount(NumberUtils.toString(e.getAmount()));
		d.setCount(e.getCount());
		d.setDailyInstallment(NumberUtils.toString(e.getDailyInstallment()));
		d.setPrice(NumberUtils.toString(e.getProduct().getPrice()));
		d.setProductId(e.getProduct().getId());
		d.setProductCode(e.getProduct().getCode());
		d.setProductDescription(e.getProduct().getDescription());
		
		return d;
	}

}
