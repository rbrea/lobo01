package com.icetea.manager.pagodiario.model.type;

public enum ProductType {
	
	BLANCO("Blanco"), ELECTRODOMESTICO("Electrodomestico");
	
	private String description;
	
	private ProductType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
