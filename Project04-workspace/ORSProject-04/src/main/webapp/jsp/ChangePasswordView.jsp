<%@page import="in.co.rays.proj4.controller.ChangePasswordCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Change Password</title>
</head>
<body>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>

	<%@ include file="Header.jsp"%>

	<div align="center">

		<h1>Change Your Password</h1>

		<h3 style="color: green"><%=succ%></h3>
		<h tyle="color: red"><%=error%></h>

		<form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">

			<table>

				<tr>

					<th align="left">Login<font color="red">*</font></th>
					<td><input type="text" name="login" value=""
						placeholder="enter your email"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("login", request)%></td>

				</tr>

				<tr>
					<th align="left">New Password<font color="red">*</font></th>
					<td><input type="password" name="newPassword" value=""
						placeholder="enter your new password"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("newPassword", request)%></td>

				</tr>

				<tr>
					<th align="left">Old Password<font color="red">*</font></th>
					<td><input type="password" name="oldPassword" value=""
						placeholder="enter your old password"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("oldPassword", request)%></td>

				</tr>

				<tr>

					<th></th>
					<td><input type="submit" name="operation"
						value=<%=ChangePasswordCtl.OP_SAVE%>></td>

				</tr>
			</table>

		</form>

	</div>
	
	<%@ include file="Footer.jsp" %>
	
</body>
</html>