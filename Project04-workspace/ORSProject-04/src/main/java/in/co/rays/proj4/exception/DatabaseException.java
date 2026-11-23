
package in.co.rays.proj4.exception;

import org.apache.log4j.Logger;

/**
 * DatabaseException is a custom unchecked exception class
 * used to handle database-related errors in the application.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class DatabaseException extends RuntimeException {

	private static final Logger log = Logger.getLogger(DatabaseException.class);

	/**
	 * Constructs a DatabaseException with the specified message.
	 *
	 * @param msg the detail message describing the database exception
	 */
	public DatabaseException(String msg) {

		super(msg);
		log.error("Database exception occurred: " + msg);

	}

}
