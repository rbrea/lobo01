package com.icetea.manager.pagodiario.transformer.jasper;

import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.BillDetailDevolutionDto;
import com.icetea.manager.pagodiario.api.dto.BillDetailDiscountDto;
import com.icetea.manager.pagodiario.api.dto.BillDetailDto;
import com.icetea.manager.pagodiario.api.dto.BillDetailPaymentDto;
import com.icetea.manager.pagodiario.api.dto.BillDetailReductionDto;
import com.icetea.manager.pagodiario.api.dto.BillProductDetailDto;
import com.icetea.manager.pagodiario.api.pojo.jasper.CreditDetailPojo;
import com.icetea.manager.pagodiario.api.pojo.jasper.DevDetailPojo;
import com.icetea.manager.pagodiario.api.pojo.jasper.DiscountDetailPojo;
import com.icetea.manager.pagodiario.api.pojo.jasper.PaymentDetailPojo;
import com.icetea.manager.pagodiario.api.pojo.jasper.ProductDetailPojo;
import com.icetea.manager.pagodiario.api.pojo.jasper.ReductionDetailPojo;

@Named
public class CreditDetailTransformer {

	public CreditDetailPojo transform(BillDetailDto d){
		CreditDetailPojo c = new CreditDetailPojo();
		
		if(d == null){
			return c;
		}
		
		c.setClientAddress(d.getClientAddress());
		c.setClientName(d.getClientName());
		c.setCreditAmount(d.getCreditAmount());
		c.setCreditDate(d.getCreditDate());
		c.setCreditNumber(d.getCreditNumber());
		
		for (BillDetailDevolutionDto devolution : d.getDevolutions()) {
			DevDetailPojo dev = new DevDetailPojo();
			dev.setAmount(devolution.getAmount());
			dev.setDevDate(devolution.getDate());
			dev.setInstallmentAmount(devolution.getInstallmentAmount());
			dev.setProduct(devolution.getProductDescription());
			
			c.getDevs().add(dev);
		}
		
		for (BillDetailDiscountDto discount : d.getDiscounts()) {
			DiscountDetailPojo dis = new DiscountDetailPojo();
			dis.setAmount(discount.getAmount());
			dis.setDate(discount.getDate());
			
			c.getDiscounts().add(dis);
		}
		
		c.setFirstInstallmentAmount(d.getFirstInstallmentAmount());
		c.setInstallmentAmount(d.getInstallmentAmount());
		
		for (BillDetailPaymentDto p : d.getPayments()) {
			PaymentDetailPojo pa = new PaymentDetailPojo();
			pa.setAmount(p.getAmount());
			pa.setCollector(p.getCollector());
			pa.setPaymentDate(p.getDate());
			
			c.getPayments().add(pa);
		}
		
		for (BillProductDetailDto product : d.getProducts()) {
			ProductDetailPojo p = new ProductDetailPojo();
			p.setCode(product.getCodProducto());
			p.setCount(product.getCount());
			p.setDescription(product.getDescription());
			p.setInstallmentAmount(product.getInstallmentAmount());
			p.setTotalAmount(p.getTotalAmount());
			
			c.getProducts().add(p);
		}
		
		for (BillDetailReductionDto red : d.getReductions()) {
			ReductionDetailPojo r = new ReductionDetailPojo();
			r.setAmount(red.getAmount());
			r.setDate(red.getDate());
			
			c.getReductions().add(r);
		}
		
		c.setTotalAmount(d.getCreditAmount());
		c.setTraderName(d.getTraderName());
		
		return c;
	}
	
}
