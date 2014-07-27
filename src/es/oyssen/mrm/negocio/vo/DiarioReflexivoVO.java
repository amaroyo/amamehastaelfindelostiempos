package es.oyssen.mrm.negocio.vo;


public class DiarioReflexivoVO { 

	private String id_diario_reflexivo;
	private String id_portafolio;
	private String nombre;
	private String diario_reflexivo;

	
	
	public DiarioReflexivoVO() {
		
	}

	
	public DiarioReflexivoVO(String id_diario_reflexivo, String id_portafolio) {
		this.id_diario_reflexivo = id_diario_reflexivo;
		this.id_portafolio = id_portafolio;
	}

	



	public String getIdDiarioReflexivo() {
		return id_diario_reflexivo;
	}


	public void setIdDiarioReflexivo(String id_diario_reflexivo) {
		this.id_diario_reflexivo = id_diario_reflexivo;
	}


	public String getIdPortafolio() {
		return id_portafolio;
	}


	public void setIdPortafolio(String id_portafolio) {
		this.id_portafolio = id_portafolio;
	}


	public String getDiarioReflexivo() {
		return diario_reflexivo;
	}


	public void setDiarioReflexivo(String diario_reflexivo) {
		this.diario_reflexivo = diario_reflexivo;
	}


	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((id_diario_reflexivo == null) ? 0 : id_diario_reflexivo
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
		DiarioReflexivoVO other = (DiarioReflexivoVO) obj;
		if (id_diario_reflexivo == null) {
			if (other.id_diario_reflexivo != null)
				return false;
		} else if (!id_diario_reflexivo.equals(other.id_diario_reflexivo))
			return false;
		if (id_portafolio == null) {
			if (other.id_portafolio != null)
				return false;
		} else if (!id_portafolio.equals(other.id_portafolio))
			return false;
		return true;
	}





	
}
