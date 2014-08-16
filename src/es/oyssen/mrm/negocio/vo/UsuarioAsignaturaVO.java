package es.oyssen.mrm.negocio.vo;


public class UsuarioAsignaturaVO { 

	private String id_usuario;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String codigo;
	private String id_asignatura;
	private String nombreAsignatura;
	private String id_profesor;
	private String id_portafolio;
	
	public UsuarioAsignaturaVO() {
		
	}
	
	
	
	



	public String getIdProfesor() {
		return id_profesor;
	}







	public void setIdProfesor(String id_profesor) {
		this.id_profesor = id_profesor;
	}







	public String getIdUsuario() {
		return id_usuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.id_usuario = idUsuario;
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

	

	
		
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}







	public String getCodigo() {
		return codigo;
	}







	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}







	public String getIdAsignatura() {
		return id_asignatura;
	}







	public void setIdAsignatura(String id_asignatura) {
		this.id_asignatura = id_asignatura;
	}







	public String getNombreAsignatura() {
		return nombreAsignatura;
	}







	public void setNombreAsignatura(String nombreAsignatura) {
		this.nombreAsignatura = nombreAsignatura;
	}




	


	public String getIdPortafolio() {
		return id_portafolio;
	}







	public void setIdPortafolio(String id_portafolio) {
		this.id_portafolio = id_portafolio;
	}







	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_asignatura == null) ? 0 : id_asignatura.hashCode());
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
		UsuarioAsignaturaVO other = (UsuarioAsignaturaVO) obj;
		if (id_asignatura == null) {
			if (other.id_asignatura != null)
				return false;
		} else if (!id_asignatura.equals(other.id_asignatura))
			return false;
		if (id_usuario == null) {
			if (other.id_usuario != null)
				return false;
		} else if (!id_usuario.equals(other.id_usuario))
			return false;
		return true;
	}

	
	
	


	
}
