package es.oyssen.mrm.struts.forms.asignaturas;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class GridSeminariosUsuarioForm extends DhtmlxGridForm {

	private String idSeminario;
	private String idPortafolio;
	private String idAlumno;
	private String nombreSeminario;
	private String codigoSeminario;
	private String Fecha;
	private String idAsignatura;
	private String peticion;
	
	public String getIdSeminario() {
		return idSeminario;
	}
	public void setIdSeminario(String idSeminario) {
		this.idSeminario = idSeminario;
	}
	public String getIdPortafolio() {
		return idPortafolio;
	}
	public void setIdPortafolio(String idPortafolio) {
		this.idPortafolio = idPortafolio;
	}
	public String getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(String idAlumno) {
		this.idAlumno = idAlumno;
	}
	public String getNombreSeminario() {
		return nombreSeminario;
	}
	public void setNombreSeminario(String nombreSeminario) {
		this.nombreSeminario = nombreSeminario;
	}
	public String getCodigoSeminario() {
		return codigoSeminario;
	}
	public void setCodigoSeminario(String codigoSeminario) {
		this.codigoSeminario = codigoSeminario;
	}
	public String getFecha() {
		return Fecha;
	}
	public void setFecha(String fecha) {
		Fecha = fecha;
	}
	public String getIdAsignatura() {
		return idAsignatura;
	}
	public void setIdAsignatura(String idAsignatura) {
		this.idAsignatura = idAsignatura;
	}
	public String getPeticion() {
		return peticion;
	}
	public void setPeticion(String peticion) {
		this.peticion = peticion;
	}
	
	
	
	
}
