package com.icetea.manager.pagodiario.jasper.factory.sample;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.pojo.jasper.DiscountDetailPojo;

public class DiscountDetailPojoFactory {

	public static Collection<DiscountDetailPojo> createBeanCollection(){
		List<DiscountDetailPojo> list = Lists.newArrayList();
		
		DiscountDetailPojo p = new DiscountDetailPojo();
		p.setAmount("150.00");
		p.setDate("10/10/2015");
		
		list.add(p);
		
		return list;
	}
	
}
