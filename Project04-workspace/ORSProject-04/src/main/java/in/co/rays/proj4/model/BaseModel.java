
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DatabaseException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * BaseModel is an abstract base class that provides common database
 * operations such as add, update, delete, search and find records.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public abstract class BaseModel<T extends BaseBean> {

	private static Logger log = Logger.getLogger(BaseModel.class);

	/**
	 * Adds a new record to the database.
	 *
	 * @param bean the bean object containing record details
	 * @return the primary key of the inserted record
	 * @throws ApplicationException if an application error occurs
	 * @throws DuplicateRecordException if a duplicate record exists
	 */
	public abstract long add(T bean) throws ApplicationException, DuplicateRecordException;

	/**
	 * Updates an existing record in the database.
	 *
	 * @param bean the bean object containing updated details
	 * @throws ApplicationException if an application error occurs
	 * @throws DuplicateRecordException if a duplicate record exists
	 */
	public abstract void update(T bean) throws ApplicationException, DuplicateRecordException;

	/**
	 * Returns the WHERE clause used for searching records.
	 *
	 * @param bean the bean object containing search criteria
	 * @return the SQL WHERE condition
	 */
	public abstract String getWhereClause(T bean);

	/**
	 * Returns the database table name.
	 *
	 * @return the table name
	 */
	public abstract String getTable();

	/**
	 * Creates and returns a new bean object.
	 *
	 * @return a new bean instance
	 */
	public abstract T getBean();

	/**
	 * Generates the next primary key by finding the maximum
	 * ID value from the database table.
	 *
	 * @return the next primary key
	 * @throws DatabaseException if an error occurs while retrieving the primary key
	 */
	public Integer nextPk() throws DatabaseException {

		log.debug("BaseModel nextPk() started");

		Connection conn = null;

		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();

			log.info("Database connection established for nextPk()");

			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM " + getTable());

			log.debug("Executing query to get maximum primary key");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				pk = rs.getInt(1);

			}

			rs.close();

			log.info("Next primary key generated: " + (pk + 1));

		} catch (Exception e) {

			log.error("Exception occurred while getting primary key", e);

			throw new DatabaseException("Exception : Exception in getting PK");

		} finally {

			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed in nextPk()");

		}

		return pk + 1;

	}

	/**
	 * Deletes a record from the database using its primary key.
	 *
	 * @param id the primary key of the record to be deleted
	 * @throws DatabaseException if a database error occurs
	 */
	public void delete(int id) throws DatabaseException {

		log.debug("BaseModel delete() started for ID: " + id);

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			log.info("Database connection established for delete()");

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("delete from " + getTable() + " where id = ? ");

			pstmt.setInt(1, id);

			log.debug("Executing delete query for ID: " + id);

			int i = pstmt.executeUpdate();

			System.out.println("record deleted: " + i);

			conn.commit();

			log.info("Record deleted successfully. ID: " + id);

			if (i == 0) {
				log.warn("No record found to delete for ID: " + id);
			}

		} catch (Exception e) {

			log.error("Exception occurred while deleting record. ID: " + id, e);

			e.printStackTrace();

			JDBCDataSource.trnRollBack(conn);

			log.warn("Transaction rollback performed in delete()");

		} finally {
			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed in delete()");
		}
	}

	/**
	 * Finds a record from the database using its primary key.
	 *
	 * @param id the primary key of the record
	 * @return the matching bean object, or null if no record is found
	 */
	public T findByPk(long id) {

		log.debug("BaseModel findByPk() started for ID: " + id);

		Connection conn = null;
		T bean = null;

		try {

			conn = JDBCDataSource.getConnection();

			log.info("Database connection established for findByPk()");

			PreparedStatement pstmt = conn.prepareStatement("select * from " + getTable() + " where id = ? ");
			pstmt.setLong(1, id);

			log.debug("Executing findByPk query for ID: " + id);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
			}

			if (bean == null) {
				log.warn("No record found for ID: " + id);
			} else {
				log.info("Record found successfully for ID: " + id);
			}

		} catch (Exception e) {

			log.error("Exception occurred while finding record by ID: " + id, e);

			e.printStackTrace();
		} finally {

			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed in findByPk()");
		}

		return bean;

	}

	/**
	 * Searches records from the database using the supplied bean
	 * as search criteria and supports pagination.
	 *
	 * @param bean the bean containing search criteria
	 * @param pageNo the page number to retrieve
	 * @param pageSize the number of records per page
	 * @return a list of matching records
	 */
	public List<T> search(T bean, int pageNo, int pageSize) {

		log.debug("BaseModel search() started");

		Connection conn = null;
		List<T> list = new ArrayList<T>();

		StringBuffer sql = new StringBuffer("select * from " + getTable() + " where 1 = 1");

		sql.append(getWhereClause(bean));

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		System.out.println("sql ===> " + sql.toString());

		log.debug("Search SQL query: " + sql.toString());

		try {

			conn = JDBCDataSource.getConnection();

			log.info("Database connection established for search()");

			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			log.debug("Executing search query");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
				list.add(bean);
			}

			log.info("Search completed. Total records found: " + list.size());

			if (list.isEmpty()) {
				log.warn("No records found in search()");
			}

		} catch (Exception e) {

			log.error("Exception occurred while searching records", e);

			e.printStackTrace();

		} finally {
			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed in search()");
		}

		return list;

	}

	/**
	 * Finds a record using a unique column and its value.
	 *
	 * @param column the name of the unique database column
	 * @param value the value to search for
	 * @return the matching bean object, or null if no record is found
	 */
	public T findByUniqueColumn(String column, String value) {

		log.debug("BaseModel findByUniqueColumn() started");

		Connection conn = null;

		T bean = null;

		try {

			conn = JDBCDataSource.getConnection();

			log.info("Database connection established for findByUniqueColumn()");

			PreparedStatement pstmt = conn.prepareStatement(
					"select * from " + getTable() + " where " + column + " = ?");

			pstmt.setString(1, value);

			log.debug("Executing findByUniqueColumn query for column: " + column);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = getBean();
				bean.setResultSet(rs);
			}

			if (bean == null) {
				log.warn("No record found for column: " + column);
			} else {
				log.info("Record found successfully using column: " + column);
			}

		} catch (Exception e) {

			log.error("Exception occurred in findByUniqueColumn()", e);

			e.printStackTrace();

		} finally {
			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed in findByUniqueColumn()");
		}

		return bean;
	}
}
