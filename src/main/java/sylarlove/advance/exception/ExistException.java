/**
 * 
 */
package sylarlove.advance.exception;

/**
 * @author 武继明
 *  @since 2013年10月12日  上午10:22:26
 *
 */
public class ExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExistException() {
		super();
	}

	public ExistException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExistException(String message) {
		super(message);
	}

	public ExistException(Throwable cause) {
		super(cause);
	}

}
