
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * SubjectListCtl is a controller class used to display
 * the list of subjects and handle subject search operations.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/SubjectListCtl")
public class SubjectListCtl extends BaseListCtl<SubjectBean, SubjectModel> {

	private static final Logger log = Logger.getLogger(SubjectListCtl.class);

	/**
	 * Populates the SubjectBean with subject details received
	 * from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated SubjectBean object
	 */
	@Override
	protected SubjectBean populateBean(HttpServletRequest request) {

		log.debug("Populating SubjectBean for subject list");

		SubjectBean bean = new SubjectBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		log.debug("SubjectBean populated successfully");

		return bean;

	}

	/**
	 * Returns the SubjectModel object.
	 *
	 * @return SubjectModel instance
	 */
	@Override
	public SubjectModel getModel() {

		log.debug("Creating SubjectModel instance");
		return new SubjectModel();
	}

	/**
	 * Returns the subject list view.
	 *
	 * @return the subject list view name
	 */
	@Override
	public String getView() {

		return ORSView.SUBJECT_LIST_VIEW;
	}

}
