package com.icetea.manager.pagodiario.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "COLLECTOR")
public class Collector extends Identifiable {

	private static final long serialVersionUID = 1L;
	@Column(name = "DESCRIPTION", length = 140)
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
