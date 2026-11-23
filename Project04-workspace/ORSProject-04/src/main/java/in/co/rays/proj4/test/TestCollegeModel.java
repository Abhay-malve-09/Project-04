package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;

/**
 * TestCollegeModel class is used to test the CRUD operations
 * of CollegeModel class.
 * It tests add, update, delete, findByPk and search operations.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class TestCollegeModel {

	/**
	 * CollegeModel object used to perform database operations.
	 */
	public static CollegeModel model = new CollegeModel();

	/**
	 * Main method to execute CollegeModel test methods.
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
	 * Tests the add operation of CollegeModel.
	 * Creates a CollegeBean object and inserts college details
	 * into the database.
	 */
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

	/**
	 * Tests the update operation of CollegeModel.
	 * Updates the college details using the college ID.
	 */
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

	/**
	 * Tests the delete operation of CollegeModel.
	 * Deletes a college record using its ID.
	 */
	private static void testDelete() {

		model.delete(6);
	}

	/**
	 * Tests the findByPk operation of CollegeModel.
	 * Retrieves college details using the primary key
	 * and displays the retrieved information.
	 */
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

	/**
	 * Tests the search operation of CollegeModel.
	 * Searches college records based on college name
	 * and displays the retrieved records.
	 */
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