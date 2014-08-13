package es.oyssen.mrm.struts.forms.asignaturas;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class CrearTrabajoCampoForm extends DhtmlxGridForm {

	private String idAsignatura;
	private String idProfesor;
	private String nombre;
	private String descripcion;
	private String fechaFin;
	private String hora;
	private String idPortafolio;
	private String idTrabajoCampo;
	private String idTrabajoInfo;
	private String cambioFechaIndividual;
	
	
	public String getIdAsignatura() {
		return idAsignatura;
	}
	public void setIdAsignatura(String idAsignatura) {
		this.idAsignatura = idAsignatura;
	}
	public String getIdProfesor() {
		return idProfesor;
	}
	public void setIdProfesor(String idProfesor) {
		this.idProfesor = idProfesor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getIdPortafolio() {
		return idPortafolio;
	}
	public void setIdPortafolio(String idPortafolio) {
		this.idPortafolio = idPortafolio;
	}
	public String getIdTrabajoCampo() {
		return idTrabajoCampo;
	}
	public void setIdTrabajoCampo(String idTrabajoCampo) {
		this.idTrabajoCampo = idTrabajoCampo;
	}
	public String getIdTrabajoInfo() {
		return idTrabajoInfo;
	}
	public void setIdTrabajoInfo(String idTrabajoInfo) {
		this.idTrabajoInfo = idTrabajoInfo;
	}
	
	public String getCambioFechaIndividual() {
		return cambioFechaIndividual;
	}
	public void setCambioFechaIndividual(String cambioFechaIndividual) {
		this.cambioFechaIndividual = cambioFechaIndividual;
	}
	
	
	

	
}
