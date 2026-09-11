package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.RoleBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.RoleModel;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/UserCtl")
public class UserCtl extends BaseCtl<UserBean, UserModel> {
	
	@Override
	protected void preload(HttpServletRequest request) {

		RoleModel rmodel = new RoleModel();
		List<RoleBean> roleList = rmodel.list();
		request.setAttribute("roleList", roleList);

	}


	@Override
	protected boolean validate(HttpServletRequest request) {

	    boolean pass = true;

	    if (DataValidator.isNull(request.getParameter("firstName"))) {
	        request.setAttribute("firstName", "first name is require");
	        pass = false;
	    }

	    if (DataValidator.isNull(request.getParameter("lastName"))) {
	        request.setAttribute("lastName", "last name is require");
	        pass = false;
	    }

	    if (DataValidator.isNull(request.getParameter("login"))) {
	        request.setAttribute("login", "login is not in valid formate");
	        pass = false;
	    }

	    if (DataValidator.isNull(request.getParameter("password"))) {
	        request.setAttribute("password", "password is require");
	        pass = false;
	    }
	    
	    if (DataValidator.isNull(request.getParameter("confirmPassword"))) {
	        request.setAttribute("confirmPassword", "confirm password is require");
	        pass = false;
	    }
	    
	    if (DataValidator.isNull(request.getParameter("gender"))) {
	        request.setAttribute("gender", "gender is require");
	        pass = false;
	    }

	    if (DataValidator.isNull(request.getParameter("roleId"))) {
	        request.setAttribute("roleId", "role is require");
	        pass = false;
	    }


	    if (DataValidator.isNull(request.getParameter("dob"))) {
	        request.setAttribute("dob", "date of birth is require");
	        pass = false;
	    }
	    
	    if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword"))) {
			request.setAttribute("confirmPassword", "Confirm  Password  should not be matched.");
			pass = false;
		}

	  
	return pass;
}
	@Override
	protected UserBean populateBean(HttpServletRequest request) {
		
		UserBean bean = new UserBean();
		
		bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setLogin(DataUtility.getString(request.getParameter("login")));
		bean.setPassword(DataUtility.getString(request.getParameter("password")));
		bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setDob(DataUtility.getDate(request.getParameter("dob")));		
		
		populateDTO(bean, request);
		
		return bean;
	}

	@Override
	protected String getView() {

		return ORSView.USER_VIEW;
	}

	@Override
	protected UserModel getModel() {

		return new UserModel();
	}

}
