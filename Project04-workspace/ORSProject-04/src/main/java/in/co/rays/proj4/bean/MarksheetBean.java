package in.co.rays.proj4.bean;

import java.sql.ResultSet;

public class MarksheetBean extends BaseBean {

	 
	private String roll_no;
	private long student_id;
	private String name;
	private  int physics;
	private  int chemistry;
	private  int maths;
	
	public String getRoll_no() {
		return roll_no;
	}

	public void setRoll_no(String roll_no) {
		this.roll_no = roll_no;
	}

	public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhysics() {
		return physics;
	}

	public void setPhysics(int physics) {
		this.physics = physics;
	}

	public int getChemistry() {
		return chemistry;
	}

	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}

	public int getMaths() {
		return maths;
	}

	public void setMaths(int maths) {
		this.maths = maths;
	}
	
	@Override
	public void setResultset(ResultSet rs) {

		super.setResultset(rs);
		
		try {
			this.setRoll_no(rs.getString("roll_no"));
			this.setStudent_id(rs.getLong("student_id"));
			this.setName(rs.getString("name"));
			this.setPhysics(rs.getInt("physics"));
			this.setChemistry(rs.getInt("chemistry"));
			this.setMaths(rs.getInt("maths"));
			
		} catch (Exception e) {
          e.printStackTrace();
		}
	}

	@Override
	public String getValue() {
		return null;
	}

}
