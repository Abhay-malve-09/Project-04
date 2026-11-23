
package in.co.rays.proj4.exception;

import org.apache.log4j.Logger;

/**
 * ApplicationException is a custom unchecked exception class
 * used to handle application-specific errors.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class ApplicationException extends RuntimeException {

	private static final Logger log = Logger.getLogger(ApplicationException.class);

	/**
	 * Constructs an ApplicationException with the specified message.
	 *
	 * @param msg the detail message describing the exception
	 */
	public ApplicationException(String msg) {

		super(msg);
		log.error("Application exception occurred: " + msg);

	}

}
