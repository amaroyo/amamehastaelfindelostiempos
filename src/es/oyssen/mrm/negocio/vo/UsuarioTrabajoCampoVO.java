package es.oyssen.mrm.negocio.vo;


public class UsuarioTrabajoCampoVO { 

	private String id_portafolio;
	private String id_trabajo_de_campo;
	private byte[] trabajo_de_campoFile;
	private String trabajo_de_campoNombre;
	private byte[] correccion_trabajoFile;
	private String correccion_trabajoNombre;
	private String fecha_limite;
	private String id_usuario;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	
	public UsuarioTrabajoCampoVO() {
		
	}
	
	

	
	
	public String getIdPortafolio() {
		return id_portafolio;
	}








	public void setIdPortafolio(String id_portafolio) {
		this.id_portafolio = id_portafolio;
	}








	public String getIdTrabajoDeCampo() {
		return id_trabajo_de_campo;
	}








	public void setIdTrabajoDeCampo(String id_trabajo_de_campo) {
		this.id_trabajo_de_campo = id_trabajo_de_campo;
	}








	public byte[] getTrabajoDeCampoFile() {
		return trabajo_de_campoFile;
	}








	public void setTrabajoDeCampoFile(byte[] trabajo_de_campoFile) {
		this.trabajo_de_campoFile = trabajo_de_campoFile;
	}








	public String getTrabajoDeCampoNombre() {
		return trabajo_de_campoNombre;
	}








	public void setTrabajoDeCampoNombre(String trabajo_de_campoNombre) {
		this.trabajo_de_campoNombre = trabajo_de_campoNombre;
	}








	public byte[] getCorreccionTrabajoFile() {
		return correccion_trabajoFile;
	}








	public void setCorreccionTrabajoFile(byte[] correccion_trabajoFile) {
		this.correccion_trabajoFile = correccion_trabajoFile;
	}








	public String getCorreccionTrabajoNombre() {
		return correccion_trabajoNombre;
	}








	public void setCorreccionTrabajoNombre(String correccion_trabajoNombre) {
		this.correccion_trabajoNombre = correccion_trabajoNombre;
	}








	public String getFechaLimite() {
		return fecha_limite;
	}








	public void setFechaLimite(String fecha_limite) {
		this.fecha_limite = fecha_limite;
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





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_portafolio == null) ? 0 : id_portafolio.hashCode());
		result = prime
				* result
				+ ((id_trabajo_de_campo == null) ? 0 : id_trabajo_de_campo
						.hashCode());
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
		UsuarioTrabajoCampoVO other = (UsuarioTrabajoCampoVO) obj;
		if (id_portafolio == null) {
			if (other.id_portafolio != null)
				return false;
		} else if (!id_portafolio.equals(other.id_portafolio))
			return false;
		if (id_trabajo_de_campo == null) {
			if (other.id_trabajo_de_campo != null)
				return false;
		} else if (!id_trabajo_de_campo.equals(other.id_trabajo_de_campo))
			return false;
		if (id_usuario == null) {
			if (other.id_usuario != null)
				return false;
		} else if (!id_usuario.equals(other.id_usuario))
			return false;
		return true;
	}




	
}
