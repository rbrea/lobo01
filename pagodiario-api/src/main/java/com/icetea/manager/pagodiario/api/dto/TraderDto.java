package com.icetea.manager.pagodiario.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class TraderDto extends PersonDto {

	private static final long serialVersionUID = 1L;

	private boolean supervisor = false;
	private Long parentId;
	private String parentDescription;
	private List<Long> listOfTraderIds = Lists.newArrayList();

	public List<Long> getListOfTraderIds() {
		return listOfTraderIds;
	}
	public void setListOfTraderIds(List<Long> listOfTraderIds) {
		this.listOfTraderIds = listOfTraderIds;
	}
	public boolean isSupervisor() {
		return supervisor;
	}
	public void setSupervisor(boolean supervisor) {
		this.supervisor = supervisor;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getParentDescription() {
		return parentDescription;
	}
	public void setParentDescription(String parentDescription) {
		this.parentDescription = parentDescription;
	}

}
