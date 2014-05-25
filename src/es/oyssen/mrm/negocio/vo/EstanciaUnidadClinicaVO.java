package es.oyssen.mrm.negocio.vo;


public class EstanciaUnidadClinicaVO { 

	private String id_estancia_unidad;
	private String id_portafolio;
	private String centro_asociado;
	private String unidad_clinica;
	private String turno;
	private String fecha_inicio;
	private String fecha_fin;
	
	public EstanciaUnidadClinicaVO() {
		
	}
	

	public EstanciaUnidadClinicaVO(String id_estancia_unidad, String id_portfolio, String centro_asociado, String unidad_clinica) {
	
		this.id_estancia_unidad = id_estancia_unidad;
		this.id_portafolio = id_portfolio;
		this.centro_asociado = centro_asociado;
		this.unidad_clinica = unidad_clinica;
	}


	
	
	public String getIdEstanciaUnidad() {
		return id_estancia_unidad;
	}


	public void setIdEstanciaUnidad(String id_estancia_unidad) {
		this.id_estancia_unidad = id_estancia_unidad;
	}


	public String getIdPortafolio() {
		return id_portafolio;
	}


	public void setIdPortafolio(String id_portfolio) {
		this.id_portafolio = id_portfolio;
	}


	public String getCentroAsociado() {
		return centro_asociado;
	}


	public void setCentroAsociado(String centro_asociado) {
		this.centro_asociado = centro_asociado;
	}


	public String getUnidadClinica() {
		return unidad_clinica;
	}


	public void setUnidadClinica(String unidad_clinica) {
		this.unidad_clinica = unidad_clinica;
	}


	public String getTurno() {
		return turno;
	}


	public void setTurno(String turno) {
		this.turno = turno;
	}


	public String getFechaInicio() {
		return fecha_inicio;
	}


	public void setFechaInicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}


	public String getFechaFin() {
		return fecha_fin;
	}


	public void setFechaFin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((id_estancia_unidad == null) ? 0 : id_estancia_unidad
						.hashCode());
		result = prime * result
				+ ((id_portafolio == null) ? 0 : id_portafolio.hashCode());
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
		EstanciaUnidadClinicaVO other = (EstanciaUnidadClinicaVO) obj;
		if (id_estancia_unidad == null) {
			if (other.id_estancia_unidad != null)
				return false;
		} else if (!id_estancia_unidad.equals(other.id_estancia_unidad))
			return false;
		if (id_portafolio == null) {
			if (other.id_portafolio != null)
				return false;
		} else if (!id_portafolio.equals(other.id_portafolio))
			return false;
		return true;
	}


	

	
}
