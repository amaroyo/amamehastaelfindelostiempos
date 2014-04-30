/*
 * DAODeleteException.java
 *
 * Created on 17 de agosto de 2007
 */

package es.oyssen.mrm.negocio.dao.exceptions;

public class DAODeleteException extends DAOException {
    
    /**
     * Crea una nueva instancia de <code>DAODeleteException</code> vacia.
     */
    public DAODeleteException() {
    }

    /**
     * Construye una instancia de <code>DAODeleteException</code> con el mensaje
     * especificado del detalle del error.
     * @param message El mensaje del detalle del error.
     */
    public DAODeleteException(String message) {
        super(message);
    }

    /**
     * Construye una instancia de <code>DAODeleteException</code> con la causa del error.
     * @param cause La causa del error.
     */
    public DAODeleteException(Throwable cause) {
        super(cause);
    }

    /**
     * Construye una instancia de <code>DAODeleteException</code> con el mensaje
     * especificado del detalle del error y la causa del mismo.
     * @param message El mensaje del detalle del error.
     * @param cause La causa del error.
     */
    public DAODeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
