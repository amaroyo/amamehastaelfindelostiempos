package es.oyssen.mrm.negocio.vo;


public class PortafolioVO { 

	private String id_portafolio;
	private String id_alumno;
	private String id_profesor;
	private String id_asignatura;
	private String anyo_academico;
	
	
	public PortafolioVO() {
		
	}
	


	public PortafolioVO(String id_portafolio, String id_alumno, String id_asignatura, String anyo_academico) {
		this.id_portafolio = id_portafolio;
		this.id_alumno = id_alumno;
		this.id_asignatura = id_asignatura;
		this.anyo_academico = anyo_academico;
	}


	public String getIdPortafolio() {
		return id_portafolio;
	}



	public void setIdPortafolio(String id_portafolio) {
		this.id_portafolio = id_portafolio;
	}



	public String getIdAlumno() {
		return id_alumno;
	}



	public void setIdAlumno(String id_alumno) {
		this.id_alumno = id_alumno;
	}



	public String getIdAsignatura() {
		return id_asignatura;
	}



	public void setIdAsignatura(String id_asignatura) {
		this.id_asignatura = id_asignatura;
	}



	public String getAnyoAcademico() {
		return anyo_academico;
	}



	public void setAnyoAcademico(String anyo_academico) {
		this.anyo_academico = anyo_academico;
	}

	

	public String getIdProfesor() {
		return id_profesor;
	}



	public void setIdProfesor(String id_profesor) {
		this.id_profesor = id_profesor;
	}



	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_portafolio == null) ? 0 : id_portafolio.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PortafolioVO other = (PortafolioVO) obj;
		if (id_portafolio == null) {
			if (other.id_portafolio != null)
				return false;
		} else if (!id_portafolio.equals(other.id_portafolio))
			return false;
		return true;
	}

	
}
