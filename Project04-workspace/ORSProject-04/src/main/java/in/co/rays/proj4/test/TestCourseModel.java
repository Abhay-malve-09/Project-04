package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;

/**
 * TestCourseModel class is used to test CRUD operations
 * of CourseModel class.
 * It tests add, update, delete, findByPk and search operations.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public class TestCourseModel {

	/**
	 * CourseModel object used to perform database operations.
	 */
	public static CourseModel model = new CourseModel();

	/**
	 * Main method to execute CourseModel test methods.
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
	 * Tests the add operation of CourseModel.
	 * Creates a CourseBean object and inserts course details
	 * into the database.
	 */
	private static void testAdd() {

		CourseBean bean = new CourseBean();

		bean.setName("Spring Boot Development");
		bean.setDescription("Build enterprise applications using Spring Boot and REST APIs");
		bean.setDuration("80 Days");
		bean.setCreatedBy("Abhay");
		bean.setModifiedBy("Abhay");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

	/**
	 * Tests the update operation of CourseModel.
	 * Updates course details using the course ID.
	 */
	private static void testUpdate() {

		CourseBean bean = new CourseBean();

		bean.setId(2);
		bean.setName("Spring Boot Development");
		bean.setDescription("Build enterprise applications using Spring Boot and REST APIs:");
		bean.setDuration("80 Days-");
		bean.setCreatedBy("Abhay");
		bean.setModifiedBy("Abhay");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.update(bean);
	}

	/**
	 * Tests the delete operation of CourseModel.
	 * Deletes a course record using its ID.
	 */
	private static void testDelete() {

		model.delete(6);
	}

	/**
	 * Tests the findByPk operation of CourseModel.
	 * Retrieves course details using the primary key
	 * and displays the retrieved information.
	 */
	private static void testFindByPk() {

		CourseBean bean = model.findByPk(3);

		System.out.println(bean.getName());
		System.out.println(bean.getDescription());
		System.out.println(bean.getDuration());
		System.err.println(bean.getCreatedBy());
		System.out.println(bean.getModifiedBy());
		System.out.println(bean.getCreatedDatetime());
		System.out.println(bean.getModifiedDatetime());

	}

	/**
	 * Tests the search operation of CourseModel.
	 * Searches course records and displays the retrieved
	 * course information.
	 */
	private static void testSearch() {

		CourseBean bean = new CourseBean();

		// bean.setName("Java");

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
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			System.out.println("---------------------");
		}
	}

} 