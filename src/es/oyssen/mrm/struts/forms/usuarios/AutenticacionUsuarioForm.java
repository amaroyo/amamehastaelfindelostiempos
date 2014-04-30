package es.oyssen.mrm.struts.forms.usuarios;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class AutenticacionUsuarioForm extends DhtmlxGridForm {

	private String user;
	private String pass;
	
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
