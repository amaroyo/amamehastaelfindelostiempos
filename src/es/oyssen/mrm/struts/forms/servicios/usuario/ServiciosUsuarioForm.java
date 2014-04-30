package es.oyssen.mrm.struts.forms.servicios.usuario;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;

public class ServiciosUsuarioForm extends DhtmlxForm {

	private String idServicio;
	private String idUsuario;
	
	
	public String getIdServicio() {
		return idServicio;
	}
	
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}
	
	public String getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	} 

}
