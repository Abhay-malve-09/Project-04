package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.StudentModel;

public class TestStudentModel {

	public static void main(String[] args) throws ParseException {
		
		testAdd();
		testDelete();
	}
	
	public static void testAdd() throws ParseException {
		
		StudentBean s = new StudentBean();
		StudentModel model =new StudentModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		s.setFirstName("Apurv");
		s.setLastName("Jhadav");
		s.setDob(sdf.parse("2003-03-26"));
		s.setMobileNo("9088775645");
		s.setEmail("apurv@gmail.com");
		s.setCollegeId(5);
		s.setCollegeName("IPS");
		s.setCreatedBy("Abhay");
		s.setModifiedBy("Abhay");
		s.setCreatedDatetime(new Timestamp(new Date(0).getTime()));
		s.setModifiedDatetime(new Timestamp(new Date(0).getTime()));
		
		model.add(s);
		
	}
	
	public static void testDelete() {

		StudentModel model =new StudentModel();

		model.delete(0);
		
	}
}
