package com.icetea.manager.pagodiario.jasper.factory.sample;

import java.util.Collection;
import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.pojo.jasper.CreditDetailPojo;
import com.icetea.manager.pagodiario.api.pojo.jasper.DevDetailPojo;
import com.icetea.manager.pagodiario.api.pojo.jasper.DiscountDetailPojo;
import com.icetea.manager.pagodiario.api.pojo.jasper.PaymentDetailPojo;
import com.icetea.manager.pagodiario.api.pojo.jasper.ProductDetailPojo;
import com.icetea.manager.pagodiario.api.pojo.jasper.ReductionDetailPojo;

public class CreditDetailFactory {

	public static Collection<CreditDetailPojo> createBeanCollection(){
		List<CreditDetailPojo> list = Lists.newArrayList();
		CreditDetailPojo p = new CreditDetailPojo();		
		p.setClientAddress("Pereyra (PA) 179 Lomas Particular");
		p.setClientName("MARTA VIOLANTE");
		p.setCreditAmount("0.00");
		p.setCreditDate("10/09/2014");
		p.setCreditNumber("DETALLE CREDITO 262670");
		List<DevDetailPojo> devs = Lists.newArrayList();
		DevDetailPojo d = new DevDetailPojo();
		d.setAmount("480.00");
		d.setDevDate("17/09/2014");
		d.setInstallmentAmount("5.00");
		d.setProduct("toalla");
		devs.add(d);
		d = new DevDetailPojo();
		d.setAmount("480.00");
		d.setDevDate("17/09/2014");
		d.setInstallmentAmount("5.00");
		d.setProduct("toalla");
		devs.add(d);
		d = new DevDetailPojo();
		d.setAmount("480.00");
		d.setDevDate("17/09/2014");
		d.setInstallmentAmount("5.00");
		d.setProduct("toalla");
		devs.add(d);
		d = new DevDetailPojo();
		d.setAmount("360.00");
		d.setDevDate("17/09/2014");
		d.setInstallmentAmount("4.00");
		d.setProduct("almohadas");
		devs.add(d);
		p.setDevs(devs);
		p.setFirstInstallmentAmount("16.00");
		p.setInstallmentAmount("0.00");
		List<PaymentDetailPojo> payments = Lists.newArrayList();
		PaymentDetailPojo y = new PaymentDetailPojo();
		y.setAmount("0.00");
		y.setCollector("Jorge");
		y.setPaymentDate("12/09/2014");
		payments.add(y);
		y = new PaymentDetailPojo();
		y.setAmount("0.00");
		y.setCollector("Jorge");
		y.setPaymentDate("12/09/2014");
		payments.add(y);
		p.setPayments(payments);
		p.setTotalAmount("0.00");
		p.setTraderName("Flor");
		
		ProductDetailPojo product = new ProductDetailPojo();
		product.setCount("2");
		product.setCode("P001");
		product.setDescription("Producto de Test 01");
		product.setInstallmentAmount("80.00");
		product.setTotalAmount("1600.00");
		
		p.getProducts().add(product);
		
		DiscountDetailPojo discount = new DiscountDetailPojo();
		discount.setDate("10/08/2015");
		discount.setAmount("200.60");
		
		p.getDiscounts().add(discount);
		
		ReductionDetailPojo reduction = new ReductionDetailPojo();
		reduction.setAmount("180.99");
		reduction.setDate("15/09/2015");
		
		p.getReductions().add(reduction);
		
		list.add(p);
		
		return list;
	}
	
}
