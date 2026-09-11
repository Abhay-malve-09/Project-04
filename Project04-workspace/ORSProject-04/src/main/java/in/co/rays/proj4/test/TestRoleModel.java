package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.model.RoleModel;

public class TestRoleModel {

	public static void main(String[] args) throws Exception {

//		testAdd();
//		testDelete();
		testUpdate();
//		testFindByPk();
//		testFindByName();
	}

	public static void testAdd() {
		RoleBean r = new RoleBean();
		RoleModel model = new RoleModel();
		// student,college, faculty, KIOSK,

		r.setName("KIOSK");
		r.setCreatedBy("Abhay");
		r.setDescription("adding KIOSK role");
		r.setModifiedBy("Abhay");
		r.setCreatedDatetime(new Timestamp(new Date().getTime()));
		r.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(r);

	}

	public static void testUpdate() {
		RoleBean r = new RoleBean();
		RoleModel model = new RoleModel();

		r.setName("Admin");
		r.setDescription("adding Admin role");
		r.setModifiedBy("Ashish");
		r.setModifiedDatetime(new Timestamp(new Date().getTime()));
		r.setId(2);
		model.update(r);

	}

	public static void testDelete() {

		RoleModel model = new RoleModel();

		model.delete(5);

	}
	
	public static void testFindByPk() {
		   
		RoleModel model = new RoleModel();
		
		RoleBean bean = new RoleBean();
		
		bean = model.findByPK(2);
		
		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
		
	}
	
	public static void testFindByName() {
		
		RoleModel model = new RoleModel();
		
		RoleBean bean = new RoleBean();
		
		bean = model.findByName("Admin");
		
		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
		
	}
}
