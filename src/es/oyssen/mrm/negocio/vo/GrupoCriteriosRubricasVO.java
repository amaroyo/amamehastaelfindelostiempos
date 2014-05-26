package es.oyssen.mrm.negocio.vo;


public class GrupoCriteriosRubricasVO { 


	
	private String id_grupo_criterio;
	private String id_asignatura;
	private String nombre;

	
	
	public GrupoCriteriosRubricasVO() {
		
	}



	public GrupoCriteriosRubricasVO(String id_grupo_criterio,
			String id_asignatura) {
		this.id_grupo_criterio = id_grupo_criterio;
		this.id_asignatura = id_asignatura;
	}






	public String getIdGrupoCriterio() {
		return id_grupo_criterio;
	}



	public void setIdGrupoCriterio(String id_grupo_criterio) {
		this.id_grupo_criterio = id_grupo_criterio;
	}



	public String getIdAsignatura() {
		return id_asignatura;
	}



	public void setIdAsignatura(String id_asignatura) {
		this.id_asignatura = id_asignatura;
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
				+ ((id_grupo_criterio == null) ? 0 : id_grupo_criterio
						.hashCode());
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
		GrupoCriteriosRubricasVO other = (GrupoCriteriosRubricasVO) obj;
		if (id_grupo_criterio == null) {
			if (other.id_grupo_criterio != null)
				return false;
		} else if (!id_grupo_criterio.equals(other.id_grupo_criterio))
			return false;
		return true;
	}


	
}
