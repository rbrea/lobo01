package com.icetea.manager.pagodiario.transformer;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.inject.Named;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.CollectorDetailDto;
import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Collector;
import com.icetea.manager.pagodiario.model.Payment;
import com.icetea.manager.pagodiario.utils.NumberUtils;

@Named
public class CollectorDetailTransformer {

	public List<CollectorDetailDto> transform(List<Bill> bills, final Date from, final Date to){
		
		List<CollectorDetailDto> list = Lists.newArrayList();
		
		for(Bill bill : bills){
			final Collector collector = bill.getCollector();
			
			final Long collectorId = collector.getId();

			CollectorDetailDto d = CollectionUtils.find(list, new Predicate<CollectorDetailDto>() {
				@Override
				public boolean evaluate(CollectorDetailDto c) {
					return c.getId().equals(collectorId);
				}
			});
			List<Payment> payments = bill.getPayments();
			
			

			if(d == null){
				d = new CollectorDetailDto();
				d.setId(collector.getId());
				d.setZone(String.valueOf(collector.getZone()));
				d.setName(collector.getDescription());
				list.add(d);
			}
			
			if(StringUtils.isBlank(d.getAmountToCollect())){
				d.setAmountToCollect("0.00");
			}
			
			BigDecimal amountToCollect = BigDecimal.ZERO;
			
			if(payments != null && payments.size() > 0){
				Payment payment = payments.get(0);
				amountToCollect = NumberUtils.multiply(payment.getAmount(), new BigDecimal(7));
			}
			
			d.setAmountToCollect(
					NumberUtils.toString(
							NumberUtils.add(d.getAmountToCollect(), amountToCollect)));

			if(StringUtils.isBlank(d.getAmountCollected())){
				d.setAmountCollected("0.00");
			}
			if(StringUtils.isBlank(d.getRemainingAmount())){
				d.setRemainingAmount("0.00");
			}

			Collection<Payment> paymentList = CollectionUtils.select(payments, new Predicate<Payment>() {
				@Override
				public boolean evaluate(Payment p) {
					return p.getDate().after(from) && p.getDate().before(to);
				}
			});

			for(Payment p : paymentList){
				d.setAmountCollected(NumberUtils.toString(NumberUtils.add(d.getAmountCollected(), p.getAmount())));
			}
			
			d.setRemainingAmount(NumberUtils.toString(
					NumberUtils.subtract(new BigDecimal(d.getAmountToCollect()), d.getAmountCollected())));
			
		}
		
		return list;
	}
	
}
