
package in.co.rays.proj4.controller;

import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.model.FacultyModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * FacultyCtl is a controller class used to handle faculty
 * related operations such as loading college details,
 * validating faculty information and populating faculty records.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/FacultyCtl")
public class FacultyCtl extends BaseCtl<FacultyBean, FacultyModel> {

	private static final Logger log = Logger.getLogger(FacultyCtl.class);

	/**
	 * Loads the list of colleges and stores it in the request
	 * for displaying college options.
	 *
	 * @param request the HttpServletRequest object
	 */
	@Override
	protected void preload(HttpServletRequest request) {

		log.debug("FacultyCtl preload method started");

		CollegeModel model = new CollegeModel();

		List<CollegeBean> collegeList = model.search(null, 0, 0);

		ServletUtility.setList(collegeList, request);

		log.info("College list loaded successfully for faculty");
	}

	/**
	 * Validates the faculty details including first name,
	 * last name, email, mobile number, address and college ID.
	 *
	 * @param request the HttpServletRequest object
	 * @return true if validation is successful, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("FacultyCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "first name is required");
			log.warn("Faculty first name validation failed");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "last name is required");
			log.warn("Faculty last name validation failed");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", "emai is required");
			log.warn("Faculty email validation failed");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "mobileNo is required");
			log.warn("Faculty mobile number validation failed");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", "address is required");
			log.warn("Faculty address validation failed");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId", "collegeId is required");
			log.warn("Faculty college ID validation failed");
			pass = false;
		}

		log.debug("FacultyCtl validation completed");

		return pass;
	}

	/**
	 * Populates the FacultyBean with faculty details
	 * received from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated FacultyBean object
	 */
	@Override
	protected FacultyBean populateBean(HttpServletRequest request) {

		log.debug("FacultyCtl populateBean method started");

		FacultyBean bean = new FacultyBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dob")));
		bean.setId(DataUtility.getLong(request.getParameter("id")));

		populateDTO(bean, request);

		log.info("FacultyBean populated successfully");

		return bean;

	}

	/**
	 * Returns the FacultyModel object.
	 *
	 * @return FacultyModel instance
	 */
	@Override
	public FacultyModel getModel() {

		log.debug("FacultyCtl getModel method called");

		return new FacultyModel();
	}

	/**
	 * Returns the faculty view.
	 *
	 * @return the faculty view name
	 */
	@Override
	public String getView() {

		log.debug("FacultyCtl getView method called");

		return ORSView.FACULTY_VIEW;
	}

}
