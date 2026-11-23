
package in.co.rays.proj4.exception;

import org.apache.log4j.Logger;

/**
 * RecordNotFoundException is a custom unchecked exception class
 * used when a requested record is not found in the application.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class RecordNotFoundException extends RuntimeException {

	private static final Logger log = Logger.getLogger(RecordNotFoundException.class);

	/**
	 * Constructs a RecordNotFoundException with the specified message.
	 *
	 * @param msg the detail message describing the record not found exception
	 */
	public RecordNotFoundException(String msg) {

		super(msg);
		log.warn("Record not found: " + msg);

	}

}
