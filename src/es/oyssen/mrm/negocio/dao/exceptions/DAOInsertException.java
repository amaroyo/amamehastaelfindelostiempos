/*
 * DAOInsertException.java
 *
 * Created on 17 de agosto de 2007
 */

package es.oyssen.mrm.negocio.dao.exceptions;

public class DAOInsertException extends DAOException {
    
    /**
     * Crea una nueva instancia de <code>DAOInsertException</code> vacia.
     */
    public DAOInsertException() {
    }

    /**
     * Construye una instancia de <code>DAOInsertException</code> con el mensaje
     * especificado del detalle del error.
     * @param message El mensaje del detalle del error.
     */
    public DAOInsertException(String message) {
        super(message);
    }

    /**
     * Construye una instancia de <code>DAOInsertException</code> con la causa del error.
     * @param cause La causa del error.
     */
    public DAOInsertException(Throwable cause) {
        super(cause);
    }

    /**
     * Construye una instancia de <code>DAOInsertException</code> con el mensaje
     * especificado del detalle del error y la causa del mismo.
     * @param message El mensaje del detalle del error.
     * @param cause La causa del error.
     */
    public DAOInsertException(String message, Throwable cause) {
        super(message, cause);
    }
}
