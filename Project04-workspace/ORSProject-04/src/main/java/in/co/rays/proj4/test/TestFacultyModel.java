package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;

public class TestFacultyModel {

	public static void main(String[] args) throws ParseException {
		
		testAdd();
		testDelete();
		
	}
	
	public static  void testAdd() throws ParseException {
		
		FacultyBean f = new FacultyBean();
		FacultyModel model = new FacultyModel();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		f.setFirst_name(null);
		f.setLast_name(null);
		f.setDob(sdf.parse(null));
		f.setGender(null);
		f.setMobile_no(null);
		f.setEmail(null);
		f.setCollege_id(0);
		f.setCollege_name(null);
		f.setCourse_id(0);
		f.setCourse_name(null);
		f.setSubject_id(0);
		f.setSubject_name(null);
		f.setCreatedBy("Abhay");
		f.setModifiedBy("Abhay");
		f.setCreatedDatetime(new Timestamp(new Date(0).getTime()));
		f.setModifiedDatetime(new Timestamp(new Date(0).getTime()));
		
		model.add(f);
	}
	
	public static void testDelete() {
		
		FacultyModel model = new FacultyModel();
		
		model.delete(0);

	}
}
