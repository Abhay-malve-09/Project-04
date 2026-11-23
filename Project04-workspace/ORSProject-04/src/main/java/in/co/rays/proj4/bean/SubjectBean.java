
package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * SubjectBean class represents the subject details.
 * It contains subject information such as name,
 * description and course ID.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class SubjectBean extends BaseBean {

	private static final Logger log = Logger.getLogger(SubjectBean.class);

	private String name;
	private String description;
	private long courseId;

	/**
	 * Gets the subject name.
	 *
	 * @return name
	 */
	public String getName() {
		log.debug("getName() method called");
		return name;
	}

	/**
	 * Sets the subject name.
	 *
	 * @param name the subject name
	 */
	public void setName(String name) {
		log.debug("setName() method called");
		this.name = name;
	}

	/**
	 * Gets the subject description.
	 *
	 * @return description
	 */
	public String getDescription() {
		log.debug("getDescription() method called");
		return description;
	}

	/**
	 * Sets the subject description.
	 *
	 * @param description the subject description
	 */
	public void setDescription(String description) {
		log.debug("setDescription() method called");
		this.description = description;
	}

	/**
	 * Gets the course ID.
	 *
	 * @return courseId
	 */
	public long getCourseId() {
		log.debug("getCourseId() method called");
		return courseId;
	}

	/**
	 * Sets the course ID.
	 *
	 * @param courseId the course ID
	 */
	public void setCourseId(long courseId) {
		log.debug("setCourseId() method called");
		this.courseId = courseId;
	}

	/**
	 * Returns the value of the bean.
	 *
	 * @return null
	 */
	@Override
	public String getValue() {
		log.info("getValue() method called");
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Sets the subject details from the ResultSet.
	 *
	 * @param rs the ResultSet containing subject records
	 */
	@Override
	public void setResultSet(ResultSet rs) {

		log.debug("setResultSet() method started");

		try {
			setName(rs.getString("name"));
			setDescription(rs.getString("description"));
			setCourseId(rs.getLong("course_id"));

			log.info("Subject ResultSet values assigned successfully");
			
		} catch (SQLException e) {

			log.error("Error while setting Subject ResultSet values", e);
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

}
