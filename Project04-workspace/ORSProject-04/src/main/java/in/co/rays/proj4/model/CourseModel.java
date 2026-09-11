package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CourseModel extends BaseModel<CourseBean> {

	@Override
	public long add(CourseBean bean) throws ApplicationException, DuplicateRecordException {

		Connection c = null;
		
		CourseBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("course already exist");
		}

		try {

			c = JDBCDataSource.getConnection();

			c.setAutoCommit(false);

			PreparedStatement p = c.prepareStatement("insert into " + getTable() + " values(?, ?, ?, ?, ?, ?, ?, ?)");

			p.setInt(1, nextPK());
			p.setString(2, bean.getName());
			p.setString(3, bean.getDuration());
			p.setString(4, bean.getDescription());
			p.setString(5, bean.getCreatedBy());
			p.setString(6, bean.getModifiedBy());
			p.setTimestamp(7, bean.getCreatedDatetime());
			p.setTimestamp(8, bean.getModifiedDatetime());

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
	public void update(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection conn = null;
		
		CourseBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("course already exist");
		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable() + " set name=?, duration=?, description=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=?");
		
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDuration());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getModifiedBy());
			pstmt.setTimestamp(5, bean.getModifiedDatetime());
			pstmt.setLong(6, bean.getId());

			pstmt.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	
	public CourseBean findByName(String name) throws ApplicationException {
		return findByUniqueColumn("NAME", name);
	}


	@Override
	public String getWhereClause(CourseBean bean) {
		return null;
	}

	@Override
	public String getTable() {
		return "st_course";
	}

	@Override
	public CourseBean getBean() {
		return null;
	}

}
