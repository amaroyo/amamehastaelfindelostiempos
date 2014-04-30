package es.oyssen.mrm.struts.forms.usuarios;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class ForgotPasswordForm extends DhtmlxGridForm {

	private String email;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
