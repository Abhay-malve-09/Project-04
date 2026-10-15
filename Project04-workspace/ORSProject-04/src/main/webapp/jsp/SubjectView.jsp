<%@page import="in.co.rays.proj4.controller.SubjectCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.util.DataUtility"%>
<%@page import="in.co.rays.proj4.bean.StudentBean"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- For edit list -->
	
	<jsp:useBean id="bean" class="in.co.rays.proj4.bean.SubjectBean"
		scope="request"></jsp:useBean>
		
	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);

	/* For edit list  */
	/*  SubjectBean bean = (SubjectBean) request.getAttribute("bean"); */
	%>

	<%@ include file="Header.jsp"%>

	<div align="center">

		<h1><%=bean != null && bean.getId() > 0 ? "Update Subject" : "Add Subject"%></h1>

		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.SUBJECT_CTL%>" method="post">

			<!-- For edit list -->	
			<input type="hidden" name="id"
				value="<%=DataUtility.getStringData(bean.getId())%>">

			<table>

				<tr>

					<th align="left">Name<font color="red">*</font></th>
					<td><input type="text" name="name"
						placeholder="enter your name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("name", request)%></td>

				</tr>


				<tr>

					<th align="left">Description<font color="red">*</font></th>
		 			<td><input type="text" name="description"
						placeholder="enter course description"
						value="<%=DataUtility.getStringData(bean.getDescription())%>"></td>
					<td style="color: red"><%=ServletUtility.getErrorMessage("description", request)%></td>

				</tr>
				
				<tr>

					<th align="left">Course<font color="red">*</font></th>
					
					<td><select name="courseId" required>
					
					<option value="">-- Select Course --</option>
					<option value="1">Java Full Stack Development</option>
					<option value="2">Python Programming</option>
					<option value="3">Web Development</option>
					<option value="4">Database Management</option>
					<option value="5">Spring Boot Development</option>
					<option value="6">C/C++</option>
					
					</select></td>
					
					
				</tr>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=SubjectCtl.OP_SAVE%>"></td>
				</tr>

			</table>

		</form>
	</div>

	<%@ include file="Footer.jsp"%>


</body>
</html>