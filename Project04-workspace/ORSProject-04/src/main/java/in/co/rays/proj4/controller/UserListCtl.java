
package in.co.rays.proj4.controller;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * UserListCtl is a controller class used to display
 * the list of users and handle user search operations.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/UserListCtl")
public class UserListCtl extends BaseListCtl<UserBean, UserModel> {

	private static final Logger log = Logger.getLogger(UserListCtl.class);

	/**
	 * Populates the UserBean with user details received
	 * from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("Populating UserBean for user list");

		UserBean bean = new UserBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		log.debug("UserBean populated successfully");

		return bean;

	}

	/**
	 * Returns the UserModel object.
	 *
	 * @return UserModel instance
	 */
	@Override
	public UserModel getModel() {

		log.debug("Creating UserModel instance");
		return new UserModel();
	}

	/**
	 * Returns the user list view.
	 *
	 * @return the user list view name
	 */
	@Override
	public String getView() {

		return ORSView.USER_LIST_VIEW;
	}

}
