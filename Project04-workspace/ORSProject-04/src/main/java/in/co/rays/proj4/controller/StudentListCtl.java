
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * StudentListCtl is a controller class used to display
 * the list of students and handle student search operations.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/StudentListCtl")
public class StudentListCtl extends BaseListCtl<StudentBean, StudentModel> {

	private static final Logger log = Logger.getLogger(StudentListCtl.class);

	/**
	 * Populates the StudentBean with student details received
	 * from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated StudentBean object
	 */
	@Override
	protected StudentBean populateBean(HttpServletRequest request) {

		log.debug("Populating StudentBean for student list");

		StudentBean bean = new StudentBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dob")));

		log.debug("StudentBean populated successfully");

		return bean;
	}

	/**
	 * Returns the StudentModel object.
	 *
	 * @return StudentModel instance
	 */
	@Override
	public StudentModel getModel() {

		log.debug("Creating StudentModel instance");
		return new StudentModel();
	}

	/**
	 * Returns the student list view.
	 *
	 * @return the student list view name
	 */
	@Override
	public String getView() {

		return ORSView.STUDENT_LIST_VIEW;
	}

}
