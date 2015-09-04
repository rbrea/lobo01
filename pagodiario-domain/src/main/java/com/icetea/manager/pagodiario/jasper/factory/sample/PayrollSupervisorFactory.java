package com.icetea.manager.pagodiario.jasper.factory.sample;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.SupervisorConciliationItemDto;

public class PayrollSupervisorFactory {

	public static Collection<SupervisorConciliationItemDto> createBeanCollection(){
	
		List<SupervisorConciliationItemDto> list = Lists.newArrayList();
		SupervisorConciliationItemDto d = new SupervisorConciliationItemDto();
		d.setTraderName("01 Ramiro");
		d.setCreditAmount("2416.20");
		d.setDevAmount("-503.40");
		d.setBonusAmount("191.28");
		d.setReductionAmount("-73.04");
		d.setTotalTrader("2031.04");
		
		list.add(d);
		
		d = new SupervisorConciliationItemDto();
		d.setTraderName("02 Tobias");
		d.setCreditAmount("2217.00");
		d.setDevAmount("-443.50");
		d.setBonusAmount("177.35");
		d.setReductionAmount("0.00");
		d.setTotalTrader("1950.85");
		
		list.add(d);
		
		d = new SupervisorConciliationItemDto();
		d.setTraderName("10 Maria Ines");
		d.setCreditAmount("1582.80");
		d.setDevAmount("-103.50");
		d.setBonusAmount("147.93");
		d.setReductionAmount("-399.28");
		d.setTotalTrader("1227.95");
		
		list.add(d);
		
		d = new SupervisorConciliationItemDto();
		d.setTraderName("28 Jose Maria");
		d.setCreditAmount("211.50");
		d.setDevAmount("0.00");
		d.setBonusAmount("21.15");
		d.setReductionAmount("0.00");
		d.setTotalTrader("232.65");
		
		list.add(d);
		
		d = new SupervisorConciliationItemDto();
		d.setTraderName("14 Rodrigo");
		d.setCreditAmount("0.00");
		d.setDevAmount("0.00");
		d.setBonusAmount("0.00");
		d.setReductionAmount("-453.91");
		d.setTotalTrader("-453.91");
		
		list.add(d);
		
		d = new SupervisorConciliationItemDto();
		d.setTraderName("16 Francisco");
		d.setCreditAmount("542.95");
		d.setDevAmount("-30.00");
		d.setBonusAmount("51.30");
		d.setReductionAmount("0.00");
		d.setTotalTrader("564.25");
		
		list.add(d);
		
		return list;
	}
	
}
