/*
 * DAOException.java
 *
 * Created on 17 de agosto de 2007
 */

package es.oyssen.mrm.negocio.dao.exceptions;

public class DAOException extends java.lang.Exception {

    /**
     * Crea una nueva instancia de <code>DAOException</code> vacia.
     */
    public DAOException() {
    }

    /**
     * Construye una instancia de <code>DAOException</code> con el mensaje
     * especificado del detalle del error.
     * @param message El mensaje del detalle del error.
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Construye una instancia de <code>DAOException</code> con la causa del error.
     * @param cause La causa del error.
     */
    public DAOException(Throwable cause) {
        super(cause);
    }

    /**
     * Construye una instancia de <code>DAOException</code> con el mensaje
     * especificado del detalle del error y la causa del mismo.
     * @param message El mensaje del detalle del error.
     * @param cause La causa del error.
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
