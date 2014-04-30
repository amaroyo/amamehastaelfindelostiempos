package es.oyssen.mrm.negocio.vo;

public class PermisoGrupoVO {
	
	private String idPermisoGrupo;
	private String idGrupo;
	private String idPermiso;
	private String nombre; 
	
	
	public PermisoGrupoVO() {
		
	}

	public String getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getIdPermisoGrupo() {
		return idPermisoGrupo;
	}

	public void setIdPermisoGrupo(String idPermisoGrupo) {
		this.idPermisoGrupo = idPermisoGrupo;
	}
	
	public String getIdPermiso() {
		return idPermiso;
	}
	
	public void setIdPermiso(String idPermiso) {
		this.idPermiso = idPermiso;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idPermisoGrupo == null) ? 0 : idPermisoGrupo.hashCode());
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
		PermisoGrupoVO other = (PermisoGrupoVO) obj;
		if (idPermisoGrupo == null) {
			if (other.idPermisoGrupo != null)
				return false;
		} else if (!idPermisoGrupo.equals(other.idPermisoGrupo))
			return false;
		return true;
	}
	
}
