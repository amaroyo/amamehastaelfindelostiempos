package es.oyssen.mrm.negocio.vo;


public class ErrorLogVO { 

	private String id_error;
	private String tipo;
	private String descripcion;
	private String fecha;

	
	
	public ErrorLogVO() {
		
	}



	public String getIdError() {
		return id_error;
	}



	public void setIdError(String id_error) {
		this.id_error = id_error;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getFecha() {
		return fecha;
	}



	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((id_error == null) ? 0 : id_error.hashCode());
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
		ErrorLogVO other = (ErrorLogVO) obj;
		if (id_error == null) {
			if (other.id_error != null)
				return false;
		} else if (!id_error.equals(other.id_error))
			return false;
		return true;
	}

	




}