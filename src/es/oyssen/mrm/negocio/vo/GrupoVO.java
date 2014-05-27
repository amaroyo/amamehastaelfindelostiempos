package es.oyssen.mrm.negocio.vo;

public class GrupoVO {
	
	private String id_grupo;
	private String nombre; 
	private String descripcion; 
	
	public GrupoVO() {
		
	}
	
	public GrupoVO(String id, String nombre) {
		this.id_grupo = id;
		this.nombre = nombre;
	}
	
	public String getIdGrupo() {
		return id_grupo;
	}
	
	public void setIdGrupo(String idGrupo) {
		this.id_grupo = idGrupo;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_grupo == null) ? 0 : id_grupo.hashCode());
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
		GrupoVO other = (GrupoVO) obj;
		if (id_grupo == null) {
			if (other.id_grupo != null)
				return false;
		} else if (!id_grupo.equals(other.id_grupo))
			return false;
		return true;
	}


	
}
