package es.oyssen.mrm.negocio.vo;

import java.util.Date;

public class CriterioLeadVO {
	private String responsable;
	private String comercial;
	private String empresa;
	private String estado;
	private Date fechaDesde;
	private Date fechaHasta;
	private String distribuidor;
	private String servicio;
	private String canal;
	private Boolean filtrarEstadoWon;
	private Boolean filtrarEstadoLost;
	private String creacionDesde;
	private String creacionHasta;
	private String modificacionDesde;
	private String modificacionHasta;
	private String supplier;
	
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public String getComercial() {
		return comercial;
	}
	public void setComercial(String comercial) {
		this.comercial = comercial;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
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
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public Boolean getFiltrarEstadoWon() {
		return filtrarEstadoWon;
	}
	public void setFiltrarEstadoWon(Boolean filtrarEstadoWon) {
		this.filtrarEstadoWon = filtrarEstadoWon;
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
	
	public String getSupplier() {
		return supplier;
	}
	
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	public Boolean getFiltrarEstadoLost() {
		return filtrarEstadoLost;
	}
	
	public void setFiltrarEstadoLost(Boolean filtrarEstadoLost) {
		this.filtrarEstadoLost = filtrarEstadoLost;
	}	
	
}
