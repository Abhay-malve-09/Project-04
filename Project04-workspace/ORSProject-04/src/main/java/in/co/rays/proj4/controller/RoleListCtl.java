
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * RoleListCtl is a controller class used to display
 * the list of roles and handle role search operations.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/RoleListCtl")
public class RoleListCtl extends BaseListCtl<RoleBean, RoleModel> {

	private static final Logger log = Logger.getLogger(RoleListCtl.class);

	/**
	 * Populates the RoleBean with role details received
	 * from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated RoleBean object
	 */
	@Override
	protected RoleBean populateBean(HttpServletRequest request) {

		log.debug("Populating RoleBean for role list");

		RoleBean bean = new RoleBean();

		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));

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
	 * Returns the role list view.
	 *
	 * @return the role list view name
	 */
	@Override
	public String getView() {

		return ORSView.ROLE_LIST_VIEW;
	}

}
