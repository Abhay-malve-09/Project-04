
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.MarksheetBean;
import in.co.rays.proj4.model.MarksheetModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * MarksheetListCtl is a controller class used to display
 * the list of student marksheets and handle marksheet
 * search operations.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/MarksheetListCtl")
public class MarksheetListCtl extends BaseListCtl<MarksheetBean, MarksheetModel> {

	private static final Logger log = Logger.getLogger(MarksheetListCtl.class);

	/**
	 * Populates the MarksheetBean with marksheet details
	 * received from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated MarksheetBean object
	 */
	@Override
	protected MarksheetBean populateBean(HttpServletRequest request) {

		log.debug("Populating MarksheetBean for marksheet list");

		MarksheetBean bean = new MarksheetBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		bean.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
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
	 * Returns the marksheet list view.
	 *
	 * @return the marksheet list view name
	 */
	@Override
	public String getView() {

		return ORSView.MARKSHEET_LIST_VIEW;
	}

}
