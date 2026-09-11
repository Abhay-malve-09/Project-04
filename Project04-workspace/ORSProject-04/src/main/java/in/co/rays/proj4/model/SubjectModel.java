package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class SubjectModel extends BaseModel<SubjectBean> {

	@Override
	public long add(SubjectBean bean) throws ApplicationException, DuplicateRecordException {

		Connection c = null;

		SubjectBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("subject already exist");
		}
		
		try {

			c = JDBCDataSource.getConnection();

			c.setAutoCommit(false);

			PreparedStatement p = c.prepareStatement("insert into " + getTable() + " values(?, ?, ?, ?, ?, ?, ?, ?, ?)");

			p.setInt(1, nextPK());
			p.setString(2, bean.getName());
			p.setLong(3, bean.getCourse_id());
			p.setString(4, bean.getCourse_name());
			p.setString(5, bean.getDescription());
			p.setString(6, bean.getCreatedBy());
			p.setString(7, bean.getModifiedBy());
			p.setTimestamp(8, bean.getCreatedDatetime());
			p.setTimestamp(9, bean.getModifiedDatetime());

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
	public void update(SubjectBean bean) throws ApplicationException, DuplicateRecordException {
 
		Connection conn = null;

		SubjectBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("subject already exist");
		}
		
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement p = conn.prepareStatement("update " + getTable() + " set name=?, course_id=?, course_name=?, description=?, modified_by=?, modified_datetime=? where id=?");
			
			p.setString(1, bean.getName());
			p.setLong(2, bean.getCourse_id());
			p.setString(3, bean.getCourse_name());
			p.setString(4, bean.getDescription());
			p.setString(5, bean.getModifiedBy());
			p.setTimestamp(6, bean.getModifiedDatetime());
			p.setLong(7, bean.getId());

			p.executeUpdate();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	
	public SubjectBean findByName(String name) throws ApplicationException {
		SubjectBean bean = findByUniqueColumn("NAME", name);
		return bean;
	}

	@Override
	public String getWhereClause(SubjectBean bean) {
		return null;
	}

	@Override
	public String getTable() {
		return "st_subject";
	}

	@Override
	public SubjectBean getBean() {
		
		return new SubjectBean();
	}

}
