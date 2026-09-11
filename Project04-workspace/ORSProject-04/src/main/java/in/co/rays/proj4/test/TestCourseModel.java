package in.co.rays.proj4.test;

import java.sql.Timestamp;
import java.util.Date;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.model.TimeTableModel;

public class TestCourseModel {

	public static void main(String[] args) throws Exception {

		testAdd();
		testDelete();

	}

	public static void testAdd() {

		CourseBean bean = new CourseBean();
		CourseModel model = new CourseModel();

		bean.setName("Java");
		bean.setDuration("6 Months");
		bean.setDescription("Programming language");
		bean.setCreatedBy("Abhay");
		bean.setModifiedBy("Abhay");
		bean.setCreatedDatetime(new Timestamp(new Date(0).getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date(0).getTime()));

		model.add(bean);
	}

	public static void testDelete() {

		CourseModel model = new CourseModel();

		model.delete(0);

	}
}