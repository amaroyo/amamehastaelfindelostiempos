package es.oyssen.mrm.struts.forms.usuarios;

import java.awt.Image;

import org.apache.struts.upload.FormFile;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class EditarUsuarioForm extends DhtmlxGridForm {

	private String idUsuario;
	private String grupo;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	private String correo;
	private String telefono;
	private FormFile fotoFile;
	private byte[] fotoImagen;
	private String contrasenya;
	
	
	public String getIdUsuario() {
		return idUsuario;
	}


	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String id_grupo) {
		this.grupo = id_grupo;
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


	public FormFile getFotoFile() {
		return fotoFile;
	}


	public void setFotoFile(FormFile fotoFile) {
		this.fotoFile = fotoFile;
	}


	public byte[] getFotoImagen() {
		return fotoImagen;
	}


	public void setFotoImagen(byte[] fotoImagen) {
		this.fotoImagen = fotoImagen;
	}

	
}
