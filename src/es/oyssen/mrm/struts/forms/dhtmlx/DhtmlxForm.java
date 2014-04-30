package es.oyssen.mrm.struts.forms.dhtmlx;

import es.oyssen.mrm.struts.forms.BaseActionForm;

public class DhtmlxForm extends BaseActionForm {
	private String nativeeditor_status;
	private String oldId;
	private String newId;
	
	private String operation;
	
	private String contenido;

	
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public String getContenido() {
		return contenido;
	}
	
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getOperation() {
		return operation;
	}
	public String getNativeeditor_status() {
		return nativeeditor_status;
	}

	public void setNativeeditor_status(String nativeeditor_status) {
		this.nativeeditor_status = nativeeditor_status;
	}

	public String getOldId() {
		return oldId;
	}

	public void setOldId(String oldId) {
		this.oldId = oldId;
	}

	public String getNewId() {
		return newId;
	}

	public void setNewId(String newId) {
		this.newId = newId;
	}
	
	
}
