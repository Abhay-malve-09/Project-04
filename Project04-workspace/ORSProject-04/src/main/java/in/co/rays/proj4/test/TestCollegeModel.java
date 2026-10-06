package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;

public class TestCollegeModel {

	public static CollegeModel model =  new CollegeModel();
	
	public static void main(String[] args) {
		
//		testAdd();
		testUpdate();
//		testDelete();
//		testFindByPk();
//		testSearch();
	}

	private static void testAdd() {
		
		CollegeBean bean = new CollegeBean();
		
		bean.setName("SGSITS");
		bean.setAddress("23, Park Road (Sir M. Visvesvaraya Marg), Vallabh Nagar");
		bean.setState("Madhya Pradesh");
		bean.setCity("Indore");
		bean.setPhoneNo("07312570000");
		bean.setCreatedBy("Abhay");
		bean.setModifiedBy("Abhay");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
		model.add(bean);
	}
	
	private static void testUpdate() {
		
		CollegeBean bean = new CollegeBean();
		
		bean.setId(2);
		bean.setName("DAVV");
		bean.setAddress("IT Park");
		bean.setState("Madhya Pradesh");
		bean.setCity("Indore");
		bean.setPhoneNo("07312570000");
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

		CollegeBean bean = model.findByPk(2);
		
		System.out.println(bean.getName());
		System.out.println(bean.getAddress());
		System.out.println(bean.getState());
		System.out.println(bean.getCity());
		System.out.println(bean.getPhoneNo());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
		
		
	}

	private static void testSearch() {

		CollegeBean bean = new CollegeBean();
		
		bean.setName("DAVV");;
		
		List<CollegeBean> list = model.search(bean, 1, 5);
		
		Iterator<CollegeBean> it = list.iterator();
		
		while (it.hasNext()) {
			bean = it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("----------------------");
		}
	}

}
