package es.oyssen.mrm.negocio.vo;


public class UsuarioAnyoSeminarioVO { 

	private String id_usuario;
	private String correo;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String telefono;
	private String anyo_academico;
	
	public UsuarioAnyoSeminarioVO() {
		
	}
	
	

	public String getIdUsuario() {
		return id_usuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.id_usuario = idUsuario;
	}	
	

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
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

	
	
		
	public String getAnyo_academico() {
		return anyo_academico;
	}



	public void setAnyo_academico(String anyo_academico) {
		this.anyo_academico = anyo_academico;
	}



	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_usuario == null) ? 0 : id_usuario.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioAnyoSeminarioVO other = (UsuarioAnyoSeminarioVO) obj;
		if (id_usuario == null) {
			if (other.id_usuario != null)
				return false;
		} else if (!id_usuario.equals(other.id_usuario))
			return false;
		return true;
	}




	
}
