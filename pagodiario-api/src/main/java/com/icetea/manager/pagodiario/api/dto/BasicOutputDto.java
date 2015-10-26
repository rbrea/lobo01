package com.icetea.manager.pagodiario.api.dto;

import com.icetea.manager.pagodiario.api.dto.exception.ErrorType;

public class BasicOutputDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private String cause;
	private String message;
	private Integer status = 0;
	private ErrorType errorType;
	
	public static enum BasicOutputType {
		OK(0), CLIENT_NOT_FOUND(1), ILLEGAL_ARGUMENTS(2), UNHANDLED_ERROR(3), SQL_CONSTRAINT_ERROR(4), 
			CLIENT_COULD_NOT_BE_SAVE(5), NOT_FOUND(6), TYPED_ERROR(7);
		
		private Integer id;
		
		private BasicOutputType(int id) {
			if(id < 0){
				throw new IllegalArgumentException("id cannot be unless 0");
			}
			this.id = id;
		}
		
		public Integer getId(){
			return this.id;
		}
		
		public static Integer getId(BasicOutputType type){
			for(BasicOutputType t : values()){
				if(t == type){
					return t.getId();
				}
			}
			
			return -1;
		}
		
		public static BasicOutputType valueOf(Integer id){
			for(BasicOutputType t : values()){
				if(t.getId() == id){
					return t;
				}
			}
			
			return null;
		}
		
	}
	
	public BasicOutputDto() {
		super();
		this.status = BasicOutputType.OK.getId();
	}
	
	public BasicOutputDto(Integer status) {
		super();
		this.status = status;
	}

	public BasicOutputDto(Integer status, String message, String cause) {
		super();
		this.status = status;
		this.message = message;
		this.cause = cause;
	}

	public String getMessage() {
		return message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}
	
}
