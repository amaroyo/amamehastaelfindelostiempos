package es.oyssen.mrm.negocio.vo;

public class CriterioServicioVO {
	
	private String idServicio;
	private String nombre;
	private String personaContacto;
	private String proveedor;
	private String idUsuario;
	
	public CriterioServicioVO() {
		
	}
	public CriterioServicioVO(String idServicio, String nombre) {
		this.idServicio = idServicio;
		this.nombre = nombre;
	}
	
	public String getIdServicio() {
		return idServicio;
	}
	
	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPersonaContacto() {
		return personaContacto;
	}
	
	public void setPersonaContacto(String personaContacto) {
		this.personaContacto = personaContacto;
	}
	
	public String getProveedor() {
		return proveedor;
	}
	
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}

	public String getIdUsuario() {
		return idUsuario;
	}
	
	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idServicio == null) ? 0 : idServicio.hashCode());
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
		CriterioServicioVO other = (CriterioServicioVO) obj;
		if (idServicio == null) {
			if (other.idServicio != null)
				return false;
		} else if (!idServicio.equals(other.idServicio))
			return false;
		return true;
	}
	
}
