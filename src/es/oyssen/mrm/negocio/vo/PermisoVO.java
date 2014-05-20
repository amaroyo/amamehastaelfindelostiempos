package es.oyssen.mrm.negocio.vo;

public class PermisoVO {
	
	private String id_permiso;
	private String nombre;
	private String descripcion;
	
	
	public PermisoVO() {
		
	}
	
	public PermisoVO(String id, String nombre) {
		this.id_permiso = id;
		this.nombre = nombre;
	}
	
	public String getIdPermiso() {
		return id_permiso;
	}
	
	public void setIdPermiso(String idPermiso) {
		this.id_permiso = idPermiso;
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
				+ ((id_permiso == null) ? 0 : id_permiso.hashCode());
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
		PermisoVO other = (PermisoVO) obj;
		if (id_permiso == null) {
			if (other.id_permiso != null)
				return false;
		} else if (!id_permiso.equals(other.id_permiso))
			return false;
		return true;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
