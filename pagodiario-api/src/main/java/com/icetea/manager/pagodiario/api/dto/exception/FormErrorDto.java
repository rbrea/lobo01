package com.icetea.manager.pagodiario.api.dto.exception;

import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class FormErrorDto extends BasicDto {
	
	private static final long serialVersionUID = 1L;
	
	private int numberLine = 0;
	private String field;
    private String error;

    public FormErrorDto() {
        super();
    }

    public FormErrorDto(String field, String error) {
        this();
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getError() {
        return this.error;
    }

    public void setError(String error) {
        this.error = error;
    }

	public int getNumberLine() {
		return numberLine;
	}

	public void setNumberLine(int numberLine) {
		this.numberLine = numberLine;
	}
}
