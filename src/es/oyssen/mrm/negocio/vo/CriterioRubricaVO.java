package es.oyssen.mrm.negocio.vo;


public class CriterioRubricaVO { 

	private String id_criterio;
	private String id_asignatura;
	private String id_grupo_criterio;
	private String nombre;

	
	
	public CriterioRubricaVO() {
		
	}



	public CriterioRubricaVO(String id_criterio, String id_asignatura,
			String id_grupo_criterio) {
		this.id_criterio = id_criterio;
		this.id_asignatura = id_asignatura;
		this.id_grupo_criterio = id_grupo_criterio;
	}



	

	public String getIdCriterio() {
		return id_criterio;
	}



	public void setIdCriterio(String id_criterio) {
		this.id_criterio = id_criterio;
	}



	public String getIdAsignatura() {
		return id_asignatura;
	}



	public void setIdAsignatura(String id_asignatura) {
		this.id_asignatura = id_asignatura;
	}



	public String getIdGrupoCriterio() {
		return id_grupo_criterio;
	}



	public void setIdGrupoCriterio(String id_grupo_criterio) {
		this.id_grupo_criterio = id_grupo_criterio;
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
				+ ((id_criterio == null) ? 0 : id_criterio.hashCode());
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
		CriterioRubricaVO other = (CriterioRubricaVO) obj;
		if (id_criterio == null) {
			if (other.id_criterio != null)
				return false;
		} else if (!id_criterio.equals(other.id_criterio))
			return false;
		return true;
	}


	
}
