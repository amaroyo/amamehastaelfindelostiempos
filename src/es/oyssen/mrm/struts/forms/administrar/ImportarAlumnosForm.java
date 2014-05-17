package es.oyssen.mrm.struts.forms.administrar;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class ImportarAlumnosForm extends ActionForm {
	
	private FormFile fichero;
	private String apellido1;
	private String apellido2;
	private String nombre;
	private String dni;
	private String asignatura;
	private String grupoHospital;
	private String unidadAsistencial;
	private String fechaIni;
	private String fechaFin;
	
	public FormFile getFichero() {
		return fichero;
	}
	public void setFichero(FormFile fichero) {
		this.fichero = fichero;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getAsignatura() {
		return asignatura;
	}
	public void setAsignatura(String asignatura) {
		this.asignatura = asignatura;
	}
	public String getUnidadAsistencial() {
		return unidadAsistencial;
	}
	public void setUnidadAsistencial(String unidadAsistencial) {
		this.unidadAsistencial = unidadAsistencial;
	}
	public String getGrupoHospital() {
		return grupoHospital;
	}
	public void setGrupoHospital(String grupoHospital) {
		this.grupoHospital = grupoHospital;
	}
	public String getFechaIni() {
		return fechaIni;
	}
	public void setFechaIni(String fechaIni) {
		this.fechaIni = fechaIni;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
}
