package com.icetea.manager.pagodiario.transformer.jasper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.pojo.jasper.VoucherPojo;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;
import com.icetea.manager.pagodiario.utils.StringUtils;

@Named
public class BillToVoucherTransformer {

	public VoucherPojo transform(final BillDto bill, final Date date){
		VoucherPojo v = new VoucherPojo();
		v.setVoucherId("V" + bill.getCreditNumber());
		v.setTraderData(bill.getTraderName() + "(" + bill.getTraderPhone() + ")");
		v.setClientName(StringUtils.upperCase(bill.getClientName()));
		v.setClientData(bill.getClientAddress() + "(" + bill.getClientCompanyType() + ")");
		v.setClientDocumentNumber(bill.getClientDocumentNumber());
		
		BigDecimal installmentAmount = NumberUtils.toBigDecimal(bill.getTotalDailyInstallment());
		
		BigDecimal discountAmount = NumberUtils.multiply(installmentAmount, new BigDecimal(7));
		
		v.setDiscountAmount("$ " + NumberUtils.toString(discountAmount));
		v.setInstallmentAmount("$ " + bill.getTotalDailyInstallment());
		v.setExpirationDate(DateUtils.toDate(DateUtils.addDays(date, 15)));
		
		v.setZone((bill.getCollectorId() != null) ? bill.getCollectorId().toString() : StringUtils.EMPTY);
		
		return v;
	}

	public List<VoucherPojo> transform(List<BillDto> bills, Date date){
		List<VoucherPojo> list = Lists.newArrayList();
		
		for(BillDto bill : bills){
			list.add(this.transform(bill, date));
		}
		
		return list;
	}
	
}
