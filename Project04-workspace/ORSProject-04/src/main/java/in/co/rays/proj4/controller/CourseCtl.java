
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * CourseCtl is a controller class used to handle course
 * related operations such as validation and processing
 * course details.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/CourseCtl")
public class CourseCtl extends BaseCtl<CourseBean, CourseModel> {

	private static final Logger log = Logger.getLogger(CourseCtl.class);

	/**
	 * Validates the course name, description and duration.
	 *
	 * @param request the HttpServletRequest object
	 * @return true if validation is successful, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CourseCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("courseName"))) {
			request.setAttribute("courseName", "Course Name is required");
			log.warn("Course name validation failed");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", "description is required");
			log.warn("Course description validation failed");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("duration"))) {
			request.setAttribute("duration", "duration is required");
			log.warn("Course duration validation failed");
			pass = false;
		}

		log.debug("CourseCtl validation completed");

		return pass;
	}

	/**
	 * Populates the CourseBean with course details
	 * received from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated CourseBean object
	 */
	@Override
	protected CourseBean populateBean(HttpServletRequest request) {

		log.debug("CourseCtl populateBean method started");

		CourseBean bean = new CourseBean();

		bean.setName(DataUtility.getString(request.getParameter("courseName")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));;

		populateDTO(bean, request);

		log.info("CourseBean populated successfully");

		return bean;

	}

	/**
	 * Returns the CourseModel object.
	 *
	 * @return CourseModel instance
	 */
	@Override
	public CourseModel getModel() {

		log.debug("CourseCtl getModel method called");

		return new CourseModel();
	}

	/**
	 * Returns the course view.
	 *
	 * @return the course view name
	 */
	@Override
	public String getView() {

		log.debug("CourseCtl getView method called");

		return ORSView.COURSE_VIEW;
	}

}
