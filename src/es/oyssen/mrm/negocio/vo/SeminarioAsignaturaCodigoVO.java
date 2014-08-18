package es.oyssen.mrm.negocio.vo;


public class SeminarioAsignaturaCodigoVO { 

	private String id_seminario;
	private String nombre;
	private String codigo;
	private String codigoAsignatura;
	private String curso;
	
	public SeminarioAsignaturaCodigoVO() {
		
	}


	


	



	public String getCurso() {
		return curso;
	}









	public void setCurso(String curso) {
		this.curso = curso;
	}









	public String getIdSeminario() {
		return id_seminario;
	}


	public void setIdSeminario(String id_seminario) {
		this.id_seminario = id_seminario;
	}


	


	public String getCodigoAsignatura() {
		return codigoAsignatura;
	}









	public void setCodigoAsignatura(String codigoAsignatura) {
		this.codigoAsignatura = codigoAsignatura;
	}









	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	



	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		SeminarioAsignaturaCodigoVO other = (SeminarioAsignaturaCodigoVO) obj;
		if (id_seminario == null) {
			if (other.id_seminario != null)
				return false;
		} else if (!id_seminario.equals(other.id_seminario))
			return false;
		return true;
	}

	
}
