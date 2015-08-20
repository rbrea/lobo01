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
			if(StringUtils.isNotBlank(d.getClient().getAddress())){
				address += d.getClient().getAddress();
				if(StringUtils.isNotBlank(d.getClient().getCity())){
					address += " / " + d.getClient().getCity();
				}
			}
			p.setClientAddress(address);
			p.setClientCity(d.getClient().getCity());
			String companyAddress = "";
			if(StringUtils.isNotBlank(d.getClient().getCompanyAddress())){
				companyAddress = d.getClient().getCompanyAddress();
				if(StringUtils.isNotBlank(d.getClient().getCompanyType())){
					companyAddress += " / " + d.getClient().getCompanyType(); 
				}
			}
			p.setClientCompanyAddress(companyAddress);
			p.setClientName(d.getClient().getName());
			p.setClientPhone(d.getClient().getPhone());
			p.setCompanyType(d.getClient().getCompanyType());
		}
		p.setCreditNumber(String.valueOf(d.getCreditNumber()));
		String installment = "";
		if(d.getTotalDailyInstallment() != null){
			installment = "Cuota Imp   $" + NumberUtils.toString(d.getTotalDailyInstallment());
		}
		p.setInstallmentAmount(installment);
		p.setOverdueDays(String.valueOf(d.getOverdueDays()));
		List<ProductPojo> list = this.productPojoTransformer.transform(d.getBillProducts());
		if(list != null){
			p.setProducts(list);
		}
		String purchaseDate = "";
		if(d.getStartDate() != null){
			purchaseDate = "Fec Compra   " + DateUtils.toDate(d.getStartDate());
		}
		p.setPurchaseDate(purchaseDate);
		String remainingAmount = "";
		if(d.getRemainingAmount() != null){
			remainingAmount = "Saldo Actual   $" + NumberUtils.toString(d.getRemainingAmount());
		}
		p.setRemainingAmount(remainingAmount);
		p.setTicketNumber(String.valueOf(d.getId()));
		String totalAmount = "";
		if(d.getTotalAmount() != null){
			totalAmount = "Total Compra   $" + NumberUtils.toString(d.getTotalAmount());
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
		
		return p;
	}
	
	public BillTicketPojo transform(BillTicketPojo p, Bill d){
		if(d.getClient() != null){
			p.setClientAddress2(d.getClient().getAddress());
			p.setClientCity2(d.getClient().getCity());
			p.setClientCompanyAddress2(d.getClient().getCompanyAddress());
			p.setClientName2(d.getClient().getName());
			p.setClientPhone2(d.getClient().getPhone());
			p.setCompanyType2(d.getClient().getCompanyType());
		}
		p.setCreditNumber2(String.valueOf(d.getCreditNumber()));
		p.setInstallmentAmount2(NumberUtils.toString(d.getTotalDailyInstallment()));
		p.setOverdueDays2(String.valueOf(d.getOverdueDays()));
		List<ProductPojo> list = this.productPojoTransformer.transform(d.getBillProducts());
		if(list != null){
			p.setProducts2(list);
		}
		p.setPurchaseDate2(DateUtils.toDate(d.getStartDate()));
		p.setRemainingAmount2(NumberUtils.toString(d.getRemainingAmount()));
		p.setTicketNumber2(String.valueOf(d.getId()));
		p.setTotalAmount2(NumberUtils.toString(d.getTotalAmount()));
		if(d.getTrader() != null){
			p.setTraderName2(d.getTrader().getName());
			p.setTraderPhone2(d.getTrader().getPhone());
		}
		
		return p;
	}
	
	public List<BillTicketPojo> transform(List<Bill> list){
		if(list == null){
			return null;
		}
		List<BillTicketPojo> r = Lists.newArrayList();
		boolean change = true;
		BillTicketPojo billTicket = null;
		for(Bill b : list){
			if(change){
				billTicket = this.transform(b);
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
