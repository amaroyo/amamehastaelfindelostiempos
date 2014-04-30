package es.oyssen.mrm.struts.forms.permisos.grupo;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class GridPermisosGrupoForm extends DhtmlxGridForm {
	
	private String idGrupo;
	private String idUsuario;

	public String getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

}
