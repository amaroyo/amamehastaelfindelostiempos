package es.oyssen.mrm.negocio.vo;

import java.util.Date;


public class ArchivoCasoClinicoVO { 

	

	private String idPortfolio;
	private String idCasoClinico;
	private String nombre;
	private byte[] datos;
	private String fechaSubida;
	
	public ArchivoCasoClinicoVO() {
		
	}

	public String getIdPortfolio() {
		return idPortfolio;
	}

	public void setIdPortfolio(String idPortfolio) {
		this.idPortfolio = idPortfolio;
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

	public byte[] getDatos() {
		return datos;
	}

	public void setDatos(byte[] datos) {
		this.datos = datos;
	}

	public String getFechaSubida() {
		return fechaSubida;
	}

	public void setFechaSubida(String fechaSubida) {
		this.fechaSubida = fechaSubida;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idCasoClinico == null) ? 0 : idCasoClinico.hashCode());
		result = prime * result
				+ ((idPortfolio == null) ? 0 : idPortfolio.hashCode());
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
		ArchivoCasoClinicoVO other = (ArchivoCasoClinicoVO) obj;
		if (idCasoClinico == null) {
			if (other.idCasoClinico != null)
				return false;
		} else if (!idCasoClinico.equals(other.idCasoClinico))
			return false;
		if (idPortfolio == null) {
			if (other.idPortfolio != null)
				return false;
		} else if (!idPortfolio.equals(other.idPortfolio))
			return false;
		return true;
	}
	
	

	
}
