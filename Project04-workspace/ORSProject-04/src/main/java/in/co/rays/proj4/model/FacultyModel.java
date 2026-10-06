package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class FacultyModel extends BaseModel<FacultyBean>{

	@Override
	public long add(FacultyBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		
		FacultyBean existBean = findByEmail(bean.getEmail());
		
		if (existBean != null) {
			
			throw new DuplicateRecordException("email already exist");
		}

		//Foregin key concept
		CollegeModel cmodel = new CollegeModel();
		
		CollegeBean cbean = cmodel.findByPk(bean.getCollegeId());
		
		System.out.println("college Name = " + cbean.getName());
		
		int pk = 0;
		
		try {
			pk = nextPk();
			
			conn = JDBCDataSource.getConnection();
			
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
			pstmt.setTimestamp(14,  bean.getModifiedDatetime());
			
			pstmt.executeUpdate();
			conn.commit();
			
		    System.out.println("recored inserted successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
			
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk;
	}

	@Override
	public void update(FacultyBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		
		FacultyBean existBean = findByEmail(bean.getEmail());
		
		if (existBean != null && existBean.getId() != bean.getId()) {
			
			throw new DuplicateRecordException("email already exist");
		}
		
		//Foregin key concept
		CollegeModel cmodel = new CollegeModel();
		
		CollegeBean cbean = cmodel.findByPk(bean.getCollegeId());
		System.out.println("college Name = " + cbean.getName());

		
		try {
			
			conn = JDBCDataSource.getConnection();
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
			pstmt.setTimestamp(13,  bean.getModifiedDatetime());
			pstmt.setLong(14, bean.getId());
			
			pstmt.executeUpdate();
			conn.commit();
			
			System.out.println("record updated successfully");
			
		} catch (Exception e) {
			 e.printStackTrace();
			 JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}

	@Override
	public String getWhereClause(FacultyBean bean) {
		
		StringBuffer sql=  new StringBuffer("");
		
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}		
			
			if (bean.getCollegeId() > 0) {
				sql.append(" and collegeId like '" + bean.getCollegeId() + "%'");
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
			return sql.toString();
	}

	public FacultyBean findByEmail(String email) {
	
		FacultyBean bean = findByUniqueColumn("email", email);
		
		return bean;
	}
	
	@Override
	public String getTable() {

		return "st_faculty";
	}

	@Override
	public FacultyBean getBean() {

		return new FacultyBean();
	}

}
