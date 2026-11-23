
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.SubjectBean;
import in.co.rays.proj4.model.SubjectModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * SubjectCtl is a controller class used to handle subject-related
 * operations such as adding, updating and validating subject details.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/SubjectCtl")
public class SubjectCtl extends BaseCtl<SubjectBean, SubjectModel> {

	private static final Logger log = Logger.getLogger(SubjectCtl.class);

	/**
	 * Validates the subject name, description and course ID fields.
	 *
	 * @param request the HttpServletRequest object
	 * @return true if validation is successful, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("Validating subject details");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "name is required");
			pass = false;
			log.warn("Subject name is required");
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", "description is required");
			pass = false;
			log.warn("Subject description is required");
		}

		if (DataValidator.isNull(request.getParameter("courseId"))) {
			request.setAttribute("courseId", "courseId is required");
			pass = false;
			log.warn("Course ID is required");
		}

		log.debug("Subject validation completed. Result: " + pass);

		return pass;
	}

	/**
	 * Populates the SubjectBean with subject details received
	 * from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated SubjectBean object
	 */
	@Override
	protected SubjectBean populateBean(HttpServletRequest request) {

		log.debug("Populating SubjectBean");

		SubjectBean bean = new SubjectBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
		bean.setId(DataUtility.getLong(request.getParameter("id")));

		populateDTO(bean, request);

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
	 * Returns the subject view.
	 *
	 * @return the subject view name
	 */
	@Override
	public String getView() {

		return ORSView.SUBJECT_VIEW;
	}

}
