package com.icetea.manager.pagodiario.jasper.factory.sample;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.PayrollItemCollectDto;

public class PayrollCollectFactory {

	public static Collection<PayrollItemCollectDto> createBeanCollection(){
		List<PayrollItemCollectDto> list = Lists.newArrayList();
		
		PayrollItemCollectDto d = new PayrollItemCollectDto();
		d.setId(1L);
		d.setCollectorZone(11L);
		d.setCollectorDescription("COBRADOR 11");
		d.setCardsCount(1);
		d.setTotalAmount("10.00");
		d.setTotalPayment("10.00");
		d.setAmountToPay("2.00");
		d.setTotalToCollect("8.00");
		
		list.add(d);
		
		d = new PayrollItemCollectDto();
		d.setId(2L);
		d.setCollectorZone(8L);
		d.setCollectorDescription("COBRADOR 8");
		d.setCardsCount(1);
		d.setTotalAmount("200.00");
		d.setTotalPayment("200.00");
		d.setAmountToPay("40.00");
		d.setTotalToCollect("160.00");
		
		list.add(d);
		
		return list;
	}
	
}
