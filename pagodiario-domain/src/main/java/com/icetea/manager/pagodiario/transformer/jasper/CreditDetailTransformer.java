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
		
//		if(d.getDevolutions() == null || d.getDevolutions().isEmpty()){
//			DevDetailPojo dev = new DevDetailPojo();
//			dev.setAmount("");
//			dev.setDevDate("");
//			dev.setInstallmentAmount("");
//			dev.setProduct("");
//			
//			c.getDevs().add(dev);
//		} else {
			for (BillDetailDevolutionDto devolution : d.getDevolutions()) {
				DevDetailPojo dev = new DevDetailPojo();
				dev.setAmount(devolution.getAmount());
				dev.setDevDate(devolution.getDate());
				dev.setInstallmentAmount(devolution.getInstallmentAmount());
				dev.setProduct(devolution.getProductDescription());
				
				c.getDevs().add(dev);
			}
//		}
		
//		if(d.getDiscounts() == null || d.getDiscounts().isEmpty()){
//			DiscountDetailPojo dis = new DiscountDetailPojo();
//			dis.setAmount("");
//			dis.setDate("");
//			
//			c.getDiscounts().add(dis);
//		} else {
			for (BillDetailDiscountDto discount : d.getDiscounts()) {
				DiscountDetailPojo dis = new DiscountDetailPojo();
				dis.setAmount(discount.getAmount());
				dis.setDate(discount.getDate());
				
				c.getDiscounts().add(dis);
			}
//		}
		
		c.setFirstInstallmentAmount(d.getFirstInstallmentAmount());
		c.setInstallmentAmount(d.getInstallmentAmount());
		
//		if(d.getPayments() == null || d.getPayments().isEmpty()){
//			PaymentDetailPojo pa = new PaymentDetailPojo();
//			pa.setAmount("");
//			pa.setCollector("");
//			pa.setPaymentDate("");
//			
//			c.getPayments().add(pa);
//		} else {
			for (BillDetailPaymentDto p : d.getPayments()) {
				PaymentDetailPojo pa = new PaymentDetailPojo();
				pa.setAmount(p.getAmount());
				pa.setCollector(p.getCollector());
				pa.setPaymentDate(p.getDate());
				pa.setTraderPayment(p.getTraderPayment());
				
				c.getPayments().add(pa);
			}
//		}
		
		
//		if(d.getProducts() == null || d.getProducts().isEmpty()){
//			ProductDetailPojo p = new ProductDetailPojo();
//			p.setCode("");
//			p.setCount("");
//			p.setDescription("");
//			p.setInstallmentAmount("");
//			p.setTotalAmount("");
//			
//			c.getProducts().add(p);
//		} else {
			for (BillProductDetailDto product : d.getProducts()) {
				ProductDetailPojo p = new ProductDetailPojo();
				p.setCode(product.getCodProducto());
				p.setCount(product.getCount());
				p.setDescription(product.getDescription());
				p.setInstallmentAmount(product.getInstallmentAmount());
				p.setTotalAmount(product.getTotalAmount());
				
				c.getProducts().add(p);
			}
//		}
		
//		if(d.getReductions() == null || d.getReductions().isEmpty()){
//			ReductionDetailPojo r = new ReductionDetailPojo();
//			r.setAmount("");
//			r.setDate("");
//			
//			c.getReductions().add(r);
//		} else {
			for (BillDetailReductionDto red : d.getReductions()) {
				ReductionDetailPojo r = new ReductionDetailPojo();
				r.setAmount(red.getAmount());
				r.setDate(red.getDate());
				
				c.getReductions().add(r);
			}
//		}
		
		c.setTotalAmount(d.getCreditAmount());
		c.setTraderName(d.getTraderName());
		
		return c;
	}
	
}
