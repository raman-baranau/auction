package by.baranau.auction.exception;

public class ConnectionPoolInterruptedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConnectionPoolInterruptedException() {
		super();
	}

	public ConnectionPoolInterruptedException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ConnectionPoolInterruptedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConnectionPoolInterruptedException(String message) {
		super(message);
	}

	public ConnectionPoolInterruptedException(Throwable cause) {
		super(cause);
	}
}
