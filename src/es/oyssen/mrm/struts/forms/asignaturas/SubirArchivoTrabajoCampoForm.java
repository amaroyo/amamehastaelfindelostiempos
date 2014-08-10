package es.oyssen.mrm.struts.forms.asignaturas;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class SubirArchivoTrabajoCampoForm extends ActionForm {

	private FormFile fichero;
	private String idTrabajoInfo;
	private String nombre;
	
	
	
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

	public String getIdTrabajoInfo() {
		return idTrabajoInfo;
	}

	public void setIdTrabajoInfo(String idTrabajoInfo) {
		this.idTrabajoInfo = idTrabajoInfo;
	}


	

	

	

	
}
