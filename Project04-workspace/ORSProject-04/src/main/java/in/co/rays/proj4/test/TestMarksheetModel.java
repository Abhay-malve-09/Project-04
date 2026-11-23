package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;

/**
 * TestMarksheetModel class is used to test CRUD operations
 * of MarksheetModel class.
 * It tests add, update, delete, findByPk and search operations.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class TestMarksheetModel {

	/**
	 * MarksheetModel object used to perform database operations.
	 */
	public static MarksheetModel model = new MarksheetModel();

	/**
	 * Main method to execute MarksheetModel test methods.
	 *
	 * @param args command line arguments
	 */
	public static void main(String[] args) {

		// testAdd();
		testUpdate();
		// testDelete();
		// testFindByPk();
		// testSearch();
	}

	/**
	 * Tests the add operation of MarksheetModel.
	 * Creates a MarksheetBean object and inserts marksheet details
	 * into the database.
	 */
	private static void testAdd() {

		MarksheetBean bean = new MarksheetBean();

		bean.setRollNo("106");
		bean.setStudentId(4);
		// bean.setName("Rupali Bhargave");
		bean.setPhysics(93);
		bean.setChemistry(95);
		bean.setMaths(89);
		bean.setCreatedBy("Abhay");
		bean.setModifiedBy("Abhay");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

	/**
	 * Tests the update operation of MarksheetModel.
	 * Updates marksheet details using the marksheet ID.
	 */
	private static void testUpdate() {

		MarksheetBean bean = new MarksheetBean();

		bean.setId(6);
		bean.setRollNo("106");
		bean.setStudentId(2);
		// bean.setName("Rupalii Bhargav");
		bean.setPhysics(93);
		bean.setChemistry(95);
		bean.setMaths(89);
		bean.setCreatedBy("Abhay");
		bean.setModifiedBy("Abhay");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);

	}

	/**
	 * Tests the delete operation of MarksheetModel.
	 * Deletes a marksheet record using its ID.
	 */
	private static void testDelete() {

		model.delete(6);
	}

	/**
	 * Tests the findByPk operation of MarksheetModel.
	 * Retrieves marksheet details using the primary key
	 * and displays the retrieved information.
	 */
	private static void testFindByPk() {

		MarksheetBean bean = model.findByPk(2);

		System.out.println(bean.getId());
		System.out.println(bean.getRollNo());
		System.out.println(bean.getStudentId());
		System.out.println(bean.getName());
		System.out.println(bean.getPhysics());
		System.out.println(bean.getChemistry());
		System.out.println(bean.getMaths());
		System.out.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}

	/**
	 * Tests the search operation of MarksheetModel.
	 * Searches marksheet records and displays the retrieved
	 * marksheet information.
	 */
	private static void testSearch() {

		MarksheetBean bean = new MarksheetBean();

		// bean.setName("Rupali");

		List<MarksheetBean> list = model.search(bean, 1, 5);

		Iterator<MarksheetBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();

			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getStudentId());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("-----------------------");

		}
	}
}