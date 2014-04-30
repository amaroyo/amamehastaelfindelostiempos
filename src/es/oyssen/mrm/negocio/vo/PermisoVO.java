package es.oyssen.mrm.negocio.vo;

public class PermisoVO {
	
	private String idPermiso;
	private String nombre; 
	
	
	public PermisoVO() {
		
	}
	
	public PermisoVO(String id, String nombre) {
		this.idPermiso = id;
		this.nombre = nombre;
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
				+ ((idPermiso == null) ? 0 : idPermiso.hashCode());
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
		if (idPermiso == null) {
			if (other.idPermiso != null)
				return false;
		} else if (!idPermiso.equals(other.idPermiso))
			return false;
		return true;
	}
	
}
