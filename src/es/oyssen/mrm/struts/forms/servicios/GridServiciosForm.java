package es.oyssen.mrm.struts.forms.servicios;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class GridServiciosForm extends DhtmlxGridForm {

	private String idServicio;
	private String nombre; 
	private String personaContacto; 
	private String proveedor; 	

	
	public String getIdServicio() {
		return idServicio;
	}
	
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPersonaContacto() {
		return personaContacto;
	}

	public void setPersonaContacto(String personaContacto) {
		this.personaContacto = personaContacto;
	}

	public String getProveedor() {
		return proveedor;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	
}
