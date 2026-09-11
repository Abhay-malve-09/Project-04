package in.co.rays.proj4.bean;

import java.sql.ResultSet;
import java.util.Date;

public class FacultyBean extends BaseBean {
//
//	 id                   BIGINT PRIMARY KEY       Unique identifier for the faculty member
//	 # first_name           VARCHAR(45)              First name of the faculty member
//	 # last_name            VARCHAR(45)              Last name of the faculty member
//	 # dob                  DATETIME                 Date of birth of the faculty member
//	 # gender               VARCHAR(45)              Gender of the faculty member
//	 # mobile_no            VARCHAR(45)              Mobile number of the faculty member
//	 # email                VARCHAR(255)             Email address of the faculty member
//	 # college_id           BIGINT                   Foreign key referencing st_college (college)
//	 # college_name         VARCHAR(255)             Name of the college (denormalized)
//	 # course_id            BIGINT                   Foreign key referencing st_course (course)
//	 # course_name          VARCHAR(255)             Name of the course (denormalized)
//	 # subject_id           BIGINT                   Foreign key referencing st_subject (subject)
//	 # subject_name         VARCHAR(255)             Name of the subject (denormalized)
//	 # created_by           VARCHAR(45)              User who created the record
//	 # modified_by          VARCHAR(45)              User who last modified the record
//	 # created_datetime     DATETIME                 Timestamp of record creation
//	 # modified_datetime    DATETIME   

	private String first_name;
	private String last_name;
	private Date dob;
	private String gender;
	private String mobile_no;
	private String email;
	private long college_id;
	private String college_name;
	private long course_id;
	private String course_name;
	private long subject_id;
	private String subject_name;

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getCollege_id() {
		return college_id;
	}

	public void setCollege_id(long college_id) {
		this.college_id = college_id;
	}

	public String getCollege_name() {
		return college_name;
	}

	public void setCollege_name(String college_name) {
		this.college_name = college_name;
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

	public long getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(long subject_id) {
		this.subject_id = subject_id;
	}

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	@Override
	public void setResultset(ResultSet rs) {

		super.setResultset(rs);

		try {

			this.setFirst_name(rs.getString("first_name"));
			this.setLast_name(rs.getString("last_name"));
			this.setDob(rs.getDate("dob"));
			this.setGender(rs.getString("gender"));
			this.setMobile_no(rs.getString("mobile_no"));
			this.setEmail(rs.getString("email"));
			this.setCollege_id(rs.getLong("college_id"));
			this.setCollege_name(rs.getString("college_name"));
			this.setCourse_id(rs.getLong("course_id"));
			this.setCourse_name(rs.getString("course_name"));
			this.setSubject_id(rs.getLong("subject_id"));
			this.setSubject_name(rs.getString("subject_name"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getValue() {
		return null;
	}

}
