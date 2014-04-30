package es.oyssen.mrm.struts.forms.empresas;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;

public class EmpresaForm extends DhtmlxForm {
	
	private String idEmpresa;
	private String orgnr;
	private String nombre;
	private String telefono;
	private String telefonoMovil;
	private String email;
	private String informacionContacto;
	private String direccion;
	
	
	public String getIdEmpresa() {
		return idEmpresa;
	}
	
	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getOrgnr() {
		return orgnr;
	}

	public void setOrgnr(String orgnr) {
		this.orgnr = orgnr;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getInformacionContacto() {
		return informacionContacto;
	}

	public void setInformacionContacto(String informacionContacto) {
		this.informacionContacto = informacionContacto;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
}
