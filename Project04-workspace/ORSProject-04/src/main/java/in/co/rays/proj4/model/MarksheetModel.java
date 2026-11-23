
package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

/**
 * MarksheetModel class handles database operations related to Marksheet.
 * It provides methods to add, update, search and retrieve marksheet records.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class MarksheetModel extends BaseModel<MarksheetBean> {

	private static Logger log = Logger.getLogger(MarksheetModel.class);

	/**
	 * Adds a new marksheet record into the database.
	 *
	 * @param bean MarksheetBean object containing marksheet details
	 * @return primary key of the inserted record
	 * @throws ApplicationException if application error occurs
	 * @throws DuplicateRecordException if roll number already exists
	 */
	@Override
	public long add(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("MarksheetModel add() started");

		Connection conn = null;

		MarksheetBean existBean = findByRolNo(bean.getRollNo());

		if (existBean != null) {

			log.warn("Marksheet already exists with roll number: " + bean.getRollNo());

			throw new DuplicateRecordException("rollNo already exist");

		}

		//Foregin key concept
		StudentModel smodel = new StudentModel();

		log.debug("Finding student using student ID: " + bean.getStudentId());

		StudentBean sbean = smodel.findByPk(bean.getStudentId());

		log.info("Student details retrieved successfully");

//		System.out.println("first Name = " + sbean.getFirstName());

		int pk = 0;

		try {

			pk = nextPk();

			log.debug("Generated marksheet primary key: " + pk);

			conn = JDBCDataSource.getConnection();

			log.info("Database connection established for adding marksheet");

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getRollNo());
			pstmt.setLong(3, bean.getStudentId());
			pstmt.setString(4, sbean.getFirstName());
			pstmt.setInt(5, bean.getPhysics());
			pstmt.setInt(6, bean.getChemistry());
			pstmt.setInt(7, bean.getMaths());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());

			log.debug("Executing insert query for roll number: " + bean.getRollNo());

			pstmt.executeUpdate();
			conn.commit();

			log.info("Marksheet record inserted successfully. ID: " + pk);

			System.out.println("record inserted successfuly");

		} catch (Exception e) {

			log.error("Exception occurred while adding marksheet record", e);

			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);

			log.warn("Transaction rollback performed in MarksheetModel add()");

		} finally {
			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed in MarksheetModel add()");
		}

		return pk;
	}

	/**
	 * Updates an existing marksheet record in the database.
	 *
	 * @param bean MarksheetBean object containing updated marksheet details
	 * @throws ApplicationException if application error occurs
	 * @throws DuplicateRecordException if roll number already exists
	 */
	@Override
	public void update(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("MarksheetModel update() started for ID: " + bean.getId());

		Connection conn = null;

		MarksheetBean existBean = findByRolNo(bean.getRollNo());

		if (existBean != null && existBean.getId() != bean.getId()) {

			log.warn("Marksheet roll number already exists: " + bean.getRollNo());

			throw new DuplicateRecordException("rollNo already exist");

		}

		//Foregin key concept
		StudentModel smodel = new StudentModel();

		log.debug("Finding student using student ID: " + bean.getStudentId());

		StudentBean sbean = smodel.findByPk(bean.getStudentId());

		log.info("Student details retrieved successfully");

		System.out.println("first Name = " + sbean.getFirstName());

		try {

			conn = JDBCDataSource.getConnection();

			log.info("Database connection established for updating marksheet");

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("update " + getTable() + " set roll_no = ?, student_id = ?, name = ?, physics = ?, chemistry = ?, maths = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id  = ?");

			pstmt.setString(1, bean.getRollNo());
			pstmt.setLong(2, bean.getStudentId());

			pstmt.setString(3, sbean.getFirstName());
			pstmt.setInt(4, bean.getPhysics());
			pstmt.setInt(5, bean.getChemistry());
			pstmt.setInt(6, bean.getMaths());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());

			log.debug("Executing update query for marksheet ID: " + bean.getId());

			pstmt.executeUpdate();

			conn.commit();

			log.info("Marksheet record updated successfully. ID: " + bean.getId());

			System.out.println("record updted successfully");

		} catch (Exception e) {

			log.error("Exception occurred while updating marksheet record. ID: " + bean.getId(), e);

			e.printStackTrace();

			JDBCDataSource.getConnection();

		} finally {

			JDBCDataSource.closeConnection(conn);

			log.debug("Database connection closed in MarksheetModel update()");

		}
	}

	/**
	 * Generates the WHERE clause for marksheet searching.
	 *
	 * @param bean MarksheetBean object containing search criteria
	 * @return SQL WHERE condition as a String
	 */
	@Override
	public String getWhereClause(MarksheetBean bean) {

		log.debug("MarksheetModel getWhereClause() started");

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}

			if (bean.getRollNo() != null && bean.getRollNo().length() > 0) {
				sql.append(" and rollNo like '" + bean.getRollNo() + "%'");
			}

			if (bean.getStudentId() > 0) {
				sql.append(" and studentId = " + bean.getStudentId());
			}

			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}

			if (bean.getPhysics() > 0) {
				sql.append(" and physics like '" + bean.getPhysics() + "%'");
			}

			if (bean.getChemistry() > 0) {
				sql.append(" and chemistry like '" + bean.getChemistry() + "%'");
			}

			if (bean.getMaths() > 0) {
				sql.append(" and maths like '" + bean.getMaths() + "%'");
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

		log.debug("Generated marksheet WHERE clause: " + sql.toString());

		return sql.toString();
	}

	/**
	 * Finds a marksheet record using its roll number.
	 *
	 * @param rollNO roll number of the student
	 * @return MarksheetBean object if found, otherwise null
	 */
	public MarksheetBean findByRolNo(String rollNO) {

		log.debug("MarksheetModel findByRolNo() started for roll number: " + rollNO);

		MarksheetBean bean = findByUniqueColumn("roll_no", rollNO);

		if (bean != null) {
			log.info("Marksheet found with roll number: " + rollNO);
		} else {
			log.warn("Marksheet not found with roll number: " + rollNO);
		}

		return bean;

	}

	/**
	 * Returns the database table name of Marksheet.
	 *
	 * @return marksheet table name
	 */
	@Override
	public String getTable() {

		log.debug("MarksheetModel getTable() called");

		return "st_marksheet";
	}

	/**
	 * Creates and returns a new MarksheetBean object.
	 *
	 * @return new MarksheetBean object
	 */
	@Override
	public MarksheetBean getBean() {

		log.debug("MarksheetModel getBean() called");

		return new MarksheetBean();
	}

}
