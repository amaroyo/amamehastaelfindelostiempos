package es.oyssen.mrm.struts.forms.cursos;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class GridCursosForm extends DhtmlxGridForm {

	private String idASignaturaOSeminario;
	private String nombre;
	private String codigo;
	private String curso;
	private String descripcion;
	public String getIdASignaturaOSeminario() {
		return idASignaturaOSeminario;
	}
	public void setIdASignaturaOSeminario(String idASignaturaOSeminario) {
		this.idASignaturaOSeminario = idASignaturaOSeminario;
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
