
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * RoleModel class provides database operations for Role entity.
 * It extends BaseModel and implements methods for adding,
 * updating, searching and retrieving role records.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class RoleModel extends BaseModel<RoleBean>{

	private static Logger log = Logger.getLogger(RoleModel.class);

	/**
	 * Adds a new role record into the database.
	 *
	 * @param bean RoleBean object containing role details
	 * @return primary key of the inserted record
	 * @throws ApplicationException if an application error occurs
	 * @throws DuplicateRecordException if a duplicate record exists
	 */
	@Override
	public long add(RoleBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("RoleModel add() started");

		Connection conn = null;

		int pk = 0;

		try {
			pk = nextPk();

			log.debug("Generated role primary key: " + pk);

			conn = JDBCDataSource.getConnection();

			log.info("Database connection established for adding role");

			conn.setAutoCommit(false); //begin transaction

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?, ?, ?, ?, ?, ?, ?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());

			log.debug("Executing insert query for role: " + bean.getName());

			pstmt.executeUpdate();
			conn.commit();

			log.info("Role record inserted successfully. ID: " + pk);

		    System.out.println("recored inserted successfully");

		} catch (Exception e) {

			log.error("Exception occurred while adding role record", e);

			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

			log.warn("Transaction rollback performed in RoleModel add()");

		} finally {
			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed in RoleModel add()");
		}

		return pk;
	}

	/**
	 * Updates an existing role record in the database.
	 *
	 * @param bean RoleBean object containing updated role details
	 * @throws ApplicationException if an application error occurs
	 * @throws DuplicateRecordException if a duplicate record exists
	 */
	@Override
	public void update(RoleBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("RoleModel update() started for ID: " + bean.getId());

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			log.info("Database connection established for updating role");

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable() + " set name = ?, description = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getCreatedBy());
			pstmt.setString(4, bean.getModifiedBy());
			pstmt.setTimestamp(5, bean.getCreatedDatetime());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setLong(7,bean.getId());

			log.debug("Executing update query for role ID: " + bean.getId());

			pstmt.executeUpdate();
			conn.commit();

			log.info("Role record updated successfully. ID: " + bean.getId());

			System.out.println("record updated successfully");

		} catch (Exception e) {

			log.error("Exception occurred while updating role record. ID: " + bean.getId(), e);

			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

			log.warn("Transaction rollback performed in RoleModel update()");

		} finally {
			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed in RoleModel update()");
		}

	}

	/**
	 * Generates the WHERE clause based on the provided RoleBean fields.
	 *
	 * @param bean RoleBean object containing search criteria
	 * @return SQL WHERE conditions as a String
	 */
	@Override
	public String getWhereClause(RoleBean bean) {

		log.debug("RoleModel getWhereClause() started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}

			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}

			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "%'");
			}

			if (bean.getCreatedBy() != null && bean.getCreatedBy().length() > 0) {
				sql.append(" and createdBy like '" + bean.getCreatedBy() + "%'");
			}

			if (bean.getModifiedBy() != null && bean.getModifiedBy().length() > 0) {
				sql.append(" and modifiedBy like '" + bean.getModifiedBy() + "%'");
			}

			if (bean.getCreatedDatetime() != null) {
				sql.append(" and createdDateTime like '" + bean.getCreatedDatetime() + "%'");
			}

			if (bean.getModifiedDatetime() != null) {
				sql.append(" and modifiedDateTime like '" + bean.getModifiedDatetime() + "%");
			}
		}

		log.debug("Generated role WHERE clause: " + sql.toString());

		return sql.toString();
	}

	/**
	 * Returns the database table name for Role entity.
	 *
	 * @return table name
	 */
	@Override
	public String getTable() {

		log.debug("RoleModel getTable() called");

		return "st_role";
	}

	/**
	 * Creates and returns a new RoleBean object.
	 *
	 * @return new RoleBean instance
	 */
	@Override
	public RoleBean getBean() {

		log.debug("RoleModel getBean() called");

		return new RoleBean();
	}

}
