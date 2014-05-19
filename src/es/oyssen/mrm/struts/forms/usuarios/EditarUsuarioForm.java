package es.oyssen.mrm.struts.forms.usuarios;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class EditarUsuarioForm extends DhtmlxGridForm {

	private String id_usuario;
	private String id_grupo;
	private String correo_ucm;
	private String contrasenya;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String telefono;
	private String foto;
	
	
	
	public String getIdUsuario() {
		return id_usuario;
	}
	public void setIdUsuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getIdGrupo() {
		return id_grupo;
	}
	public void setIdGrupo(String id_grupo) {
		this.id_grupo = id_grupo;
	}
	public String getCorreo() {
		return correo_ucm;
	}
	public void setCorreo(String correo_ucm) {
		this.correo_ucm = correo_ucm;
	}
	public String getContrasenya() {
		return contrasenya;
	}
	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
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
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	

	
	
}
