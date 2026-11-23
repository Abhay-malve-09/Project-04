
package in.co.rays.proj4.bean;

import java.sql.Timestamp;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

/**
 * BaseBean is an abstract class that provides common properties
 * and methods for all bean classes.
 * 
 * @author Abhay Malve
 * @version 1.0
 */
public abstract class BaseBean implements DropdownListBean {

    private static final Logger log = Logger.getLogger(BaseBean.class);

    protected long id;
    protected String createdBy;
    protected String modifiedBy;
    protected Timestamp createdDatetime;
    protected Timestamp modifiedDatetime;

    /**
     * Gets the ID.
     * 
     * @return id
     */
    public long getId() {
        log.debug("getId() method called");
        return id;
    }

    /**
     * Sets the ID.
     * 
     * @param id
     */
    public void setId(long id) {
        log.debug("setId() method called");
        this.id = id;
    }

    /**
     * Gets the creator name.
     * 
     * @return createdBy
     */
    public String getCreatedBy() {
        log.debug("getCreatedBy() method called");
        return createdBy;
    }

    /**
     * Sets the creator name.
     * 
     * @param createdBy
     */
    public void setCreatedBy(String createdBy) {
        log.debug("setCreatedBy() method called");
        this.createdBy = createdBy;
    }

    /**
     * Gets the modifier name.
     * 
     * @return modifiedBy
     */
    public String getModifiedBy() {
        log.debug("getModifiedBy() method called");
        return modifiedBy;
    }

    /**
     * Sets the modifier name.
     * 
     * @param modifiedBy
     */
    public void setModifiedBy(String modifiedBy) {
        log.debug("setModifiedBy() method called");
        this.modifiedBy = modifiedBy;
    }

    /**
     * Gets the creation datetime.
     * 
     * @return createdDatetime
     */
    public Timestamp getCreatedDatetime() {
        log.debug("getCreatedDatetime() method called");
        return createdDatetime;
    }

    /**
     * Sets the creation datetime.
     * 
     * @param createdDatetime
     */
    public void setCreatedDatetime(Timestamp createdDatetime) {
        log.debug("setCreatedDatetime() method called");
        this.createdDatetime = createdDatetime;
    }

    /**
     * Gets the modification datetime.
     * 
     * @return modifiedDatetime
     */
    public Timestamp getModifiedDatetime() {
        log.debug("getModifiedDatetime() method called");
        return modifiedDatetime;
    }

    /**
     * Sets the modification datetime.
     * 
     * @param modifiedDatetime
     */
    public void setModifiedDatetime(Timestamp modifiedDatetime) {
        log.debug("setModifiedDatetime() method called");
        this.modifiedDatetime = modifiedDatetime;
    }

    /**
     * Returns the ID as a String key.
     * 
     * @return id as String
     */
    @Override
    public String getKey() {

        log.info("getKey() method called");

        return id + "";
    }

    /**
     * Sets bean properties using the values from ResultSet.
     * 
     * @param rs ResultSet containing bean data
     */
    public void setResultSet(ResultSet rs) {

        log.debug("setResultSet() method started");

        try {

            setId(rs.getLong("id"));
            setCreatedBy(rs.getString("created_by"));
            setModifiedBy(rs.getString("modified_by"));
            setCreatedDatetime(rs.getTimestamp("created_datetime"));
            setModifiedDatetime(rs.getTimestamp("modified_datetime"));

            log.info("ResultSet values assigned successfully");

        } catch (Exception e) {

            log.error("Error while setting ResultSet values", e);
            e.printStackTrace();

        }
    }

}
