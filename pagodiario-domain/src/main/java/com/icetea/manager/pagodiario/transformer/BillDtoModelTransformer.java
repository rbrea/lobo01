package com.icetea.manager.pagodiario.transformer;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.BooleanUtils;

import com.icetea.manager.pagodiario.api.dto.BillDto;
import com.icetea.manager.pagodiario.api.dto.BillProductDto;
import com.icetea.manager.pagodiario.api.dto.PaydayDto;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.BillProduct;
import com.icetea.manager.pagodiario.service.ConciliationItemService;
import com.icetea.manager.pagodiario.utils.DateUtils;
import com.icetea.manager.pagodiario.utils.NumberUtils;
import com.icetea.manager.pagodiario.utils.StringUtils;

@Named
public class BillDtoModelTransformer extends AbstractDtoModelTransformer<BillDto, Bill> {

	private final BillProductDtoModelTransformer billProductDtoModelTransformer;
	private final ConciliationItemService conciliationItemService;
	
	@Inject
	public BillDtoModelTransformer(
			BillProductDtoModelTransformer billProductDtoModelTransformer,
			ConciliationItemService conciliationItemService) {
		super();
		this.billProductDtoModelTransformer = billProductDtoModelTransformer;
		this.conciliationItemService = conciliationItemService;
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
		
		d.setWeekFriday(BooleanUtils.toBoolean(e.getWeekFriday(), "S", "N"));
		d.setWeekMonday(BooleanUtils.toBoolean(e.getWeekMonday(), "S", "N"));
		d.setWeekSaturday(BooleanUtils.toBoolean(e.getWeekSaturday(), "S", "N"));
		d.setWeekSunday(BooleanUtils.toBoolean(e.getWeekSunday(), "S", "N"));
		d.setWeekThursday(BooleanUtils.toBoolean(e.getWeekThursday(), "S", "N"));
		d.setWeekTuesday(BooleanUtils.toBoolean(e.getWeekTuesday(), "S", "N"));
		d.setWeekWednesday(BooleanUtils.toBoolean(e.getWeekWednesday(), "S", "N"));
		
		Date payrollDate = this.conciliationItemService.searchPayrollDateFromBillId(e.getId());
		d.setPayrollDate(DateUtils.toDate(payrollDate));
		d.setDevTotalMark(e.getDevTotalMark());
		
		return d;
	}
	
	public PaydayDto transformToPayday(Bill bill){
		PaydayDto d = new PaydayDto();
		
		d.setBillId(bill.getId());
		d.setWeekFriday(BooleanUtils.toBoolean(bill.getWeekFriday(), "S", "N"));
		d.setWeekMonday(BooleanUtils.toBoolean(bill.getWeekMonday(), "S", "N"));
		d.setWeekSaturday(BooleanUtils.toBoolean(bill.getWeekSaturday(), "S", "N"));
		d.setWeekSunday(BooleanUtils.toBoolean(bill.getWeekSunday(), "S", "N"));
		d.setWeekThursday(BooleanUtils.toBoolean(bill.getWeekThursday(), "S", "N"));
		d.setWeekTuesday(BooleanUtils.toBoolean(bill.getWeekTuesday(), "S", "N"));
		d.setWeekWednesday(BooleanUtils.toBoolean(bill.getWeekWednesday(), "S", "N"));
		
		return d;
	}

}
