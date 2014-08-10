package es.oyssen.mrm.negocio.vo;


public class TrabajoDeCampoVO { 

	private String id_trabajo_de_campo;
	private String id_portafolio;
	private String id_trabajo_info;
	private String nombre;
	private String nombre_archivo;
	private byte[] enunciado;
	private String descripcion;
	private String nombre_trabajo;
	private String trabajo_de_campo;
	private String nombre_correccion;
	private String correccion_trabajo;
	private String fecha_limite;
	
	
	public TrabajoDeCampoVO() {
		
	}

	public TrabajoDeCampoVO(String id_trabajo_de_campo, String id_portafolio) {
		this.id_trabajo_de_campo = id_trabajo_de_campo;
		this.id_portafolio = id_portafolio;
	}

	 
	
	public String getIdTrabajoDeCampo() {
		return id_trabajo_de_campo;
	}

	public void setIdTrabajoDeCampo(String id_trabajo_de_campo) {
		this.id_trabajo_de_campo = id_trabajo_de_campo;
	}

	public String getIdPortafolio() {
		return id_portafolio;
	}

	public void setIdPortafolio(String id_portafolio) {
		this.id_portafolio = id_portafolio;
	}

	public String getTrabajoDeCampo() {
		return trabajo_de_campo;
	}

	public void setTrabajoDeCampo(String trabajo_de_campo) {
		this.trabajo_de_campo = trabajo_de_campo;
	}

	public String getCorreccionTrabajo() {
		return correccion_trabajo;
	}

	public void setCorreccionTrabajo(String correccion_trabajo) {
		this.correccion_trabajo = correccion_trabajo;
	}

	public String getFechaLimite() {
		return fecha_limite;
	}

	public void setFechaLimite(String fecha_limite) {
		this.fecha_limite = fecha_limite;
	}

	
	
	
	
	public String getNombreArchivo() {
		return nombre_archivo;
	}

	public void setNombreArchivo(String nombre_archivo) {
		this.nombre_archivo = nombre_archivo;
	}

	public String getNombreTrabajo() {
		return nombre_trabajo;
	}

	public void setNombreTrabajo(String nombre_trabajo) {
		this.nombre_trabajo = nombre_trabajo;
	}

	public String getNombreCorreccion() {
		return nombre_correccion;
	}

	public void setNombreCorreccion(String nombre_correccion) {
		this.nombre_correccion = nombre_correccion;
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
				+ ((id_portafolio == null) ? 0 : id_portafolio.hashCode());
		result = prime
				* result
				+ ((id_trabajo_de_campo == null) ? 0 : id_trabajo_de_campo
						.hashCode());
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
		TrabajoDeCampoVO other = (TrabajoDeCampoVO) obj;
		if (id_portafolio == null) {
			if (other.id_portafolio != null)
				return false;
		} else if (!id_portafolio.equals(other.id_portafolio))
			return false;
		if (id_trabajo_de_campo == null) {
			if (other.id_trabajo_de_campo != null)
				return false;
		} else if (!id_trabajo_de_campo.equals(other.id_trabajo_de_campo))
			return false;
		return true;
	}

	

	
}
