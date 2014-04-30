package es.oyssen.mrm.negocio.vo;


public class ContactoCanalVO { 

	private String idContacto;
	private String idCanal;
	private String nombre;
	private String telefono;
	private String telefonoMovil;
	private String direccion;
	private String codigoPostal;
	private String ciudad;
	private String pais;
	private String email;
	private String cargo;
	private String comentarios;
	
	public ContactoCanalVO() {
		
	}
	
	public ContactoCanalVO(String idContacto, String nombre) {
		this.idContacto= idContacto;
		this.nombre = nombre;
	}
	
	public void setIdContacto(String idContacto) {
		this.idContacto = idContacto;
	}
	
	public String getIdContacto() {
		return idContacto;
	}

	public String getIdCanal() {
		return idCanal;
	}

	public void setIdCanal(String idCanal) {
		this.idCanal = idCanal;
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
	
	public String getTelefonoMovil() {
		return telefonoMovil;
	}
	
	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getCodigoPostal() {
		return codigoPostal;
	}
	
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	
	public String getCiudad() {
		return ciudad;
	}
	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public String getPais() {
		return pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getComentarios() {
		return comentarios;
	}
	
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idContacto == null) ? 0 : idContacto.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactoCanalVO other = (ContactoCanalVO) obj;
		if (idContacto == null) {
			if (other.idContacto != null)
				return false;
		} else if (!idContacto.equals(other.idContacto))
			return false;
		return true;
	}

		
}
