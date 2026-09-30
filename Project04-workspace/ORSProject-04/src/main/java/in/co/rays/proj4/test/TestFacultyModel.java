package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;

public class TestFacultyModel {

	public static FacultyModel model = new FacultyModel();

	public static void main(String[] args) throws ParseException {
		
//		testAdd();
		testUpdate();
//		testDelete();
//		testFindByPk();
//		testSearch();
		
	}

	private static void testAdd() throws ParseException {

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	FacultyBean bean = new FacultyBean();
	
	bean.setCollegeId(4);
//	bean.setCollegeName("IIT Indore");
	bean.setFirstName("Aa");
	bean.setLastName("patel");
	bean.setEmail("aapatel@iiti.ac.in");
	bean.setMobileNo("9876543217");
	bean.setAddress("Rau, Indore, Madhya Pradesh");
	bean.setGender("Male");
	bean.setDateOfBirth(sdf.parse("1983-12-05"));
	bean.setCreatedBy("Abhay");
	bean.setModifiedBy("Abhay");
	bean.setCreateDatetime(new Timestamp(new Date().getTime()));
	bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
	
	model.add(bean);
	
	}

	private static void testUpdate() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		FacultyBean bean = new FacultyBean();
		
		bean.setId(8);
		bean.setCollegeId(3);
//		bean.setCollegeName("IIT Indore");
		bean.setFirstName("Aap");
		bean.setLastName("patel");
		bean.setEmail("aappatel@iiti.ac.in");
		bean.setMobileNo("9876543217");
		bean.setAddress("Rau, Indore, Madhya Pradesh");
		bean.setGender("Male");
		bean.setDateOfBirth(sdf.parse("1983-12-05"));
		bean.setCreatedBy("Abhay");
		bean.setModifiedBy("Abhay");
		bean.setCreateDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
		model.update(bean);
	}

	private static void testDelete() {

		model.delete(8);
	}

	private static void testFindByPk() {

		FacultyBean bean = model.findByPk(6);
		
		System.out.println(bean.getCollegeId());
		System.out.println(bean.getCollegeName());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getEmail());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getAddress());
		System.out.println(bean.getGender());
		System.out.println(bean.getDateOfBirth());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreateDatetime());
		System.out.println(bean.getModifiedDatetime());	
	
	}

	private static void testSearch() {

		FacultyBean bean = new FacultyBean();
		
		List<FacultyBean> list = model.search(bean, 1, 5);
		
		Iterator<FacultyBean> it = list.iterator();
		
		while (it.hasNext()) {
			bean = it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getEmail());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getAddress());
			System.out.println(bean.getGender());
			System.out.println(bean.getDateOfBirth());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreateDatetime());
			System.out.println(bean.getModifiedDatetime());	
			System.out.println("--------------------");
		}
	}
}
