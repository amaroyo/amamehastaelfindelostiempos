package es.oyssen.mrm.negocio.vo;


public class ComercialVO { 

	private String idComercial;
	private String idDistribuidor;
	private String idCanal;
	private String nombre;
	private String telefono;
	private String telefonoMovil;
	private String direccion;
	private String codigoPostal;
	private String ciudad;
	private String pais;
	private String email;
	private String comentarios;
	
	public ComercialVO() {
		
	}
	
	public ComercialVO(String idComercial, String nombre) {
		this.idComercial= idComercial;
		this.nombre = nombre;
	}
	
	public String getIdComercial() {
		return idComercial;
	}
	
	public void setIdComercial(String idComercial) {
		this.idComercial = idComercial;
	}

	public String getIdDistribuidor() {
		return idDistribuidor;
	}

	public void setIdDistribuidor(String idDistribuidor) {
		this.idDistribuidor = idDistribuidor;
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
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idComercial == null) ? 0 : idComercial.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComercialVO other = (ComercialVO) obj;
		if (idComercial == null) {
			if (other.idComercial != null)
				return false;
		} else if (!idComercial.equals(other.idComercial))
			return false;
		return true;
	}
		
}
