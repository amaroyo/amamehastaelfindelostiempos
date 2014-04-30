package es.oyssen.mrm.struts.forms.usuarios;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class EditarUsuarioForm extends DhtmlxGridForm {

	private String idUsuario;
	private String idGrupo;
	private String idAsociado;
	private String nombre;
	private String telefono;
	private String telefonoMovil;
	private String direccion;
	private String codigoPostal;
	private String ciudad;
	private String pais;
	private String email;
	private String comentarios;
	private String user;
	private String pass;
	

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdGrupo() {
		return idGrupo;
	}

	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getIdAsociado() {
		return idAsociado;
	}

	public void setIdAsociado(String idAsociado) {
		this.idAsociado = idAsociado;
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

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}
