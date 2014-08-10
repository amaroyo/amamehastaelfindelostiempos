package es.oyssen.mrm.negocio.vo;

public class TrabajoDeCampoInfoVO {
	
	private String id_trabajo_info;
	private String nombre;
	private byte[] enunciado;
	private String nombre_archivo;
	private String descripcion;
	
	public TrabajoDeCampoInfoVO() {
		
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getIdTrabajoInfo() {
		return id_trabajo_info;
	}


	public void setIdTrabajoInfo(String id_trabajo_info) {
		this.id_trabajo_info = id_trabajo_info;
	}


	


	public byte[] getEnunciado() {
		return enunciado;
	}


	public void setEnunciado(byte[] enunciado) {
		this.enunciado = enunciado;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	

	public String getNombreArchivo() {
		return nombre_archivo;
	}


	public void setNombreArchivo(String nombre_archivo) {
		this.nombre_archivo = nombre_archivo;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_trabajo_info == null) ? 0 : id_trabajo_info.hashCode());
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
		TrabajoDeCampoInfoVO other = (TrabajoDeCampoInfoVO) obj;
		if (id_trabajo_info == null) {
			if (other.id_trabajo_info != null)
				return false;
		} else if (!id_trabajo_info.equals(other.id_trabajo_info))
			return false;
		return true;
	}
	
	
	
}
