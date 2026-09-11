package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.TimeTableBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class TimeTableModel  extends BaseModel<TimeTableBean> {

	@Override
	public long add(TimeTableBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection c = null;
		 
		try {
			
			c = JDBCDataSource.getConnection();

			c.setAutoCommit(false);

			PreparedStatement p = c.prepareStatement("insert into " + getTable() + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			p.setInt(1, nextPK());
			p.setString(2, bean.getSemester());
			p.setString(3, bean.getDescription());
			p.setDate(4, new java.sql.Date(bean.getExam_date().getTime()));
			p.setString(5, bean.getExam_time());
			p.setLong(6, bean.getCourse_id());
			p.setString(7, bean.getCourse_name());
			p.setLong(8, bean.getSubject_id());
			p.setString(9, bean.getSubject_name());
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
	public void update(TimeTableBean bean) throws ApplicationException, DuplicateRecordException {
 
		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement p = conn.prepareStatement("update " + getTable() + " set semester=?, description=?, exam_date=?, exam_time=?, course_id=?, course_name=?, subject_id=?, subject_name=?, modified_by=?, , modified_datetime=?, where id=?");
			
			p.setString(1, bean.getSemester());
			p.setString(2, bean.getDescription());
			p.setDate(3, new java.sql.Date(bean.getExam_date().getTime()));
			p.setString(4, bean.getExam_time());
			p.setLong(5, bean.getCourse_id());
			p.setString(6, bean.getCourse_name());
			p.setLong(7, bean.getSubject_id());
			p.setString(8, bean.getSubject_name());
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


	@Override
	public String getWhereClause(TimeTableBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTable() {
		// TODO Auto-generated method stub
		return "st_timetable";
	}

	@Override
	public TimeTableBean getBean() {
		// TODO Auto-generated method stub
		return null;
	}

}
