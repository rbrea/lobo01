package com.icetea.manager.pagodiario.jasper.factory.sample;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.pojo.jasper.BillTicketPojo;
import com.icetea.manager.pagodiario.api.pojo.jasper.ProductPojo;

public class BillTicketFactory {

	public static Collection<BillTicketPojo> createBeanCollection(){
		List<BillTicketPojo> list = Lists.newArrayList();
		
		BillTicketPojo p = new BillTicketPojo();
		p.setClientAddress("Larrea 1055 / B.Vista");
		p.setClientAddress2("Saraza 1234 / Castelar");
		p.setClientCity("B.Vista");
		p.setClientCity2("Castelar");
		p.setClientCompanyAddress("Roca 2865 / Kiosco");
		p.setClientCompanyAddress2("Arias 1234 / Remis");
		p.setClientName("MARGARITA TRIPI");
		p.setClientName2("RODRIGO H");
		p.setClientPhone("1133631310");
		p.setClientPhone2("1143434343");
		p.setCompanyType("Kiosco");
		p.setCompanyType2("Remis");
		p.setCreditNumber("226256");
		p.setCreditNumber2("229988");
		p.setInstallmentAmount("Cuota Imp.: 28.00");
		p.setInstallmentAmount2("Cuota Imp.: 22.00");
		p.setOverdueDays("DIAS DE ATRASO: 26");
		p.setOverdueDays2("DIAS DE ATRASO: 9");
		ProductPojo pr = new ProductPojo();
		pr.setCount(3);
		pr.setName("-almohadas x 3");
		ProductPojo pr1 = new ProductPojo();
		pr1.setCount(2);
		pr1.setName("-cover pc 11/2 x 2");
		p.setProducts(Lists.newArrayList(pr, pr1));
		ProductPojo pr3 = new ProductPojo();
		pr3.setCount(4);
		pr3.setName("-toallas x 4");
		ProductPojo pr4 = new ProductPojo();
		pr4.setCount(6);
		pr4.setName("-cover pc 15/7 x 6");
		p.setProducts2(Lists.newArrayList(pr3, pr4));
		p.setPurchaseDate("Fec Compra: 04/01/2014");
		p.setPurchaseDate2("Fec Compra: 30/04/2015");
		p.setRemainingAmount("Saldo Actual: 1999.00");
		p.setRemainingAmount2("Saldo Actual: 600.00");
		p.setTicketNumber("NRO TCK: 254352");
		p.setTicketNumber2("NRO TCK: 445679");
		p.setTraderName("Flor (1133631310)");
		p.setTraderName2("Eliana (1165778890)");
		p.setTraderPhone("1133631310");
		p.setTraderPhone2("1165778890");
		p.setTotalAmount("Total Compra: 2500.00");
		p.setTotalAmount("Total Compra: 1000.00");
		
		p.setCurrentDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		list.add(p);
		
		p = new BillTicketPojo();
		p.setClientAddress("2Larrea 1055 / B.Vista");
		p.setClientAddress2("2Saraza 1234 / Castelar");
		p.setClientCity("2B.Vista");
		p.setClientCity2("2Castelar");
		p.setClientCompanyAddress("2Roca 2865 / Kiosco");
		p.setClientCompanyAddress2("2Arias 1234 / Remis");
		p.setClientName("2MARGARITA TRIPI");
		p.setClientName2("2RODRIGO H");
		p.setClientPhone("21133631310");
		p.setClientPhone2("21143434343");
		p.setCompanyType("2Kiosco");
		p.setCompanyType2("2Remis");
		p.setCreditNumber("2226256");
		p.setCreditNumber2("2229988");
		p.setInstallmentAmount("Cuota Imp.: 228.00");
		p.setInstallmentAmount2("Cuota Imp.: 222.00");
		p.setOverdueDays("DIAS DE ATRASO: 226");
		p.setOverdueDays2("DIAS DE ATRASO: 29");
		pr = new ProductPojo();
		pr.setCount(3);
		pr.setName("-2almohadas x 3");
		pr1 = new ProductPojo();
		pr1.setCount(2);
		pr1.setName("-2cover pc 11/2 x 2");
		p.setProducts(Lists.newArrayList(pr, pr1));
		pr3 = new ProductPojo();
		pr3.setCount(4);
		pr3.setName("-2toallas x 4");
		pr4 = new ProductPojo();
		pr4.setCount(6);
		pr4.setName("-2cover pc 15/7 x 6");
		p.setProducts2(Lists.newArrayList(pr3, pr4));
		p.setPurchaseDate("Fec Compra: 204/01/2014");
		p.setPurchaseDate2("Fec Compra: 230/04/2015");
		p.setRemainingAmount("Saldo Actual: 21999.00");
		p.setRemainingAmount2("Saldo Actual: 2600.00");
		p.setTicketNumber("NRO TCK: 2254352");
		p.setTicketNumber2("NRO TCK: 2445679");
		p.setTraderName("2Flor (1133631310)");
		p.setTraderName2("2Eliana (1165778890)");
		p.setTraderPhone("21133631310");
		p.setTraderPhone2("21165778890");
		p.setTotalAmount("Total Compra: 22500.00");
		p.setTotalAmount("Total Compra: 21000.00");
		
		p.setCurrentDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		list.add(p);
		
		return list;
	}
}
