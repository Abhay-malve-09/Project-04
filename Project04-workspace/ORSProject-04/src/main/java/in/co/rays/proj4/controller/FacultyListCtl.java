
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.FacultyModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * FacultyListCtl is a controller class used to display
 * the list of faculty members and handle faculty search operations.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/FacultyListCtl")
public class FacultyListCtl extends BaseListCtl<FacultyBean, FacultyModel> {

	private static final Logger log = Logger.getLogger(FacultyListCtl.class);

	/**
	 * Populates the FacultyBean with faculty details
	 * received from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated FacultyBean object
	 */
	@Override
	protected FacultyBean populateBean(HttpServletRequest request) {

		log.debug("FacultyListCtl populateBean method started");

		FacultyBean bean = new FacultyBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dob")));

		log.info("FacultyBean populated for faculty list search");

		return bean;
	}

	/**
	 * Returns the FacultyModel object.
	 *
	 * @return FacultyModel instance
	 */
	@Override
	public FacultyModel getModel() {

		log.debug("FacultyListCtl getModel method called");

		return new FacultyModel();
	}

	/**
	 * Returns the faculty list view.
	 *
	 * @return the faculty list view name
	 */
	@Override
	public String getView() {

		log.debug("FacultyListCtl getView method called");

		return ORSView.FACULTY_LIST_VIEW;
	}

}
