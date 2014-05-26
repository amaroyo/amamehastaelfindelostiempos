package es.oyssen.mrm.negocio.vo;


public class SeminarioAsignaturaVO { 

	private String id_seminario;
	private String id_asignatura;
	private String nombre;
	private String codigo;
	private String descripcion;

	
	
	public SeminarioAsignaturaVO() {
		
	}


	public SeminarioAsignaturaVO(String id_seminario, String id_asignatura,
			String nombre, String codigo) {
		this.id_seminario = id_seminario;
		this.id_asignatura = id_asignatura;
		this.nombre = nombre;
		this.codigo = codigo;
	}


	



	public String getIdSeminario() {
		return id_seminario;
	}


	public void setIdSeminario(String id_seminario) {
		this.id_seminario = id_seminario;
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


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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
		SeminarioAsignaturaVO other = (SeminarioAsignaturaVO) obj;
		if (id_seminario == null) {
			if (other.id_seminario != null)
				return false;
		} else if (!id_seminario.equals(other.id_seminario))
			return false;
		return true;
	}

	
}
