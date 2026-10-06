package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;

public class TestUserModel {

	public static UserModel model = new UserModel();
	
	public static void main(String[] args) throws ParseException {
		
//		 testAdd();
		 testUpdate();
//		testDelete();
//		testFindByPk();
//		testSearch();
	}

	private static void testAdd() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserBean bean  = new UserBean();

		bean.setFirstName("piyush");
		bean.setLastName("sing");
		bean.setLogin("piyush@gmail.com");
		bean.setPassword("piyush@23");
		bean.setDob(sdf.parse("1998-08-24"));
		bean.setMobileNo("8899778877");
		bean.setRoleId(5); 
		bean.setUnsuccessfulLogin(0);
		bean.setGender("Male");
//		bean.setLastLogin(sdf.parse("2026-09-22"));
		bean.setUserLock("N");
		bean.setRegisteredIp("192.168.0.101");
		bean.setLastLoginIp("192.168.0.115");
		bean.setCreatedBy("Abhay");
		bean.setModifiedBy("Abhay");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		
		model.add(bean);
	}
	
	private static void testUpdate() throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserBean bean =  new UserBean();
		
		bean.setId(1);
		bean.setFirstName("Pramood");
		bean.setLastName("Kumar");
		bean.setLogin("pramood@gmail.com");
		bean.setPassword("12");
		bean.setDob(sdf.parse("2002-09-25"));
		bean.setMobileNo("6275169966");
		bean.setRoleId(1); 
		bean.setUnsuccessfulLogin(0);
		bean.setGender("Male");
//		bean.setLastLogin(sdf.parse("2026-09-23"));
		bean.setUserLock("N");
		bean.setRegisteredIp("127.0.0.1");
		bean.setLastLoginIp("127.0.0.1");
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
		
		UserBean bean = model.findByPk(2);
		
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getLogin());
		System.out.println(bean.getPassword());
		System.out.println(bean.getDob());
		System.out.println(bean.getMobileNo());
		System.out.println(bean.getRoleId());
		System.out.println(bean.getUnsuccessfulLogin());
		System.out.println(bean.getGender());
		System.out.println(bean.getLastLogin());
		System.out.println(bean.getUserLock());
		System.out.println(bean.getRegisteredIp());
		System.out.println(bean.getLastLoginIp());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());
		
		
	}
	
	private static void testSearch() {
		
		UserBean bean = new UserBean();
		
		List<UserBean> list = model.search(bean, 1, 5);
		
		Iterator<UserBean> it  = list.iterator();
		
		while (it.hasNext()) {
			bean = it.next();
			
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getUnsuccessfulLogin());
			System.out.println(bean.getGender());
			System.out.println(bean.getLastLogin());
			System.out.println(bean.getUserLock());
			System.out.println(bean.getRegisteredIp());
			System.out.println(bean.getLastLoginIp());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("----------------------");
		}
	}
	
}
