package es.oyssen.mrm.struts.forms.usuarios;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class EditarContrasenaForm extends DhtmlxGridForm {

	private String oldPass;
	private String newPass1;
	
	
	public String getOldPass() {
		return oldPass;
	}
	
	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass1() {
		return newPass1;
	}

	public void setNewPass1(String newPass1) {
		this.newPass1 = newPass1;
	}
	
	
}
