
package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.xml.crypto.Data;

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
 * ForgetPasswordCtl is a controller class used to handle
 * forgot password operations.
 * It validates the user's login email and sends the password
 * to the registered email address.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ForgetPasswordCtl")
public class ForgetPasswordCtl extends BaseCtl<UserBean, UserModel> {

	private static final Logger log = Logger.getLogger(ForgetPasswordCtl.class);

	/**
	 * Validates the login field and checks whether
	 * the entered login is in a valid email format.
	 *
	 * @param request the HttpServletRequest object
	 * @return true if validation is successful, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("ForgetPasswordCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required");
			log.warn("Login validation failed: login is required");
			pass = false;

		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", "login is not in valid formate");
			log.warn("Login validation failed: invalid email format");
			pass = false;
		}

		log.debug("ForgetPasswordCtl validation completed");

		return pass;
	}

	/**
	 * Populates the UserBean with the login details
	 * received from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("ForgetPasswordCtl populateBean method started");

		UserBean bean = new UserBean();

		bean.setLogin(DataUtility.getString(request.getParameter("login")));

		log.info("UserBean populated for forgot password request");

		return bean;

	}

	/**
	 * Handles HTTP POST requests for forgot password functionality.
	 * Retrieves the user login and requests the model to process
	 * the forgot password operation.
	 *
	 * @param request the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("ForgetPasswordCtl doPost method started");

		UserBean bean = populateBean(request);
		UserModel model = getModel();

		log.info("Forgot password request received");

		bean = model.forgotPassword(bean.getLogin());

		if (bean != null) {

			log.info("Forgot password request processed successfully");

			ServletUtility.setSuccessMessage("Password has been sent to your email", request);

		} else {
			log.warn("Forgot password request could not be processed");

			ServletUtility.forward(getView(), request, response);

		}

		log.debug("ForgetPasswordCtl doPost method completed");
	}

	/**
	 * Returns the UserModel object.
	 *
	 * @return UserModel instance
	 */
	@Override
	public UserModel getModel() {

		log.debug("ForgetPasswordCtl getModel method called");

		return new UserModel();
	}

	/**
	 * Returns the forgot password view.
	 *
	 * @return the forgot password view name
	 */
	@Override
	public String getView() {

		log.debug("ForgetPasswordCtl getView method called");

		return ORSView.FORGET_PASSWORD_VIEW;
	}

}
