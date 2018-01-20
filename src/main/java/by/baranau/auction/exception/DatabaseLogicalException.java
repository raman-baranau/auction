package by.baranau.auction.exception;

public class DatabaseLogicalException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseLogicalException() {
		super();
	}

	public DatabaseLogicalException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DatabaseLogicalException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatabaseLogicalException(String message) {
		super(message);
	}

	public DatabaseLogicalException(Throwable cause) {
		super(cause);
	}
}
