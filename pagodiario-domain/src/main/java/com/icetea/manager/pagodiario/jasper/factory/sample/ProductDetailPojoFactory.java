package com.icetea.manager.pagodiario.jasper.factory.sample;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.pojo.jasper.ProductDetailPojo;

public class ProductDetailPojoFactory {

	public static Collection<ProductDetailPojo> createBeanCollection(){
		List<ProductDetailPojo> list = Lists.newArrayList();
		
		ProductDetailPojo p = new ProductDetailPojo();
		p.setCode("P001");
		p.setCount("1");
		p.setDescription("Producto de muestra 1");
		p.setInstallmentAmount("100.00");
		p.setTotalAmount("1000.00");
		
		list.add(p);
		
		return list;
	}
	
}
