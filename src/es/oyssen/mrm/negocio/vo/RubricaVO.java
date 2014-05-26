package es.oyssen.mrm.negocio.vo;


public class RubricaVO { 


	
	private String id_asignatura;
	private String competencias;
	private String numero_criterios;

	
	
	public RubricaVO() {
		
	}


	public RubricaVO(String id_asignatura) {
		this.id_asignatura = id_asignatura;
	}

	



	public String getIdAsignatura() {
		return id_asignatura;
	}


	public void setIdAsignatura(String id_asignatura) {
		this.id_asignatura = id_asignatura;
	}


	public String getCompetencias() {
		return competencias;
	}


	public void setCompetencias(String competencias) {
		this.competencias = competencias;
	}


	public String getNumeroCriterios() {
		return numero_criterios;
	}


	public void setNumeroCriterios(String numero_criterios) {
		this.numero_criterios = numero_criterios;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_asignatura == null) ? 0 : id_asignatura.hashCode());
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
		RubricaVO other = (RubricaVO) obj;
		if (id_asignatura == null) {
			if (other.id_asignatura != null)
				return false;
		} else if (!id_asignatura.equals(other.id_asignatura))
			return false;
		return true;
	}


	
}
