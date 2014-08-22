package es.oyssen.mrm.struts.forms.administrar;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class GridRegistroErroresForm extends DhtmlxGridForm {

	private String idError;
	private String tipo;
	private String fecha;
	
	public String getIdError() {
		return idError;
	}
	public void setIdError(String idError) {
		this.idError = idError;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
	
}
