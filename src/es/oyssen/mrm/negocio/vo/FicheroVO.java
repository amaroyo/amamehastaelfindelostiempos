package es.oyssen.mrm.negocio.vo;

import java.util.Date;


public class FicheroVO { 

	private String idFichero;
	private String idServicio;
	private String nombre;
	private String tipoContenido;
	private byte[] datos;
	private Date fechaUltimaDescarga;
	private String descripcion;
	private String idLead;
	
	public FicheroVO() {
		
	}
	
	public FicheroVO(String idFichero, String idServicio, String tipoContenido) {
		this.idFichero= idFichero;
		this.setIdServicio(idServicio);
		this.setTipoContenido(tipoContenido);
	}

	public String getIdFichero() {
		return idFichero;
	}

	public void setIdFichero(String idFichero) {
		this.idFichero = idFichero;
	}

	public String getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(String idServicio) {
		this.idServicio = idServicio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
	
	public String getTipoContenido() {
		return tipoContenido;
	}

	public void setTipoContenido(String tipoContenido) {
		this.tipoContenido = tipoContenido;
	}

	public byte[] getDatos() {
		return datos;
	}

	public void setDatos(byte[] datos) {
		this.datos = datos;
	}	

	public Date getFechaUltimaDescarga() {
		return fechaUltimaDescarga;
	}

	public void setFechaUltimaDescarga(Date fechaUltimaDescarga) {
		this.fechaUltimaDescarga = fechaUltimaDescarga;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getIdLead() {
		return idLead;
	}

	public void setIdLead(String idLead) {
		this.idLead = idLead;
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idFichero == null) ? 0 : idFichero.hashCode());
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FicheroVO other = (FicheroVO) obj;
		if (idFichero == null) {
			if (other.idFichero != null)
				return false;
		} else if (!idFichero.equals(other.idFichero))
			return false;
		return true;
	}

}
