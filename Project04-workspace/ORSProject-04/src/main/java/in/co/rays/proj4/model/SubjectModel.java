
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * SubjectModel class provides database operations for Subject records.
 * It supports adding, updating, searching and retrieving subject records.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class SubjectModel extends BaseModel<SubjectBean> {

	private static Logger log = Logger.getLogger(SubjectModel.class);

	/**
	 * Adds a new subject record into the database.
	 * Checks for duplicate subject name and retrieves course details.
	 *
	 * @param bean SubjectBean object containing subject details
	 * @return primary key of the inserted record
	 * @throws ApplicationException if application error occurs
	 * @throws DuplicateRecordException if subject name already exists
	 */
	@Override
	public long add(SubjectBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("SubjectModel add() method started");

		Connection cnn = null;

		SubjectBean existBean = findByName(bean.getName());

		if (existBean != null) {

			log.warn("Subject already exists with name: " + bean.getName());

			throw new DuplicateRecordException("name already exist");

		}

		//Foregin key concept
		CourseModel cmodel = new CourseModel();

		log.debug("Finding course using course ID: " + bean.getCourseId());

		CourseBean cbean = cmodel.findByPk(bean.getCourseId());

		log.info("Course details retrieved successfully");

		System.out.println("course Name = " + cbean.getName());

		int pk = 0;

		try {

			pk = nextPk();

			log.debug("Generated subject primary key: " + pk);

			cnn = JDBCDataSource.getConnection();

			log.info("Database connection established for adding subject");

			cnn.setAutoCommit(false);

			PreparedStatement pstmt = cnn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			// add cbean
			pstmt.setString(2, cbean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setLong(4, bean.getCourseId());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());

			log.debug("Executing insert query for subject: " + bean.getName());

			pstmt.executeUpdate();
			cnn.commit();

			log.info("Subject record inserted successfully. ID: " + pk);

			System.out.println("record inserted successfully");

		} catch (Exception e) {

			log.error("Exception occurred while adding subject record", e);

			e.printStackTrace();
			JDBCDataSource.trnRollBack(cnn);

			log.warn("Transaction rollback performed in SubjectModel add()");

		} finally {
			JDBCDataSource.closeConnection(cnn);

			log.debug("Database connection closed in SubjectModel add()");
		}

		return pk;

	}

	/**
	 * Updates an existing subject record in the database.
	 * Checks for duplicate subject name and retrieves course details.
	 *
	 * @param bean SubjectBean object containing updated subject details
	 * @throws ApplicationException if application error occurs
	 * @throws DuplicateRecordException if subject name already exists
	 */
	@Override
	public void update(SubjectBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("SubjectModel update() method started for ID: " + bean.getId());

		Connection cnn = null;

		SubjectBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {

			log.warn("Subject already exists with name: " + bean.getName());

			throw new DuplicateRecordException("name already exist");

		}

		//Foregin key concept
		CourseModel cmodel = new CourseModel();

		log.debug("Finding course using course ID: " + bean.getCourseId());

		CourseBean cbean = cmodel.findByPk(bean.getCourseId());

		log.info("Course details retrieved successfully");

		System.out.println("course name = " + cbean.getName());

		try {

			cnn = JDBCDataSource.getConnection();

			log.info("Database connection established for updating subject");

			cnn.setAutoCommit(false);

			PreparedStatement pstmt = cnn.prepareStatement("update " + getTable() + " set name = ?, description = ?, course_id = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			// add cbean
			pstmt.setString(1, cbean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());

			log.debug("Executing update query for subject ID: " + bean.getId());

			pstmt.executeUpdate();

			cnn.commit();

			log.info("Subject record updated successfully. ID: " + bean.getId());

			System.out.println("record updated successfully");

		} catch (Exception e) {

			log.error("Exception occurred while updating subject record. ID: " + bean.getId(), e);

			e.printStackTrace();
			JDBCDataSource.trnRollBack(cnn);

			log.warn("Transaction rollback performed in SubjectModel update()");

		} finally {
			JDBCDataSource.closeConnection(cnn);

			log.debug("Database connection closed in SubjectModel update()");
		}
	}

	/**
	 * Generates a WHERE clause based on the provided subject details.
	 *
	 * @param bean SubjectBean object containing search criteria
	 * @return SQL WHERE condition as a String
	 */
	@Override
	public String getWhereClause(SubjectBean bean) {

		log.debug("SubjectModel getWhereClause() method started");

		StringBuffer sql = new StringBuffer("");

		if(bean != null) {
			if(bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}

			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "%'");
			}

			if (bean.getCourseId() > 0) {
				sql.append(" and courseId like '" + bean.getCourseId() + "%'");
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

		log.debug("Generated subject WHERE clause: " + sql.toString());

		return sql.toString();
	}

	/**
	 * Finds a subject record using its name.
	 *
	 * @param name subject name to search
	 * @return SubjectBean object if found, otherwise null
	 */
	public SubjectBean findByName(String name) {

		log.debug("SubjectModel findByName() started for name: " + name);

		SubjectBean bean = findByUniqueColumn("name", name);

		if (bean != null) {
			log.info("Subject found with name: " + name);
		} else {
			log.warn("Subject not found with name: " + name);
		}

		return bean;
	}

	/**
	 * Returns the database table name for Subject records.
	 *
	 * @return subject table name
	 */
	@Override
	public String getTable() {

		log.debug("SubjectModel getTable() called");

		return "st_subject";

	}

	/**
	 * Creates and returns a new SubjectBean object.
	 *
	 * @return new SubjectBean object
	 */
	@Override
	public SubjectBean getBean() {

		log.debug("SubjectModel getBean() called");

		return new SubjectBean();

	}

}
