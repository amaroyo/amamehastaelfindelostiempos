package es.oyssen.mrm.struts.forms.cursos;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class GridAsignaturasForm extends DhtmlxGridForm {

	private String idASignatura;
	private String nombre;
	private String codigo;
	private String curso;
	private String descripcion;
	
	public String getIdASignaturaOSeminario() {
		return idASignatura;
	}
	public void setIdASignatura(String idASignatura) {
		this.idASignatura = idASignatura;
	}
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
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	

	
	
}
