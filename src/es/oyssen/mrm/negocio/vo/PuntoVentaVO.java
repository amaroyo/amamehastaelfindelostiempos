package es.oyssen.mrm.negocio.vo;

public class PuntoVentaVO {
	private String idPuntoVenta;
	private String nombre;
	public String getIdPuntoVenta() {
		return idPuntoVenta;
	}
	public void setIdPuntoVenta(String idPuntoVenta) {
		this.idPuntoVenta = idPuntoVenta;
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
				+ ((idPuntoVenta == null) ? 0 : idPuntoVenta.hashCode());
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
		PuntoVentaVO other = (PuntoVentaVO) obj;
		if (idPuntoVenta == null) {
			if (other.idPuntoVenta != null)
				return false;
		} else if (!idPuntoVenta.equals(other.idPuntoVenta))
			return false;
		return true;
	}
	
	
}
