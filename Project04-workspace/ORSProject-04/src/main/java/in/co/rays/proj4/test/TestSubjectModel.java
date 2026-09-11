package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.model.TimeTableModel;

public class TestSubjectModel {

	public static void main(String[] args) {
		
		testAdd();
		testDelete();
		
	}
	
	public static void testAdd() {
		
		SubjectBean s = new SubjectBean();
		SubjectModel model = new SubjectModel();
		
		s.setName("String");
		s.setCourse_id(1);
		s.setCourse_name("Java");
		s.setDescription("String ia array of char");
		s.setCreatedBy("Abhay");
		s.setModifiedBy("Abhay");
		s.setCreatedDatetime(new Timestamp(new Date(0).getTime()));
		s.setModifiedDatetime(new Timestamp(new Date(0).getTime()));

		model.add(s);
	}
	
	public static void testDelete() {

		SubjectModel model = new SubjectModel();

		model.delete(0);
	}
}
