package es.oyssen.mrm.negocio.vo;

import java.util.Date;

public class LeadHistoryVO {
	private String idHistory;
	private String idLead;
	private String tipo;
	private Date fecha;
	private String campo;
	private String valorAnterior;
	private String valorPosterior;

	public String getIdHistory() {
		return idHistory;
	}

	public void setIdHistory(String idHistory) {
		this.idHistory = idHistory;
	}

	public String getIdLead() {
		return idLead;
	}

	public void setIdLead(String idLead) {
		this.idLead = idLead;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getValorAnterior() {
		return valorAnterior;
	}

	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public String getValorPosterior() {
		return valorPosterior;
	}

	public void setValorPosterior(String valorPosterior) {
		this.valorPosterior = valorPosterior;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idHistory == null) ? 0 : idHistory.hashCode());
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
		LeadHistoryVO other = (LeadHistoryVO) obj;
		if (idHistory == null) {
			if (other.idHistory != null)
				return false;
		} else if (!idHistory.equals(other.idHistory))
			return false;
		return true;
	}

}
