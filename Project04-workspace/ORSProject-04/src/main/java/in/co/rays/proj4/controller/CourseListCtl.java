
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CourseBean;
import in.co.rays.proj4.model.CourseModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * CourseListCtl is a controller class used to display
 * the list of courses and handle course search operations.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/CourseListCtl")
public class CourseListCtl extends BaseListCtl<CourseBean, CourseModel> {

	private static final Logger log = Logger.getLogger(CourseListCtl.class);

	/**
	 * Populates the CourseBean with course details
	 * received from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated CourseBean object
	 */
	@Override
	protected CourseBean populateBean(HttpServletRequest request) {

		log.debug("CourseListCtl populateBean method started");

		CourseBean bean = new CourseBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setDuration(DataUtility.getString(request.getParameter("duration")));

		log.info("CourseBean populated for course list search");

		return bean;
	}

	/**
	 * Returns the CourseModel object.
	 *
	 * @return CourseModel instance
	 */
	@Override
	public CourseModel getModel() {

		log.debug("CourseListCtl getModel method called");

		return new CourseModel();
	}

	/**
	 * Returns the course list view.
	 *
	 * @return the course list view name
	 */
	@Override
	public String getView() {

		log.debug("CourseListCtl getView method called");

		return ORSView.COURSE_LIST_VIEW;
	}

}
