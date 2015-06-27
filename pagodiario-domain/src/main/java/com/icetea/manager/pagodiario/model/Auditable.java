package com.icetea.manager.pagodiario.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Auditable extends Model {

	private static final long serialVersionUID = 1L;

	public static final String DEFAULT_USERNAME = "MYO_USER";

	@Column(name = "CREATED_BY", length = 40)
	private String createdBy;
	@Column(name = "CREATED_DATE", columnDefinition = "DATETIME", nullable = false)
	private Date createdDate;
	@Column(name = "UPDATED_BY", length = 40)
	private String updatedBy;
	@Column(name = "UPDATED_DATE", columnDefinition = "DATETIME")
	private Date updatedDate;

	/**
	 * @param createdBy
	 */
	public Auditable() {
		this.createdDate = new Date();
	}

	/**
	 * @param createdBy
	 */
	public Auditable(String createdBy) {
		this();
		this.createdBy = createdBy;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void audit() {
		this.audit(DEFAULT_USERNAME);
	}

	public void audit(String updatedBy) {
		this.updatedBy = updatedBy;
		this.updatedDate = new Date();
	}
}
