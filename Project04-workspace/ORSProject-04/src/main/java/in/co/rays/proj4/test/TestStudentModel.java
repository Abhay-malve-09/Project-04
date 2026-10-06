package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.StudentModel;

public class TestStudentModel {

	public static StudentModel model = new StudentModel();
	
	public static void main(String[] args) throws ParseException {
		
		testAdd();
//		testUpdate();
//		testDelete();
//		testFindByPk();
//		testSearch();
	}

	private static void testAdd() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StudentBean bean = new StudentBean();
		
		bean.setCollegeId(4);
//		bean.setCollegeName("Medicaps University");
		bean.setFirstName("Male");
		bean.setLastName("Jhada");
		bean.setDateOfBirth(sdf.parse("2002-05-13"));
		bean.setMobileNo("7573920168");
		bean.setEmail("male@gmail.com");
		bean.setCreatedBy("Abhay");
		bean.setModifiedBy("Abhay");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
		model.add(bean);
	}

	private static void testUpdate() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StudentBean bean = new StudentBean();
		
		bean.setId(2);
		bean.setCollegeId(2);
		bean.setCollegeName("SGSITS");
		bean.setFirstName("Atul");
		bean.setLastName("Karma");
		bean.setDateOfBirth(sdf.parse("2002-06-09"));
		bean.setMobileNo("9977623167");
		bean.setEmail("atul@gmail.com");
		bean.setCreatedBy("Abhay");
		bean.setModifiedBy("Abhay");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
		model.update(bean);
		
	}

	private static void testDelete() {

		model.delete(6);
	}

	private static void testFindByPk() {

		StudentBean bean = model.findByPk(2);
		
		System.out.println(bean.getCollegeId());
		System.out.println(bean.getCollegeName());
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getDateOfBirth());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getEmail());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}

	private static void testSearch() {

		StudentBean bean = new StudentBean();
		
		List<StudentBean> list = model.search(bean, 1, 5);
		
		Iterator<StudentBean> it = list.iterator();
		
		while (it.hasNext()) {
			bean = it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getCollegeId());
			System.out.println(bean.getCollegeName());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getDateOfBirth());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getEmail());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("------------------");
			
			
		}
	}
}
