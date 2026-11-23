
package in.co.rays.proj4.bean;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * UserBean class represents the user details.
 * It contains user information such as first name, last name,
 * login, password, date of birth, mobile number, role ID,
 * gender, login details and IP address.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class UserBean extends BaseBean {

	private static final Logger log = Logger.getLogger(UserBean.class);

	private String firstName;
	private String lastName;
	private String login;
	private String password;
	private String confirmPassword;
	private Date dob;
	private String mobileNo;
	private long roleId;
	private int unsuccessfulLogin;
	private String gender;
	private Timestamp lastLogin;
	private String userLock;
	private String registeredIp;
	private String lastLoginIp;

	/**
	 * Gets the first name of the user.
	 *
	 * @return firstName
	 */
	public String getFirstName() {
		log.debug("getFirstName() method called");
		return firstName;
	}

	/**
	 * Sets the first name of the user.
	 *
	 * @param firstName the first name
	 */
	public void setFirstName(String firstName) {
		log.debug("setFirstName() method called");
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the user.
	 *
	 * @return lastName
	 */
	public String getLastName() {
		log.debug("getLastName() method called");
		return lastName;
	}

	/**
	 * Sets the last name of the user.
	 *
	 * @param lastName the last name
	 */
	public void setLastName(String lastName) {
		log.debug("setLastName() method called");
		this.lastName = lastName;
	}

	/**
	 * Gets the login ID of the user.
	 *
	 * @return login
	 */
	public String getLogin() {
		log.debug("getLogin() method called");
		return login;
	}

	/**
	 * Sets the login ID of the user.
	 *
	 * @param login the login ID
	 */
	public void setLogin(String login) {
		log.debug("setLogin() method called");
		this.login = login;
	}

	/**
	 * Gets the password of the user.
	 *
	 * @return password
	 */
	public String getPassword() {
		log.debug("getPassword() method called");
		return password;
	}

	/**
	 * Sets the password of the user.
	 *
	 * @param password the password
	 */
	public void setPassword(String password) {
		log.debug("setPassword() method called");
		this.password = password;
	}
	
	/**
	 * Gets the confirm password.
	 *
	 * @return confirmPassword
	 */
	public String getConfirmPassword() {
		log.debug("getConfirmPassword() method called");
		return confirmPassword;
	}

	/**
	 * Sets the confirm password.
	 *
	 * @param confirmPassword the confirm password
	 */
	public void setConfirmPassword(String confirmPassword) {
		log.debug("setConfirmPassword() method called");
		this.confirmPassword = confirmPassword;
	}

	/**
	 * Gets the date of birth of the user.
	 *
	 * @return dob
	 */
	public Date getDob() {
		log.debug("getDob() method called");
		return dob;
	}

	/**
	 * Sets the date of birth of the user.
	 *
	 * @param dob the date of birth
	 */
	public void setDob(Date dob) {
		log.debug("setDob() method called");
		this.dob = dob;
	}

	/**
	 * Gets the mobile number of the user.
	 *
	 * @return mobileNo
	 */
	public String getMobileNo() {
		log.debug("getMobileNo() method called");
		return mobileNo;
	}

	/**
	 * Sets the mobile number of the user.
	 *
	 * @param mobileNo the mobile number
	 */
	public void setMobileNo(String mobileNo) {
		log.debug("setMobileNo() method called");
		this.mobileNo = mobileNo;
	}

	/**
	 * Gets the role ID of the user.
	 *
	 * @return roleId
	 */
	public long getRoleId() {
		log.debug("getRoleId() method called");
		return roleId;
	}

	/**
	 * Sets the role ID of the user.
	 *
	 * @param roleId the role ID
	 */
	public void setRoleId(long roleId) {
		log.debug("setRoleId() method called");
		this.roleId = roleId;
	}

	/**
	 * Gets the unsuccessful login count.
	 *
	 * @return unsuccessfulLogin
	 */
	public int getUnsuccessfulLogin() {
		log.debug("getUnsuccessfulLogin() method called");
		return unsuccessfulLogin;
	}

	/**
	 * Sets the unsuccessful login count.
	 *
	 * @param unsuccessfulLogin the unsuccessful login count
	 */
	public void setUnsuccessfulLogin(int unsuccessfulLogin) {
		log.debug("setUnsuccessfulLogin() method called");
		this.unsuccessfulLogin = unsuccessfulLogin;
	}

	/**
	 * Gets the gender of the user.
	 *
	 * @return gender
	 */
	public String getGender() {
		log.debug("getGender() method called");
		return gender;
	}

	/**
	 * Sets the gender of the user.
	 *
	 * @param gender the gender
	 */
	public void setGender(String gender) {
		log.debug("setGender() method called");
		this.gender = gender;
	}

	/**
	 * Gets the last login timestamp.
	 *
	 * @return lastLogin
	 */
	public Timestamp getLastLogin() {
		log.debug("getLastLogin() method called");
		return lastLogin;
	}

	/**
	 * Sets the last login timestamp.
	 *
	 * @param lastLogin the last login timestamp
	 */
	public void setLastLogin(Timestamp lastLogin) {
		log.debug("setLastLogin() method called");
		this.lastLogin = lastLogin;
	}

	/**
	 * Gets the user lock status.
	 *
	 * @return userLock
	 */
	public String getUserLock() {
		log.debug("getUserLock() method called");
		return userLock;
	}

	/**
	 * Sets the user lock status.
	 *
	 * @param userLock the user lock status
	 */
	public void setUserLock(String userLock) {
		log.debug("setUserLock() method called");
		this.userLock = userLock;
	}

	/**
	 * Gets the registered IP address.
	 *
	 * @return registeredIp
	 */
	public String getRegisteredIp() {
		log.debug("getRegisteredIp() method called");
		return registeredIp;
	}

	/**
	 * Sets the registered IP address.
	 *
	 * @param registeredIp the registered IP address
	 */
	public void setRegisteredIp(String registeredIp) {
		log.debug("setRegisteredIp() method called");
		this.registeredIp = registeredIp;
	}

	/**
	 * Gets the last login IP address.
	 *
	 * @return lastLoginIp
	 */
	public String getLastLoginIp() {
		log.debug("getLastLoginIp() method called");
		return lastLoginIp;
	}

	/**
	 * Sets the last login IP address.
	 *
	 * @param lastLoginIp the last login IP address
	 */
	public void setLastLoginIp(String lastLoginIp) {
		log.debug("setLastLoginIp() method called");
		this.lastLoginIp = lastLoginIp;
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
	 * Sets the user details from the ResultSet.
	 *
	 * @param rs the ResultSet containing user records
	 */
	@Override
	public void setResultSet(ResultSet rs) {

		log.debug("setResultSet() method started");

		try {

			setFirstName(rs.getString("first_name"));
			setLastName(rs.getString("last_name"));
			setLogin(rs.getString("login"));
			setPassword(rs.getString("password"));
			setDob(rs.getTimestamp("dob"));
			setMobileNo(rs.getString("mobile_no"));
			setRoleId(rs.getLong("role_id"));
			setUnsuccessfulLogin(rs.getInt("unsuccessful_login"));
			setGender(rs.getString("gender"));
			setLastLogin(rs.getTimestamp("last_login"));
			setUserLock(rs.getString("user_lock"));
			setRegisteredIp(rs.getString("registered_ip"));
			setLastLoginIp(rs.getString("last_login_ip"));

			log.info("User ResultSet values assigned successfully");

		} catch (SQLException e) {

			log.error("Error while setting User ResultSet values", e);
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}

}
