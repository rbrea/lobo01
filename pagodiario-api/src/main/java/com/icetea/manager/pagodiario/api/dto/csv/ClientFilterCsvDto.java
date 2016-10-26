package com.icetea.manager.pagodiario.api.dto.csv;

import com.icetea.manager.pagodiario.api.dto.BasicDto;

public class ClientFilterCsvDto extends BasicDto {

	private static final long serialVersionUID = 1L;

	private String nombreApellido;
    private String domicilioEmpresa;
    private String telEmpresa;
    private String dni;
    private String tipoDeComercio;
    private String localidadEmpresa;
    
    private Long id;
    private String domicilio;
    private String localidad;
    private String tel;
    private String callesCercanas;
    private String email;
	private String tuvoDevolucion;
	private String tuvoCancelacion;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocalidadEmpresa() {
		return localidadEmpresa;
	}

	public void setLocalidadEmpresa(String localidadEmpresa) {
		this.localidadEmpresa = localidadEmpresa;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getCallesCercanas() {
		return callesCercanas;
	}

	public void setCallesCercanas(String callesCercanas) {
		this.callesCercanas = callesCercanas;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTuvoDevolucion() {
		return tuvoDevolucion;
	}

	public void setTuvoDevolucion(String tuvoDevolucion) {
		this.tuvoDevolucion = tuvoDevolucion;
	}

	public String getTuvoCancelacion() {
		return tuvoCancelacion;
	}

	public void setTuvoCancelacion(String tuvoCancelacion) {
		this.tuvoCancelacion = tuvoCancelacion;
	}

	public String getTelEmpresa() {
		return telEmpresa;
	}

	public void setTelEmpresa(String telEmpresa) {
		this.telEmpresa = telEmpresa;
	}
    
}
