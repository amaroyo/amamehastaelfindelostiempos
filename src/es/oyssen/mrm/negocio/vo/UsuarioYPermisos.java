package es.oyssen.mrm.negocio.vo;

import java.util.List;


public class UsuarioYPermisos { 

	private UsuarioVO usuario;
	private List<GrupoPermisoVO> permisos;
	
	
	public UsuarioVO getUsuario() {
		return usuario;
	}
	
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
	
	public List<GrupoPermisoVO> getPermisos() {
		return permisos;
	}
	
	public void setPermisos(List<GrupoPermisoVO> permisos) {
		this.permisos = permisos;
	}

}
