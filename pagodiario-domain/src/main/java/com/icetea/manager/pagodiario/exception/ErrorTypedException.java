package com.icetea.manager.pagodiario.exception;

import java.util.List;

import com.google.common.collect.Lists;
import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;
import com.icetea.manager.pagodiario.api.dto.exception.FormErrorDto;

public class ErrorTypedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final ErrorType errorType;
	private final List<FormErrorDto> formErrors = Lists.newArrayList();

	public ErrorTypedException(ErrorType errorType) {
		super();
		this.errorType = errorType;
	}
	
	public ErrorTypedException(List<FormErrorDto> formErrors) {
		super();
		this.errorType = ErrorType.VALIDATION_ERRORS;
		this.formErrors.addAll(formErrors);
	}

    public ErrorTypedException(FormErrorDto formErrors) {
        this(Lists.newArrayList(formErrors));
    }

	public ErrorTypedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace,
			ErrorType errorType) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errorType = errorType;
	}

	public ErrorTypedException(String message, Throwable cause,
			ErrorType errorType) {
		super(message, cause);
		this.errorType = errorType;
	}

	public ErrorTypedException(String message,
			ErrorType errorType) {
		super(message);
		this.errorType = errorType;
	}

	public ErrorTypedException(Throwable cause,
			ErrorType errorType) {
		super(cause);
		this.errorType = errorType;
	}
	
	public ErrorTypedException(String message,
			ErrorType errorType, FormErrorDto formError) {
		super(message);
		this.errorType = errorType;
		this.formErrors.add(formError);
	}
	
	public ErrorTypedException(String message) {
		super(message);
		this.errorType = ErrorType.UNKNOWN_ERROR;
	}

	public ErrorType getErrorType() {
		return this.errorType;
	}

	public List<FormErrorDto> getFormErrors() {
		return this.formErrors;
	}
	
	public void addFormError(FormErrorDto formErrorDto){
		if(formErrorDto != null){
			this.formErrors.add(formErrorDto);
		}
	}

}
