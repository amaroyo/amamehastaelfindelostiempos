package es.oyssen.mrm.struts.forms.asignaturas;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class GridAnexos2UsuarioAsignaturaForm extends DhtmlxGridForm {



	private String idAlumno;
	private String idAsignatura;
	private String idPortafolio;
	
	
	public String getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(String idUsuario) {
		this.idAlumno = idUsuario;
	}
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
	
	


	

	
}
