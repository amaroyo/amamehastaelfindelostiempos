package es.oyssen.mrm.negocio.vo;

import java.util.List;


public class UsuarioYPermisos { 

	private UsuarioVO usuario;
	private List<PermisoVO> permisos;
	
	
	public UsuarioVO getUsuario() {
		return usuario;
	}
	
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
	
	public List<PermisoVO> getPermisos() {
		return permisos;
	}
	
	public void setPermisos(List<PermisoVO> permisos) {
		this.permisos = permisos;
	}

}
