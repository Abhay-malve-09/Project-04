
package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * RoleBean class is used to store and manage role details.
 * It extends BaseBean to inherit common properties.
 * 
 * @author Abhay Malve
 * @version 1.0
 */
public class RoleBean extends BaseBean {

	private static final Logger log = Logger.getLogger(RoleBean.class);

	private String name;
	private String description;

	/**
	 * Gets the role name.
	 * 
	 * @return name
	 */
	public String getName() {
		log.debug("getName() method called");
		return name;
	}

	/**
	 * Sets the role name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		log.debug("setName() method called");
		this.name = name;
	}

	/**
	 * Gets the role description.
	 * 
	 * @return description
	 */
	public String getDescription() {
		log.debug("getDescription() method called");
		return description;
	}

	/**
	 * Sets the role description.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		log.debug("setDescription() method called");
		this.description = description;
	}

	/**
	 * Returns the role name as a value.
	 * 
	 * @return name
	 */
	@Override
	public String getValue() {
		log.info("getValue() method called");
		return name;
	}

	/**
	 * Sets role properties using the values from ResultSet.
	 * 
	 * @param rs ResultSet containing role details
	 */
	@Override
	public void setResultSet(ResultSet rs) {

		log.debug("setResultSet() method started");

		try {
			setName(rs.getString("name"));
			setDescription(rs.getString("description"));

			log.info("Role ResultSet values assigned successfully");

		} catch (SQLException e) {
			log.error("Error while setting Role ResultSet values", e);
			e.printStackTrace();
		}
		super.setResultSet(rs);
	}
}
