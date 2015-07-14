package com.icetea.manager.pagodiario.api.dto.exception;

import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.BasicOutputDto;

public class FormErrorsOutputDto extends BasicOutputDto {

	private static final long serialVersionUID = 1L;

	private List<FormErrorDto> formErrorList = Lists.newArrayList();

	public FormErrorsOutputDto() {
		super();
	}

	public FormErrorsOutputDto(Integer status, String message, String cause) {
		super(status, message, cause);
	}

	public FormErrorsOutputDto(Integer status) {
		super(status);
	}

	public FormErrorsOutputDto(List<FormErrorDto> formErrorList) {
		super();
		this.formErrorList = formErrorList;
	}

	public List<FormErrorDto> getFormErrorList() {
		return formErrorList;
	}

	public void setFormErrorList(List<FormErrorDto> formErrorList) {
		this.formErrorList = formErrorList;
	}
	
}
