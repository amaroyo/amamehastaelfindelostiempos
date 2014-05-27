package es.oyssen.mrm.negocio.vo;

public class UsuarioPermisosVO {
	
	private String id_usuario;
	private String id_permiso;
	
	
	public UsuarioPermisosVO() {
		
	}

	


	public UsuarioPermisosVO(String id_usuario, String id_permiso) {
		super();
		this.id_usuario = id_usuario;
		this.id_permiso = id_permiso;
	}

	


	public String getIdUsuario() {
		return id_usuario;
	}




	public void setIdUsuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}




	public String getIdPermiso() {
		return id_permiso;
	}
	
	public void setIdPermiso(String idPermiso) {
		this.id_permiso = idPermiso;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_permiso == null) ? 0 : id_permiso.hashCode());
		result = prime * result
				+ ((id_usuario == null) ? 0 : id_usuario.hashCode());
		return result;
	}




	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioPermisosVO other = (UsuarioPermisosVO) obj;
		if (id_permiso == null) {
			if (other.id_permiso != null)
				return false;
		} else if (!id_permiso.equals(other.id_permiso))
			return false;
		if (id_usuario == null) {
			if (other.id_usuario != null)
				return false;
		} else if (!id_usuario.equals(other.id_usuario))
			return false;
		return true;
	}
	
	
	
}
