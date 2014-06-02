package es.oyssen.mrm.struts.forms.cursos;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class CrearAsignaturaForm extends DhtmlxGridForm {

	private String numeroRubricas;
	private String parte;
	private String codigo;
	private String curso;
	private String nombre;
	private String descripcion;
	
	
	
	public String getNumeroRubricas() {
		return numeroRubricas;
	}
	public void setNumeroRubricas(String numeroRubricas) {
		this.numeroRubricas = numeroRubricas;
	}
	public String getParte() {
		return parte;
	}
	public void setParte(String parte) {
		this.parte = parte;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	
	
}
