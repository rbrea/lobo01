package com.icetea.manager.pagodiario.jasper.factory.sample;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.ProductDto;

public class ProductReportFactory {

	public static Collection<ProductDto> createBeanCollection(){
		List<ProductDto> products = Lists.newArrayList();
		
		ProductDto p = new ProductDto();
		p.setCode("P001");
		p.setDailyInstallment("11.99");
		p.setDescription("Product de muestra 1");
		p.setId(1L);
		p.setPrice("1111.99");
		p.setWeekInstallment("83.93");
		p.setTwoWeeksInstallment("167.86");
		p.setPriceWithDiscount("879.98");
		
		products.add(p);
		
		p = new ProductDto();
		p.setCode("P002");
		p.setDailyInstallment("22.99");
		p.setDescription("Product de muestra 2");
		p.setId(2L);
		p.setPrice("2222.99");
		p.setWeekInstallment("161.00");
		p.setTwoWeeksInstallment("322.00");
		p.setPriceWithDiscount("1980.00");
		
		products.add(p);
		
		p = new ProductDto();
		p.setCode("P003");
		p.setDailyInstallment("33.99");
		p.setDescription("Product de muestra 3");
		p.setId(3L);
		p.setPrice("3333.99");
		p.setWeekInstallment("238.00");
		p.setTwoWeeksInstallment("476.00");
		p.setPriceWithDiscount("2603.99");
		
		products.add(p);
		
		p = new ProductDto();
		p.setCode("P004");
		p.setDailyInstallment("44.99");
		p.setDescription("Product de muestra 4");
		p.setId(4L);
		p.setPrice("4444.99");
		p.setWeekInstallment("315");
		p.setTwoWeeksInstallment("630");
		p.setPriceWithDiscount("3400.76");
		
		products.add(p);
		
		p = new ProductDto();
		p.setCode("P005");
		p.setDailyInstallment("55.99");
		p.setDescription("Product de muestra 5");
		p.setId(5L);
		p.setPrice("5555.99");
		p.setWeekInstallment("392.00");
		p.setTwoWeeksInstallment("800.00");
		p.setPriceWithDiscount("4500.99");
		
		products.add(p);
		
		return products;
	}
	
}
