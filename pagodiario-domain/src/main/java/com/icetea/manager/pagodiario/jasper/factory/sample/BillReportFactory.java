package com.icetea.manager.pagodiario.jasper.factory.sample;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BillDto;

public class BillReportFactory {

	public static Collection<BillDto> createBeanCollection(){
	
		List<BillDto> bills = Lists.newArrayList();
		
		BillDto b = new BillDto();
		b.setClientId(1L);
		b.setCollectorId(8L);
		b.setCreditNumber(11111L);
		b.setEndDate("15/06/2015");
		b.setId(1L);
		b.setOverdueDays(1);
		b.setRemainingAmount("1000.00");
		b.setStartDate("14/05/2015");
		b.setStatus("FINALIZED");
		b.setTotalAmount("1100.00");
		b.setTotalDailyInstallment("100.00");
		b.setTraderId(1L);
		
		bills.add(b);
		
		b = new BillDto();
		b.setClientId(2L);
		b.setCollectorId(8L);
		b.setCreditNumber(22222L);
		b.setEndDate("25/07/2015");
		b.setId(2L);
		b.setOverdueDays(2);
		b.setRemainingAmount("2000.00");
		b.setStartDate("24/05/2015");
		b.setStatus("FINALIZED");
		b.setTotalAmount("2200.00");
		b.setTotalDailyInstallment("200.00");
		b.setTraderId(2L);
		
		bills.add(b);
		
		return bills;
	}
	
}
