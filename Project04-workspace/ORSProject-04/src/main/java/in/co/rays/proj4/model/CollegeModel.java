package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class CollegeModel extends BaseModel<CollegeBean> {

	@Override
	public long add(CollegeBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection c = null;
		
		CollegeBean existBean = findByName(bean.getName());

		if (existBean != null) {
			throw new DuplicateRecordException("college already exist");
		}
		
		try {
			
			c = JDBCDataSource.getConnection();
			c.setAutoCommit(false);
			
			PreparedStatement p = c.prepareStatement("insert into " + getTable() + " values(?, ?, ?, ?, ?, ?, ? ,? , ?, ?)");
			
			p.setInt(1, nextPK());
			p.setString(2, bean.getName());
			p.setString(3, bean.getAddress());
			p.setString(4, bean.getState());
			p.setString(5, bean.getCity());
			p.setString(6, bean.getPhone_no());
			p.setString(7, bean.getCreatedBy());
			p.setString(8, bean.getModifiedBy());
			p.setTimestamp(9, bean.getCreatedDatetime());
			p.setTimestamp(10, bean.getModifiedDatetime());
			
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
	public void update(CollegeBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection conn = null;
		
		CollegeBean existBean = findByName(bean.getName());

		if (existBean != null && existBean.getId() != bean.getId()) {
			throw new DuplicateRecordException("college already exist");
		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable() + " set name=?, address=?, state=?, city=?, phone_no=?, modified_by=?, modified_datetime=? where id=?");
		
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getAddress());
			pstmt.setString(3, bean.getState());
			pstmt.setString(4, bean.getCity());
			pstmt.setString(5, bean.getPhone_no());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());

			int i = pstmt.executeUpdate();
			conn.commit();
			
			System.out.println("recored updated successfully: " + i);

		} catch (Exception e) {
			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	

	public CollegeBean findByName(String name) throws ApplicationException {
		CollegeBean bean = findByUniqueColumn("NAME", name);
		return bean;
	}

	@Override
	public String getWhereClause(CollegeBean bean) {
		return null;
	}

	@Override
	public String getTable() {
		return "st_college";
	}

	@Override
	public CollegeBean getBean() {
		return new CollegeBean();
	}

}
