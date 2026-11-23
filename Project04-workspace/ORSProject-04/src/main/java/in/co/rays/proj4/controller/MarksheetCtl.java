
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * MarksheetCtl is a controller class used to handle
 * marksheet-related operations such as validating and
 * populating student marksheet details.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/MarksheetCtl")
public class MarksheetCtl extends BaseCtl<MarksheetBean, MarksheetModel> {

	private static final Logger log = Logger.getLogger(MarksheetCtl.class);

	/**
	 * Validates the roll number and subject marks entered
	 * by the user.
	 *
	 * @param request the HttpServletRequest object
	 * @return true if validation is successful, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("Marksheet validation started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", "roll no is required");
			pass = false;
			log.warn("Marksheet validation failed: roll number is required");
		}

		/*
		 * if (DataValidator.isNull(request.getParameter("name"))) {
		 * request.setAttribute("name", "name is required"); pass = false; }
		 */

		if (!DataValidator.isInteger(request.getParameter("physics"))) {
			request.setAttribute("physics", "Enter valid marks");
			pass = false;
			log.warn("Marksheet validation failed: invalid physics marks");
		}
		if (DataValidator.isNull(request.getParameter("chemistry"))) {
			request.setAttribute("chemistry", "Enter valid marks");
			pass = false;
			log.warn("Marksheet validation failed: chemistry marks are required");
		}
		if (DataValidator.isNull(request.getParameter("maths"))) {
			request.setAttribute("maths", "Enter valid marks");
			pass = false;
			log.warn("Marksheet validation failed: maths marks are required");
		}

		log.debug("Marksheet validation completed");
		return pass;
	}

	/**
	 * Populates the MarksheetBean with marksheet details
	 * received from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated MarksheetBean object
	 */
	@Override
	protected MarksheetBean populateBean(HttpServletRequest request) {

		log.debug("Populating MarksheetBean");

		MarksheetBean bean = new MarksheetBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		//bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));

		return bean;
	}

	/**
	 * Returns the MarksheetModel object.
	 *
	 * @return MarksheetModel instance
	 */
	@Override
	public MarksheetModel getModel() {

		log.debug("Creating MarksheetModel instance");
		return new MarksheetModel();
	}

	/**
	 * Returns the marksheet view.
	 *
	 * @return the marksheet view name
	 */
	@Override
	public String getView() {

		return ORSView.MARKSHEET_VIEW;
	}

}
