<%@page import="in.co.rays.proj4.bean.UserBean"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%

UserBean user = (UserBean) session.getAttribute("user");
String role = (String) session.getAttribute("role");
boolean isLogin = user != null;
String welcomeMsg = "Hii, ";

%>

<%

if (isLogin) {
	
%>

<h2><%=welcomeMsg + user.getFirstName() + "(" + role + ")"%></h2>

<a href="<%=ORSView.ROLE_CTL%>">Add Role</a> |
<a href="<%=ORSView.ROLE_LIST_CTL%>">Role List</a> |
<a href="<%=ORSView.USER_CTL%>">Add User</a> |
<a href="<%=ORSView.USER_LIST_CTL%>">User List</a> |
<a href="<%=ORSView.COLLEGE_CTL %>">Add College</a> |
<a href="<%=ORSView.COLLEGE_LIST_CTL %>">College List</a> |
<a href="<%=ORSView.STUDENT_CTL %>">Add Student</a> |
<a href="<%=ORSView.STUDENT_LIST_CTL %>">Student List</a> |
<a href="<%=ORSView.COURSE_CTL %>">Add Course</a> |
<a href="<%=ORSView.COURSE_LIST_CTL %>">Course List</a> |
<a href="<%=ORSView.SUBJECT_CTL %>">Add Subject</a> |
<a href="<%=ORSView.SUBJECT_LIST_CTL %>">Subject List</a> |
<a href="<%=ORSView.FACULTY_CTL %>">Add Faculty</a> |
<a href="<%=ORSView.FACULTY_LIST_CTL %>">Faculty List</a> |
<a href="<%=ORSView.LOGIN_CTL + "?operation=logout"%>">Logout</a> |

<%

} else {

%>

<h2>Hii, Guest</h2>
<a href="<%=ORSView.LOGIN_CTL%>">Login</a> |
<a href="<%=ORSView.USER_REGISTRATION_CTL%>">SignUp</a> |

<%

}

%>

<a href="<%=ORSView.WELCOME_CTL %>">Welcome</a>


<hr>

</body>
</html>