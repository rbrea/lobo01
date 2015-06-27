package com.icetea.manager.pagodiario.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class AutoCompleteResponseDto extends BasicOutputDto {

	private static final long serialVersionUID = 1L;
	
	private List<String> list = Lists.newArrayList();

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
	public void addElement(String element){
		this.list.add(element);
	}
	
}
