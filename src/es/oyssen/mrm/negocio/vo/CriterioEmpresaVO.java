package es.oyssen.mrm.negocio.vo;

public class CriterioEmpresaVO {
	private String nombre;
	private String direccion;
	private String ciudad;
	private boolean busquedaExacta;	
	private String canal;
	private String distribuidor;
	private String comercial;
	
	public void setBusquedaExacta(boolean busquedaExacta) {
		this.busquedaExacta = busquedaExacta;
	}
	public boolean isBusquedaExacta() {
		return busquedaExacta;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public String getDistribuidor() {
		return distribuidor;
	}
	public void setDistribuidor(String distribuidor) {
		this.distribuidor = distribuidor;
	}
	public String getComercial() {
		return comercial;
	}
	public void setComercial(String comercial) {
		this.comercial = comercial;
	}

}
