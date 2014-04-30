package es.oyssen.mrm.negocio.dao.exceptions;

public class DuplicateKeyException  extends DAOException {
	public DuplicateKeyException() {
		super();
	}
	
	public DuplicateKeyException(String message) {
		super(message);
	}
	
	public DuplicateKeyException(Throwable cause) {
		super(cause);
	}
	
	public DuplicateKeyException(String message, Throwable cause) {
		super(message, cause);
	}
}