package es.oyssen.mrm.struts.forms.asignaturas;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class DescargarCasoClinicoForm extends DhtmlxGridForm {
	

	private String idPortafolio;
	private String idCasoClinico;
	
	
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
	
	

	
}
