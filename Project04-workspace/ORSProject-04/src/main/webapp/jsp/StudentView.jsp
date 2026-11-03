<%@page import="in.co.rays.proj4.util.HTMLUtility"%>
<%@page import="in.co.rays.proj4.bean.StudentBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add-Student</title>
</head>
<body>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.StudentBean"
		scope="request"></jsp:useBean>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	
	/* List<StudentBean> list = (List<StudentBean>) request.getAttribute("list"); */
	List<StudentBean> list = ServletUtility.getList(request);
	%>

	<%@ include file="Header.jsp"%>

	<div align="center">

		<h1><%=bean != null && bean.getId() > 0 ? "Update Student" : "Add Student"%></h1>

		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.STUDENT_CTL%>" method="post">

			<input type="hidden" name="id"
				value="<%=DataUtility.getStringData(bean.getId())%>">

			<table>

				<tr>

					<th>FirstName<font color="red">*</font></th>
					<td><input type="text" name="firstName"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"
						placeholder="enter your firstName"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("firstName", request)%></td>
				</tr>

				<tr>

					<th>LastName<font color="red">*</font></th>
					<td><input type="text" name="lastName"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"
						placeholder="enter your lastName"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("lastName", request)%></td>

				</tr>

				<tr>
					<th>Email<font color="red">*</font></th>
					<td><input type="email" name="email"
						value="<%=DataUtility.getStringData(bean.getEmail())%>"
						placeholder="enter your email"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("email", request)%></td>
				</tr>

				<tr>
					<th>Mobile No.<font color="red">*</font></th>
					<td><input type="text" name="mobileNo"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>"
						placeholder="enter your mobileNo"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></td>
				</tr>

				<tr>

					<th align="left">College<font color="red">*</font></th>

					<td><%=HTMLUtility.getList("collegeId", DataUtility.getStringData(bean.getCollegeId()), list) %></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("collegeId", request)%></td>

				</tr>

				<tr>

					<th align="left">DOB<font color="red">*</font></th>
					<td><input type="date" name="dob"
						value="<%=DataUtility.getStringData(bean.getDateOfBirth())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("dob", request)%></td>

				</tr>

				<tr>

					<th></th>
					<td><input type="submit" name="operation"
						value="<%=BaseCtl.OP_SAVE%>"></td>

				</tr>

			</table>

		</form>

	</div>

	<%@ include file="Footer.jsp"%>

</body>
</html>