package es.oyssen.mrm.struts.forms.asignaturas;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class SubirArchivoCasoClinicoForm extends ActionForm {

	private FormFile fichero;
	private String tipoContenido;
	private String idFichero;
	private String nombre;
	private String idPortafolio;
	
	
	
	public FormFile getFichero() {
		return fichero;
	}

	public void setFichero(FormFile fichero) {
		this.fichero = fichero;
	}

	

	public String getTipoContenido() {
		return tipoContenido;
	}

	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}

	public String getIdFichero() {
		return idFichero;
	}

	public void setIdFichero(String idFichero) {
		this.idFichero = idFichero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIdPortafolio() {
		return idPortafolio;
	}

	public void setIdPortafolio(String idPortafolio) {
		this.idPortafolio = idPortafolio;
	}

	

	
}
