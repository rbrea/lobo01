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
		p.setInstallmentAmount("28.00");
		p.setInstallmentAmount2("22.00");
		p.setOverdueDays("DIAS DE ATRASO: 26");
		p.setOverdueDays2("DIAS DE ATRASO: 9");
		p.setRemainingDays("28");
		p.setRemainingDays2("34");
		ProductPojo pr = new ProductPojo();
		pr.setCount(3);
		pr.setName("almohadas");
		ProductPojo pr1 = new ProductPojo();
		pr1.setCount(2);
		pr1.setName("cover pc 11/2");
		p.setProducts(Lists.newArrayList(pr, pr1));
		ProductPojo pr3 = new ProductPojo();
		pr3.setCount(4);
		pr3.setName("toallas");
		ProductPojo pr4 = new ProductPojo();
		pr4.setCount(6);
		pr4.setName("cover pc 15/7");
		p.setProducts2(Lists.newArrayList(pr3, pr4));
		p.setPurchaseDate("04/01/2014");
		p.setPurchaseDate2("30/04/2015");
		p.setRemainingAmount("1999.00");
		p.setRemainingAmount2("600.00");
		p.setTicketNumber("NRO TCK: 254352");
		p.setTicketNumber2("NRO TCK: 445679");
		p.setTraderName("Flor (1133631310)");
		p.setTraderName2("Eliana (1165778890)");
		p.setTraderPhone("1133631310");
		p.setTraderPhone2("1165778890");
		p.setTotalAmount("2500.00");
		p.setTotalAmount2("1000.00");
		p.setZone("1");
		p.setZone2("2");
		p.setWeekAmount("196");
		p.setWeekAmount2("154");
		p.setCurrentAmount("1000");
		p.setCurrentAmount2("1100");
		
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
		pr.setName("2almohadas");
		pr1 = new ProductPojo();
		pr1.setCount(2);
		pr1.setName("2cover pc 11/2");
		p.setProducts(Lists.newArrayList(pr, pr1));
		pr3 = new ProductPojo();
		pr3.setCount(4);
		pr3.setName("2toallas");
		pr4 = new ProductPojo();
		pr4.setCount(6);
		pr4.setName("2cover pc 15/7");
		p.setProducts2(Lists.newArrayList(pr3, pr4));
		p.setPurchaseDate("Fec Compra: 204/01/2014");
		p.setPurchaseDate2("Fec Compra: 230/04/2015");
		p.setRemainingAmount("Saldo Actual: 21999.00");
		p.setRemainingAmount2("Saldo Actual: 2600.00");
		p.setRemainingDays("228");
		p.setRemainingDays2("234");
		p.setTicketNumber("NRO TCK: 2254352");
		p.setTicketNumber2("NRO TCK: 2445679");
		p.setTraderName("2Flor (1133631310)");
		p.setTraderName2("2Eliana (1165778890)");
		p.setTraderPhone("21133631310");
		p.setTraderPhone2("21165778890");
		p.setTotalAmount("Total Compra: 22500.00");
		p.setTotalAmount("Total Compra: 21000.00");
		
		p.setCurrentDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		p.setZone("3");
		p.setZone2("4");
		p.setWeekAmount("210");
		p.setWeekAmount2("250");
		p.setCurrentAmount("1500");
		p.setCurrentAmount2("1700");
		
		list.add(p);
		
		p = new BillTicketPojo();
		p.setClientAddress("3Larrea 1055 / B.Vista");
		p.setClientAddress2("3Saraza 1234 / Castelar");
		p.setClientCity("3B.Vista");
		p.setClientCity2("3Castelar");
		p.setClientCompanyAddress("3Roca 2865 / Kiosco");
		p.setClientCompanyAddress2("3Arias 1234 / Remis");
		p.setClientName("3MARGARITA TRIPI");
		p.setClientName2("3RODRIGO H");
		p.setClientPhone("31133631310");
		p.setClientPhone2("31143434343");
		p.setCompanyType("3Kiosco");
		p.setCompanyType2("3Remis");
		p.setCreditNumber("3226256");
		p.setCreditNumber2("3229988");
		p.setInstallmentAmount("Cuota Imp.: 228.00");
		p.setInstallmentAmount2("Cuota Imp.: 222.00");
		p.setOverdueDays("DIAS DE ATRASO: 226");
		p.setOverdueDays2("DIAS DE ATRASO: 29");
		pr = new ProductPojo();
		pr.setCount(3);
		pr.setName("3almohadas");
		pr1 = new ProductPojo();
		pr1.setCount(2);
		pr1.setName("3cover pc 11/2");
		p.setProducts(Lists.newArrayList(pr, pr1));
		pr3 = new ProductPojo();
		pr3.setCount(4);
		pr3.setName("3toallas");
		pr4 = new ProductPojo();
		pr4.setCount(6);
		pr4.setName("3cover pc 15/7");
		p.setProducts2(Lists.newArrayList(pr3, pr4));
		p.setPurchaseDate("Fec Compra: 204/01/2014");
		p.setPurchaseDate2("Fec Compra: 230/04/2015");
		p.setRemainingAmount("Saldo Actual: 21999.00");
		p.setRemainingAmount2("Saldo Actual: 2600.00");
		p.setRemainingDays("328");
		p.setRemainingDays2("334");
		p.setTicketNumber("NRO TCK: 2254352");
		p.setTicketNumber2("NRO TCK: 2445679");
		p.setTraderName("3Flor (1133631310)");
		p.setTraderName2("3Eliana (1165778890)");
		p.setTraderPhone("31133631310");
		p.setTraderPhone2("31165778890");
		p.setTotalAmount("Total Compra: 22500.00");
		p.setTotalAmount("Total Compra: 21000.00");
		
		p.setZone("5");
		p.setZone2("6");
		
		p.setCurrentDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		p.setWeekAmount("400");
		p.setWeekAmount2("345");
		p.setCurrentAmount("2100");
		p.setCurrentAmount2("1600");
		
		list.add(p);
		
		p = new BillTicketPojo();
		p.setClientAddress("4Larrea 1055 / B.Vista");
		p.setClientAddress2("4Saraza 1234 / Castelar");
		p.setClientCity("4B.Vista");
		p.setClientCity2("4Castelar");
		p.setClientCompanyAddress("4Roca 2865 / Kiosco");
		p.setClientCompanyAddress2("4Arias 1234 / Remis");
		p.setClientName("4MARGARITA TRIPI");
		p.setClientName2("4RODRIGO H");
		p.setClientPhone("41133631310");
		p.setClientPhone2("41143434343");
		p.setCompanyType("4Kiosco");
		p.setCompanyType2("4Remis");
		p.setCreditNumber("4226256");
		p.setCreditNumber2("4229988");
		p.setInstallmentAmount("Cuota Imp.: 228.00");
		p.setInstallmentAmount2("Cuota Imp.: 222.00");
		p.setOverdueDays("DIAS DE ATRASO: 226");
		p.setOverdueDays2("DIAS DE ATRASO: 29");
		pr = new ProductPojo();
		pr.setCount(3);
		pr.setName("4almohadas");
		pr1 = new ProductPojo();
		pr1.setCount(2);
		pr1.setName("4cover pc 11/2");
		p.setProducts(Lists.newArrayList(pr, pr1));
		pr3 = new ProductPojo();
		pr3.setCount(4);
		pr3.setName("4toallas");
		pr4 = new ProductPojo();
		pr4.setCount(6);
		pr4.setName("4cover pc 15/7");
		p.setProducts2(Lists.newArrayList(pr3, pr4));
		p.setPurchaseDate("Fec Compra: 204/01/2014");
		p.setPurchaseDate2("Fec Compra: 230/04/2015");
		p.setRemainingAmount("Saldo Actual: 21999.00");
		p.setRemainingAmount2("Saldo Actual: 2600.00");
		p.setRemainingDays("428");
		p.setRemainingDays2("434");
		p.setTicketNumber("NRO TCK: 2254352");
		p.setTicketNumber2("NRO TCK: 2445679");
		p.setTraderName("4Flor (1133631310)");
		p.setTraderName2("4Eliana (1165778890)");
		p.setTraderPhone("41133631310");
		p.setTraderPhone2("41165778890");
		p.setTotalAmount("Total Compra: 22500.00");
		p.setTotalAmount("Total Compra: 21000.00");
		
		p.setCurrentDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		p.setZone("88");
		p.setZone2("99");
		
		p.setWeekAmount("78");
		p.setWeekAmount2("452");
		p.setCurrentAmount("2900");
		p.setCurrentAmount2("890");
		
		list.add(p);

		p = new BillTicketPojo();
		p.setClientAddress("5Larrea 1055 / B.Vista");
		p.setClientAddress2("5Saraza 1234 / Castelar");
		p.setClientCity("5B.Vista");
		p.setClientCity2("5Castelar");
		p.setClientCompanyAddress("5Roca 2865 / Kiosco");
		p.setClientCompanyAddress2("5Arias 1234 / Remis");
		p.setClientName("5MARGARITA TRIPI");
		p.setClientName2("5RODRIGO H");
		p.setClientPhone("51133631310");
		p.setClientPhone2("51143434343");
		p.setCompanyType("5Kiosco");
		p.setCompanyType2("5Remis");
		p.setCreditNumber("5226256");
		p.setCreditNumber2("5229988");
		p.setInstallmentAmount("Cuota Imp.: 228.00");
		p.setInstallmentAmount2("Cuota Imp.: 222.00");
		p.setOverdueDays("DIAS DE ATRASO: 226");
		p.setOverdueDays2("DIAS DE ATRASO: 29");
		pr = new ProductPojo();
		pr.setCount(3);
		pr.setName("5almohadas");
		pr1 = new ProductPojo();
		pr1.setCount(2);
		pr1.setName("5cover pc 11/2");
		p.setProducts(Lists.newArrayList(pr, pr1));
		pr3 = new ProductPojo();
		pr3.setCount(4);
		pr3.setName("5toallas");
		pr4 = new ProductPojo();
		pr4.setCount(6);
		pr4.setName("5cover pc 15/7");
		p.setProducts2(Lists.newArrayList(pr3, pr4));
		p.setPurchaseDate("Fec Compra: 204/01/2014");
		p.setPurchaseDate2("Fec Compra: 230/04/2015");
		p.setRemainingAmount("Saldo Actual: 21999.00");
		p.setRemainingAmount2("Saldo Actual: 2600.00");
		p.setRemainingDays("528");
		p.setRemainingDays2("534");
		p.setTicketNumber("NRO TCK: 2254352");
		p.setTicketNumber2("NRO TCK: 2445679");
		p.setTraderName("5Flor (1133631310)");
		p.setTraderName2("5Eliana (1165778890)");
		p.setTraderPhone("51133631310");
		p.setTraderPhone2("51165778890");
		p.setTotalAmount("Total Compra: 22500.00");
		p.setTotalAmount("Total Compra: 21000.00");
		
		p.setCurrentDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		p.setZone("9");
		p.setZone2("34");
		
		p.setWeekAmount("123");
		p.setWeekAmount2("321");
		p.setCurrentAmount("1456");
		p.setCurrentAmount2("1234");
		
		list.add(p);
		
		return list;
	}
}
