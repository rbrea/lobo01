package com.icetea.manager.pagodiario.api.dto;

import java.util.LinkedList;

import com.google.common.collect.Lists;

public class FileResponseDto extends BasicOutputDto {

	private static final long serialVersionUID = 1L;

	private LinkedList<FileMeta> files = Lists.newLinkedList();
	private String error;
	private String printKey;

	public FileResponseDto() {
		super();
	}

	public FileResponseDto(Integer status, String message, String causes) {
		super(status, message, causes);
	}

	public FileResponseDto(Integer status) {
		super(status);
	}
	
	public FileResponseDto(LinkedList<FileMeta> files) {
		super();
		this.files = files;
	}

	public LinkedList<FileMeta> getFiles() {
		return files;
	}

	public void setFiles(LinkedList<FileMeta> files) {
		this.files = files;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getPrintKey() {
		return printKey;
	}

	public void setPrintKey(String printKey) {
		this.printKey = printKey;
	}
	
}
