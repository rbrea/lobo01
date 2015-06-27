package com.icetea.manager.pagodiario.xls;

import java.io.Serializable;

public class ProjectXlsRow implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final String projectName;
	private final String version;
	private final String tableName;
	private final String name;
	private final String type;
	private final String size;
	private final String unique;
	private final String nullable;
	private final String pk;
	private final String fk;
	private final String description;
	
	public ProjectXlsRow(String projectName, String version, String tableName,
			String name, String type, String size, String unique,
			String nullable, String pk, String fk, String description) {
		super();
		this.projectName = projectName;
		this.version = version;
		this.tableName = tableName;
		this.name = name;
		this.type = type;
		this.size = size;
		this.unique = unique;
		this.nullable = nullable;
		this.pk = pk;
		this.fk = fk;
		this.description = description;
	}

	public String getProjectName() {
		return projectName;
	}

	public String getVersion() {
		return version;
	}

	public String getTableName() {
		return tableName;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getSize() {
		return size;
	}

	public String getUnique() {
		return unique;
	}

	public String getNullable() {
		return nullable;
	}

	public String getPk() {
		return pk;
	}

	public String getFk() {
		return fk;
	}

	public String getDescription() {
		return description;
	}
	
}
