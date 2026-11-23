
package in.co.rays.proj4.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * LoginCtl is a controller class used to handle user login
 * and logout operations.
 * It validates user credentials, authenticates users and
 * manages user session and role information.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/LoginCtl")
public class LoginCtl extends BaseCtl<UserBean, UserModel> {

	private static final Logger log = Logger.getLogger(LoginCtl.class);

	/**
	 * Operation constant used for user sign-in.
	 */
	public final static String OP_SIGN_IN = "SignIn";

	/**
	 * Validates the login and password fields.
	 *
	 * @param request the HttpServletRequest object
	 * @return true if validation is successful, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("Login validation started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required");
			pass = false;
			log.warn("Login validation failed: login is required");
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", "password is required");
			pass = false;
			log.warn("Login validation failed: password is required");
		}

		log.debug("Login validation completed");
		return pass;

	}

	/**
	 * Handles HTTP GET requests.
	 * Performs logout operation when an operation parameter
	 * is provided and forwards the request to the login view.
	 *
	 * @param request the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("LoginCtl doGet started");

		String op = request.getParameter("operation");

		if (op != null) {
			HttpSession session = request.getSession();
			ServletUtility.setSuccessMessage("user logout successfully", request);
			session.invalidate();
			log.info("User logged out successfully");
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("LoginCtl doGet completed");

	}

	/**
	 * Populates the UserBean with login and password
	 * values received from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("Populating UserBean");

		UserBean bean = new UserBean();

		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));

		return bean;

	}

	/**
	 * Handles HTTP POST requests for user authentication.
	 * Authenticates the user, creates a session and stores
	 * the user and role information after successful login.
	 *
	 * @param request the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("LoginCtl doPost started");

		String op = DataUtility.getString(request.getParameter("operation"));
		HttpSession session = request.getSession();

		UserBean bean = populateBean(request);
		UserModel model = getModel();
		RoleModel rmodel = new RoleModel();

		if (OP_SIGN_IN.equalsIgnoreCase(op)) {

			log.debug("User authentication started");

			bean = model.authenticate(bean.getLogin(), bean.getPassword());

			if (bean != null) {

				session.setAttribute("user", bean);
				RoleBean rbean = rmodel.findByPk(bean.getRoleId());
				session.setAttribute("role", rbean.getName());

				log.info("User authenticated successfully");

				ServletUtility.redirect(ORSView.WELCOME_CTL, request, response);

				return;

			} else {

				log.warn("User authentication failed");
				ServletUtility.setErrorMessage("Invalid login or passwod", request);
			}

		}

		//Field already fill

		bean = populateBean(request);
		ServletUtility.setBean(bean, request);
		ServletUtility.forward(getView(), request, response);

		log.debug("LoginCtl doPost completed");
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
	 * Returns the login view.
	 *
	 * @return the login view name
	 */
	@Override
	public String getView() {
		return ORSView.LOGIN_VIEW;
	}

}
