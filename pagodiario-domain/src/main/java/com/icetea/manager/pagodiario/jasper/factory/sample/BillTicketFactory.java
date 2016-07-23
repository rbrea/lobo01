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
		p.setClientCity("B.Vista");
		p.setClientCompanyAddress("Roca 2865 / Kiosco");
		p.setClientName("MARGARITA TRIPI");
		p.setClientPhone("1133631310");
		p.setCompanyType("Kiosco");
		p.setCreditNumber("226256");
		p.setInstallmentAmount("28.00");
		p.setOverdueDays("DIAS DE ATRASO: 26");
		p.setRemainingDays("28");
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
		p.setPurchaseDate("04/01/2014");
		p.setRemainingAmount("1999.00");
		p.setTicketNumber("NRO TCK: 254352");
		p.setTraderName("Flor (1133631310)");
		p.setTraderPhone("1133631310");
		p.setTotalAmount("2500.00");
		p.setZone("1");
		p.setWeekAmount("196");
		p.setCurrentAmount("1000");
		p.setLastPayday("10/07/2016");
		
		p.setCurrentDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		p.setCustomerAddress("saraza C 1234 / city C");
		
		list.add(p);
		
		p = new BillTicketPojo();
		p.setClientAddress("2Larrea 1055 / B.Vista");
		p.setClientCity("2B.Vista");
		p.setClientCompanyAddress("2Roca 2865 / Kiosco");
		p.setClientName("2MARGARITA TRIPI");
		p.setClientPhone("21133631310");
		p.setCompanyType("2Kiosco");
		p.setCreditNumber("2226256");
		p.setInstallmentAmount("Cuota Imp.: 228.00");
		p.setOverdueDays("DIAS DE ATRASO: 226");
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
		p.setPurchaseDate("Fec Compra: 204/01/2014");
		p.setRemainingAmount("Saldo Actual: 21999.00");
		p.setRemainingDays("228");
		p.setTicketNumber("NRO TCK: 2254352");
		p.setTraderName("2Flor (1133631310)");
		p.setTraderPhone("21133631310");
		p.setTotalAmount("Total Compra: 22500.00");
		
		p.setCurrentDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		p.setZone("3");
		p.setWeekAmount("210");
		p.setCurrentAmount("1500");
		
		p.setCustomerAddress("1saraza C 1234 / 1city C");
		p.setLastPayday("10/07/2016");
		
		list.add(p);
		
		p = new BillTicketPojo();
		p.setClientAddress("3Larrea 1055 / B.Vista");
		p.setClientCity("3B.Vista");
		p.setClientCompanyAddress("3Roca 2865 / Kiosco");
		p.setClientName("3MARGARITA TRIPI");
		p.setClientPhone("31133631310");
		p.setCompanyType("3Kiosco");
		p.setCreditNumber("3226256");
		p.setInstallmentAmount("Cuota Imp.: 228.00");
		p.setOverdueDays("DIAS DE ATRASO: 226");
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
		p.setPurchaseDate("Fec Compra: 204/01/2014");
		p.setRemainingAmount("Saldo Actual: 21999.00");
		p.setRemainingDays("328");
		p.setTicketNumber("NRO TCK: 2254352");
		p.setTraderName("3Flor (1133631310)");
		p.setTraderPhone("31133631310");
		p.setTotalAmount("Total Compra: 21000.00");
		
		p.setZone("5");
		
		p.setCurrentDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		p.setWeekAmount("400");
		p.setCurrentAmount("2100");
		
		p.setCustomerAddress("2saraza C 1234 / 2city C");
		p.setLastPayday("10/07/2016");
		
		list.add(p);
		
		p = new BillTicketPojo();
		p.setClientAddress("4Larrea 1055 / B.Vista");
		p.setClientCity("4B.Vista");
		p.setClientCompanyAddress("4Roca 2865 / Kiosco");
		p.setClientName("4MARGARITA TRIPI");
		p.setClientPhone("41133631310");
		p.setCompanyType("4Kiosco");
		p.setCreditNumber("4226256");
		p.setInstallmentAmount("Cuota Imp.: 228.00");
		p.setOverdueDays("DIAS DE ATRASO: 226");
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
		p.setPurchaseDate("Fec Compra: 204/01/2014");
		p.setRemainingAmount("Saldo Actual: 21999.00");
		p.setRemainingDays("428");
		p.setTicketNumber("NRO TCK: 2254352");
		p.setTraderName("4Flor (1133631310)");
		p.setTraderPhone("41133631310");
		p.setTotalAmount("Total Compra: 22500.00");
		
		p.setCurrentDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		p.setZone("88");
		
		p.setWeekAmount("78");
		p.setCurrentAmount("2900");
		
		p.setCustomerAddress("3saraza C 1234 / 3city C");
		p.setLastPayday("10/07/2016");
		
		list.add(p);

		p = new BillTicketPojo();
		p.setClientAddress("5Larrea 1055 / B.Vista");
		p.setClientCity("5B.Vista");
		p.setClientCompanyAddress("5Roca 2865 / Kiosco");
		p.setClientName("5MARGARITA TRIPI");
		p.setClientPhone("51133631310");
		p.setCompanyType("5Kiosco");
		p.setCreditNumber("5226256");
		p.setInstallmentAmount("Cuota Imp.: 228.00");
		p.setOverdueDays("DIAS DE ATRASO: 226");
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
		p.setPurchaseDate("Fec Compra: 204/01/2014");
		p.setRemainingAmount("Saldo Actual: 21999.00");
		p.setRemainingDays("528");
		p.setTicketNumber("NRO TCK: 2254352");
		p.setTraderName("5Flor (1133631310)");
		p.setTraderPhone("51133631310");
		p.setTotalAmount("Total Compra: 22600.00");
		
		p.setCurrentDate(new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
		
		p.setZone("9");
		
		p.setWeekAmount("123");
		p.setCurrentAmount("1456");
		
		p.setCustomerAddress("4saraza C 1234 / 4city C");
		p.setLastPayday("10/07/2016");
		
		list.add(p);
		
		return list;
	}
}
