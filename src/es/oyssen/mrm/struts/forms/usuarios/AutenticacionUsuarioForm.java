package es.oyssen.mrm.struts.forms.usuarios;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class AutenticacionUsuarioForm extends DhtmlxGridForm {

	private String correo;
	private String pass;
	
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String user) {
		this.correo = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
