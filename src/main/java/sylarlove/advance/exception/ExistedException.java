/**
 * 
 */
package sylarlove.advance.exception;

/**
 * @author 武继明
 *  @since 2013年10月12日  上午10:22:26
 *
 */
public class ExistedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExistedException() {
		super();
	}

	public ExistedException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExistedException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExistedException(String message) {
		super(message);
	}

	public ExistedException(Throwable cause) {
		super(cause);
	}

}
