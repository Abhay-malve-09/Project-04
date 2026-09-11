package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class CourseBean extends BaseBean {

	private String name;
	private String duration;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
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
			this.setDuration(rs.getString("duration"));
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
