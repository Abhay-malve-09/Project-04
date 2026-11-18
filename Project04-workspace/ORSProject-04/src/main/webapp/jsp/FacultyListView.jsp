<%@page import="in.co.rays.proj4.controller.FacultyCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.bean.FacultyBean"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Faculty List</title>
</head>
<body>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);

	List<FacultyBean> list = ServletUtility.getList(request);

	/* List<FacultyBean> nextList = (List<FacultyBean>) request.getAttribute("nextList"); */
	int nextListSize = (int) request.getAttribute("nextList");

	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);

	int index = (pageNo - 1) * pageSize + 1;

	Iterator<FacultyBean> it = list.iterator();
	%>

	<%@ include file="Header.jsp"%>

	<div align="center">

		<h1>Faculty List</h1>

		<div class="d-flex gap-2">
			<a href="<%=ORSView.FACULTY_REPORT_CTL%>" target="_blank"
				class="btn btn-sm btn-warning fw-semibold"> <i
				class="bi bi-file-earmark-pdf me-1"></i> Print PDF
			</a> <a href="<%=ORSView.FACULTY_REPORT_CTL%>?type=doc" target="_blank"
				class="btn btn-sm btn-info fw-semibold"> <i
				class="bi bi-file-earmark-word me-1"></i> Print DOC 
		</div>
		
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>


		<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post">

			<table>

				<tr>

					<th>First Name</th>
					<td><input type="text" name="firstName" value=""
						placeholder="search by firstName"></td>
					<th>Last Name</th>
					<td><input type="text" name="lastName" value=""
						placeholder="search by lastName"></td>
					<td><input type="submit" name="operation"
						value="<%=FacultyCtl.OP_SEARCH%>"></td>

				</tr>

			</table>

			<table border="1px" width="100%">

				<tr style="background: skyblue">

					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(C=>c.checked=this.checked)">Select
						All</th>

					<th>S.No</th>
					<th>Id</th>
					<th>College Name</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>MobileNo.</th>
					<th>Address</th>
					<th>CollegeId</th>
					<th>Gender</th>
					<th>DOB</th>
					<th>Edit</th>

				</tr>

				<%
				while (it.hasNext()) {
					FacultyBean bean = it.next();
				%>

				<tr align="center">

					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>

					<td><%=index++%></td>
					<td><%=bean.getId()%></td>
					<td><%=bean.getCollegeName()%>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getEmail()%></td>
					<td><%=bean.getMobileNo()%></td>
					<td><%=bean.getAddress()%></td>
					<td><%=bean.getCollegeId()%></td>
					<td><%=bean.getGender()%></td>
					<td><%=bean.getDateOfBirth()%></td>

					<td><a href="<%=ORSView.FACULTY_CTL + "?id=" + bean.getId()%>">Edit</a></td>

				</tr>

				<%
				}
				%>

			</table>

			<table width="100%">

				<input type="hidden" name="pageNo" value="<%=pageNo%>">

				<tr>

					<td><input type="submit" name="operation"
						<%=pageNo == 1 ? "disabled" : ""%>
						value="<%=BaseCtl.OP_PREVIOUS%>"></td>
					<td align="center"><input type="submit" name="operation"
						value="<%=BaseCtl.OP_DELETE%>"></td>
					<td align="right"><input type="submit" name="operation"
						<%=nextListSize == 0 ? "disabled" : ""%>
						value="<%=BaseCtl.OP_NEXT%>"></td>

				</tr>

			</table>
		</form>

	</div>

	<%@ include file="Footer.jsp"%>

</body>
</html>