
package in.co.rays.proj4.controller;

import java.io.IOException;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * UserRegistrationCtl is a controller class used to handle
 * new user registration and validate user details.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/UserRegistrationCtl")
public class UserRegistrationCtl extends BaseCtl<UserBean, UserModel> {

	private static final Logger log = Logger.getLogger(UserRegistrationCtl.class);

	public static final String OP_SIGN_UP = "SignUp";

	/**
	 * Validates the user registration details such as first name,
	 * last name, login, password, confirm password, gender and DOB.
	 *
	 * @param request the HttpServletRequest object
	 * @return true if validation is successful, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("Validating user registration details");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "firstName is required");
			pass = false;
			log.warn("First name is required");
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "lastName is required");
			pass = false;
			log.warn("Last name is required");
		}

		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", "login is required");
			pass = false;
			log.warn("Login is required");

		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", "login is not in valid formate");
			pass = false;
			log.warn("Invalid login format");
		}

		if (DataValidator.isNull(request.getParameter("password"))) {
			request.setAttribute("password", "password is required");
			pass = false;
			log.warn("Password is required");
		}

		if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", "confirmPassword is required");
			pass = false;
			log.warn("Confirm password is required");
		}

		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", "gender is required");
			pass = false;
			log.warn("Gender is required");
		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", "dob is required");
			pass = false;
			log.warn("Date of birth is required");
		}

		if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", "Confirm  Password  should not be matched.");
			pass = false;
			log.warn("Password and confirm password do not match");
		}

		log.debug("User registration validation completed. Result: " + pass);

		return pass;

	}

	/**
	 * Populates the UserBean with registration details received
	 * from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("Populating UserBean for registration");

		UserBean bean = new UserBean();

		// View se get kiya or bean me set kiya

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setRoleId(2L);

		populateDTO(bean, request);

		log.debug("UserBean populated successfully");

		return bean;

	}

	/**
	 * Handles the user registration request.
	 * Registers the user and displays success or error messages.
	 *
	 * @param request the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 * @throws ServletException if a servlet-related error occurs
	 * @throws IOException if an input or output error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.info("User registration request received");

		// operation get kiya

		String op = DataUtility.getString(request.getParameter("operation"));

		UserBean bean = populateBean(request);

		// model ka object liya
		UserModel model = getModel();

		if (OP_SIGN_UP.equalsIgnoreCase(op)) {

			try {

				log.info("User registration process started");

				model.register(bean);

//				model.add(bean);

				ServletUtility.setSuccessMessage("User is registered, Login now", request);

				log.info("User registered successfully");

			} catch (DuplicateRecordException e) {

				log.warn("User registration failed because login ID already exists");
				ServletUtility.setErrorMessage("Login id already exists", request);

			}
		}

		ServletUtility.forward(getView(), request, response);

		log.debug("User registration request completed");

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
	 * Returns the user registration view.
	 *
	 * @return the user registration view name
	 */
	@Override
	public String getView() {

		return ORSView.USER_REGISTRATION_VIEW;
	}

}
