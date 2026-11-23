package in.co.rays.proj4.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.sql.Timestamp;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;

//1 = admin
//2 = student
//3 = faculty
//4 = college
//5 = KIOSK

/**
 * TestRoleModel class is used to test CRUD operations
 * of RoleModel class.
 * It tests add, update, delete, findByPk and search operations.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class TestRoleModel {

	/**
	 * RoleModel object used to perform database operations.
	 */
	public static RoleModel model = new RoleModel();

	/**
	 * Main method to execute RoleModel test methods.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {

		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		testSearch();
	}

	/**
	 * Tests the add operation of RoleModel.
	 * Creates a RoleBean object and inserts role details
	 * into the database.
	 */
	private static void testAdd() {
		RoleBean bean = new RoleBean();

		bean.setName("KIOSK");
		bean.setDescription("KIOSK Role");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

	/**
	 * Tests the delete operation of RoleModel.
	 * Deletes a role record using its ID.
	 */
	private static void testDelete() {

		model.delete(7);
	}

	/**
	 * Tests the update operation of RoleModel.
	 * Updates role details using the role ID.
	 */
	private static void testUpdate() {

		RoleBean bean = new RoleBean();

		bean.setId(1);
		bean.setName("Admin");
		bean.setDescription("Admin role");
		bean.setCreatedBy("root");
		bean.setModifiedBy("root");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}

	/**
	 * Tests the findByPk operation of RoleModel.
	 * Retrieves role details using the primary key
	 * and displays the retrieved information.
	 */
	private static void testFindByPk() {

		RoleBean bean = model.findByPk(2); //rolemodel ke through call huin he

		System.out.println(bean.getId());
		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}

	/**
	 * Tests the search operation of RoleModel.
	 * Searches role records and displays the retrieved
	 * role information.
	 */
	private static void testSearch() {

		RoleBean bean = new RoleBean();

		// bean.setName("student");

		List<RoleBean> list = model.search(bean, 1, 5);

		Iterator<RoleBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("----------------------");
		}
	}

}