package es.oyssen.mrm.struts.forms.leads;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxGridForm;

public class GridLeadsForm extends DhtmlxGridForm {
	
	private Boolean filtrarEstadoWon;
	private Boolean filtrarEstadoLost;
	private String idLead;
	private String creacionDesde;
	private String creacionHasta;
	private String modificacionDesde;
	private String modificacionHasta;

	public Boolean getFiltrarEstadoWon() {
		return filtrarEstadoWon;
	}

	public void setFiltrarEstadoWon(Boolean filtrarEstadoWon) {
		this.filtrarEstadoWon = filtrarEstadoWon;
	}

	public String getIdLead() {
		return idLead;
	}

	public void setIdLead(String idLead) {
		this.idLead = idLead;
	}

	public String getCreacionDesde() {
		return creacionDesde;
	}

	public void setCreacionDesde(String creacionDesde) {
		this.creacionDesde = creacionDesde;
	}

	public String getCreacionHasta() {
		return creacionHasta;
	}

	public void setCreacionHasta(String creacionHasta) {
		this.creacionHasta = creacionHasta;
	}

	public String getModificacionDesde() {
		return modificacionDesde;
	}

	public void setModificacionDesde(String modificacionDesde) {
		this.modificacionDesde = modificacionDesde;
	}

	public String getModificacionHasta() {
		return modificacionHasta;
	}

	public void setModificacionHasta(String modificacionHasta) {
		this.modificacionHasta = modificacionHasta;
	}

	public Boolean getFiltrarEstadoLost() {
		return filtrarEstadoLost;
	}

	public void setFiltrarEstadoLost(Boolean filtrarEstadoLost) {
		this.filtrarEstadoLost = filtrarEstadoLost;
	}
	
}
