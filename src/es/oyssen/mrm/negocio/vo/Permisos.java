/*
 * Permisos.java
 *
 * Created on 13 de septiembre de 2007
 */

package es.oyssen.mrm.negocio.vo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;


public class Permisos implements java.io.Serializable {
    
    /** Coleccion de permisos generales. */
    private Collection permisosGenerales;
    
    /** Coleccion de permisos por operacion. */
    private Collection permisosOperacion;
    
    /** Coleccion de permisos. */
    private Collection permisos;
    
    /** Constructor por defecto. */
    public Permisos() {
        this.permisosGenerales = null;
        this.permisosOperacion = null;
        this.permisos = null;
    }
    
    /**
     * Constructor.
     * @param permisos Lista de permisos a asignar.
     */
    public Permisos(String[] permisos) {
        this.permisosGenerales = Arrays.asList(permisos);
        this.permisosOperacion = new ArrayList();
        this.permisos = new ArrayList(permisosGenerales);
    }
    
    /**
     * Indica si el usuario esta autorizado, es decir posee el permiso pasado.
     * @return boolean true si esta autorizado, false en caso contrario.
     */
    public boolean isAutorizado(String permiso) {
        return permisos.contains(permiso);
    }
    
    /**
     * Metodo que establece los permisos por operacion.
     * @param permisos Lista de permisos a asignar por operacion.
     */
    public void setPermisosOperacion(String[] permisos) {
        this.permisos.retainAll(permisosGenerales);
        this.permisosOperacion = Arrays.asList(permisos);
        this.permisos.addAll(this.permisosOperacion);
    }
    
    /**
     * Metodo que obtiene los permisos generales.
     * @return Una coleccion con los permisos generales.
     */
    public Collection getPermisosGenerales() {
        return permisosGenerales;
    }
    
    /**
     * Metodo que obtiene los permisos por operacion.
     * @return Una coleccion con los permisos por operacion.
     */
    public Collection getPermisosOperacion() {
        return permisosOperacion;
    }
    
    /**
     * Metodo que obtiene todos los permisos.
     * @return Una coleccion con todos los permisos.
     */
    public Collection getPermisos() {
        return permisos;
    }
    
    /**
     * Metodo que convierto los permisos a String.
     * @return Un String con la representacion de los permisos.
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Iterator it = permisos.iterator(); it.hasNext(); ) {
            sb.append(it.next() + ",");
        }
        if (sb.length() > 0) sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
