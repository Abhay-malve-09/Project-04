package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/UserListCtl")
public class UserListCtl extends BaseListCtl<UserBean, UserModel>{

	
	@Override
	protected UserBean populateBean(HttpServletRequest request) {

	UserBean bean = new UserBean();
	
	bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
	bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
	bean.setLogin(DataUtility.getString(request.getParameter("login")));
	bean.setGender(DataUtility.getString(request.getParameter("gender")));
	bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")) );
	bean.setDob(DataUtility.getDate(request.getParameter("dob")));
	
	return bean;
	
	}
	
	@Override
	public UserModel getModel() {

		return new UserModel();
	}

	@Override
	public String getView() {

		return ORSView.USER_LIST_VIEW;
	}

}
