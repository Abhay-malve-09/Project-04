package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class SubjectModel extends BaseModel<SubjectBean> {

	@Override
	public long add(SubjectBean bean) throws ApplicationException, DuplicateRecordException {

		Connection cnn = null;
		
		SubjectBean existBean = findByName(bean.getName());
		
		if (existBean != null) {
			
			throw new DuplicateRecordException("name already exist");
			
		}

		//Foregin key concept
		CourseModel cmodel = new CourseModel();
		
		CourseBean cbean = cmodel.findByPk(bean.getCourseId());
		
		System.out.println("course Name = " + cbean.getName());

		
		
		
		int pk = 0;
		
		try {
			
			pk = nextPk();
			
			cnn = JDBCDataSource.getConnection();
			cnn.setAutoCommit(false);
			
			PreparedStatement pstmt = cnn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?)");
			
			pstmt.setLong(1, pk);
			// add cbean
			pstmt.setString(2, cbean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setLong(4, bean.getCourseId());
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
	public void update(SubjectBean bean) throws ApplicationException, DuplicateRecordException {

		Connection cnn = null;
		
		SubjectBean existBean = findByName(bean.getName());
		
		if (existBean != null && existBean.getId() != bean.getId()) {
			
			throw new DuplicateRecordException("name already exist");
			
		}
		
		//Foregin key concept
		CourseModel cmodel = new CourseModel();
		
		CourseBean cbean = cmodel.findByPk(bean.getCourseId());
		System.out.println("course name = " + cbean.getName());
		
		try {
			
			cnn = JDBCDataSource.getConnection();
			
			cnn.setAutoCommit(false);
			
			PreparedStatement pstmt = cnn.prepareStatement("update " + getTable() + " set name = ?, description = ?, course_id = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			
			// add cbean
			pstmt.setString(1, cbean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setLong(3, bean.getCourseId());
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
	public String getWhereClause(SubjectBean bean) {

StringBuffer sql = new StringBuffer("");
		
		if(bean != null) {
			if(bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "%'");
			}
			
			if (bean.getCourseId() > 0) {
				sql.append(" and courseId like '" + bean.getCourseId() + "%'");
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
	
	public SubjectBean findByName(String name) {
		
		SubjectBean bean = findByUniqueColumn("name", name);
		
		return bean;
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
