package es.oyssen.mrm.negocio.vo;

public class GrupoPermisoVO {
	
	private String id_grupo;
	private String id_permiso;
	
	
	public GrupoPermisoVO() {
		
	}

	
	
	
	public GrupoPermisoVO(String id_grupo, String id_permiso) {
		this.id_grupo = id_grupo;
		this.id_permiso = id_permiso;
	}




	public String getIdGrupo() {
		return id_grupo;
	}

	public void setIdGrupo(String idGrupo) {
		this.id_grupo = idGrupo;
	}
	
	public String getIdPermiso() {
		return id_permiso;
	}
	
	public void setIdPermiso(String idPermiso) {
		this.id_permiso = idPermiso;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		GrupoPermisoVO other = (GrupoPermisoVO) obj;
		if (id_grupo == null) {
			if (other.id_grupo != null) {
				return false;
			}
		}
		else if (!id_grupo.equals(other.id_grupo)) {
			return false;
		}
		if (id_permiso == null) {
			if (other.id_permiso != null) {
				return false;
			}
		}
		else if (!id_permiso.equals(other.id_permiso)) {
			return false;
		}
		return true;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_grupo == null) ? 0 : id_grupo.hashCode());
		result = prime * result
				+ ((id_permiso == null) ? 0 : id_permiso.hashCode());
		return result;
	}
	
}
