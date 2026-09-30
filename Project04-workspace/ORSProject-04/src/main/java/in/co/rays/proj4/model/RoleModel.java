package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class RoleModel extends BaseModel<RoleBean>{

	@Override
	public long add(RoleBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		
		int pk = 0;
		
		try {
			pk = nextPk();
			
			conn = JDBCDataSource.getConnection();
			
			conn.setAutoCommit(false); //begin transaction
			
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?, ?, ?, ?, ?, ?, ?)");
			
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreateDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			
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
	public void update(RoleBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		
		try {
			
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable() + " set name = ?, description = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getCreatedBy());
			pstmt.setString(4, bean.getModifiedBy());
			pstmt.setTimestamp(5, bean.getCreateDatetime());
			pstmt.setTimestamp(6, bean.getModifiedDatetime());
			pstmt.setLong(7,bean.getId());
			
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
	public String getWhereClause(RoleBean bean) {
		
		StringBuffer sql = new StringBuffer("");
		
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "%'");
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

	@Override
	public String getTable() {
		
		return "st_role";
	}

	@Override
	public RoleBean getBean() {
		
		return new RoleBean();
	}

}
