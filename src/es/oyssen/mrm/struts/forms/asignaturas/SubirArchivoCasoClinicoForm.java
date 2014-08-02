package es.oyssen.mrm.struts.forms.asignaturas;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class SubirArchivoCasoClinicoForm extends ActionForm {

	private FormFile fichero;
	private String idAlumno;
	private String idAsignatura;
	private String nombre;
	private String idPortafolio;
	
	
	
	public FormFile getFichero() {
		return fichero;
	}

	public void setFichero(FormFile fichero) {
		this.fichero = fichero;
	}

	

	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(String idAlumno) {
		this.idAlumno = idAlumno;
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
