package in.co.rays.proj4.controller;

import in.co.rays.proj4.bean.StudentBean;
import in.co.rays.proj4.model.StudentModel;
import in.co.rays.proj4.util.DataUtility;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;

@WebServlet("/ctl/StudentListCtl")
public class StudentListCtl extends BaseListCtl<StudentBean, StudentModel>{
	
	
	@Override
	protected StudentBean populateBean(HttpServletRequest request) {

		StudentBean bean = new StudentBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		bean.setEmail(DataUtility.getString(request.getParameter("email")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
		bean.setDateOfBirth(DataUtility.getDate(request.getParameter("dob")));
		
		return bean;
	}

	@Override
	public StudentModel getModel() {
		
		return new StudentModel();
	}

	@Override
	public String getView() {
		
		return ORSView.STUDENT_LIST_VIEW;
	}

}
