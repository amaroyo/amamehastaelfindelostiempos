package es.oyssen.mrm.negocio.vo;


public class ResponsableVO { 

	private String idResponsable;
	private String nombre;
	private String telefono;
	private String telefonoMovil;
	private String direccion;
	private String codigoPostal;
	private String ciudad;
	private String pais;
	private String email;
	private String comentarios;
	
	public ResponsableVO() {
		
	}
	
	public ResponsableVO(String idResponsable, String nombre) {
		this.idResponsable= idResponsable;
		this.nombre = nombre;
	}
	
	public void setIdResponsable(String idResponsable) {
		this.idResponsable = idResponsable;
	}
	
	public String getIdResponsable() {
		return idResponsable;
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
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idResponsable == null) ? 0 : idResponsable.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponsableVO other = (ResponsableVO) obj;
		if (idResponsable == null) {
			if (other.idResponsable != null)
				return false;
		} else if (!idResponsable.equals(other.idResponsable))
			return false;
		return true;
	}
		
}
