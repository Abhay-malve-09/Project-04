
package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * StudentBean class represents the student details.
 * It contains student information such as college name,
 * first name, last name, date of birth, mobile number and email.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class StudentBean extends BaseBean {

	private static final Logger log = Logger.getLogger(StudentBean.class);

	private long collegeId;
	private String collegeName;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String mobileNo;
	private String email;


	/**
	 * Gets the college ID.
	 *
	 * @return collegeId
	 */
	public long getCollegeId() {
		log.debug("getCollegeId() method called");
		return collegeId;
	}

	/**
	 * Sets the college ID.
	 *
	 * @param collegeId the college ID
	 */
	public void setCollegeId(long collegeId) {
		log.debug("setCollegeId() method called");
		this.collegeId = collegeId;
	}

	/**
	 * Gets the college name.
	 *
	 * @return collegeName
	 */
	public String getCollegeName() {
		log.debug("getCollegeName() method called");
		return collegeName;
	}

	/**
	 * Sets the college name.
	 *
	 * @param collegeName the college name
	 */
	public void setCollegeName(String collegeName) {
		log.debug("setCollegeName() method called");
		this.collegeName = collegeName;
	}

	/**
	 * Gets the first name of the student.
	 *
	 * @return firstName
	 */
	public String getFirstName() {
		log.debug("getFirstName() method called");
		return firstName;
	}

	/**
	 * Sets the first name of the student.
	 *
	 * @param firstName the first name
	 */
	public void setFirstName(String firstName) {
		log.debug("setFirstName() method called");
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the student.
	 *
	 * @return lastName
	 */
	public String getLastName() {
		log.debug("getLastName() method called");
		return lastName;
	}

	/**
	 * Sets the last name of the student.
	 *
	 * @param lastName the last name
	 */
	public void setLastName(String lastName) {
		log.debug("setLastName() method called");
		this.lastName = lastName;
	}

	/**
	 * Gets the date of birth of the student.
	 *
	 * @return dateOfBirth
	 */
	public Date getDateOfBirth() {
		log.debug("getDateOfBirth() method called");
		return dateOfBirth;
	}

	/**
	 * Sets the date of birth of the student.
	 *
	 * @param dateOfBirth the date of birth
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		log.debug("setDateOfBirth() method called");
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * Gets the mobile number of the student.
	 *
	 * @return mobileNo
	 */
	public String getMobileNo() {
		log.debug("getMobileNo() method called");
		return mobileNo;
	}

	/**
	 * Sets the mobile number of the student.
	 *
	 * @param mobileNo the mobile number
	 */
	public void setMobileNo(String mobileNo) {
		log.debug("setMobileNo() method called");
		this.mobileNo = mobileNo;
	}

	/**
	 * Gets the email address of the student.
	 *
	 * @return email
	 */
	public String getEmail() {
		log.debug("getEmail() method called");
		return email;
	}

	/**
	 * Sets the email address of the student.
	 *
	 * @param email the email address
	 */
	public void setEmail(String email) {
		log.debug("setEmail() method called");
		this.email = email;
	}
	
	/**
	 * Returns the value of the bean.
	 *
	 * @return null
	 */
	@Override
	public String getValue() {
		log.info("getValue() method called");
		return null;
	}
	
	/**
	 * Sets the student details from the ResultSet.
	 *
	 * @param rs the ResultSet containing student records
	 */
	@Override
	public void setResultSet(ResultSet rs) {

		log.debug("setResultSet() method started");

		try {
			setCollegeId(rs.getLong("college_id"));
			setCollegeName(rs.getString("college_name"));
			setFirstName(rs.getString("first_name"));
			setLastName(rs.getString("last_Name"));
			setDateOfBirth(rs.getTimestamp("date_of_birth"));
			setMobileNo(rs.getString("mobile_no"));
			setEmail(rs.getString("email"));

			log.info("Student ResultSet values assigned successfully");
			
		} catch (SQLException e) {

			log.error("Error while setting Student ResultSet values", e);
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}
	
}
