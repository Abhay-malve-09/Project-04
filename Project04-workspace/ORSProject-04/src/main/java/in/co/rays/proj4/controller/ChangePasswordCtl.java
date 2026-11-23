
package in.co.rays.proj4.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * ChangePasswordCtl is a controller class used to handle
 * user password change operations.
 * It validates user input, retrieves user details and
 * updates the password through UserModel.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/ChangePasswordCtl")
public class ChangePasswordCtl extends BaseCtl<UserBean, UserModel> {

	private static final Logger log = Logger.getLogger(ChangePasswordCtl.class);

	/**
	 * Validates the login, new password and old password fields.
	 *
	 * @param request the HttpServletRequest object
	 * @return true if all fields are valid, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ChangePasswordCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required");

			log.warn("Login validation failed");

			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("newPassword"))) {

			request.setAttribute("newPassword", "newPassword is required");

			log.warn("New password validation failed");

			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("oldPassword"))) {

			request.setAttribute("oldPassword", "oldPassword is required");

			log.warn("Old password validation failed");

			pass = false;
		}

		log.debug("ChangePasswordCtl validation completed");

		return pass;
	}

	/**
	 * Populates the UserBean with login and old password
	 * values received from the request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("ChangePasswordCtl populateBean method started");

		UserBean bean = new UserBean();

		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("oldPassword")));

		log.debug("UserBean populated successfully");

		return bean;
	}

	/**
	 * Handles HTTP POST requests to change the user's password.
	 * Displays a success message if the password is changed,
	 * otherwise displays an error message.
	 *
	 * @param request the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("ChangePasswordCtl doPost method started");

		UserBean bean = populateBean(request);
		UserModel model = getModel();

		log.info("Password change request received");

		bean = model.changePassword(request.getParameter("newPassword"), bean.getPassword(), bean.getLogin());

		if (bean != null) {
			log.info("Password changed successfully");

			ServletUtility.setSuccessMessage("your password has been changed check your email", request);

		} else {
			log.warn("Password change failed");

			ServletUtility.setErrorMessage("your email is incorrect", request);

		}

		ServletUtility.forward(getView(), request, response);

		log.debug("ChangePasswordCtl doPost method completed");

	}

	/**
	 * Returns the UserModel object.
	 *
	 * @return UserModel instance
	 */
	@Override
	public UserModel getModel() {

		log.debug("ChangePasswordCtl getModel method called");

		return new UserModel();
	}

	/**
	 * Returns the change password view.
	 *
	 * @return the change password view name
	 */
	@Override
	public String getView() {

		log.debug("ChangePasswordCtl getView method called");

		return ORSView.CHANGE_PASSWORD_VIEW;
	}
}
