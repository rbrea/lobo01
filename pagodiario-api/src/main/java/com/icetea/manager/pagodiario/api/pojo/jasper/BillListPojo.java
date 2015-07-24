package com.icetea.manager.pagodiario.api.pojo.jasper;

import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class BillListPojo extends BasicDto {

	private static final long serialVersionUID = 1L;

	private List<BillPojo> bills = Lists.newArrayList();

	public List<BillPojo> getBills() {
		return bills;
	}

	public void setBills(List<BillPojo> bills) {
		this.bills = bills;
	}
	
}
