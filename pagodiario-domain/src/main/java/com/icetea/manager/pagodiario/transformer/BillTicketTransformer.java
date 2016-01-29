package com.icetea.manager.pagodiario.transformer;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.pojo.jasper.BillTicketPojo;
import com.icetea.manager.pagodiario.api.pojo.jasper.ProductPojo;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;
import com.icetea.manager.pagodiario.utils.StringUtils;

@Named
public class BillTicketTransformer {
	
	private final ProductPojoTransformer productPojoTransformer;
	
	@Inject
	public BillTicketTransformer(ProductPojoTransformer productPojoTransformer) {
		super();
		this.productPojoTransformer = productPojoTransformer;
	}

	public BillTicketPojo transform(Bill d){
		BillTicketPojo p = new BillTicketPojo();
		
		if(d.getClient() != null){
			String address = "";
//			if(StringUtils.isNotBlank(d.getClient().getAddress())){
//				address += d.getClient().getAddress();
//				if(StringUtils.isNotBlank(d.getClient().getCity())){
//					address += " / " + d.getClient().getCity();
//				}
//			}
			if(StringUtils.isNotBlank(d.getClient().getCompanyType())){
				address = d.getClient().getCompanyType(); 
			} else {
				address = " - "; 
			}
			p.setClientAddress(address);
			p.setClientCity(d.getClient().getCity());
			String companyAddress = this.buildCompanyAddress(d);
			p.setClientCompanyAddress(companyAddress);
			p.setClientName(d.getClient().getName());
			p.setClientPhone((StringUtils.isNotBlank(d.getClient().getCompanyPhone())) ? d.getClient().getCompanyPhone() : " - ");
			p.setCompanyType(d.getClient().getCompanyType());
		}
		String creditNumber = "";
		if(d.getCreditNumber() != null){
			creditNumber = "*" + String.valueOf(d.getCreditNumber()) + "*";
			p.setCreditNumber(creditNumber);
		}
		String installment = "";
		if(d.getTotalDailyInstallment() != null){
			installment = NumberUtils.toString(d.getTotalDailyInstallment());
		}
		p.setInstallmentAmount(installment);
		p.setOverdueDays("DÍAS DE ATRASO: " + String.valueOf(d.getOverdueDays()));
		List<ProductPojo> list = this.productPojoTransformer.transform(d.getBillProducts());
		if(list != null){
			p.setProducts(list);
		}
		String purchaseDate = "";
		if(d.getStartDate() != null){
			purchaseDate = DateUtils.toDate(d.getStartDate());
		}
		p.setPurchaseDate(purchaseDate);
		String remainingAmount = "";
		if(d.getRemainingAmount() != null){
			remainingAmount = NumberUtils.toString(d.getRemainingAmount());
		}
		p.setRemainingAmount(remainingAmount);
		String ticketNumber = "";
		if(d.getId() != null){
			ticketNumber = "NRO TCK: " + String.valueOf(d.getId());
		}
		p.setTicketNumber(ticketNumber);
		String totalAmount = "";
		if(d.getTotalAmount() != null){
			totalAmount = NumberUtils.toString(d.getTotalAmount());
		}
		p.setTotalAmount(totalAmount);
		if(d.getTrader() != null){
			String traderName = StringUtils.emptyWhenNull(d.getTrader().getName());
			if(StringUtils.isNotBlank(traderName) 
					&& StringUtils.isNotBlank(d.getTrader().getPhone())){
				traderName += "(" + d.getTrader().getPhone() + ")";
			}
			p.setTraderName(traderName);
			p.setTraderPhone(d.getTrader().getPhone());
		}
		p.setRemainingDays("DR: " + String.valueOf(d.remainingDays()));
		if(d.getCollector() != null){
			p.setZone(String.valueOf(d.getCollector().getZone()));
		}
		
		return p;
	}

