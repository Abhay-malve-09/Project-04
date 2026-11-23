
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * CollegelListCtl is a controller class used to display
 * the list of colleges and handle college search operations.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/CollegeListCtl")
public class CollegelListCtl extends BaseListCtl<CollegeBean, CollegeModel> {

	private static final Logger log = Logger.getLogger(CollegelListCtl.class);

	/**
	 * Populates the CollegeBean with college details
	 * received from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated CollegeBean object
	 */
	@Override
	protected CollegeBean populateBean(HttpServletRequest request) {

		log.debug("CollegelListCtl populateBean method started");

		CollegeBean bean = new CollegeBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setState(DataUtility.getString(request.getParameter("state")));
		bean.setCity(DataUtility.getString(request.getParameter("city")));

		log.info("CollegeBean populated for college list search");

		return bean;
	}

	/**
	 * Returns the CollegeModel object.
	 *
	 * @return CollegeModel instance
	 */
	@Override
	public CollegeModel getModel() {

		log.debug("CollegelListCtl getModel method called");

		return new CollegeModel();
	}

	/**
	 * Returns the college list view.
	 *
	 * @return the college list view name
	 */
	@Override
	public String getView() {

		log.debug("CollegelListCtl getView method called");

		return ORSView.COLLEGE_LIST_VIEW;
	}

}
