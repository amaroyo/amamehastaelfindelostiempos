/*
 * DataNotFoundException.java
 *
 * Created on 17 de agosto de 2007
 */

package es.oyssen.mrm.negocio.dao.exceptions;

public class DataNotFoundException extends DAOException {
    
    /**
     * Crea una nueva instancia de <code>DataNotFoundException</code> vacia.
     */
    public DataNotFoundException() {
    }

    /**
     * Construye una instancia de <code>DataNotFoundException</code> con el mensaje
     * especificado del detalle del error.
     * @param message El mensaje del detalle del error.
     */
    public DataNotFoundException(String message) {
        super(message);
    }

    /**
     * Construye una instancia de <code>DataNotFoundException</code> con la causa del error.
     * @param cause La causa del error.
     */
    public DataNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Construye una instancia de <code>DataNotFoundException</code> con el mensaje
     * especificado del detalle del error y la causa del mismo.
     * @param message El mensaje del detalle del error.
     * @param cause La causa del error.
     */
    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
