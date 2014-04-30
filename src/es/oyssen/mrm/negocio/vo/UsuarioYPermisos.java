package es.oyssen.mrm.negocio.vo;

import java.util.List;


public class UsuarioYPermisos { 

	private UsuarioVO usuario;
	private List<PermisoGrupoVO> permisos;
	private String bloqueado;
	
	
	public UsuarioVO getUsuario() {
		return usuario;
	}
	
	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}
	
	public List<PermisoGrupoVO> getPermisos() {
		return permisos;
	}
	
	public void setPermisos(List<PermisoGrupoVO> permisos) {
		this.permisos = permisos;
	}

	public String getBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(String bloqueado) {
		this.bloqueado = bloqueado;
	}

}
