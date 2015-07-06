package com.icetea.manager.pagodiario.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Model implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int BIG_DECIMAL_PRECISION = 34;
	public static final int BIG_DECIMAL_SCALE = 18;
	
}
