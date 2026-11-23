
package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * CourseBean class is used to store and manage course details.
 * It extends BaseBean to inherit common properties.
 * 
 * @author Abhay Malve
 * @version 1.0
 */
public class CourseBean extends BaseBean {

    private static final Logger log = Logger.getLogger(CourseBean.class);

    private String name;
    private String description;
    private String duration;

    /**
     * Gets the course name.
     * 
     * @return name
     */
    public String getName() {
        log.debug("getName() method called");
        return name;
    }

    /**
     * Sets the course name.
     * 
     * @param name
     */
    public void setName(String name) {
        log.debug("setName() method called");
        this.name = name;
    }

    /**
     * Gets the course description.
     * 
     * @return description
     */
    public String getDescription() {
        log.debug("getDescription() method called");
        return description;
    }

    /**
     * Sets the course description.
     * 
     * @param description
     */
    public void setDescription(String description) {
        log.debug("setDescription() method called");
        this.description = description;
    }

    /**
     * Gets the course duration.
     * 
     * @return duration
     */
    public String getDuration() {
        log.debug("getDuration() method called");
        return duration;
    }

    /**
     * Sets the course duration.
     * 
     * @param duration
     */
    public void setDuration(String duration) {
        log.debug("setDuration() method called");
        this.duration = duration;
    }

    /**
     * Returns the course value.
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
     * Sets course properties using the values from ResultSet.
     * 
     * @param rs ResultSet containing course details
     */
    @Override
    public void setResultSet(ResultSet rs) {

        log.debug("setResultSet() method started");

        try {
            setName(rs.getString("name"));
            setDescription(rs.getString("description"));
            setDuration(rs.getString("duration"));

            log.info("Course ResultSet values assigned successfully");

        } catch (SQLException e) {

            log.error("Error while setting Course ResultSet values", e);
            e.printStackTrace();
        }

        super.setResultSet(rs);
    }
}
