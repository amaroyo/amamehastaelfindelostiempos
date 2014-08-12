package es.oyssen.mrm.struts.forms.asignaturas;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class DescargarForm extends DhtmlxGridForm {
	

	private String tipoConsulta;
	private String idPortafolio;
	private String idCasoClinico;
	private String idTrabajoCampo;
	private String idDiarioReflexivo;
	
	
	public String getIdPortafolio() {
		return idPortafolio;
	}
	public void setIdPortafolio(String idPortafolio) {
		this.idPortafolio = idPortafolio;
	}
	public String getIdCasoClinico() {
		return idCasoClinico;
	}
	public void setIdCasoClinico(String idCasoClinico) {
		this.idCasoClinico = idCasoClinico;
	}
	public String getTipoConsulta() {
		return tipoConsulta;
	}
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}
	public String getIdTrabajoCampo() {
		return idTrabajoCampo;
	}
	public void setIdTrabajoCampo(String idTrabajoCampo) {
		this.idTrabajoCampo = idTrabajoCampo;
	}
	public String getIdDiarioReflexivo() {
		return idDiarioReflexivo;
	}
	public void setIdDiarioReflexivo(String idDiarioReflexivo) {
		this.idDiarioReflexivo = idDiarioReflexivo;
	}
	
	
	
	
}
