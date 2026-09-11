package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class SubjectBean extends BaseBean {
//
//	 id                   BIGINT PRIMARY KEY       Unique identifier for the subject
//	 # name                 VARCHAR(255)             Name of the subject
//	 # course_id            BIGINT                   Foreign key referencing st_course (course)
//	 # course_name          VARCHAR(255)             Name of the course (denormalized)
//	 # description          VARCHAR(255)             Description of the subject
//	 # created_by           VARCHAR(45)              User who created the record
//	 # modified_by          VARCHAR(45)              User who last modified the record
//	 # created_datetime     DATETIME                 Timestamp of record creation
//	 # modified_datetime    DATETIME               
//	 

	private String name;
	private long course_id;
	private String course_name;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(long course_id) {
		this.course_id = course_id;
	}

	public String getCourse_name() {
		return course_name;
	}

	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setResultset(ResultSet rs) {

		super.setResultset(rs);

		try {
			this.setName(rs.getString("name"));
			this.setCourse_id(rs.getLong("course_id"));
			this.setCourse_name(rs.getString("course_name"));
			this.setDescription(rs.getString("description"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getValue() {
		return null;
	}

}
