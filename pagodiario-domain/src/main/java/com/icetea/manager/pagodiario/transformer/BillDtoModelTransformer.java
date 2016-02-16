package com.icetea.manager.pagodiario.transformer;

import javax.inject.Inject;
import javax.inject.Named;

import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.BillProductDto;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.BillProduct;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;
import com.icetea.manager.pagodiario.utils.StringUtils;

@Named
public class BillDtoModelTransformer extends AbstractDtoModelTransformer<BillDto, Bill> {

	private final BillProductDtoModelTransformer billProductDtoModelTransformer;
	
	@Inject
	public BillDtoModelTransformer(
			BillProductDtoModelTransformer billProductDtoModelTransformer) {
		super();
		this.billProductDtoModelTransformer = billProductDtoModelTransformer;
	}

	@Override
	protected BillDto doTransform(Bill e, int depth) {
		BillDto d = new BillDto();
		d.setClientId(e.getClient().getId());
		d.setCollectorId(e.getCollector().getZone());
		d.setCollectorZone(e.getCollector().getId());
		d.setCollectorDescription(e.getCollector().getDescription());
		d.setCreditNumber(e.getCreditNumber());
		d.setEndDate(DateUtils.toDate(e.getEndDate(), "dd/MM/yyyy"));
		d.setId(e.getId());
		d.setOverdueDays(e.getOverdueDays());
		d.setRemainingAmount(NumberUtils.toString(e.getRemainingAmount()));
		d.setStartDate(DateUtils.toDate(e.getStartDate(), "dd/MM/yyyy"));
		d.setStatus(e.getStatus().name());
		d.setTotalAmount(NumberUtils.toString(e.getTotalAmount()));
		d.setTotalDailyInstallment(NumberUtils.toString(e.getTotalDailyInstallment()));
		d.setTraderId(e.getTrader().getId());
		
		for(BillProduct bp : e.getBillProducts()){
			BillProductDto bpd = this.billProductDtoModelTransformer.transform(bp);
			d.getBillProducts().add(bpd);
		}
		d.setWeekOfYear(e.getWeekOfYear());
		d.setMonth(e.getMonth());
		d.setYear(e.getYear());
		
		d.setTraderName(StringUtils.emptyWhenNull(e.getTrader().getName()));
		d.setTraderPhone(StringUtils.emptyWhenNull(e.getTrader().getPhone()));
		d.setClientName(StringUtils.emptyWhenNull(e.getClient().getName()));
		d.setClientAddress(StringUtils.emptyWhenNull(e.getClient().getCompanyAddress()));
		d.setClientCompanyType(StringUtils.emptyWhenNull(e.getClient().getCompanyType()));
		d.setClientDocumentNumber((e.getClient().getDocumentNumber() != null) 
				? e.getClient().getDocumentNumber().toString() : StringUtils.EMPTY);
		d.setWeekFriday(e.isWeekFriday());
		d.setWeekMonday(e.isWeekMonday());
		d.setWeekSaturday(e.isWeekSaturday());
		d.setWeekSunday(e.isWeekSunday());
		d.setWeekThursday(e.isWeekThursday());
		d.setWeekTuesday(e.isWeekTuesday());
		d.setWeekWednesday(e.isWeekWednesday());
		
		return d;
	}

}
