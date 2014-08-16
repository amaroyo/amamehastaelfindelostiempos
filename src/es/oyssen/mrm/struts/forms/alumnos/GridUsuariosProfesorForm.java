package es.oyssen.mrm.struts.forms.alumnos;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class GridUsuariosProfesorForm extends DhtmlxGridForm {

	private String idUsuario;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String nombreAsignatura;
	private String codigo;
	private String id_asignatura;
	private String busqueda;
	
	
	public String getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getNombreAsignatura() {
		return nombreAsignatura;
	}


	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getId_asignatura() {
		return id_asignatura;
	}


	public void setId_asignatura(String id_asignatura) {
		this.id_asignatura = id_asignatura;
	}


	public String getBusqueda() {
		return busqueda;
	}


	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}
	
	
	
	
}
