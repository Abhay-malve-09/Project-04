
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * CourseModel class handles database operations related to Course.
 * It provides methods to add, update, search and retrieve course records.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class CourseModel extends BaseModel<CourseBean> {

	private static Logger log = Logger.getLogger(CourseModel.class);

	/**
	 * Adds a new course record into the database.
	 *
	 * @param bean CourseBean object containing course details
	 * @return primary key of the inserted record
	 * @throws ApplicationException if application error occurs
	 * @throws DuplicateRecordException if course name already exists
	 */
	@Override
	public long add(CourseBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("CourseModel add() started");

		Connection cnn = null;

		CourseBean existBean = findByName(bean.getName());

		if (existBean != null) {

			log.warn("Course already exists with name: " + bean.getName());

			throw new DuplicateRecordException("name already exist");

		}

		int pk = 0;

		try {

			pk = nextPk();

			log.debug("Generated course primary key: " + pk);

			cnn = JDBCDataSource.getConnection();

			log.info("Database connection established for adding course");

			cnn.setAutoCommit(false);

			PreparedStatement pstmt = cnn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getDuration());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());

			log.debug("Executing insert query for course: " + bean.getName());

			pstmt.executeUpdate();
			cnn.commit();

			log.info("Course record inserted successfully. ID: " + pk);

			System.out.println("record inserted successfully");

		} catch (Exception e) {

			log.error("Exception occurred while adding course record", e);

			e.printStackTrace();
			JDBCDataSource.trnRollBack(cnn);

			log.warn("Transaction rollback performed in CourseModel add()");

		} finally {
			JDBCDataSource.closeConnection(cnn);

			log.debug("Database connection closed in CourseModel add()");
		}
		return pk;
	}

	/**
	 * Updates an existing course record in the database.
	 *
	 * @param bean CourseBean object containing updated course details
	 * @throws ApplicationException if application error occurs
	 * @throws DuplicateRecordException if course name already exists
	 */
	@Override
	public void update(CourseBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("CourseModel update() started for ID: " + bean.getId());

		Connection cnn = null;

		CourseBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {

			log.warn("Course name already exists: " + bean.getName());

			throw new DuplicateRecordException("name already exist");

		}

		try {

			cnn = JDBCDataSource.getConnection();

			log.info("Database connection established for updating course");

			cnn.setAutoCommit(false);

			PreparedStatement pstmt = cnn.prepareStatement("update " + getTable() + " set name = ?, description = ?, duration = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getDuration());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());

			log.debug("Executing update query for course ID: " + bean.getId());

			pstmt.executeUpdate();

			cnn.commit();

			log.info("Course record updated successfully. ID: " + bean.getId());

			System.out.println("record updated successfully");

		} catch (Exception e) {

			log.error("Exception occurred while updating course record. ID: " + bean.getId(), e);

			e.printStackTrace();
			JDBCDataSource.trnRollBack(cnn);

			log.warn("Transaction rollback performed in CourseModel update()");

		} finally {
			JDBCDataSource.closeConnection(cnn);

			log.debug("Database connection closed in CourseModel update()");
		}
	}

	/**
	 * Generates the WHERE clause for course searching.
	 *
	 * @param bean CourseBean object containing search criteria
	 * @return SQL WHERE condition as a String
	 */
	@Override
	public String getWhereClause(CourseBean bean) {

		log.debug("CourseModel getWhereClause() started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}

			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "%'");
			}

			if (bean.getDuration() != null && bean.getDuration().length() > 0) {
				sql.append(" and duration like '" + bean.getDuration() + "%'");
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

		log.debug("Generated WHERE clause: " + sql.toString());

		return sql.toString();
	}

	/**
	 * Finds a course record using its name.
	 *
	 * @param name course name to search
	 * @return CourseBean object if found, otherwise null
	 */
	public CourseBean findByName(String name) {

		log.debug("CourseModel findByName() started for name: " + name);

		CourseBean bean = findByUniqueColumn("name", name);

		if (bean != null) {
			log.info("Course found with name: " + name);
		} else {
			log.warn("Course not found with name: " + name);
		}

		return bean;

	}

	/**
	 * Returns the database table name of Course.
	 *
	 * @return course table name
	 */
	@Override
	public String getTable() {

		log.debug("CourseModel getTable() called");

		return "st_course";
	}

	/**
	 * Creates and returns a new CourseBean object.
	 *
	 * @return new CourseBean object
	 */
	@Override
	public CourseBean getBean() {

		log.debug("CourseModel getBean() called");

		return new CourseBean();
	}

}
