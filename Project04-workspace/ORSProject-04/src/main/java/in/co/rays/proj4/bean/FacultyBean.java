
package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * FacultyBean class is used to store and manage faculty details.
 * It extends BaseBean to inherit common properties.
 * 
 * @author Abhay Malve
 * @version 1.0
 */
public class FacultyBean extends BaseBean {

    private static final Logger log = Logger.getLogger(FacultyBean.class);

    private long collegeId;
    private String collegeName;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNo;
    private String address;
    private String gender;
    private Date dateOfBirth;

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
     * @param collegeId
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
     * @param collegeName
     */
    public void setCollegeName(String collegeName) {
        log.debug("setCollegeName() method called");
        this.collegeName = collegeName;
    }

    /**
     * Gets the first name.
     * 
     * @return firstName
     */
    public String getFirstName() {
        log.debug("getFirstName() method called");
        return firstName;
    }

    /**
     * Sets the first name.
     * 
     * @param firstName
     */
    public void setFirstName(String firstName) {
        log.debug("setFirstName() method called");
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     * 
     * @return lastName
     */
    public String getLastName() {
        log.debug("getLastName() method called");
        return lastName;
    }

    /**
     * Sets the last name.
     * 
     * @param lastName
     */
    public void setLastName(String lastName) {
        log.debug("setLastName() method called");
        this.lastName = lastName;
    }

    /**
     * Gets the email address.
     * 
     * @return email
     */
    public String getEmail() {
        log.debug("getEmail() method called");
        return email;
    }

    /**
     * Sets the email address.
     * 
     * @param email
     */
    public void setEmail(String email) {
        log.debug("setEmail() method called");
        this.email = email;
    }

    /**
     * Gets the mobile number.
     * 
     * @return mobileNo
     */
    public String getMobileNo() {
        log.debug("getMobileNo() method called");
        return mobileNo;
    }

    /**
     * Sets the mobile number.
     * 
     * @param mobileNo
     */
    public void setMobileNo(String mobileNo) {
        log.debug("setMobileNo() method called");
        this.mobileNo = mobileNo;
    }

    /**
     * Gets the address.
     * 
     * @return address
     */
    public String getAddress() {
        log.debug("getAddress() method called");
        return address;
    }

    /**
     * Sets the address.
     * 
     * @param address
     */
    public void setAddress(String address) {
        log.debug("setAddress() method called");
        this.address = address;
    }

    /**
     * Gets the gender.
     * 
     * @return gender
     */
    public String getGender() {
        log.debug("getGender() method called");
        return gender;
    }

    /**
     * Sets the gender.
     * 
     * @param gender
     */
    public void setGender(String gender) {
        log.debug("setGender() method called");
        this.gender = gender;
    }

    /**
     * Gets the date of birth.
     * 
     * @return dateOfBirth
     */
    public Date getDateOfBirth() {
        log.debug("getDateOfBirth() method called");
        return dateOfBirth;
    }

    /**
     * Sets the date of birth.
     * 
     * @param dateOfBirth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        log.debug("setDateOfBirth() method called");
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Returns the faculty value.
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
     * Sets faculty properties using the values from ResultSet.
     * 
     * @param rs ResultSet containing faculty details
     */
    @Override
    public void setResultSet(ResultSet rs) {

        log.debug("setResultSet() method started");

        try {
            setCollegeId(rs.getLong("college_id"));
            setCollegeName(rs.getString("college_name"));
            setFirstName(rs.getString("first_name"));
            setLastName(rs.getString("last_name"));
            setEmail(rs.getString("email"));
            setMobileNo(rs.getString("mobile_no"));
            setAddress(rs.getString("address"));
            setGender(rs.getString("gender"));
            setDateOfBirth(rs.getDate("date_of_birth"));

            log.info("Faculty ResultSet values assigned successfully");

        } catch (SQLException e) {

            log.error("Error while setting Faculty ResultSet values", e);
            e.printStackTrace();
        }

        super.setResultSet(rs);
    }

}
