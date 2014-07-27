package es.oyssen.mrm.negocio.vo;


public class AnexoVO { 

	private String id_anexo;
	private String id_portafolio;
	private String nombre;
	private String anexo;

	
	
	public AnexoVO() {
		
	}

	public AnexoVO(String id_anexo, String id_portafolio) {
		this.id_anexo = id_anexo;
		this.id_portafolio = id_portafolio;
	}





	public String getIdAnexo() {
		return id_anexo;
	}

	public void setIdAnexo(String id_anexo) {
		this.id_anexo = id_anexo;
	}

	public String getIdPortafolio() {
		return id_portafolio;
	}

	public void setIdPortafolio(String id_portafolio) {
		this.id_portafolio = id_portafolio;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
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
		result = prime * result
				+ ((id_anexo == null) ? 0 : id_anexo.hashCode());
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
		AnexoVO other = (AnexoVO) obj;
		if (id_anexo == null) {
			if (other.id_anexo != null)
				return false;
		} else if (!id_anexo.equals(other.id_anexo))
			return false;
		if (id_portafolio == null) {
			if (other.id_portafolio != null)
				return false;
		} else if (!id_portafolio.equals(other.id_portafolio))
			return false;
		return true;
	}

	


	
}
