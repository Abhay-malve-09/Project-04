package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;

public class TestCourseModel {
	
	public static CourseModel model = new CourseModel();
	
	public static void main(String[] args) {
		
//		testAdd();
		testUpdate();
//		testDelete();
//		testFindByPk();
//		testSearch();	
		
	}

	private static void testAdd() {

		CourseBean bean = new CourseBean();
		
		bean.setName("Spring Boot Development");
		bean.setDescription("Build enterprise applications using Spring Boot and REST APIs");
		bean.setDuration("80 Days");
		bean.setCreatedBy("Abhay");
		bean.setModifiedBy("Abhay");
		bean.setCreateDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

	private static void testUpdate() {

		CourseBean bean = new CourseBean();	
		
		bean.setId(2);
		bean.setName("Spring Boot Development");
		bean.setDescription("Build enterprise applications using Spring Boot and REST APIs:");
		bean.setDuration("80 Days-");
		bean.setCreatedBy("Abhay");
		bean.setModifiedBy("Abhay");
		bean.setCreateDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
		model.update(bean);
	}

	private static void testDelete() {

		model.delete(6);
	}

	private static void testFindByPk() {

		CourseBean bean = model.findByPk(3);
		
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getDuration());
		System.err.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreateDatetime());
		System.out.println(bean.getModifiedDatetime());
		
		
	}

	private static void testSearch() {

		CourseBean bean = new CourseBean();
		
//		bean.setName("Java");
		
		List<CourseBean> list = model.search(bean, 1, 5);
		
		Iterator<CourseBean> it = list.iterator();
		
		while (it.hasNext()) {
			bean = it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getDuration());
			System.err.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreateDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("---------------------");
		}
	}

}
