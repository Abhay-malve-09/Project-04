<!DOCTYPE html>
<%@page import="in.co.rays.proj4.controller.ForgetPasswordCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<html>
<head>
<meta charset="UTF-8">
<title>Forget Password</title>
</head>
<body>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
		String error = ServletUtility.getErrorMessage(request);
	%>

	<%@ include file="Header.jsp"%>

	<div align="center">

		<h1>Forget Your Password</h1>

		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>


		<form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">

			<table>

				<tr>
					<th align="left">Login<font color="red">*</font></th>
					<td><input type="text" name="login" value=""
						placeholder="enter your email"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("login", request)%></td>

				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value=<%=ForgetPasswordCtl.OP_SAVE%>></td>

				</tr>

			</table>

		</form>

	</div>

	<%@ include file="Footer.jsp"%>

</body>
</html>