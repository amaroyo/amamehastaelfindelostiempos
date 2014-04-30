package es.oyssen.mrm.negocio.exceptions;

public class ServiceException extends Exception {
    private String message;
    private Exception exception;
    
    public ServiceException(String message) {
        this.message = message;
        this.exception = new Exception(message);
    }
    public ServiceException(Exception exception) {
        this.exception = exception;
    }
    public ServiceException(String message, Exception exception) {
        this.message = message;
        this.exception = exception;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public Exception getException() {
        return this.exception;
    }
}
