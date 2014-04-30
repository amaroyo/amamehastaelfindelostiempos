package es.oyssen.mrm.negocio.vo;

public class ServicioUsuarioVO {
	
	private String idServicioUsuario;
	private String idServicio;
	private String idUsuario;
	private String nombre;

	
	public ServicioUsuarioVO() {
		
	}

	public String getIdServicioUsuario() {
		return idServicioUsuario;
	}

	public void setIdServicioUsuario(String idServicioUsuario) {
		this.idServicioUsuario = idServicioUsuario;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
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
				+ ((idServicioUsuario == null) ? 0 : idServicioUsuario.hashCode());
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
		ServicioUsuarioVO other = (ServicioUsuarioVO) obj;
		if (idServicioUsuario == null) {
			if (other.idServicioUsuario != null)
				return false;
		} else if (!idServicioUsuario.equals(other.idServicioUsuario))
			return false;
		return true;
	}
	
}
