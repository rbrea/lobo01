package com.icetea.manager.pagodiario.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class ListOutputDto<T extends BasicDto> extends BasicOutputDto {

	private static final long serialVersionUID = 1L;
	
	private List<T> data = Lists.newArrayList();

	public ListOutputDto() {
		super();
	}

	public ListOutputDto(Integer status, String message, String cause) {
		super(status, message, cause);
	}

	public ListOutputDto(Integer status) {
		super(status);
	}

	public void add(T t){
		if(t != null){
			this.data.add(t);
		}
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
	public void addAll(List<T> list){
		if(list != null){
			this.data.addAll(list);
		}
	}
	
}
