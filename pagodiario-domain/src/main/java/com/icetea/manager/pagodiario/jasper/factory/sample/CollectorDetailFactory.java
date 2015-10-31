package com.icetea.manager.pagodiario.jasper.factory.sample;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.CollectorDetailDto;

public class CollectorDetailFactory {

	public static Collection<CollectorDetailDto> createBeanCollection(){
		List<CollectorDetailDto> list = Lists.newArrayList();
		CollectorDetailDto d = new CollectorDetailDto();
		d.setId(1L);
		d.setName("COBRADOR 1");
		d.setZone("8");
		d.setAmountToCollect("1900.00");
		d.setAmountCollected("900.00");
		d.setRemainingAmount("1000.00");
		d.setFromDate("01/10/2015");
		d.setToDate("08/10/2015");
		
		list.add(d);
		
		d = new CollectorDetailDto();
		d.setId(1L);
		d.setName("COBRADOR 2");
		d.setZone("11");
		d.setAmountToCollect("1200.00");
		d.setAmountCollected("400.00");
		d.setRemainingAmount("800.00");
		d.setFromDate("11/10/2015");
		d.setToDate("18/10/2015");
		
		list.add(d);
		
		return list;
	}
	
}
