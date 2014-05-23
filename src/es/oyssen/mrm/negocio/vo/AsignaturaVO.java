package es.oyssen.mrm.negocio.vo;


public class AsignaturaVO { 

	private String id_asignatura;
	private String curso;
	private String codigo;
	private String nombre;
	private String descripcion;
	
	
	public AsignaturaVO() {
		
	}
	
	public AsignaturaVO(String idAsignatura, String nombre) {
		this.id_asignatura= idAsignatura;
		this.nombre = nombre;
	}

	
	public String getIdAsignatura() {
		return id_asignatura;
	}

	public void setIdAsignatura(String id_asignatura) {
		this.id_asignatura = id_asignatura;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_asignatura == null) ? 0 : id_asignatura.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AsignaturaVO other = (AsignaturaVO) obj;
		if (id_asignatura == null) {
			if (other.id_asignatura != null)
				return false;
		} else if (!id_asignatura.equals(other.id_asignatura))
			return false;
		return true;
	}

	
}
