<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add-Marksheet</title>
</head>
<body>

	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.MarksheetBean"
		scope="request"></jsp:useBean>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);
	%>

	<%@ include file="Header.jsp"%>

	<div align="center">

		<h1><%=bean != null && bean.getId() > 0 ? "Update Marksheet" : "Add Marksheet"%></h1>

		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">

			<input type="hidden" name="id"
				value="<%=DataUtility.getStringData(bean.getId())%>">


			<table>

				<tr>
					<th align="left">RollNo<font color="red">*</font></th>
					<td><input type="text" name="rollNo"
						value="<%=DataUtility.getStringData(bean.getRollNo())%>"
						placeholder="enter your rollno"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("rollNo", request)%></td>
				</tr>

				<tr>
					<th align="left">Student ID</th>
					<td><input type="number" name="studentId"
						value="<%=DataUtility.getStringData(bean.getStudentId())%>"
						placeholder="Enter existing Student ID"></td>
				</tr>

				<%-- <tr>
					<th align="left">Name<font color="red">*</font></th>
					<td><input type="text" name="name"
						value="<%=DataUtility.getStringData(bean.getName())%>"
						placeholder="enter your name"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("name", request)%></td>
				</tr> --%>

				<tr>
					<th align="left">Physics<font color="red">*</font></th>
					<td><input type="text" name="physics"
						value="<%=DataUtility.getStringData(bean.getPhysics())%>"
						placeholder="enter physics marks"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("physics", request)%></td>
				</tr>

				<tr>
					<th align="left">Chemistry<font color="red">*</font></th>
					<td><input type="text" name="chemistry"
						value="<%=DataUtility.getStringData(bean.getChemistry())%>"
						placeholder="enter chemistry marks"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("chemistry", request)%></td>
				</tr>

				<tr>
					<th align="left">Maths<font color="red">*</font></th>
					<td><input type="text" name="maths"
						value="<%=DataUtility.getStringData(bean.getMaths())%>"
						placeholder="enter maths marks"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("maths", request)%></td>
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