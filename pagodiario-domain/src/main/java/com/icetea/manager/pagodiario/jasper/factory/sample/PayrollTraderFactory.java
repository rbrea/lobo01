package com.icetea.manager.pagodiario.jasper.factory.sample;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.ConciliationItemDto;

public class PayrollTraderFactory {

	public static Collection<ConciliationItemDto> createBeanCollection(){
		List<ConciliationItemDto> list = Lists.newArrayList();
		ConciliationItemDto c = new ConciliationItemDto();
		c.setDescription("'CREDITO' 245147 FECHA 09/01/2014");
		c.setCollectAmount("209.00");
		c.setDiscountAmount("19.00");
		list.add(c);
		
		c = new ConciliationItemDto();
		c.setDescription("'CREDITO' 245148 FECHA 10/01/2014");
		c.setCollectAmount("50.00");
		c.setDiscountAmount("4.00");
		list.add(c);
		
		c = new ConciliationItemDto();
		c.setDescription("'CREDITO' 245151 FECHA 09/01/2014");
		c.setCollectAmount("125.00");
		c.setDiscountAmount("10.00");
		list.add(c);
		
		c = new ConciliationItemDto();
		c.setDescription("'CREDITO' 245152 FECHA 09/01/2014");
		c.setCollectAmount("248.00");
		c.setDiscountAmount("22.00");
		list.add(c);
		
		c = new ConciliationItemDto();
		c.setDescription("'CREDITO' 245153 FECHA 08/01/2014");
		c.setCollectAmount("120.00");
		c.setDiscountAmount("12.00");
		list.add(c);
		
		c = new ConciliationItemDto();
		c.setDescription("'DEV CRED' 255918 FEC 02/01/2014");
		c.setCollectAmount("-110.00");
		c.setDiscountAmount("0.00");
		list.add(c);
		
		c = new ConciliationItemDto();
		c.setDescription("PREMIO 20% (prendas periodo(201)/Días Hábiles(10)");
		c.setCollectAmount("794.68");
		c.setDiscountAmount("0.00");
		list.add(c);
		
		c = new ConciliationItemDto();
		c.setDescription("'1/2 BAJA CRED' 252727 FEC 15/08/2013");
		c.setCollectAmount("-170.87");
		c.setDiscountAmount("0.00");
		list.add(c);
		
		return list;
	}
	
}
