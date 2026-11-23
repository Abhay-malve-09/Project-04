
package in.co.rays.proj4.controller;

import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

/**
 * UserCtl is a controller class used to handle user-related
 * operations such as adding, updating and validating user details.
 * It also loads role information for the user form.
 *
 * @author Abhay Malve
 * @version 1.0
 */
@WebServlet("/ctl/UserCtl")
public class UserCtl extends BaseCtl<UserBean, UserModel> {

	private static final Logger log = Logger.getLogger(UserCtl.class);

	/**
	 * Loads the available roles from RoleModel and stores
	 * the role list in the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 */
	@Override
	protected void preload(HttpServletRequest request) {

		log.debug("Loading available roles for user form");

		RoleModel rmodel = new RoleModel();

		List<RoleBean> list = rmodel.search(null, 0, 0);

//		request.setAttribute("list", list);
		ServletUtility.setList(list, request);

		log.debug("Available roles loaded successfully");
	}

//	public static final String OP_SIGN_UP = "Save";

	/**
	 * Validates the user details including first name, last name,
	 * login, password, confirm password, role, gender and date of birth.
	 *
	 * @param request the HttpServletRequest object
	 * @return true if validation is successful, otherwise false
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("Validating user details");

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

		if (DataValidator.isNull(request.getParameter("roleId"))) {
			request.setAttribute("roleId", "roleId is required");
			pass = false;
			log.warn("Role ID is required");
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

		log.debug("User validation completed. Result: " + pass);

		return pass;

	}

	/**
	 * Populates the UserBean with user details received
	 * from the HTTP request.
	 *
	 * @param request the HttpServletRequest object
	 * @return the populated UserBean object
	 */
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

		log.debug("Populating UserBean");

		UserBean bean = new UserBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));
		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
		bean.setId(DataUtility.getLong(request.getParameter("id")));

		populateDTO(bean, request);

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
	 * Returns the user view.
	 *
	 * @return the user view name
	 */
	@Override
	public String getView() {

		return ORSView.USER_VIEW;
	}
}
