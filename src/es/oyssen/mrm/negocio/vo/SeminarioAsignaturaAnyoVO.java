package es.oyssen.mrm.negocio.vo;


public class SeminarioAsignaturaAnyoVO { 

	private String id_seminario;
	private String nombre;
	private String codigo;
	private String anyo_academico;


	
	
	public SeminarioAsignaturaAnyoVO() {
		
	}


	public SeminarioAsignaturaAnyoVO(String id_seminario, String nombre,
			String codigo, String anyo_academico) {
	
		this.id_seminario = id_seminario;
		this.nombre = nombre;
		this.codigo = codigo;
		this.anyo_academico = anyo_academico;
	}













	public String getIdSeminario() {
		return id_seminario;
	}


	public void setIdSeminario(String id_seminario) {
		this.id_seminario = id_seminario;
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
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		result = prime * result
				+ ((id_seminario == null) ? 0 : id_seminario.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		SeminarioAsignaturaAnyoVO other = (SeminarioAsignaturaAnyoVO) obj;
		if (anyo_academico == null) {
			if (other.anyo_academico != null)
				return false;
		} else if (!anyo_academico.equals(other.anyo_academico))
			return false;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (id_seminario == null) {
			if (other.id_seminario != null)
				return false;
		} else if (!id_seminario.equals(other.id_seminario))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}




	
}
