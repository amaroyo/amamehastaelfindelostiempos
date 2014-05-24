package es.oyssen.mrm.negocio.vo;


public class ProfesorAsociadoVO { 

	private String id_profesor;
	private String turno;
	private String centro_asociado;
	private String id_asignatura;
	private String anyo_academico;
	
	public ProfesorAsociadoVO() {
		
	}
	
	public ProfesorAsociadoVO(String id_profesor, String turno, String centro_asociado, String id_asignatura, String anyo_academico) {
		super();
		this.id_profesor = id_profesor;
		this.turno = turno;
		this.centro_asociado = centro_asociado;
		this.id_asignatura = id_asignatura;
		this.anyo_academico = anyo_academico;
	}



	public String getIdProfesor() {
		return id_profesor;
	}

	public void setIdProfesor(String id_profesor) {
		this.id_profesor = id_profesor;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public String getCentroAsociado() {
		return centro_asociado;
	}

	public void setCentroAsociado(String centro_asociado) {
		this.centro_asociado = centro_asociado;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((anyo_academico == null) ? 0 : anyo_academico.hashCode());
		result = prime * result
				+ ((id_asignatura == null) ? 0 : id_asignatura.hashCode());
		result = prime * result
				+ ((id_profesor == null) ? 0 : id_profesor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		ProfesorAsociadoVO other = (ProfesorAsociadoVO) obj;
		if (anyo_academico == null) {
			if (other.anyo_academico != null) {
				return false;
			}
		} else if (!anyo_academico.equals(other.anyo_academico)) {
			return false;
		}
		if (id_asignatura == null) {
			if (other.id_asignatura != null) {
				return false;
			}
		} else if (!id_asignatura.equals(other.id_asignatura)) {
			return false;
		}
		if (id_profesor == null) {
			if (other.id_profesor != null) {
				return false;
			}
		} else if (!id_profesor.equals(other.id_profesor)) {
			return false;
		}
		return true;
	}

	
}
