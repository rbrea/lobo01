package com.icetea.manager.pagodiario.jasper.factory.sample;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.pojo.jasper.ReductionDetailPojo;

public class ReductionDetailPojoFactory {

	public static Collection<ReductionDetailPojo> createBeanCollection(){
		List<ReductionDetailPojo> list = Lists.newArrayList();
		
		ReductionDetailPojo p = new ReductionDetailPojo();
		p.setAmount("150.00");
		p.setDate("10/10/2015");
		
		list.add(p);
		
		return list;
	}
	
}
