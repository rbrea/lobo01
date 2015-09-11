package com.icetea.manager.pagodiario.api.dto;

public class CollectorDto extends BasicIdentifiableDto {

	private static final long serialVersionUID = 1L;

	private Long zone;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getZone() {
		return zone;
	}

	public void setZone(Long zone) {
		this.zone = zone;
	}
	
	
}
