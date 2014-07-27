package es.oyssen.mrm.struts.forms.asignaturas;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class GridDiariosReflexivosAsignaturaUsuarioForm extends DhtmlxGridForm {

	private String idAsignatura;
	private String idPortafolio;
	private String idDiarioReflexivo;
	private String nombre;
	private String fecha;
	
	
	public String getIdAsignatura() {
		return idAsignatura;
	}
	public void setIdAsignatura(String idAsignatura) {
		this.idAsignatura = idAsignatura;
	}
	public String getIdPortafolio() {
		return idPortafolio;
	}
	public void setIdPortafolio(String idPortafolio) {
		this.idPortafolio = idPortafolio;
	}
	
	public String getIdDiarioReflexivo() {
		return idDiarioReflexivo;
	}
	public void setIdDiarioReflexivo(String idDiarioReflexivo) {
		this.idDiarioReflexivo = idDiarioReflexivo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	


	
}