	private String buildCompanyAddress(Bill d) {
		String companyAddress = "";
		if(StringUtils.isNotBlank(d.getClient().getCompanyAddress())){
			companyAddress = d.getClient().getCompanyAddress();
			if(StringUtils.isNotBlank(d.getClient().getCompanyCity())){
				companyAddress += " / " + d.getClient().getCompanyCity();
			} else {
				companyAddress += " / " + " - ";
			}
		}
		return companyAddress;
	}
	
	public BillTicketPojo transform(BillTicketPojo p, Bill d){
		if(d.getClient() != null){
			String address = "";
//			if(StringUtils.isNotBlank(d.getClient().getAddress())){
//				address += d.getClient().getAddress();
//				if(StringUtils.isNotBlank(d.getClient().getCity())){
//					address += " / " + d.getClient().getCity();
//				}
//			}
			if(StringUtils.isNotBlank(d.getClient().getCompanyType())){
				address = d.getClient().getCompanyType(); 
			} else {
				address = " - "; 
			}
			
			
			p.setClientAddress2(address);
			p.setClientCity2(d.getClient().getCity());
			String companyAddress = this.buildCompanyAddress(d);
			p.setClientCompanyAddress2(companyAddress);
			p.setClientName2(d.getClient().getName());
			p.setClientPhone2((StringUtils.isNotBlank(d.getClient().getCompanyPhone())) ? d.getClient().getCompanyPhone() : " - ");
			p.setCompanyType2(d.getClient().getCompanyType());
		}
		if(d.getCreditNumber() != null){
			String creditNumber = "*" + String.valueOf(d.getCreditNumber()) + "*";
			p.setCreditNumber2(creditNumber);
		}
		String installment = "";
		if(d.getTotalDailyInstallment() != null){
			installment = NumberUtils.toString(d.getTotalDailyInstallment());
		}
		p.setInstallmentAmount2(installment);
		p.setOverdueDays2("DÍAS DE ATRASO: " + String.valueOf(d.getOverdueDays()));
		List<ProductPojo> list = this.productPojoTransformer.transform(d.getBillProducts());
		if(list != null){
			p.setProducts2(list);
		}
		String purchaseDate = "";
		if(d.getStartDate() != null){
			purchaseDate = DateUtils.toDate(d.getStartDate());
		}
		p.setPurchaseDate2(purchaseDate);
		String remainingAmount = "";
		if(d.getRemainingAmount() != null){
			remainingAmount = NumberUtils.toString(d.getRemainingAmount());
		}
		p.setRemainingAmount2(remainingAmount);
		
		if(d.getId() != null){
			String ticketNumber = "NRO TCK: " + String.valueOf(d.getId());
			p.setTicketNumber2(ticketNumber);
		}
		String totalAmount = "";
		if(d.getTotalAmount() != null){
			totalAmount = NumberUtils.toString(d.getTotalAmount());
		}
		p.setTotalAmount2(totalAmount);
		if(d.getTrader() != null){
			String traderName = StringUtils.emptyWhenNull(d.getTrader().getName());
			if(StringUtils.isNotBlank(traderName) 
					&& StringUtils.isNotBlank(d.getTrader().getPhone())){
				traderName += "(" + d.getTrader().getPhone() + ")";
			}
			p.setTraderName2(traderName);
			p.setTraderPhone2(d.getTrader().getPhone());
		}
		p.setRemainingDays2("DR: " + String.valueOf(d.remainingDays()));
		if(d.getCollector() != null){
			p.setZone2(String.valueOf(d.getCollector().getZone()));
		}
		
		return p;
	}
	
	public List<BillTicketPojo> transform(String ticketDateValue, List<Bill> list){
		if(list == null){
			return null;
		}
		List<BillTicketPojo> r = Lists.newArrayList();
		boolean change = true;
		BillTicketPojo billTicket = null;
		for(Bill b : list){
			if(change){
				billTicket = this.transform(b);
				billTicket.setCurrentDate(ticketDateValue);
				r.add(billTicket);
				change = false;
			} else {
				this.transform(billTicket, b);
				change = true;
			}
		}
		
		return r;
	}
	
}
