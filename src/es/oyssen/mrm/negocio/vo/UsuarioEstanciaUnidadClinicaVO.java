package es.oyssen.mrm.negocio.vo;


public class UsuarioEstanciaUnidadClinicaVO { 

	private String id_usuario;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String centro_asociado;
	private String unidad_clinica;
	private String turno;
	
	
	
	
	public UsuarioEstanciaUnidadClinicaVO() {
		
	}
	
	
	
	
	public String getIdUsuario() {
		return id_usuario;
	}




	public void setIdUsuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getApellido1() {
		return apellido1;
	}




	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}




	public String getApellido2() {
		return apellido2;
	}




	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}




	public String getDni() {
		return dni;
	}




	public void setDni(String dni) {
		this.dni = dni;
	}




	public String getCentroAsociado() {
		return centro_asociado;
	}




	public void setCentroAsociado(String centro_asociado) {
		this.centro_asociado = centro_asociado;
	}




	public String getUnidadClinica() {
		return unidad_clinica;
	}




	public void setUnidadClinica(String unidad_clinica) {
		this.unidad_clinica = unidad_clinica;
	}




	public String getTurno() {
		return turno;
	}




	public void setTurno(String turno) {
		this.turno = turno;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((centro_asociado == null) ? 0 : centro_asociado.hashCode());
		result = prime * result
				+ ((id_usuario == null) ? 0 : id_usuario.hashCode());
		result = prime * result + ((turno == null) ? 0 : turno.hashCode());
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
		UsuarioEstanciaUnidadClinicaVO other = (UsuarioEstanciaUnidadClinicaVO) obj;
		if (centro_asociado == null) {
			if (other.centro_asociado != null)
				return false;
		} else if (!centro_asociado.equals(other.centro_asociado))
			return false;
		if (id_usuario == null) {
			if (other.id_usuario != null)
				return false;
		} else if (!id_usuario.equals(other.id_usuario))
			return false;
		if (turno == null) {
			if (other.turno != null)
				return false;
		} else if (!turno.equals(other.turno))
			return false;
		return true;
	}




	

	
}
