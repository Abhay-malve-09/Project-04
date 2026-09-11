package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class StudentModel extends BaseModel<StudentBean> {

	@Override
	public long add(StudentBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection c = null;
		
		StudentBean existBean = findByEmailId(bean.getEmail());

		if (existBean != null) {
			throw new DuplicateRecordException("student already exist");
		}

		CollegeModel cmodel = new CollegeModel();
		CollegeBean cbean = cmodel.findByPK(bean.getCollegeId());
//		if (cbean != null) {
//			bean.setCollege_name(cbean.getName());
//		}

		bean.setCollegeName(cbean.getName());

		
		try {
			
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);
			
			PreparedStatement p = c.prepareStatement("insert into " + getTable() + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
			p.setInt(1, nextPK());
			p.setString(2, bean.getFirstName());
			p.setString(3, bean.getLastName());
			p.setDate(4, new java.sql.Date(bean.getDob().getTime()));
			p.setString(6, bean.getMobileNo());
			p.setString(7, bean.getEmail());
			p.setLong(8, bean.getCollegeId());
			p.setString(9, bean.getCollegeName());
			p.setString(10, bean.getCreatedBy());
			p.setString(11, bean.getModifiedBy());
			p.setTimestamp(12, bean.getCreatedDatetime());
			p.setTimestamp(13, bean.getModifiedDatetime());

			
			p.executeUpdate();
			c.commit();
			
			
		} catch (Exception e) {
           e.printStackTrace();
           JDBCDataSource.trnRollBack(c);
           
		} finally {
			
			JDBCDataSource.closeConnection(c);
		}
		
		return bean.getId();
	}

	@Override
	public void update(StudentBean bean) throws ApplicationException, DuplicateRecordException {
 
		Connection conn = null;
		
		StudentBean existBean = findByEmailId(bean.getEmail());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("student already exist");
		}

		CollegeModel cmodel = new CollegeModel();
		CollegeBean cbean = cmodel.findByPK(bean.getCollegeId());
//		if (cbean != null) {
//			bean.setCollege_name(cbean.getName());
//		}
//		
		
		bean.setCollegeName(cbean.getName());
		
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement p = conn.prepareStatement("update " + getTable() + " set first_name=?, last_name=?, dob=?, gender=?, mobile_no=?, email=?, college_id=?, college_name=?, modified_by=?, modified_datetime=? where id=?");
			
			p.setString(1, bean.getFirstName());
			p.setString(2, bean.getLastName());
			p.setDate(3, new java.sql.Date(bean.getDob().getTime()));
			p.setString(5, bean.getMobileNo());
			p.setString(6, bean.getEmail());
			p.setLong(7, bean.getCollegeId());
			p.setString(8, bean.getCollegeName());
			p.setString(9, bean.getModifiedBy());
			p.setTimestamp(10, bean.getModifiedDatetime());
			p.setLong(11, bean.getId());

			p.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	
	public StudentBean findByEmailId(String email) throws ApplicationException {
		StudentBean bean = findByUniqueColumn("EMAIL", email);
		return bean;
	}


	@Override
	public String getWhereClause(StudentBean bean) {

		StringBuffer sql = new StringBuffer("");

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}

			if (bean.getFirstName() != null
					&& bean.getFirstName().length() > 0) {
				sql.append(" and first_name like '" + bean.getFirstName() + "%'");
			}

			if (bean.getLastName() != null
					&& bean.getLastName().length() > 0) {
				sql.append(" and last_name like '" + bean.getLastName() + "%'");
			}

			if (bean.getDob() != null
					&& bean.getDob().getTime() > 0) {
				sql.append(" and dob like '"
						+ new java.sql.Date(bean.getDob().getTime()) + "%'");
			}

			if (bean.getMobileNo() != null
					&& bean.getMobileNo().length() > 0) {
				sql.append(" and mobile_no like '" + bean.getMobileNo() + "%'");
			}

			if (bean.getEmail() != null
					&& bean.getEmail().length() > 0) {
				sql.append(" and email like '" + bean.getEmail() + "%'");
			}

			if (bean.getCollegeId() > 0) {
				sql.append(" and college_id = " + bean.getCollegeId());
			}

			if (bean.getCollegeName() != null
					&& bean.getCollegeName().length() > 0) {
				sql.append(" and college_name like '" + bean.getCollegeName() + "%'");
			}
		}

		return sql.toString();
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
