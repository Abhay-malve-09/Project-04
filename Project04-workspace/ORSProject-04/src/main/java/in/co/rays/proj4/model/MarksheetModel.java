package in.co.rays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.util.JDBCDataSource;

public class MarksheetModel extends BaseModel<MarksheetBean> {

	@Override
	public long add(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		
		MarksheetBean existBean = findByRolNo(bean.getRollNo());
		
		if (existBean != null) {
			
		  throw new  DuplicateRecordException("rollNo already exist");
		  
		}
		
		//Foregin key concept
		StudentModel smodel = new StudentModel();
		
		StudentBean sbean = smodel.findByPk(bean.getStudentId());
		
//		System.out.println("first Name = " + sbean.getFirstName());
		
		int pk = 0;
		
		try {
			
			pk = nextPk();
			
			conn = JDBCDataSource.getConnection();
		
			conn.setAutoCommit(false);
			
			PreparedStatement pstmt = conn.prepareStatement("insert into " + getTable() + " values(?,?,?,?,?,?,?,?,?,?,?)");
			
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getRollNo());
			pstmt.setLong(3, bean.getStudentId());
			pstmt.setString(4, sbean.getFirstName());
			pstmt.setInt(5, bean.getPhysics());
			pstmt.setInt(6, bean.getChemistry());
			pstmt.setInt(7, bean.getMaths());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreateDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			
			pstmt.executeUpdate();
			conn.commit();
			
			System.out.println("record inserted successfuly");
			
		} catch (Exception e) {

			e.printStackTrace();
			JDBCDataSource.trnRollBack(conn);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk;
	}

	@Override
	public void update(MarksheetBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		
		MarksheetBean existBean = findByRolNo(bean.getRollNo());
		
		if (existBean != null && existBean.getId() != bean.getId()) {
			
		  throw new  DuplicateRecordException("rollNo already exist");
		  
		}
		
		//Foregin key concept
		StudentModel smodel = new StudentModel();
		
		StudentBean sbean = smodel.findByPk(bean.getStudentId());
		System.out.println("first Name = " + sbean.getFirstName());

		
		try {
			
			conn = JDBCDataSource.getConnection();
			
			conn.setAutoCommit(false);
			
			PreparedStatement pstmt = conn.prepareStatement("update " + getTable() + " set roll_no = ?, student_id = ?, name = ?, physics = ?, chemistry = ?, maths = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id  = ?");
			
			pstmt.setString(1, bean.getRollNo());
			pstmt.setLong(2, bean.getStudentId());
			
			pstmt.setString(3, sbean.getFirstName());
			pstmt.setInt(4, bean.getPhysics());
			pstmt.setInt(5, bean.getChemistry());
			pstmt.setInt(6, bean.getMaths());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreateDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());
			
			pstmt.executeUpdate();
			
			conn.commit();
			
			System.out.println("record updted successfully");
			
		} catch (Exception e) {

			e.printStackTrace();
			JDBCDataSource.getConnection();
			
		} finally {
			
			JDBCDataSource.closeConnection(conn);
			
		}
	}

	@Override
	public String getWhereClause(MarksheetBean bean) {

		StringBuffer sql = new StringBuffer("");
		
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			
			if (bean.getRollNo() != null && bean.getRollNo().length() > 0) {
				sql.append(" and rollNo like '" + bean.getRollNo() + "%'");
			}
			
			if (bean.getStudentId() > 0) {
				sql.append(" and studentId = " + bean.getStudentId());
			}
			
			if (bean.getName() !=null && bean.getName().length()> 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			
			if (bean.getPhysics() > 0 ) {
				sql.append(" and physics like '" + bean.getPhysics() + "%'");
			}
			
			if (bean.getChemistry() > 0 ) {
				sql.append(" and chemistry like '" + bean.getChemistry() + "%'");
			}
			
			if (bean.getMaths() > 0 ) {
				sql.append(" and maths like '" + bean.getMaths() + "%'");
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
	
	public MarksheetBean findByRolNo(String rollNO) {
		
		MarksheetBean bean = findByUniqueColumn("roll_no", rollNO);
		
		return bean;
		
	}

	@Override
	public String getTable() {

		return "st_marksheet";
	}

	@Override
	public MarksheetBean getBean() {

		return new MarksheetBean();
	}

}
