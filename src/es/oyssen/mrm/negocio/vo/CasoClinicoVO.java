package es.oyssen.mrm.negocio.vo;

import org.apache.struts.upload.FormFile;


public class CasoClinicoVO { 

	private String idPortafolio;
	private String idCasoClinico;
	private String nombre;
	private byte[] caso_clinico;
	private String fechaSubida;
	
	public CasoClinicoVO() {
		
	}

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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	

	public String getFechaSubida() {
		return fechaSubida;
	}

	public void setFechaSubida(String fechaSubida) {
		this.fechaSubida = fechaSubida;
	}

	public byte[] getCasoClinico() {
		return caso_clinico;
	}

	public void setCasoClinico(byte[] caso_clinico) {
		this.caso_clinico = caso_clinico;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCasoClinico == null) ? 0 : idCasoClinico.hashCode());
		result = prime * result
				+ ((idPortafolio == null) ? 0 : idPortafolio.hashCode());
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
		CasoClinicoVO other = (CasoClinicoVO) obj;
		if (idCasoClinico == null) {
			if (other.idCasoClinico != null)
				return false;
		} else if (!idCasoClinico.equals(other.idCasoClinico))
			return false;
		if (idPortafolio == null) {
			if (other.idPortafolio != null)
				return false;
		} else if (!idPortafolio.equals(other.idPortafolio))
			return false;
		return true;
	}

	
	



	
}
