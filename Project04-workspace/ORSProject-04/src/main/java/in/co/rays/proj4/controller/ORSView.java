
package in.co.rays.proj4.controller;

/**
 * ORSView interface contains all application view paths,
 * controller URLs, report paths and application context constants.
 * It is used to maintain centralized navigation paths
 * throughout the application.
 *
 * @author Abhay Malve
 * @version 1.0
 */
public interface ORSView {

	/** Application context path. */
	public String APP_CONTEXT = "/ORSProject-04";

	/** Folder containing JSP pages. */
	public String PAGE_FOLDER = "/jsp";

	/** Java documentation view path. */
	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	/** Error view path. */
	public String ERROR_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";

	/** Marksheet view path. */
	public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";

	/** Marksheet list view path. */
	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";

	/** Get marksheet view path. */
	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";

	/** User view path. */
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";

	/** User list view path. */
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";

	/** College view path. */
	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";

	/** College list view path. */
	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";

	/** Student view path. */
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";

	/** Student list view path. */
	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";

	/** Role view path. */
	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";

	/** Role list view path. */
	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";

	/** Course view path. */
	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";

	/** Course list view path. */
	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";

	/** Subject view path. */
	public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";

	/** Subject list view path. */
	public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";

	/** User registration view path. */
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";

	/** Login view path. */
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";

	/** Welcome view path. */
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";

	/** Change password view path. */
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";

	/** My profile view path. */
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";

	/** Forget password view path. */
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	/** Marksheet merit list view path. */
	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMeritListView.jsp";

	/** Product view path. */
	public String PRODUCT_VIEW = PAGE_FOLDER + "/ProductView.jsp";

	/** Product list view path. */
	public String PRODUCT_LIST_VIEW = PAGE_FOLDER + "/ProductListView.jsp";

	/** Error controller URL. */
	public String ERROR_CTL = "/ctl/MarksheetCtl";

	/** Marksheet controller URL. */
	public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl";

	/** Marksheet list controller URL. */
	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl";

	/** User controller URL. */
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";

	/** User list controller URL. */
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";

	/** College controller URL. */
	public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";

	/** College list controller URL. */
	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";

	/** Student controller URL. */
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";

	/** Student list controller URL. */
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";

	/** Role controller URL. */
	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";

	/** Role list controller URL. */
	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";

	/** Course controller URL. */
	public String COURSE_CTL = APP_CONTEXT + "/ctl/CourseCtl";

	/** Course list controller URL. */
	public String COURSE_LIST_CTL = APP_CONTEXT + "/ctl/CourseListCtl";

	/** Subject controller URL. */
	public String SUBJECT_CTL = APP_CONTEXT + "/ctl/SubjectCtl";

	/** Subject list controller URL. */
	public String SUBJECT_LIST_CTL = APP_CONTEXT + "/ctl/SubjectListCtl";

	/** User registration controller URL. */
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";

	/** Login controller URL. */
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";

	/** Welcome controller URL. */
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";

	/** Logout controller URL. */
	public String LOGOUT_CTL = APP_CONTEXT + "/LoginCtl";

	/** Get marksheet controller URL. */
	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";

	/** Change password controller URL. */
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";

	/** My profile controller URL. */
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";

	/** Forget password controller URL. */
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";

	/** Marksheet merit list controller URL. */
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";

	/** Upload photo controller URL. */
	public String UPLOAD_PHOTO_CTL = APP_CONTEXT + "/ctl/uploadphoto";

	/** Faculty view path. */
	public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";

	/** Faculty list view path. */
	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";

	/** Faculty controller URL. */
	public String FACULTY_CTL = APP_CONTEXT + "/ctl/FacultyCtl";

	/** Faculty list controller URL. */
	public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/FacultyListCtl";

	/** Student report controller URL. */
	public String STUDENT_REPORT_CTL = APP_CONTEXT + "/ctl/StudentReportCtl";

	/** College report controller URL. */
	public String COLLEGE_REPORT_CTL = APP_CONTEXT + "/ctl/CollegeReportCtl";

	/** User report controller URL. */
	public String USER_REPORT_CTL = APP_CONTEXT + "/ctl/UserReportCtl";

	/** Role report controller URL. */
	public String ROLE_REPORT_CTL = APP_CONTEXT + "/ctl/RoleReportCtl";

	/** Course report controller URL. */
	public String COURSE_REPORT_CTL = APP_CONTEXT + "/ctl/CourseReportCtl";

	/** Subject report controller URL. */
	public String SUBJECT_REPORT_CTL = APP_CONTEXT + "/ctl/SubjectReportCtl";

	/** Marksheet report controller URL. */
	public String MARKSHEET_REPORT_CTL = APP_CONTEXT + "/ctl/MarksheetReportCtl";

	/** Faculty report controller URL. */
	public String FACULTY_REPORT_CTL = APP_CONTEXT + "/ctl/FacultyReportCtl";

	/** Product controller URL. */
	public String PRODUCT_CTL = APP_CONTEXT + "/ctl/ProductCtl";

	/** Product list controller URL. */
	public String PRODUCT_LIST_CTL = APP_CONTEXT + "/ctl/ProductListCtl";
		

	/** Course report view path. */
	public String COURSE_REPORT_VIEW = "/reports/CourseListReport.jrxml";

	/** Student report view path. */
	public String STUDENT_REPORT_VIEW = "/reports/StudentListReport.jrxml";

	/** College report view path. */
	public String COLLEGE_REPORT_VIEW = "/reports/CollegeListReport.jrxml";

	/** User report view path. */
	public String USER_REPORT_VIEW = "/reports/UserListReport.jrxml";

	/** Role report view path. */
	public String ROLE_REPORT_VIEW = "/reports/RoleListReport.jrxml";

	/** Subject report view path. */
	public String SUBJECT_REPORT_VIEW = "/reports/SubjectListReport.jrxml";

	/** Marksheet report view path. */
	public String MARKSHEET_REPORT_VIEW = "/reports/MarksheetListReport.jrxml";

	/** Faculty report view path. */
	public String FACULTY_REPORT_VIEW = "/reports/FacultyListReport.jrxml";

}
