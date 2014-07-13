package es.oyssen.mrm.negocio.vo;


public class DatosUsuarioEstanciaUnidadClinicaVO { 

	private String id_portafolio;
	private String id_estancia_unidad;
	private String centro_asociado;
	private String unidad_clinica;
	private String nombreProfesor;
	private String apellido1Profesor;
	private String apellido2Profesor;
	private String turno;
	private String fecha_inicio;
	private String fecha_fin;
	
	
	
	public DatosUsuarioEstanciaUnidadClinicaVO() {
		
	}
	
	
	

	public String getIdPortafolio() {
		return id_portafolio;
	}




	public void setIdPortafolio(String id_portafolio) {
		this.id_portafolio = id_portafolio;
	}




	public String getIdEstanciaUnidad() {
		return id_estancia_unidad;
	}



	public void setIdEstanciaUnidad(String id_estancia_unidad) {
		this.id_estancia_unidad = id_estancia_unidad;
	}



	public String getCentroAsociado() {
		return centro_asociado;
	}




	public void setCentroAsociado(String centro_asociado) {
		this.centro_asociado = centro_asociado;
	}





	public String getUnidadClinica() {
		return unidad_clinica;
	}



	public void setUnidadClinica(String unidad_clinica) {
		this.unidad_clinica = unidad_clinica;
	}





	public String getNombreProfesor() {
		return nombreProfesor;
	}




	public void setNombreProfesor(String nombreProfesor) {
		this.nombreProfesor = nombreProfesor;
	}




	public String getApellido1Profesor() {
		return apellido1Profesor;
	}




	public void setApellido1Profesor(String apellido1Profesor) {
		this.apellido1Profesor = apellido1Profesor;
	}




	public String getApellido2Profesor() {
		return apellido2Profesor;
	}




	public void setApellido2Profesor(String apellido2Profesor) {
		this.apellido2Profesor = apellido2Profesor;
	}




	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}


	public String getFechaInicio() {
		return fecha_inicio;
	}



	public void setFechaInicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}



	public String getFechaFin() {
		return fecha_fin;
	}


	public void setFechaFin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((id_estancia_unidad == null) ? 0 : id_estancia_unidad
						.hashCode());
		result = prime * result
				+ ((id_portafolio == null) ? 0 : id_portafolio.hashCode());
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
		DatosUsuarioEstanciaUnidadClinicaVO other = (DatosUsuarioEstanciaUnidadClinicaVO) obj;
		if (id_estancia_unidad == null) {
			if (other.id_estancia_unidad != null)
				return false;
		} else if (!id_estancia_unidad.equals(other.id_estancia_unidad))
			return false;
		if (id_portafolio == null) {
			if (other.id_portafolio != null)
				return false;
		} else if (!id_portafolio.equals(other.id_portafolio))
			return false;
		return true;
	}




	

	
}
