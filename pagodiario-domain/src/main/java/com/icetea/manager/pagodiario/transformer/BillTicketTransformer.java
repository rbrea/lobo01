package com.icetea.manager.pagodiario.transformer;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.pojo.jasper.BillTicketPojo;
import com.icetea.manager.pagodiario.api.pojo.jasper.ProductPojo;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Payment;
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

	public BillTicketPojo transform(Bill bill){
		BillTicketPojo p = new BillTicketPojo();
		
		if(bill.getClient() != null){
			String address = "";
//			if(StringUtils.isNotBlank(d.getClient().getAddress())){
//				address += d.getClient().getAddress();
//				if(StringUtils.isNotBlank(d.getClient().getCity())){
//					address += " / " + d.getClient().getCity();
//				}
//			}
			if(StringUtils.isNotBlank(bill.getClient().getCompanyType())){
				address = bill.getClient().getCompanyType(); 
			} else {
				address = " - "; 
			}
			p.setClientAddress(address);
			p.setClientCity(bill.getClient().getCity());
			String companyAddress = this.buildAddress(bill.getClient().getCompanyAddress(), bill.getClient().getCompanyCity()); //this.buildCompanyAddress(d);
			p.setClientCompanyAddress(companyAddress);
			p.setClientName(bill.getClient().getName());
			p.setClientPhone((StringUtils.isNotBlank(bill.getClient().getCompanyPhone())) ? bill.getClient().getCompanyPhone() : " - ");
			p.setCompanyType(bill.getClient().getCompanyType());
		}
		String creditNumber = "";
		if(bill.getCreditNumber() != null){
			creditNumber = "*" + String.valueOf(bill.getCreditNumber()) + "*";
			p.setCreditNumber(creditNumber);
		}
		String installment = "";
		if(bill.getTotalDailyInstallment() != null){
			installment = NumberUtils.toString(bill.getTotalDailyInstallment());
			
			String weekAmount = NumberUtils.toString(
					NumberUtils.multiply(
							bill.getTotalDailyInstallment(), NumberUtils.toBigDecimal("7")));
			p.setWeekAmount(weekAmount);
		}
		p.setInstallmentAmount(installment);
		p.setOverdueDays("DÍAS DE ATRASO: " + String.valueOf(bill.getOverdueDays()));
		List<ProductPojo> list = this.productPojoTransformer.transform(bill.getBillProducts());
		if(list != null){
			p.setProducts(list);
		}
		String purchaseDate = "";
		if(bill.getStartDate() != null){
			purchaseDate = DateUtils.toDate(bill.getStartDate());
		}
		p.setPurchaseDate(purchaseDate);
		String remainingAmount = "";
		if(bill.getRemainingAmount() != null){
			remainingAmount = NumberUtils.toString(bill.getRemainingAmount());
		}
		p.setRemainingAmount(remainingAmount);
		String ticketNumber = "";
		if(bill.getId() != null){
			ticketNumber = "NRO TCK: " + String.valueOf(bill.getId());
		}
		p.setTicketNumber(ticketNumber);
		String totalAmount = "";
		if(bill.getTotalAmount() != null){
			totalAmount = NumberUtils.toString(bill.getTotalAmount());
		}
		p.setTotalAmount(totalAmount);
		if(bill.getTrader() != null){
			String traderName = StringUtils.emptyWhenNull(bill.getTrader().getName());
			if(StringUtils.isNotBlank(traderName) 
					&& StringUtils.isNotBlank(bill.getTrader().getPhone())){
				traderName += "(" + bill.getTrader().getPhone() + ")";
			}
			p.setTraderName(traderName);
			p.setTraderPhone(bill.getTrader().getPhone());
		}
		p.setRemainingDays("DR: " + String.valueOf(bill.remainingDays()));
		if(bill.getCollector() != null){
			p.setZone(String.valueOf(bill.getCollector().getZone()));
		}
		
		BigDecimal currentAmount = NumberUtils.subtract(totalAmount, remainingAmount);
		p.setCurrentAmount(NumberUtils.toString(currentAmount));
		
		p.setCustomerAddress(this.buildAddress(bill.getClient().getAddress(), bill.getClient().getCity()));
		
		List<Payment> payments = bill.getPayments();
		if(payments != null){
			Payment max = null;
			for (int i = 0; i < payments.size(); i++) {
				Payment payment = payments.get(i);
				if(max == null){
					max = payment;
				} else {
					if(max.getDate().before(payment.getDate())){
						max = payment;
					}
				}
			}
			p.setLastPayday((max != null) ? DateUtils.toDate(max.getDate()) : " - ");
		}
		
		return p;
	}

	@SuppressWarnings("unused")
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
	
	private String buildAddress(String address, String city) {
		String r = "";
		if(StringUtils.isNotBlank(address)){
			r = address;
			if(StringUtils.isNotBlank(city)){
				r += " / " + city;
			} else {
				r += " / " + " - ";
			}
		}
		return r;
	}
	
	public List<BillTicketPojo> transform(String ticketDateValue, List<Bill> list){
		if(list == null){
			return null;
		}
		List<BillTicketPojo> r = Lists.newArrayList();
		BillTicketPojo billTicket = null;
		for(Bill b : list){
			billTicket = this.transform(b);
			billTicket.setCurrentDate(ticketDateValue);
			r.add(billTicket);
		}
		
		return r;
	}
	
}
