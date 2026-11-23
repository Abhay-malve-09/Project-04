<%@page import="in.co.rays.proj4.controller.StudentCtl"%>
<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.bean.StudentBean"%>
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

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);

	List<StudentBean> list = ServletUtility.getList(request);

	/* List<StudentBean> nextList = (List<StudentBean>) request.getAttribute("nextList"); */
	int nextListSize = (int) request.getAttribute("nextList");

	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);

	int index = (pageNo - 1) * pageSize + 1;

	Iterator<StudentBean> it = list.iterator();
	%>

	<%@ include file="Header.jsp"%>

	<div align="center">

		<h1>Student List</h1>
		
		<div class="d-flex gap-2">

    <a href="<%=ORSView.STUDENT_REPORT_CTL%>" target="_blank"
        class="btn btn-sm btn-warning fw-semibold">
        <i class="bi bi-file-earmark-pdf me-1"></i> Print PDF
    </a>

    <a href="<%=ORSView.STUDENT_REPORT_CTL%>?type=doc" target="_blank"
        class="btn btn-sm btn-info fw-semibold">
        <i class="bi bi-file-earmark-word me-1"></i> Print DOC
    </a>

</div>
		
		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.STUDENT_LIST_CTL%>" method="post">

			<table>
				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName" value=""
						placeholder="search by firstName"></td>
					<th>Last Name</th>
					<td><input type="text" name="lastName" value=""
						placeholder="search by lastName"></td>
					<td><input type="submit" name="operation"
						value="<%=StudentCtl.OP_SEARCH%>"></td>
				</tr>
			</table>

			<table border="1" width="100%">

				<tr style="background-color: skyblue">

					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>

					<th>S No.</th>
					<th>Id</th>
					<th>College Name</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Mobile Number</th>
					<th>College ID</th>
					<th>DOB</th>
					<th>Edit</th>

				</tr>

				<%
				while (it.hasNext()) {
					StudentBean bean = it.next();
				%>

				<tr align="center">

					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>

					<td><%=index++%></td>
					<td><%=bean.getId()%></td>
					<td><%=bean.getCollegeName()%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getEmail()%></td>
					<td><%=bean.getMobileNo()%></td>
					<td><%=bean.getCollegeId()%></td>
					<td><%=bean.getDateOfBirth()%></td>

					<td><a href="<%=ORSView.STUDENT_CTL + "?id=" + bean.getId()%>">Edit</a></td>
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