package com.icetea.manager.pagodiario.api.dto;

import java.util.List;

import com.google.common.collect.Lists;

public class BasicOutputDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private List<String> causes = null;
	private String message;
	private Integer status;
	
	public static enum BasicOutputType {
		OK(0), ILLEGAL_ARGUMENTS(1), UNHANDLED_ERROR(2), SQL_CONSTRAINT_ERROR(3);
		
		private Integer id;
		
		private BasicOutputType(int id) {
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

	public BasicOutputDto(Integer status, String message, List<String> causes) {
		super();
		this.status = status;
		this.message = message;
		this.causes = causes;
	}

	public void addCause(String cause){
		if(cause != null){
			if(this.causes == null){
				this.causes = Lists.newArrayList();
			}
			this.causes.add(cause);
		}
	}

	public List<String> getCauses() {
		return causes;
	}

	public String getMessage() {
		return message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setCauses(List<String> cause) {
		this.causes = cause;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
}
