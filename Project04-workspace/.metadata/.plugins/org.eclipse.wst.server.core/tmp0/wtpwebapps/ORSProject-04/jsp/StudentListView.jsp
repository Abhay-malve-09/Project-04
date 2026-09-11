<%@page import="in.co.rays.proj4.bean.StudentBean"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="in.co.rays.proj4.controller.ORSView"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student List</title>
</head>

<body>

<%@ include file="Header.jsp"%>

<%
int pageNo = ServletUtility.getPageNo(request);
int pageSize = ServletUtility.getPageSize(request);
int index = ((pageNo - 1) * pageSize) + 1;

List<StudentBean> list = ServletUtility.getList(request);
Iterator<StudentBean> it = list.iterator();

String _suc = ServletUtility.getSuccessMessage(request);
String _err = ServletUtility.getErrorMessage(request);
%>

<form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post">

	<div align="center">

		<h1>Student List</h1>

		<h3 style="color: green"><%=_suc != null ? _suc : ""%></h3>
		<h3 style="color: red"><%=_err != null ? _err : ""%></h3>

		<input type="hidden" name="pageNo" value="<%=pageNo%>">
		<input type="hidden" name="pageSize" value="<%=pageSize%>">

		<table>

			<tr>

				<td>
					<input type="text"
						name="firstName"
						value=""
						placeholder="search by first name">
				</td>

				<td>
					<input type="text"
						name="lastName"
						value=""
						placeholder="search by last name">
				</td>

				<td>
					<input type="text"
						name="mobileNo"
						value=""
						placeholder="search by mobile no">
				</td>

				<td>
					<input type="text"
						name="email"
						value=""
						placeholder="search by email">
				</td>

				<td>
					<input type="submit"
						name="operation"
						value="<%=BaseCtl.OP_SEARCH%>">
				</td>

			</tr>

		</table>


		<table border="1px" width="100%">

			<tr style="background-color: skyblue">

				<th>S.No</th>
				<th>FirstName</th>
				<th>LastName</th>
				<th>DOB</th>
				<th>MobileNo</th>
				<th>Email</th>
				<th>CollegeId</th>
				<th>CollegeName</th>

			</tr>


			<%
			while (it.hasNext()) {

				StudentBean bean = it.next();
			%>

			<tr align="center" style="background-color: lightgrey">

				<td>
					<%=index++%>
				</td>

				<td>
					<%=bean.getFirstName()%>
				</td>

				<td>
					<%=bean.getLastName()%>
				</td>

				<td>
					<%=bean.getDob()%>
				</td>

				<td>
					<%=bean.getMobileNo()%>
				</td>

				<td>
					<%=bean.getEmail()%>
				</td>

				<td>
					<%=bean.getCollegeId()%>
				</td>

				<td>
					<%=bean.getCollegeName()%>
				</td>

			</tr>

			<%
			}
			%>

		</table>

	</div>


	<table width="100%">

		<tr>

			<td>
				<input type="submit"
					name="operation"
					<%=pageNo == 1 ? "disabled" : ""%>
					value="<%=BaseCtl.OP_PREVIOUS%>">
			</td>

			<td align="center">
				<input type="submit"
					name="operation"
					value="<%=BaseCtl.OP_DELETE%>">
			</td>

			<td align="right">
				<input type="submit"
					name="operation"
					<%=list.size() < 10 ? "disabled" : ""%>
					value="<%=BaseCtl.OP_NEXT%>">
			</td>

		</tr>

	</table>

</form>

<%@ include file="Footer.jsp"%>

</body>
</html>
