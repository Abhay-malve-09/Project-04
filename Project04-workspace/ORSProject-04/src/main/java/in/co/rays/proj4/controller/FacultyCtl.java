package in.co.rays.proj4.controller;

import java.util.List;

import in.co.rays.proj4.bean.CollegeBean;
import in.co.rays.proj4.bean.FacultyBean;
import in.co.rays.proj4.model.CollegeModel;
import in.co.rays.proj4.model.FacultyModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.ServletUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/FacultyCtl")
public class FacultyCtl extends BaseCtl<FacultyBean, FacultyModel> {

	@Override
	protected void preload(HttpServletRequest request) {

	CollegeModel model = new CollegeModel();
	
	List<CollegeBean> collegeList = model.search(null, 0, 0);
	
	ServletUtility.setList(collegeList, request);
	}
	
	
	@Override
	protected boolean validate(HttpServletRequest request) {

		boolean pass = true;
		
		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "first name is required");
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", "last name is required");
			pass =  false;
		}
		
		if (DataValidator.isNull(request.getParameter("email"))) {
			request.setAttribute("email", "emai is required");
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "mobileNo is required");
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("address"))) {
			request.setAttribute("address", "address is required");
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("collegeId"))) {
			request.setAttribute("collegeId", "collegeId is required");
			pass = false;
		}
		
		return pass;
	}
		
	
	@Override
	protected FacultyBean populateBean(HttpServletRequest request) {

	FacultyBean bean = new FacultyBean();
	
	bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
	bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
	bean.setEmail(DataUtility.getString(request.getParameter("email")));
	bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
	bean.setAddress(DataUtility.getString(request.getParameter("address")));
	bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
	bean.setGender(DataUtility.getString(request.getParameter("gender")));
	bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dob")));
	bean.setId(DataUtility.getLong(request.getParameter("id")));
	
	populateDTO(bean, request);
	
	return bean;
	
	}
	
	
	@Override
	public FacultyModel getModel() {

		return new FacultyModel();
	}

	@Override
	public String getView() {

		return ORSView.FACULTY_VIEW;
	}

}
