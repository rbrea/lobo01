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
			p.setClientAddress(d.getClient().getAddress());
			p.setClientCity(d.getClient().getCity());
			p.setClientCompanyAddress(d.getClient().getCompanyAddress());
			p.setClientName(d.getClient().getName());
			p.setClientPhone(d.getClient().getPhone());
			p.setCompanyType(d.getClient().getCompanyType());
		}
		p.setCreditNumber(String.valueOf(d.getCreditNumber()));
		p.setInstallmentAmount(NumberUtils.toString(d.getTotalDailyInstallment()));
		p.setOverdueDays(String.valueOf(d.getOverdueDays()));
		List<ProductPojo> list = this.productPojoTransformer.transform(d.getBillProducts());
		if(list != null){
			p.setProducts(list);
		}
		p.setPurchaseDate(DateUtils.toDate(d.getStartDate()));
		p.setRemainingAmount(NumberUtils.toString(d.getRemainingAmount()));
		p.setTicketNumber(String.valueOf(d.getId()));
		p.setTotalAmount(NumberUtils.toString(d.getTotalAmount()));
		if(d.getTrader() != null){
			p.setTraderName(d.getTrader().getName());
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
