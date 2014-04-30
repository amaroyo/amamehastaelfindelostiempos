package es.oyssen.mrm.struts.forms.ficheros;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class SubirFicheroForm extends ActionForm {

	private FormFile fichero;
	private String tipoContenido;
	private String idServicio;
	private String idFichero;
	private String descripcion;
	private String idLead;
	
	public FormFile getFichero() {
		return fichero;
	}

	public void setFichero(FormFile fichero) {
		this.fichero = fichero;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdLead() {
		return idLead;
	}

	public void setIdLead(String idLead) {
		this.idLead = idLead;
	}
	
}
