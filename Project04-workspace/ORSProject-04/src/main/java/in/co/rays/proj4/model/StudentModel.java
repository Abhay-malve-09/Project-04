
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * StudentModel class provides database operations for Student entity.
 * It performs add, update, search and retrieval operations
 * for student records.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class StudentModel extends BaseModel<StudentBean> {

	private static Logger log = Logger.getLogger(StudentModel.class);

	/**
	 * Adds a new student record into the database.
	 *
	 * @param bean StudentBean object containing student details
	 * @return primary key of the inserted record
	 * @throws ApplicationException if an application error occurs
	 * @throws DuplicateRecordException if email already exists
	 */
	@Override
	public long add(StudentBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("StudentModel add() method started");

		Connection conn = null;

		StudentBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {

			log.warn("Student already exists with email: " + bean.getEmail());

			throw new DuplicateRecordException("email already exist");
		}

		//Foregin key concept
		CollegeModel smodel = new CollegeModel();

		log.debug("Finding college using college ID: " + bean.getCollegeId());

		CollegeBean sbean = smodel.findByPk(bean.getCollegeId());

		log.info("College details retrieved successfully");

		System.out.println("college name = " + sbean.getName());

		int pk = 0;

		try {

			pk = nextPk();

			log.debug("Generated student primary key: " + pk);

			conn = JDBCDataSource.getConnection();

			log.info("Database connection established for adding student");

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " value(?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setLong(2, bean.getCollegeId());

			pstmt.setString(3, sbean.getName());
			pstmt.setString(4, bean.getFirstName());
			pstmt.setString(5, bean.getLastName());
			pstmt.setDate(6, new java.sql.Date(bean.getDateOfBirth().getTime()));
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setString(8, bean.getEmail());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());

			log.debug("Executing insert query for student: " + bean.getEmail());

			pstmt.executeUpdate();
			conn.commit();

			log.info("Student record inserted successfully. ID: " + pk);

			System.out.println("record inserted successfully");

		} catch (Exception e) {

			log.error("Exception occurred while adding student record", e);

			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

			log.warn("Transaction rollback performed in StudentModel add()");

		} finally {

			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed in StudentModel add()");
		}

		return pk;
	}

	/**
	 * Updates an existing student record in the database.
	 *
	 * @param bean StudentBean object containing updated student details
	 * @throws ApplicationException if an application error occurs
	 * @throws DuplicateRecordException if email already exists
	 */
	@Override
	public void update(StudentBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("StudentModel update() method started for ID: " + bean.getId());

		Connection c = null;

		StudentBean existBean = findByEmail(bean.getEmail());

		if (existBean != null && existBean.getId() != bean.getId()) {

			log.warn("Student already exists with email: " + bean.getEmail());

			throw new DuplicateRecordException("email already exist");
		}

		//Foregin key concept
		StudentModel smodel = new StudentModel();

		log.debug("Finding college using college ID: " + bean.getCollegeId());

		StudentBean sbean = smodel.findByPk(bean.getCollegeId());

		log.info("College details retrieved successfully");

		System.out.println("college name = " + sbean.getCollegeName());

		try {

			c = JDBCDataSource.getConnection();

			log.info("Database connection established for updating student");

			c.setAutoCommit(false);

			PreparedStatement pstmt = c.prepareStatement("update " + getTable() + " set college_id = ?, college_name = ?, first_name = ?, last_name = ?, date_of_birth = ?, mobile_no = ?, email = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setLong(1, bean.getCollegeId());
			pstmt.setString(2, sbean.getCollegeName());
			pstmt.setString(3, bean.getFirstName());
			pstmt.setString(4, bean.getLastName());
			pstmt.setDate(5, new java.sql.Date(bean.getDateOfBirth().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getEmail());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12, bean.getId());

			log.debug("Executing update query for student ID: " + bean.getId());

			pstmt.executeUpdate();
			c.commit();

			log.info("Student record updated successfully. ID: " + bean.getId());

			System.out.println("record updated successfully");

		} catch (Exception e) {

			log.error("Exception occurred while updating student record. ID: " + bean.getId(), e);

			e.printStackTrace();
			JDBCDataSource.trnRollBack(c);

			log.warn("Transaction rollback performed in StudentModel update()");

		} finally {

			JDBCDataSource.closeConnection(c);

			log.debug("Database connection closed in StudentModel update()");
		}
	}

	/**
	 * Generates the WHERE clause using the supplied StudentBean fields.
	 *
	 * @param bean StudentBean object containing search criteria
	 * @return SQL WHERE conditions as a String
	 */
	@Override
	public String getWhereClause(StudentBean bean) {

		log.debug("StudentModel getWhereClause() method started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());

			}

			if (bean.getCollegeId() > 0) {
				sql.append(" and CollegeId = " + bean.getCollegeId());
			}

			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" and college_name like '" + bean.getCollegeName() + "%'");
			}

			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "%'");
			}

			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastName() + "%'");
			}

			if (bean.getDateOfBirth() != null) {
				sql.append(" and dateOfBirth like '" + bean.getDateOfBirth() + "%'");
			}

			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" and mobileNo like '" + bean.getMobileNo() + "%'");
			}

			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" and email like '" + bean.getEmail() + "%'");
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

		log.debug("Generated student WHERE clause: " + sql.toString());

		return sql.toString();
	}

	/**
	 * Finds a student record using its email address.
	 *
	 * @param email email address of the student
	 * @return StudentBean containing student details, or null if not found
	 */
	public StudentBean findByEmail(String email) {

		log.debug("StudentModel findByEmail() started for email: " + email);

		StudentBean bean = findByUniqueColumn("email", email);

		if (bean != null) {
			log.info("Student found with email: " + email);
		} else {
			log.warn("Student not found with email: " + email);
		}

		return bean;
	}

	/**
	 * Returns the database table name for Student entity.
	 *
	 * @return student table name
	 */
	@Override
	public String getTable() {

		log.debug("StudentModel getTable() called");

		return "st_student";
	}

	/**
	 * Creates and returns a new StudentBean object.
	 *
	 * @return new StudentBean instance
	 */
	@Override
	public StudentBean getBean() {

		log.debug("StudentModel getBean() called");

		return new StudentBean();
	}

}
