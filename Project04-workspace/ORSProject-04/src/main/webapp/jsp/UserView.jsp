<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add-User</title>
</head>
<body>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
		scope="request"></jsp:useBean>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	/* UserBean bean = (UserBean) request.getAttribute("bean"); */
	%>

	<%@ include file="Header.jsp"%>


	<div align="center">

		<h1><%=bean != null && bean.getId() > 0 ? "Update User" : "Add User"%></h1>

		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.USER_CTL%>" method="post">

			<input type="hidden" name="id"
				value="<%=DataUtility.getStringData(bean.getId())%>">

			<table>

				<tr>

					<th align="left">FirstName<font color="red">*</font></th>
					<td><input type="text" name="firstName"
						placeholder="enter your firstName"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("firstName", request)%></td>

				</tr>

				<tr>

					<th align="left">LastName<font color="red">*</font></th>
					<td><input type="text" name="lastName"
						placeholder="enter your lastName"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("lastName", request)%></td>


				</tr>

				<tr>

					<th align="left">Login<font color="red">*</font></th>
					<td><input type="text" name="login"
						placeholder="enter an email"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("login", request)%></td>

				</tr>

				<tr>

					<th align="left">Password<font color="red">*</font></th>
					<td><input type="password" name="password"
						placeholder="enter an password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("password", request)%></td>

				</tr>

				<tr>

					<th align="left">ConfirmPassword<font color="red">*</font></th>
					<td><input type="password" name="confirmPassword"
						placeholder="re-enter your password"
						value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></td>

				</tr>


				<tr>

					<th align="left">Role<font color="red">*</font></th>

					<td><select name='roleId'>
							<option selected
								value="<%=DataUtility.getStringData(bean.getRoleId())%>">---------------select--------------------</option>
							<option value='1'>Admin</option>
							<option value='2'>Student</option>
							<option value='3'>Faculty</option>
							<option value='4'>College</option>
							<option value='5'>KIOSK</option>

					</select></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("roleId", request)%></td>
				<tr>

					<th align="left">Gender<font color="red">*</font></th>

					<td><select name='gender'>
							<option selected value=''>---------------Select-----------------</option>
							<option value='female'>female</option>
							<option value='male'>male</option>

					</select></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("gender", request)%></td>

				</tr>

				<tr>

					<th align="left">DOB<font color="red">*</font></th>
					<td><input type="date" name="dob"
						value="<%=DataUtility.getStringData(bean.getDob())%>"></td>
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