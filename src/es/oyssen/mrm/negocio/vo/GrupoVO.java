package es.oyssen.mrm.negocio.vo;

public class GrupoVO {
	
	private String idGrupo;
	private String nombre; 
	
	
	public GrupoVO() {
		
	}
	
	public GrupoVO(String id, String nombre) {
		this.idGrupo = id;
		this.nombre = nombre;
	}
	
	public String getIdGrupo() {
		return idGrupo;
	}
	
	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
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
				+ ((idGrupo == null) ? 0 : idGrupo.hashCode());
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
		if (idGrupo == null) {
			if (other.idGrupo != null)
				return false;
		} else if (!idGrupo.equals(other.idGrupo))
			return false;
		return true;
	}
	
}
