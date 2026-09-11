package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class FacultyModel extends BaseModel<FacultyBean> {

	@Override
	public long add(FacultyBean bean) throws ApplicationException, DuplicateRecordException {

		Connection c = null;
		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null) {
			throw new DuplicateRecordException("faculty already exist");
		}
		
		CollegeModel cmodel = new CollegeModel();
		CollegeBean cbean = cmodel.findByPK(bean.getCollege_id());
		if (cbean != null) {
			bean.setCollege_name(cbean.getName());
		}

		try {

			c = JDBCDataSource.getConnection();

			c.setAutoCommit(false);

			PreparedStatement p = c.prepareStatement("insert into " + getTable() + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			p.setInt(1, nextPK());
			p.setString(2, bean.getFirst_name());
			p.setString(3, bean.getLast_name());
			p.setDate(4, new java.sql.Date(bean.getDob().getTime()));
			p.setString(5, bean.getGender());
			p.setString(6, bean.getMobile_no());
			p.setString(7, bean.getEmail());
			p.setLong(8, bean.getCollege_id());
			p.setString(9, bean.getCollege_name());
			p.setLong(10, bean.getCourse_id());
			p.setString(11, bean.getCourse_name());
			p.setLong(12, bean.getSubject_id());
			p.setString(13, bean.getSubject_name());
			p.setString(14, bean.getCreatedBy());
			p.setString(15, bean.getModifiedBy());
			p.setTimestamp(16, bean.getCreatedDatetime());
			p.setTimestamp(17, bean.getModifiedDatetime());

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
	public void update(FacultyBean bean) throws ApplicationException, DuplicateRecordException {
 
		Connection conn = null;
		
		FacultyBean existBean = findByEmail(bean.getEmail());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("faculty already exist");
		}


		CollegeModel cmodel = new CollegeModel();
		CollegeBean cbean = cmodel.findByPK(bean.getCollege_id());
		if (cbean != null) {
			bean.setCollege_name(cbean.getName());
		}
		
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement p = conn.prepareStatement("update " + getTable() + " set first_name=?, last_name=?, dob=?, gender=?, mobile_no=?, email=?, college_id=?, college_name=?, course_id=?, course_name=?, subject_id=?, subject_name=?, modified_by=?, modified_datetime=? where id=?");
			
			p.setString(1, bean.getFirst_name());
			p.setString(2, bean.getLast_name());
			p.setDate(3, new java.sql.Date(bean.getDob().getTime()));
			p.setString(4, bean.getGender());
			p.setString(5, bean.getMobile_no());
			p.setString(6, bean.getEmail());
			p.setLong(7, bean.getCollege_id());
			p.setString(8, bean.getCollege_name());
			p.setLong(9, bean.getCourse_id());
			p.setString(10, bean.getCourse_name());
			p.setLong(11, bean.getSubject_id());
			p.setString(12, bean.getSubject_name());
			p.setString(13, bean.getModifiedBy());
			p.setTimestamp(14, bean.getModifiedDatetime());
			p.setLong(15, bean.getId());


			p.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	
	public FacultyBean findByEmail(String email) throws ApplicationException {
		FacultyBean bean = findByUniqueColumn("EMAIL", email);
		return bean;
	}

	@Override
	public String getWhereClause(FacultyBean bean) {
		return null;
	}

	@Override
	public String getTable() {
		return "st_faculty";
	}

	@Override
	public FacultyBean getBean() {
		return null;
	}

}
