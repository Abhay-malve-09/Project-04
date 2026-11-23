
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * FacultyModel class handles database operations related to Faculty.
 * It provides methods to add, update, search and retrieve faculty records.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class FacultyModel extends BaseModel<FacultyBean>{

	private static Logger log = Logger.getLogger(FacultyModel.class);

	/**
	 * Adds a new faculty record into the database.
	 *
	 * @param bean FacultyBean object containing faculty details
	 * @return primary key of the inserted record
	 * @throws ApplicationException if application error occurs
	 * @throws DuplicateRecordException if email already exists
	 */
	@Override
	public long add(FacultyBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("FacultyModel add() started");

		Connection conn = null;

		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {

			log.warn("Faculty email already exists: " + bean.getEmail());

			throw new DuplicateRecordException("email already exist");
		}

		//Foregin key concept
		CollegeModel cmodel = new CollegeModel();

		log.debug("Finding college using college ID: " + bean.getCollegeId());

		CollegeBean cbean = cmodel.findByPk(bean.getCollegeId());

		log.info("College details retrieved successfully");

		System.out.println("college Name = " + cbean.getName());

		int pk = 0;

		try {
			pk = nextPk();

			log.debug("Generated faculty primary key: " + pk);

			conn = JDBCDataSource.getConnection();

			log.info("Database connection established for adding faculty");

			conn.setAutoCommit(false); //begin transaction

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			pstmt.setLong(1, pk);
			pstmt.setLong(2, bean.getCollegeId());
			// add cbean in this
			pstmt.setString(3, cbean.getName());
			pstmt.setString(4, bean.getFirstName());
			pstmt.setString(5, bean.getLastName());
			pstmt.setString(6, bean.getEmail());
			pstmt.setString(7, bean.getMobileNo());
			pstmt.setString(8, bean.getAddress());
			pstmt.setString(9, bean.getGender());
			pstmt.setDate(10, new java.sql.Date(bean.getDateOfBirth().getTime()));
			pstmt.setString(11, bean.getCreatedBy());
			pstmt.setString(12, bean.getModifiedBy());
			pstmt.setTimestamp(13, bean.getCreatedDatetime());
			pstmt.setTimestamp(14, bean.getModifiedDatetime());

			log.debug("Executing insert query for faculty: " + bean.getEmail());

			pstmt.executeUpdate();
			conn.commit();

			log.info("Faculty record inserted successfully. ID: " + pk);

			System.out.println("recored inserted successfully");

		} catch (Exception e) {

			log.error("Exception occurred while adding faculty record", e);

			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

			log.warn("Transaction rollback performed in FacultyModel add()");

		} finally {
			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed in FacultyModel add()");
		}

		return pk;
	}

	/**
	 * Updates an existing faculty record in the database.
	 *
	 * @param bean FacultyBean object containing updated faculty details
	 * @throws ApplicationException if application error occurs
	 * @throws DuplicateRecordException if email already exists
	 */
	@Override
	public void update(FacultyBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("FacultyModel update() started for ID: " + bean.getId());

		Connection conn = null;

		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null && existBean.getId() != bean.getId()) {

			log.warn("Faculty email already exists: " + bean.getEmail());

			throw new DuplicateRecordException("email already exist");
		}

		//Foregin key concept
		CollegeModel cmodel = new CollegeModel();

		log.debug("Finding college using college ID: " + bean.getCollegeId());

		CollegeBean cbean = cmodel.findByPk(bean.getCollegeId());

		log.info("College details retrieved successfully");

		System.out.println("college Name = " + cbean.getName());

		try {

			conn = JDBCDataSource.getConnection();

			log.info("Database connection established for updating faculty");

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable() + " set college_id = ?, college_name = ?, first_name = ?, last_name  = ?, email = ?, mobile_no = ?, address = ?, gender = ?,  date_of_birth = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");

			pstmt.setLong(1, bean.getCollegeId());
			// add cbean in this
			pstmt.setString(2, cbean.getName());
			pstmt.setString(3, bean.getFirstName());
			pstmt.setString(4, bean.getLastName());
			pstmt.setString(5, bean.getEmail());
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getAddress());
			pstmt.setString(8, bean.getGender());
			pstmt.setDate(9, new java.sql.Date(bean.getDateOfBirth().getTime()));
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDatetime());
			pstmt.setTimestamp(13, bean.getModifiedDatetime());
			pstmt.setLong(14, bean.getId());

			log.debug("Executing update query for faculty ID: " + bean.getId());

			pstmt.executeUpdate();
			conn.commit();

			log.info("Faculty record updated successfully. ID: " + bean.getId());

			System.out.println("record updated successfully");

		} catch (Exception e) {

			log.error("Exception occurred while updating faculty record. ID: " + bean.getId(), e);

			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

			log.warn("Transaction rollback performed in FacultyModel update()");

		} finally {
			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed in FacultyModel update()");
		}
	}

	/**
	 * Generates the WHERE clause for faculty searching.
	 *
	 * @param bean FacultyBean object containing search criteria
	 * @return SQL WHERE condition as a String
	 */
	@Override
	public String getWhereClause(FacultyBean bean) {

		log.debug("FacultyModel getWhereClause() started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}

			if (bean.getCollegeId() > 0) {
				sql.append(" and collegeId like '" + bean.getCollegeId() + "%'");
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

			if (bean.getEmail() != null && bean.getEmail().length() > 0) {
				sql.append(" and email like '" + bean.getEmail() + "%'");
			}

			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" and mobileNo like '" + bean.getMobileNo() + "%'");
			}

			if (bean.getAddress() != null && bean.getAddress().length() > 0) {
				sql.append(" and address like '" + bean.getAddress() + "%'");
			}

			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" and gender like '" + bean.getGender() + "%'");
			}

			if (bean.getDateOfBirth() != null) {
				sql.append(" and dateOfirth like '" + bean.getDateOfBirth() + "%'");
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

		log.debug("Generated faculty WHERE clause: " + sql.toString());

		return sql.toString();
	}

	/**
	 * Finds a faculty record using email address.
	 *
	 * @param email email address of faculty
	 * @return FacultyBean object if found, otherwise null
	 */
	public FacultyBean findByEmail(String email) {

		log.debug("FacultyModel findByEmail() started for email: " + email);

		FacultyBean bean = findByUniqueColumn("email", email);

		if (bean != null) {
			log.info("Faculty found with email: " + email);
		} else {
			log.warn("Faculty not found with email: " + email);
		}

		return bean;
	}

	/**
	 * Returns the database table name of Faculty.
	 *
	 * @return faculty table name
	 */
	@Override
	public String getTable() {

		log.debug("FacultyModel getTable() called");

		return "st_faculty";
	}

	/**
	 * Creates and returns a new FacultyBean object.
	 *
	 * @return new FacultyBean object
	 */
	@Override
	public FacultyBean getBean() {

		log.debug("FacultyModel getBean() called");

		return new FacultyBean();
	}

}
