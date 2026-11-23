
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * CollegeCtl is a controller class used to handle college
 * related operations such as validating and populating
 * college details.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/CollegeCtl")
public class CollegeCtl extends BaseCtl<CollegeBean, CollegeModel> {

	private static final Logger log = Logger.getLogger(CollegeCtl.class);

	/**
	 * Validates the college details entered by the user.
	 *
	 * @param request the HttpServletRequest object
	 * @return true if validation is successful, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("CollegeCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "role name is required");
			log.warn("College name validation failed");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", "address is required");
			log.warn("College address validation failed");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("state"))) {
			request.setAttribute("state", "city is required");
			log.warn("College state validation failed");
			pass = true;
		}

		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", "city is required");
			log.warn("College city validation failed");
			pass = false;
		}

		log.debug("CollegeCtl validation completed");

		return pass;
	}

	/**
	 * Populates the CollegeBean with college details
	 * received from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated CollegeBean object
	 */
	@Override
	protected CollegeBean populateBean(HttpServletRequest request) {

		log.debug("CollegeCtl populateBean method started");

		CollegeBean bean = new CollegeBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));
		bean.setId(DataUtility.getLong(request.getParameter("id")));

		populateDTO(bean, request);

		log.info("CollegeBean populated successfully");

		return bean;
	}

	/**
	 * Returns the CollegeModel object.
	 *
	 * @return CollegeModel instance
	 */
	@Override
	public CollegeModel getModel() {

		log.debug("CollegeCtl getModel method called");

		return new CollegeModel();
	}

	/**
	 * Returns the college view.
	 *
	 * @return the college view name
	 */
	@Override
	public String getView() {

		log.debug("CollegeCtl getView method called");

		return ORSView.COLLEGE_VIEW;
	}

}
