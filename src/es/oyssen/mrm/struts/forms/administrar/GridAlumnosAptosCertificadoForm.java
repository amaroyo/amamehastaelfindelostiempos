package es.oyssen.mrm.struts.forms.administrar;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class GridAlumnosAptosCertificadoForm extends DhtmlxGridForm {

	private String idAlumno;
	private String nombre;
	private String apellidos;
	private String dni;
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
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	
	
	
	
	
	
}
