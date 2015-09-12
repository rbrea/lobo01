package com.icetea.manager.pagodiario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(name = "COLLECTOR")
@Audited
public class Collector extends Identifiable {

	private static final long serialVersionUID = 1L;
	@Column(name = "ZONE", unique = true)
	private Long zone;
	@Column(name = "DESCRIPTION", length = 140)
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
