
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * CollegeModel is responsible for performing database operations
 * related to college records such as adding, updating, searching
 * and retrieving college details.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class CollegeModel extends BaseModel<CollegeBean> {

	private static Logger log = Logger.getLogger(CollegeModel.class);

	/**
	 * Adds a new college record to the database.
	 *
	 * @param bean the CollegeBean containing college details
	 * @return the primary key of the inserted college record
	 * @throws ApplicationException if an application error occurs
	 * @throws DuplicateRecordException if the college name already exists
	 */
	@Override
	public long add(CollegeBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("CollegeModel add() started");

		Connection conn = null;

		CollegeBean existBean = findByName(bean.getName());

		if (existBean != null) {

			log.warn("College already exists with name: " + bean.getName());

			throw new DuplicateRecordException("name already exist");

		}

		int pk = 0;

		try {
			pk = nextPk();

			log.debug("Generated college primary key: " + pk);

			conn = JDBCDataSource.getConnection();

			log.info("Database connection established for adding college");

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getAddress());
			pstmt.setString(4, bean.getState());
			pstmt.setString(5, bean.getCity());
			pstmt.setString(6, bean.getPhoneNo());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());

			log.debug("Executing insert query for college: " + bean.getName());

			pstmt.executeUpdate();
			conn.commit();

			log.info("College record inserted successfully. ID: " + pk);

			System.out.println("record inserted successfully");

		} catch (Exception e) {

			log.error("Exception occurred while adding college record", e);

			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

			log.warn("Transaction rollback performed in CollegeModel add()");

		} finally {
			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed in CollegeModel add()");
		}

		return pk;
	}

	/**
	 * Updates an existing college record in the database.
	 *
	 * @param bean the CollegeBean containing updated college details
	 * @throws ApplicationException if an application error occurs
	 * @throws DuplicateRecordException if the college name already exists
	 */
	@Override
	public void update(CollegeBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("CollegeModel update() started for ID: " + bean.getId());

		Connection conn = null;

		CollegeBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {

			log.warn("College name already exists: " + bean.getName());

			throw new DuplicateRecordException("name already exist");

		}

		try {

			conn = JDBCDataSource.getConnection();

			log.info("Database connection established for updating college");

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable() + " set name = ?, address = ?, state = ?, city = ?, phone_no= ?, created_by = ?, modified_by= ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getState());
			pstmt.setString(4, bean.getCity());
			pstmt.setString(5, bean.getPhoneNo());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setLong(10, bean.getId());

			log.debug("Executing update query for college ID: " + bean.getId());

			pstmt.executeUpdate();
			conn.commit();

			log.info("College record updated successfully. ID: " + bean.getId());

			System.out.println("record updated successfully");

		} catch (Exception e) {

			log.error("Exception occurred while updating college record. ID: " + bean.getId(), e);

			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

			log.warn("Transaction rollback performed in CollegeModel update()");

		} finally {
			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed in CollegeModel update()");

		}
	}

	/**
	 * Generates the WHERE clause based on the supplied college details.
	 *
	 * @param bean the CollegeBean containing search criteria
	 * @return the SQL WHERE clause as a String
	 */
	@Override
	public String getWhereClause(CollegeBean bean) {

		log.debug("CollegeModel getWhereClause() started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());

			}

			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}

			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" and address like '" + bean.getAddress() + "%'");
			}

			if (bean.getState() != null && bean.getState().length() > 0) {
				sql.append(" and state like '" + bean.getState() + "%'");

			}

			if (bean.getCity() != null && bean.getCity().length() > 0) {
				sql.append(" and city like '" + bean.getCity() + "%'");
			}

		}

		log.debug("Generated WHERE clause: " + sql.toString());

		return sql.toString();
	}

	/**
	 * Finds a college record using its name.
	 *
	 * @param name the name of the college
	 * @return the matching CollegeBean object
	 */
	public CollegeBean findByName(String name) {

		log.debug("CollegeModel findByName() started for name: " + name);

		CollegeBean bean = findByUniqueColumn("name", name);

		if (bean != null) {
			log.info("College found with name: " + name);
		} else {
			log.warn("College not found with name: " + name);
		}

		return bean;
	}

	/**
	 * Returns the college database table name.
	 *
	 * @return the college table name
	 */
	@Override
	public String getTable() {

		log.debug("CollegeModel getTable() called");

		return "st_college";
	}

	/**
	 * Creates and returns a new CollegeBean object.
	 *
	 * @return a new CollegeBean instance
	 */
	@Override
	public CollegeBean getBean() {

		log.debug("CollegeModel getBean() called");

		return new CollegeBean();
	}

}
