package es.oyssen.mrm.negocio.vo;

public class CanalVO {
	
	private String idCanal;
	private String nombre; 
	private String telefono; 
	private String telefonoMovil; 
	private String direccion; 
	private String codigoPostal; 
	private String ciudad; 
	private String pais; 
	private String email; 
	private String direccionFacturacion; 
	private String codigoPostalFacturacion; 
	private String ciudadFacturacion; 
	private String paisFacturacion; 
	private String nombreFacturacion; 
	private String telefonoFacturacion; 
	private String telefonoMovilFacturacion; 
	private String emailFacturacion;
	private String direccionWeb;
	private String mrmResponsable;
	
	
	public CanalVO() {
		
	}
	
	public CanalVO(String id, String nombre) {
		this.idCanal = id;
		this.nombre = nombre;
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

	public String getDireccionFacturacion() {
		return direccionFacturacion;
	}

	public void setDireccionFacturacion(String direccionFacturacion) {
		this.direccionFacturacion = direccionFacturacion;
	}

	public String getCodigoPostalFacturacion() {
		return codigoPostalFacturacion;
	}

	public void setCodigoPostalFacturacion(String codigoPostalFacturacion) {
		this.codigoPostalFacturacion = codigoPostalFacturacion;
	}

	public String getCiudadFacturacion() {
		return ciudadFacturacion;
	}

	public void setCiudadFacturacion(String ciudadFacturacion) {
		this.ciudadFacturacion = ciudadFacturacion;
	}

	public String getPaisFacturacion() {
		return paisFacturacion;
	}

	public void setPaisFacturacion(String paisFacturacion) {
		this.paisFacturacion = paisFacturacion;
	}

	public String getNombreFacturacion() {
		return nombreFacturacion;
	}

	public void setNombreFacturacion(String nombreFacturacion) {
		this.nombreFacturacion = nombreFacturacion;
	}

	public String getTelefonoFacturacion() {
		return telefonoFacturacion;
	}

	public void setTelefonoFacturacion(String telefonoFacturacion) {
		this.telefonoFacturacion = telefonoFacturacion;
	}

	public String getTelefonoMovilFacturacion() {
		return telefonoMovilFacturacion;
	}

	public void setTelefonoMovilFacturacion(String telefonoMovilFacturacion) {
		this.telefonoMovilFacturacion = telefonoMovilFacturacion;
	}

	public String getEmailFacturacion() {
		return emailFacturacion;
	}

	public void setEmailFacturacion(String emailFacturacion) {
		this.emailFacturacion = emailFacturacion;
	}

	public String getDireccionWeb() {
		return direccionWeb;
	}

	public void setDireccionWeb(String direccionWeb) {
		this.direccionWeb = direccionWeb;
	}

	public String getMrmResponsable() {
		return mrmResponsable;
	}

	public void setMrmResponsable(String mrmResponsable) {
		this.mrmResponsable = mrmResponsable;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCanal == null) ? 0 : idCanal.hashCode());
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
		CanalVO other = (CanalVO) obj;
		if (idCanal == null) {
			if (other.idCanal != null)
				return false;
		} else if (!idCanal.equals(other.idCanal))
			return false;
		return true;
	}
	
}
