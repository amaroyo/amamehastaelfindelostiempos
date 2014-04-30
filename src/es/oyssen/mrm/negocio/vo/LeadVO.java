package es.oyssen.mrm.negocio.vo;

import java.util.Date;

public class LeadVO {
	private String idLead;
	private ComercialVO comercial;
	private ResponsableVO responsable;
	private ServicioVO servicio;
	private EstadoVO estado;
	private EmpresaVO empresa;
	private Date fechaVisita;
	private MarketingActivityVO marketingActivity;
	private DistribuidorVO distribuidor;
	private CanalVO canal;
	private String usuariosPotenciales;
	private String usuarios;
	
	
	public void setDistribuidor(DistribuidorVO distribuidor) {
		this.distribuidor = distribuidor;
	}
	public DistribuidorVO getDistribuidor() {
		return distribuidor;
	}
	
	public void setMarketingActivity(MarketingActivityVO marketingActivity) {
		this.marketingActivity = marketingActivity;
	}
	public MarketingActivityVO getMarketingActivity() {
		return marketingActivity;
	}	
	public Date getFechaVisita() {
		return fechaVisita;
	}
	public void setFechaVisita(Date fechaVisita) {
		this.fechaVisita = fechaVisita;
	}
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	private Date fechaCreacion;
	private String comentarios;	
	
	
	public void setEmpresa(EmpresaVO empresa) {
		this.empresa = empresa;
	}
	public EmpresaVO getEmpresa() {
		return empresa;
	}
	public void setResponsable(ResponsableVO responsable) {
		this.responsable = responsable;
	}
	
	public ResponsableVO getResponsable() {
		return responsable;
	}
	
	public void setEstado(EstadoVO estado) {
		this.estado = estado;
	}
	public EstadoVO getEstado() {
		return estado;
	}
	
	public String getIdLead() {
		return idLead;
	}
	public void setIdLead(String idLead) {
		this.idLead = idLead;
	}
	public ComercialVO getComercial() {
		return comercial;
	}
	public void setComercial(ComercialVO comercial) {
		this.comercial = comercial;
	}
	public ServicioVO getServicio() {
		return servicio;
	}
	public void setServicio(ServicioVO servicio) {
		this.servicio = servicio;
	}
	public CanalVO getCanal() {
		return canal;
	}
	public void setCanal(CanalVO canal) {
		this.canal = canal;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLead == null) ? 0 : idLead.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LeadVO other = (LeadVO) obj;
		if (idLead == null) {
			if (other.idLead != null)
				return false;
		} else if (!idLead.equals(other.idLead))
			return false;
		return true;
	}
	
}
