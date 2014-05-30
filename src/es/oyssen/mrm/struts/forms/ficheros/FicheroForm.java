package es.oyssen.mrm.struts.forms.ficheros;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;

public class FicheroForm extends DhtmlxForm {

	private String idFichero;
	private String idServicio;
	private String nombre;
	private String tipoContenido;
	
	public String getIdFichero() {
		return idFichero;
	}
	
	public void setIdFichero(String idFichero) {
		this.idFichero = idFichero;
	}
	
	public String getIdServicio() {
		return idServicio;
	}
	
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipoContenido() {
		return tipoContenido;
	}

	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}

}
