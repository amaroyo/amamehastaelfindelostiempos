package es.oyssen.mrm.struts.forms.cursos;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class EditarSeminarioForm extends DhtmlxGridForm {

	private String idSeminario;
	private String nombre;
	private String codigo;
	private String curso;
	private String profesor;
	private String descripcion;
	
	
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
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIdSeminario() {
		return idSeminario;
	}
	public void setIdSeminario(String idSeminario) {
		this.idSeminario = idSeminario;
	}
	public String getProfesor() {
		return profesor;
	}
	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}
	
	
}
