package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class CollegeBean extends BaseBean {

	private String name;
	private String address;
	private String state;
	private String city;
	private String phone_no;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	
	@Override
	public void setResultset(ResultSet rs) {
		
		super.setResultset(rs);
		
		try {
			
			this.setName(rs.getString("name"));
			this.setAddress(rs.getString("address"));
			this.setState(rs.getString("state"));
			this.setCity(rs.getString("city"));
			this.setPhone_no(rs.getString("phone_no"));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public String getValue() {
		return name;
	}

}
