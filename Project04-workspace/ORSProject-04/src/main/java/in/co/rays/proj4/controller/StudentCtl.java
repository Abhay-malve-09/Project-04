
package in.co.rays.proj4.controller;

import java.util.List;

import javax.print.attribute.standard.RequestingUserName;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * StudentCtl is a controller class used to handle student-related
 * operations such as adding, updating and validating student details.
 * It also loads college information for the student form.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/StudentCtl")
public class StudentCtl extends BaseCtl<StudentBean, StudentModel> {

	private static final Logger log = Logger.getLogger(StudentCtl.class);

	/**
	 * Loads the college list from CollegeModel and stores it
	 * in the HTTP request for displaying college information.
	 *
	 * @param request the HttpServletRequest object
	 */
	@Override
	protected void preload(HttpServletRequest request) {

		log.debug("Loading college list for student form");

		CollegeModel model = new CollegeModel();

		List<CollegeBean> list = model.search(null, 0, 0);

		request.setAttribute("list", list);
//		ServletUtility.setList(list, request);

		log.debug("College list loaded successfully");

	}

	/**
	 * Validates the student details including first name,
	 * last name, email, mobile number, college ID and date of birth.
	 *
	 * @param request the HttpServletRequest object
	 * @return true if validation is successful, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("Student validation started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "first name is required");
			pass = false;
			log.warn("Student validation failed: first name is required");
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "last name is required");
			pass = false;
			log.warn("Student validation failed: last name is required");
		}

		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", "email is required");
			pass = false;
			log.warn("Student validation failed: email is required");

		} else if (!DataValidator.isEmail(request.getParameter("email"))) {
			request.setAttribute("email", "email is not in valid format");
			pass = false;
			log.warn("Student validation failed: invalid email format");
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "mobile No is required");
			pass = false;
			log.warn("Student validation failed: mobile number is required");
		}

		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId", "collegeId is required");
			pass = false;
			log.warn("Student validation failed: college ID is required");
		}
		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", "dob is required");
			pass = false;
			log.warn("Student validation failed: date of birth is required");
		}

		log.debug("Student validation completed");

		return pass;

	}

	/**
	 * Populates the StudentBean with student details received
	 * from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated StudentBean object
	 */
	@Override
	protected StudentBean populateBean(HttpServletRequest request) {

		log.debug("Populating StudentBean");

		StudentBean bean = new StudentBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dob")));

		populateDTO(bean, request);

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
	 * Returns the student view.
	 *
	 * @return the student view name
	 */
	@Override
	public String getView() {

		return ORSView.STUDENT_VIEW;

	}

}
