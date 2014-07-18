package es.oyssen.mrm.negocio.vo;


public class UsuarioVO { 

	private String id_usuario;
	private String id_grupo;
	private String correo;
	private String contrasenya;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String telefono;
	private byte[] fotoFile;
	private String fotoImagen;
	
	public UsuarioVO() {
		
	}
	
	
	
	public UsuarioVO(String id_usuario, String id_grupo, String correo, String contrasenya, String nombre, String apellido1, String dni) {
		this.id_usuario = id_usuario;
		this.id_grupo = id_grupo;
		this.correo = correo;
		this.contrasenya = contrasenya;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.dni = dni;
	}



	public String getIdUsuario() {
		return id_usuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.id_usuario = idUsuario;
	}	
	
	public String getIdGrupo() {
		return id_grupo;
	}
	
	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
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

	

	public void setIdGrupo(String idGrupo) {
		this.id_grupo = idGrupo;
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
		UsuarioVO other = (UsuarioVO) obj;
		if (id_usuario == null) {
			if (other.id_usuario != null)
				return false;
		} else if (!id_usuario.equals(other.id_usuario))
			return false;
		return true;
	}



	public byte[] getFotoFile() {
		return fotoFile;
	}



	public void setFotoFile(byte[] fotoFile) {
		this.fotoFile = fotoFile;
	}



	public String getFotoImagen() {
		return fotoImagen;
	}



	public void setFotoImagen(String fotoImagen) {
		this.fotoImagen = fotoImagen;
	}


	
}
