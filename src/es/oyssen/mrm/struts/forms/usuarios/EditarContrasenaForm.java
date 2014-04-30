package es.oyssen.mrm.struts.forms.usuarios;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class EditarContrasenaForm extends DhtmlxGridForm {

	private String oldPass;
	private String newPass;
	
	
	public String getOldPass() {
		return oldPass;
	}
	
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}
	
	public String getNewPass() {
		return newPass;
	}
	
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	
}
