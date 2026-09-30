package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CourseModel extends BaseModel<CourseBean> {

	@Override
	public long add(CourseBean bean) throws ApplicationException, DuplicateRecordException {

		Connection cnn = null;
		
		CourseBean existBean = findByName(bean.getName());
		
		if (existBean != null) {
			
			throw new DuplicateRecordException("name already exist");
			
		}
		
		int pk = 0;
		
		try {
			
			pk = nextPk();
			
			cnn = JDBCDataSource.getConnection();
			cnn.setAutoCommit(false);
			
			PreparedStatement pstmt = cnn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?)");
			
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getDuration());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreateDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			
			pstmt.executeUpdate();
			cnn.commit();
			
			System.out.println("record inserted successfully");
			
		} catch (Exception e) {

			e.printStackTrace();
			JDBCDataSource.trnRollBack(cnn);
		} finally {
			JDBCDataSource.closeConnection(cnn);
		}
		return pk;
	}

	@Override
	public void update(CourseBean bean) throws ApplicationException, DuplicateRecordException {

		Connection cnn = null;
		
		CourseBean existBean = findByName(bean.getName());
		
		if (existBean != null && existBean.getId() != bean.getId()) {
			
			throw new DuplicateRecordException("name already exist");
			
		}
		
		try {
			
			cnn = JDBCDataSource.getConnection();
			
			cnn.setAutoCommit(false);
			
			PreparedStatement pstmt = cnn.prepareStatement("update " + getTable() + " set name = ?, description = ?, duration = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getDuration());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreateDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());
			
			pstmt.executeUpdate();
			
			cnn.commit();
			
			System.out.println("record updated successfully");
			
		} catch (Exception e) {

			e.printStackTrace();
			JDBCDataSource.trnRollBack(cnn);
			
		} finally {
			JDBCDataSource.closeConnection(cnn);
		}
	}

	@Override
	public String getWhereClause(CourseBean bean) {

		StringBuffer sql = new StringBuffer("");
		
		if(bean != null) {
			if(bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "%'");
			}
			
			if (bean.getDuration() != null && bean.getDuration().length() > 0) {
				sql.append(" and duration like '" + bean.getDuration() + "%'");
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
	
	public CourseBean findByName(String name) {
		
		CourseBean bean = findByUniqueColumn("name", name);
		
		return bean;
		
	}

	@Override
	public String getTable() {

		return "st_course";
	}

	@Override
	public CourseBean getBean() {

		return new CourseBean();
	}

}
