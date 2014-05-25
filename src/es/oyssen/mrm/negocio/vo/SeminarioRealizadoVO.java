package es.oyssen.mrm.negocio.vo;


public class SeminarioRealizadoVO { 

	private String id_seminario;
	private String id_portafolio;

	
	
	public SeminarioRealizadoVO() {
		
	}


	 
	
	public SeminarioRealizadoVO(String id_seminario, String id_portafolio) {
		super();
		this.id_seminario = id_seminario;
		this.id_portafolio = id_portafolio;
	}


	
	
	
	public String getIdSeminario() {
		return id_seminario;
	}




	public void setIdSeminario(String id_seminario) {
		this.id_seminario = id_seminario;
	}




	public String getIdPortafolio() {
		return id_portafolio;
	}




	public void setIdPortafolio(String id_portafolio) {
		this.id_portafolio = id_portafolio;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_portafolio == null) ? 0 : id_portafolio.hashCode());
		result = prime * result
				+ ((id_seminario == null) ? 0 : id_seminario.hashCode());
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
		SeminarioRealizadoVO other = (SeminarioRealizadoVO) obj;
		if (id_portafolio == null) {
			if (other.id_portafolio != null)
				return false;
		} else if (!id_portafolio.equals(other.id_portafolio))
			return false;
		if (id_seminario == null) {
			if (other.id_seminario != null)
				return false;
		} else if (!id_seminario.equals(other.id_seminario))
			return false;
		return true;
	}



	
}
