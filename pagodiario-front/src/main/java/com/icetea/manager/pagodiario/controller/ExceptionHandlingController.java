package com.icetea.manager.pagodiario.controller;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.icetea.manager.pagodiario.api.dto.BasicOutputDto;
import com.icetea.manager.pagodiario.api.dto.BasicOutputDto.BasicOutputType;
import com.icetea.manager.pagodiario.api.dto.exception.FormErrorsOutputDto;
import com.icetea.manager.pagodiario.exception.ErrorTypedException;
import com.icetea.manager.pagodiario.exception.IncorrectUserLoginException;
import com.icetea.manager.pagodiario.utils.StringUtils;

public abstract class ExceptionHandlingController {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public @ResponseBody BasicOutputDto illegalArgumentError(
			HttpServletRequest req, Exception exception) {
		this.getOwnLogger().error(
				"Url: " + req.getRequestURL() + " raised: "
						+ exception.getMessage(), exception);

		BasicOutputDto dto = new BasicOutputDto(BasicOutputType.ILLEGAL_ARGUMENTS.getId());
		dto.setMessage("Bad Request, illegal arguments received.");
		dto.setCause(exception.getMessage());

		return dto;
	}

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IncorrectUserLoginException.class)
	public @ResponseBody BasicOutputDto incorrectUserLoginException(HttpServletRequest req,
			Exception exception) {

		BasicOutputDto dto = new BasicOutputDto(BasicOutputType.UNHANDLED_ERROR.getId());
		dto.setMessage("username incorrecto. Por favor intente nuevamente.");
		dto.setCause(exception.getMessage());

		return dto;
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	public @ResponseBody BasicOutputDto unhandledError(HttpServletRequest req,
			Exception exception) {
		this.getOwnLogger().error(
				"Url: " + req.getRequestURL() + " raised: "
						+ exception.getMessage(), exception);

		BasicOutputDto dto = new BasicOutputDto(BasicOutputType.UNHANDLED_ERROR.getId());
		dto.setMessage("An unhandled error has ocurred.");
		dto.setCause(exception.getMessage());

		return dto;
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(RuntimeException.class)
	public @ResponseBody BasicOutputDto unhandledRuntimeError(HttpServletRequest req,
			Exception exception) {
		this.getOwnLogger().error(
				"Url: " + req.getRequestURL() + " raised: "
						+ exception.getMessage(), exception);

		BasicOutputDto dto = new BasicOutputDto(BasicOutputType.UNHANDLED_ERROR.getId());
		dto.setMessage("An unhandled error has ocurred.");
		dto.setCause(exception.getMessage());

		return dto;
	}

	protected abstract Logger getOwnLogger();

	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@ExceptionHandler(ConstraintViolationException.class)
	public @ResponseBody BasicOutputDto unhandledConstraintError(HttpServletRequest req,
			Exception exception) {
		ConstraintViolationException ce = (ConstraintViolationException)exception;
		this.getOwnLogger().error(
				"Url: " + req.getRequestURL() + " raised: "
						+ ce.getSQLException().getMessage(), exception);

		BasicOutputDto dto = new BasicOutputDto(BasicOutputType.SQL_CONSTRAINT_ERROR.getId());
		dto.setMessage(String.format("Se ha producido un error de clave duplicada al querer insertar el proyecto. SQLState: %s", 
				ce.getSQLState()));
		dto.setCause(ce.getSQLException().getMessage());

		return dto;
	}
	
	@ResponseStatus(value = HttpStatus.ACCEPTED)
	@ExceptionHandler(ErrorTypedException.class)
	public @ResponseBody FormErrorsOutputDto errorTypedException(
			HttpServletRequest req, Exception exception) {
		ErrorTypedException e = (ErrorTypedException)exception;
		this.getOwnLogger().error(
				"Url: " + req.getRequestURL() + ", errorType: " + e.getErrorType() + ", raised: " + e.getMessage());
		// FIXME: [roher] porque no puedo usar el enum?!?!? es null,, q raro!
		FormErrorsOutputDto dto = new FormErrorsOutputDto(BasicOutputType.TYPED_ERROR.getId());
		String message = e.getMessage();
		if(StringUtils.isEmpty(message)){
			message = e.getErrorType().getText();
		}
		dto.setMessage(message);
		dto.setCause(e.getErrorType().getText());
		dto.setErrorType(e.getErrorType());
		dto.setFormErrorList(e.getFormErrors());
		
		return dto;
	}

}
