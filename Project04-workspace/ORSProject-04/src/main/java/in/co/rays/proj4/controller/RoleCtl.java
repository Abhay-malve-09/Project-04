
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * RoleCtl is a controller class used to handle role-related
 * operations such as adding, updating and validating role details.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/RoleCtl")
public class RoleCtl extends BaseCtl<RoleBean, RoleModel> {

	private static final Logger log = Logger.getLogger(RoleCtl.class);

	/**
	 * Validates the role name and description fields.
	 *
	 * @param request the HttpServletRequest object
	 * @return true if validation is successful, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("Role validation started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", "role name is required");
			pass = false;
			log.warn("Role validation failed: role name is required");
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", "descripion is required");
			pass = false;
			log.warn("Role validation failed: description is required");
		}

		log.debug("Role validation completed");

		return pass;

	}

	/**
	 * Populates the RoleBean with role details received
	 * from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated RoleBean object
	 */
	@Override
	protected RoleBean populateBean(HttpServletRequest request) {

		log.debug("Populating RoleBean");

		RoleBean bean = new RoleBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setId(DataUtility.getLong(request.getParameter("id")));

		populateDTO(bean, request);

		log.debug("RoleBean populated successfully");

		return bean;

	}

	/**
	 * Returns the RoleModel object.
	 *
	 * @return RoleModel instance
	 */
	@Override
	public RoleModel getModel() {

		log.debug("Creating RoleModel instance");
		return new RoleModel();
	}

	/**
	 * Returns the role view.
	 *
	 * @return the role view name
	 */
	@Override
	public String getView() {

		return ORSView.ROLE_VIEW;
	}

}
