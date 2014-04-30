package es.oyssen.mrm.struts.forms.ficheros;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class GridFicherosForm extends DhtmlxGridForm {

	private String idFichero;
	private String idServicio;
	private String nombre;
	private String tipoContenido;
	private String idLead;
	
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

	public String getIdLead() {
		return idLead;
	}

	public void setIdLead(String idLead) {
		this.idLead = idLead;
	}

}
