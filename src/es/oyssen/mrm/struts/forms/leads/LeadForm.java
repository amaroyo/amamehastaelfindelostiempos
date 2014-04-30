package es.oyssen.mrm.struts.forms.leads;

import es.oyssen.mrm.struts.forms.dhtmlx.DhtmlxForm;

public class LeadForm extends DhtmlxForm {
	private String idLead;
	private String estado;
	private String marketingActivity;
	private String comentarios;
	private String comercial;
	private String responsable;
	private String distribuidor;
	private String empresa;
	private String servicio;
	private String canal;
	private String usuariosPotenciales;
	private String usuarios;
	
	

	public String getComercial() {
		return comercial;
	}
	public void setComercial(String comercial) {
		this.comercial = comercial;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public String getDistribuidor() {
		return distribuidor;
	}
	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}
	public String getServicio() {
		return servicio;
	}
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public String getComentarios() {
		return comentarios;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getMarketingActivity() {
		return marketingActivity;
	}
	public void setMarketingActivity(String marketingActivity) {
		this.marketingActivity = marketingActivity;
	}
	public void setIdLead(String idLead) {
		this.idLead = idLead;
	}
	public String getIdLead() {
		return idLead;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getUsuariosPotenciales() {
		return usuariosPotenciales;
	}
	public void setUsuariosPotenciales(String usuariosPotenciales) {
		this.usuariosPotenciales = usuariosPotenciales;
	}
	public String getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(String usuarios) {
		this.usuarios = usuarios;
	}
}
