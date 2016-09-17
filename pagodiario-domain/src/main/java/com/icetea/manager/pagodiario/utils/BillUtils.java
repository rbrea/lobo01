package com.icetea.manager.pagodiario.utils;

import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Named;

import com.icetea.manager.pagodiario.model.Bill;
import com.icetea.manager.pagodiario.model.Bill.Status;

@Named
public class BillUtils {

	public boolean doBillCancelation(final Bill bill){
		if(bill.getRemainingAmount().compareTo(BigDecimal.ZERO) <= 0){
			bill.setStatus(Status.CANCELED);
			Date now = DateUtils.now();
			bill.setCompletedDate(now);
			bill.getClient().setCancelationMark(now);
		}
		
		return true;
	}

}
