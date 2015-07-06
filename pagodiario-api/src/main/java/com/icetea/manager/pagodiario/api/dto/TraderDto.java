package com.icetea.manager.pagodiario.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class TraderDto extends PersonDto {

	private static final long serialVersionUID = 1L;

	private boolean isSupervisor = false;
	private Long traderId;
	private List<Long> listOfTraderIds = Lists.newArrayList();

	public boolean isSupervisor() {
		return isSupervisor;
	}
	public void setSupervisor(boolean isSupervisor) {
		this.isSupervisor = isSupervisor;
	}
	public Long getTraderId() {
		return traderId;
	}
	public void setTraderId(Long traderId) {
		this.traderId = traderId;
	}
	public List<Long> getListOfTraderIds() {
		return listOfTraderIds;
	}
	public void setListOfTraderIds(List<Long> listOfTraderIds) {
		this.listOfTraderIds = listOfTraderIds;
	}

}
