/*
 * SesionUsuario.java
 *
 * Created on 13 de septiembre de 2007
 */

package es.oyssen.mrm.negocio.vo;


public class SesionUsuario implements java.io.Serializable {
    
      private Credenciales credenciales;
      private Permisos permisos;
      
    /** 
     * Constructor por defecto.
     */
    public SesionUsuario() {
        this.credenciales = null;
        this.permisos = null;
    }

    /**
     * Método que obtiene las credenciales del usuario.
     * @return Devuelve Las credenciales del usuario.
     */
    public Credenciales getCredenciales() {
        return credenciales;
    }

    /**
     * Método que establece las credenciales del usuario.
     * @param credenciales Credenciales del usuario.
     */
    public void setCredenciales(Credenciales credenciales) {
        this.credenciales = credenciales;
    }

    /**
     * Método que obtiene los permisos del usuario.
     * @return Devuelve Los permisos del usuario.
     */
    public Permisos getPermisos() {
        return permisos;
    }

    /**
     * Método que establece los permisos del usuario.
     * @param permisos Permisos del usuario.
     */
    public void setPermisos(Permisos permisos) {
        this.permisos = permisos;
    }
}
