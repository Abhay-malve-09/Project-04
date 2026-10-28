
<!DOCTYPE html>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.controller.LoginCtl"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

<jsp:useBean id="bean" class="in.co.rays.proj4.bean.UserBean"
		scope="request"></jsp:useBean>

<%

String succ = ServletUtility.getSuccessMessage(request);
String error = ServletUtility.getErrorMessage(request);

%>
	<%@ include file="Header.jsp"%>

	<div align="center">
	
		<h1><%=ms.get("login.title")%></h1>
		
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.LOGIN_CTL%>" method="post">

			<table>

				<tr>

					<th align="left"><%=ms.get("login.email")%><font color="red">*</font></th>

					<td><input type="text" name="login"
						placeholder="enter your login" value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("login", request)%></td>

				</tr>

				<tr>

					<th align="left"><%=ms.get("login.password")%><font color="red">*</font></th>

					<td><input type="password" name="password"
						placeholder="enter your password" value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("password", request)%></td>

				</tr>


				<tr>

					<th></th>
					<td><input type="submit" name="operation"
						value="<%=LoginCtl.OP_SIGN_IN%>"></td>

				</tr>

			</table>
			
			<p>
			<a href="<%=ORSView.FORGET_PASSWORD_CTL %>">ForgotPassword</a>
			</p>

		</form>

	</div>

	<%@ include file="Footer.jsp"%>

</body>
</html>