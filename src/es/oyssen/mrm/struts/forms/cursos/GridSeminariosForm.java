package es.oyssen.mrm.struts.forms.cursos;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class GridSeminariosForm extends DhtmlxGridForm {

	private String idSeminario;
	private String idAsignatura;
	private String nombre;
	private String codigo;
	private String curso;
	
	public String getIdSeminario() {
		return idSeminario;
	}
	public void setIdSeminario(String idSeminario) {
		this.idSeminario = idSeminario;
	}
	public String getIdAsignatura() {
		return idAsignatura;
	}
	public void setIdAsignatura(String idAsignatura) {
		this.idAsignatura = idAsignatura;
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
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	
	
	

	
	
}
