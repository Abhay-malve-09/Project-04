package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class StudentModel extends BaseModel<StudentBean> {

	@Override
	public long add(StudentBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		
		StudentBean existBean = findByEmail(bean.getEmail());
		
		if (existBean != null) {
			
			throw new DuplicateRecordException("email already exist");
		}
		
		//Foregin key concept
		CollegeModel smodel = new CollegeModel();
		
		CollegeBean sbean = smodel.findByPk(bean.getCollegeId());
		
		System.out.println("college name = " + sbean.getName());
		
		int pk = 0;
		
		try {
			
			pk = nextPk();
			
			conn = JDBCDataSource.getConnection();
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
			pstmt.setTimestamp(11, bean.getCreateDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			
			pstmt.executeUpdate();
			conn.commit();
			System.out.println("record inserted successfully");
			
		} catch (Exception e) {

			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk;
	}

	@Override
	public void update(StudentBean bean) throws ApplicationException, DuplicateRecordException {

		Connection c = null;
		
		StudentBean existBean = findByEmail(bean.getEmail());
		
		if (existBean != null && existBean.getId() != bean.getId()) {
			
			throw new DuplicateRecordException("email already exist");
		}
		
		//Foregin key concept
		StudentModel smodel = new StudentModel();
		
		StudentBean sbean = smodel.findByPk(bean.getCollegeId());
		System.out.println("college name = " + sbean.getCollegeName());
		
		try {
			
			c = JDBCDataSource.getConnection();
			 c.setAutoCommit(false);
			 
			PreparedStatement pstmt =  c.prepareStatement("update " + getTable() + " set college_id = ?, college_name = ?, first_name = ?, last_name = ?, date_of_birth = ?, mobile_no = ?, email = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			 
			pstmt.setLong(1, bean.getCollegeId());
			pstmt.setString(2, sbean.getCollegeName());
			pstmt.setString(3, bean.getFirstName());
			pstmt.setString(4, bean.getLastName());
			pstmt.setDate(5, new java.sql.Date(bean.getDateOfBirth().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7, bean.getEmail());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreateDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12, bean.getId());
			
			pstmt.executeUpdate();
			c.commit();
			
			System.out.println("record updated successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(c);
		} finally {
			JDBCDataSource.closeConnection(c);
		}
	}

	@Override
	public String getWhereClause(StudentBean bean) {

	 StringBuffer sql = new StringBuffer("");
	 
	 if (bean != null) {
		 if (bean.getId() > 0) {
			 sql.append(" and id = " + bean.getId());
			 
		 }
		 
		 if (bean.getCollegeId() > 0) {
			 sql.append(" and CollegeId = " + bean.getCollegeId());
		 }
		 
		 if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
			 sql.append(" and collegeName like '" + bean.getCollegeName() + "%'");
		 }
		 
		 if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
			 sql.append(" and firstName like '" + bean.getFirstName() + "%'");
		 }
		 
		 if (bean.getLastName() != null && bean.getLastName().length() > 0) {
			 sql.append(" and lastName like '" + bean.getLastName() + "%'");
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

		if (bean.getCreateDatetime() != null) {
			sql.append(" and createdDateTime like '" + bean.getCreateDatetime() + "%'");
		}

		if (bean.getModifiedDatetime() != null) {
			sql.append(" and modifiedDateTime like '" + bean.getModifiedDatetime() + "%");
		}
		
	 }
		return sql.toString();
	}
	
	public StudentBean findByEmail(String email) {
		
		StudentBean bean = findByUniqueColumn("email", email);
		
		return bean;
	}

	@Override
	public String getTable() {

		return "st_student";
	}

	@Override
	public StudentBean getBean() {

		return new StudentBean();
	}

	
}
