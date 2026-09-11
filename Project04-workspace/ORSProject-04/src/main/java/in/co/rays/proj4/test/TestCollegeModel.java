package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.model.FacultyModel;

public class TestCollegeModel {

	public static void main(String[] args) throws Exception {

//		testAdd();
		testDelete();
//		testUpdate();

	}

	public static void testAdd() {

		CollegeBean c = new CollegeBean();
		CollegeModel model = new CollegeModel();

		c.setName("IPS");
		c.setAddress("Rajendra Nagar, Indore");
		c.setState("Madhya Pradesh");
		c.setCity("Indore");
		c.setPhone_no("9976542312");
		c.setCreatedBy("Abhay");
		c.setModifiedBy("Abhay");
		c.setCreatedDatetime(new Timestamp(new Date(0).getTime()));
		c.setModifiedDatetime(new Timestamp(new Date(0).getTime()));

		model.add(c);
	}

	public static void testUpdate() {

		CollegeBean c = new CollegeBean();
		CollegeModel model = new CollegeModel();

		c.setName("Medicaps");
		c.setAddress("Rau, Indore");
		c.setState("Madhya Pradesh");
		c.setCity("Indore");
		c.setPhone_no("9977226267");
		c.setModifiedBy("Abhishekh");
		c.setModifiedDatetime(new Timestamp(new Date(0).getTime()));
		c.setId(1);

		model.update(c);
	}
	public static void testDelete() { 

		CollegeModel model = new CollegeModel();

		model.delete(1);
	}
}
