package in.co.rays.proj4.bean;

import java.sql.Timestamp;
import java.sql.ResultSet;

public abstract class BaseBean implements DropdownListBean {

	protected long id;
	protected String createdBy;
	protected String modifiedBy;
	protected Timestamp createDatetime;
	protected Timestamp modifiedDatetime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Timestamp getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Timestamp createDatetime) {
		this.createDatetime = createDatetime;
	}

	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	@Override
	public String getKey() {
		
		return id + "";
	}

	public void setResultSet(ResultSet rs) {
		
		try {
			
			setId(rs.getLong("id"));
			setCreatedBy(rs.getString("created_by"));
			setModifiedBy(rs.getString("modified_by"));
			setCreateDatetime(rs.getTimestamp("created_datetime"));
			setModifiedDatetime(rs.getTimestamp("modified_datetime"));
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
	}

}
