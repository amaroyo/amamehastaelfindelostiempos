package es.oyssen.mrm.struts.forms.administrar;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class GridAlumnosAptosCertificadoForm extends DhtmlxGridForm {

	private String idAlumno;
	private String nombre;
	private String apellido;
	private String dni;
	private String idPortafolios;
	
	public String getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(String idAlumno) {
		this.idAlumno = idAlumno;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getIdPortafolios() {
		return idPortafolios;
	}
	public void setIdPortafolios(String idPortafolios) {
		this.idPortafolios = idPortafolios;
	}
	
	
	
	
	
	
}
