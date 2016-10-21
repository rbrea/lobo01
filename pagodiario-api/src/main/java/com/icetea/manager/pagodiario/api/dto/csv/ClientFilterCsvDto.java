package com.icetea.manager.pagodiario.api.dto.csv;

import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class ClientFilterCsvDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private String nombreApellido;
    private String domicilioEmpresa;
    private String localidad;
    private String tel;
    private String dni;
    private String tipoDeComercio;
    
	public ClientFilterCsvDto() {
		super();
	}

	public String getNombreApellido() {
		return nombreApellido;
	}
	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}
	public String getDomicilioEmpresa() {
		return domicilioEmpresa;
	}
	public void setDomicilioEmpresa(String domicilioEmpresa) {
		this.domicilioEmpresa = domicilioEmpresa;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getTipoDeComercio() {
		return tipoDeComercio;
	}
	public void setTipoDeComercio(String tipoDeComercio) {
		this.tipoDeComercio = tipoDeComercio;
	}
    
}
