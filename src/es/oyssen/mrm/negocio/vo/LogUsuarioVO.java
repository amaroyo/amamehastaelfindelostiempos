package es.oyssen.mrm.negocio.vo;

import java.util.Date;


public class LogUsuarioVO { 

	private String idLogUsuario;
	private String idUsuario;
	private String nombre;
	private String user;
	private Date fecha;
	
	
	public LogUsuarioVO() {
		
	}

	public String getIdLogUsuario() {
		return idLogUsuario;
	}

	public void setIdLogUsuario(String idLogUsuario) {
		this.idLogUsuario = idLogUsuario;
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idLogUsuario == null) ? 0 : idLogUsuario.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogUsuarioVO other = (LogUsuarioVO) obj;
		if (idLogUsuario == null) {
			if (other.idLogUsuario != null)
				return false;
		} else if (!idLogUsuario.equals(other.idLogUsuario))
			return false;
		return true;
	}

}
