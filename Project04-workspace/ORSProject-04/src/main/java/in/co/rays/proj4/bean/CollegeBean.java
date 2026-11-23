
package in.co.rays.proj4.bean;

import java.sql.ResultSet;

import org.apache.log4j.Logger;

/**
 * CollegeBean class is used to store and manage college details.
 * It extends BaseBean to inherit common properties.
 * 
 * @author Abhay Malve
 * @version 1.0
 */
public class CollegeBean extends BaseBean {

    private static final Logger log = Logger.getLogger(CollegeBean.class);

    private String name;

    private String address;

    private String state;

    private String city;

    private String phoneNo;

    /**
     * Gets the college name.
     * 
     * @return name
     */
    public String getName() {

        log.debug("getName() method called");

        return name;
    }

    /**
     * Sets the college name.
     * 
     * @param name
     */
    public void setName(String name) {

        log.debug("setName() method called");

        this.name = name;
    }

    /**
     * Gets the college address.
     * 
     * @return address
     */
    public String getAddress() {

        log.debug("getAddress() method called");

        return address;
    }

    /**
     * Sets the college address.
     * 
     * @param address
     */
    public void setAddress(String address) {

        log.debug("setAddress() method called");

        this.address = address;
    }

    /**
     * Gets the college state.
     * 
     * @return state
     */
    public String getState() {

        log.debug("getState() method called");

        return state;
    }

    /**
     * Sets the college state.
     * 
     * @param state
     */
    public void setState(String state) {

        log.debug("setState() method called");

        this.state = state;
    }

    /**
     * Gets the college city.
     * 
     * @return city
     */
    public String getCity() {

        log.debug("getCity() method called");

        return city;
    }

    /**
     * Sets the college city.
     * 
     * @param city
     */
    public void setCity(String city) {

        log.debug("setCity() method called");

        this.city = city;
    }

    /**
     * Gets the college phone number.
     * 
     * @return phoneNo
     */
    public String getPhoneNo() {

        log.debug("getPhoneNo() method called");

        return phoneNo;
    }

    /**
     * Sets the college phone number.
     * 
     * @param phoneNo
     */
    public void setPhoneNo(String phoneNo) {

        log.debug("setPhoneNo() method called");

        this.phoneNo = phoneNo;
    }

    /**
     * Returns the college name as a value.
     * 
     * @return name
     */
    @Override
    public String getValue() {

        log.info("getValue() method called");

        return name;
    }

    /**
     * Sets college properties using the values from ResultSet.
     * 
     * @param rs ResultSet containing college details
     */
    @Override
    public void setResultSet(ResultSet rs) {

        log.debug("setResultSet() method started");

        try {

            setName(rs.getString("name"));
            setAddress(rs.getString("address"));
            setState(rs.getString("state"));
            setCity(rs.getString("city"));
            setPhoneNo(rs.getString("phone_no"));

            log.info("College ResultSet values assigned successfully");

        } catch (Exception e) {

            log.error("Error while setting College ResultSet values", e);
            e.printStackTrace();
        }

        super.setResultSet(rs);
    }

}
