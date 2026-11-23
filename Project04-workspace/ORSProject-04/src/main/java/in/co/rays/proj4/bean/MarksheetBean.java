
package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * MarksheetBean class is used to store and manage student marksheet details.
 * It extends BaseBean to inherit common properties.
 * 
 * @author Abhay Malve
 * @version 1.0
 */
public class MarksheetBean extends BaseBean {

	private static final Logger log = Logger.getLogger(MarksheetBean.class);

	private String rollNo;
	private long studentId;
	private String name;
	private int physics;
	private int chemistry;
	private int maths;

	/**
	 * Gets the roll number.
	 * 
	 * @return rollNo
	 */
	public String getRollNo() {
		log.debug("getRollNo() method called");
		return rollNo;
	}

	/**
	 * Sets the roll number.
	 * 
	 * @param rollNo
	 */
	public void setRollNo(String rollNo) {
		log.debug("setRollNo() method called");
		this.rollNo = rollNo;
	}

	/**
	 * Gets the student ID.
	 * 
	 * @return studentId
	 */
	public long getStudentId() {
		log.debug("getStudentId() method called");
		return studentId;
	}

	/**
	 * Sets the student ID.
	 * 
	 * @param studentId
	 */
	public void setStudentId(long studentId) {
		log.debug("setStudentId() method called");
		this.studentId = studentId;
	}

	/**
	 * Gets the student name.
	 * 
	 * @return name
	 */
	public String getName() {
		log.debug("getName() method called");
		return name;
	}

	/**
	 * Sets the student name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		log.debug("setName() method called");
		this.name = name;
	}

	/**
	 * Gets the physics marks.
	 * 
	 * @return physics
	 */
	public int getPhysics() {
		log.debug("getPhysics() method called");
		return physics;
	}

	/**
	 * Sets the physics marks.
	 * 
	 * @param physics
	 */
	public void setPhysics(int physics) {
		log.debug("setPhysics() method called");
		this.physics = physics;
	}

	/**
	 * Gets the chemistry marks.
	 * 
	 * @return chemistry
	 */
	public int getChemistry() {
		log.debug("getChemistry() method called");
		return chemistry;
	}

	/**
	 * Sets the chemistry marks.
	 * 
	 * @param chemistry
	 */
	public void setChemistry(int chemistry) {
		log.debug("setChemistry() method called");
		this.chemistry = chemistry;
	}

	/**
	 * Gets the maths marks.
	 * 
	 * @return maths
	 */
	public int getMaths() {
		log.debug("getMaths() method called");
		return maths;
	}

	/**
	 * Sets the maths marks.
	 * 
	 * @param maths
	 */
	public void setMaths(int maths) {
		log.debug("setMaths() method called");
		this.maths = maths;
	}

	/**
	 * Returns the marksheet value.
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
	 * Sets marksheet properties using the values from ResultSet.
	 * 
	 * @param rs ResultSet containing marksheet details
	 */
	@Override
	public void setResultSet(ResultSet rs) {

		log.debug("setResultSet() method started");

		try {
			setRollNo(rs.getString("roll_no"));
			setStudentId(rs.getLong("student_id"));
			setName(rs.getString("name"));
			setPhysics(rs.getInt("physics"));
			setChemistry(rs.getInt("chemistry"));
			setMaths(rs.getInt("maths"));

			log.info("Marksheet ResultSet values assigned successfully");
			
		} catch (SQLException e) {

			log.error("Error while setting Marksheet ResultSet values", e);
			e.printStackTrace();
		}
		
		super.setResultSet(rs);
	}

}
