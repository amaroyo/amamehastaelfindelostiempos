/*
 * Credenciales.java
 *
 * Created on 13 de septiembre de 2007
 */

package es.oyssen.mrm.negocio.vo;


public class Credenciales implements java.io.Serializable {
    
    /** Identificador de la aplicacion. */
    private String aplicacion;
    /** Nombre del usuario. */
    private String usuario;
    /** Identificador de sesion del usuario. */
    private String idSesion;
    
    /**
     * Constuctor por defecto.
     */
    public Credenciales() {
        this.aplicacion = null;
        this.usuario = null;
        this.idSesion = null;
    }

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}
    
    
    
}
