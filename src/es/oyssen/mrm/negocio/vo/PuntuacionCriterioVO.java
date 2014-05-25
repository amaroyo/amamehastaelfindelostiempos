package es.oyssen.mrm.negocio.vo;


public class PuntuacionCriterioVO { 

	private String id_criterio;
	private String id_portafolio;
	private String nota;

	
	
	public PuntuacionCriterioVO() {
		
	}


	public PuntuacionCriterioVO(String id_criterio, String id_portafolio) {
		this.id_criterio = id_criterio;
		this.id_portafolio = id_portafolio;
	}

	

	public String getIdCriterio() {
		return id_criterio;
	}


	public void setIdCriterio(String id_criterio) {
		this.id_criterio = id_criterio;
	}


	public String getIdPortafolio() {
		return id_portafolio;
	}


	public void setIdPortafolio(String id_portafolio) {
		this.id_portafolio = id_portafolio;
	}


	public String getNota() {
		return nota;
	}


	public void setNota(String nota) {
		this.nota = nota;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_criterio == null) ? 0 : id_criterio.hashCode());
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
		PuntuacionCriterioVO other = (PuntuacionCriterioVO) obj;
		if (id_criterio == null) {
			if (other.id_criterio != null)
				return false;
		} else if (!id_criterio.equals(other.id_criterio))
			return false;
		if (id_portafolio == null) {
			if (other.id_portafolio != null)
				return false;
		} else if (!id_portafolio.equals(other.id_portafolio))
			return false;
		return true;
	}


	


	
}
