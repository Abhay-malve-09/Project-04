package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;

public class TestSubjectModel {

public static SubjectModel model = new SubjectModel();
	
	public static void main(String[] args) {
		
//		testAdd();
		testUpdate();
//		testDelete();
//		testFindByPk();
//		testSearch();	
		
	}

	private static void testAdd() {

		SubjectBean bean = new SubjectBean();
		
//		bean.setName("SQL and Database");
		bean.setDescription("learn spring boot and frameworks");
		bean.setCourseId(5);
		bean.setCreatedBy("Abhay");
		bean.setModifiedBy("Abhay");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);
		
	}

	private static void testUpdate() {
		
		SubjectBean bean = new SubjectBean();

		bean.setId(6);
//		bean.setName("Spring Boot");
		bean.setDescription("Learn Python programming from basics to advanced concepts, including functions, OOP, modules, and exception handling");
		bean.setCourseId(2);
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

		SubjectBean bean = model.findByPk(2);
		
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCourseId());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
	}

	private static void testSearch() {

		SubjectBean bean = new SubjectBean();
		
//		bean.setName("java");
		
		List<SubjectBean> list = model.search(bean, 1, 5);
		
		Iterator<SubjectBean> it = list.iterator();
		
		while (it.hasNext()) {
			bean = it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());	
			System.out.println("-------------------");
			
		}
	}

}
