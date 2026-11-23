
package in.co.rays.proj4.exception;

import org.apache.log4j.Logger;

/**
 * DuplicateRecordException is a custom unchecked exception class
 * used to handle duplicate record errors in the application.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class DuplicateRecordException extends RuntimeException {

	private static final Logger log = Logger.getLogger(DuplicateRecordException.class);

	/**
	 * Constructs a DuplicateRecordException with the specified message.
	 *
	 * @param msg the detail message describing the duplicate record exception
	 */
	public DuplicateRecordException(String msg) {

		super(msg);
		log.warn("Duplicate record exception occurred: " + msg);

	}

}
