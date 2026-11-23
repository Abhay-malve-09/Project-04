<%@page import="in.co.rays.proj4.controller.CourseCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.bean.CourseBean"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Course List</title>
</head>
<body>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);

	List<CourseBean> list = ServletUtility.getList(request);

	/* List<CourseBean> nextList = (List<RoleBean>) request.getAttribute("nextList"); */
	/* int nextListSize = (int) request.getAttribute("nextList"); */

	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	int index = (pageNo - 1) * pageSize + 1;
	Iterator<CourseBean> it = list.iterator();
	%>
	<%@ include file="Header.jsp"%>

	<div align="center">

		<h1>Course List</h1>

		<div class="d-flex gap-2">

    <a href="<%=ORSView.COURSE_REPORT_CTL%>" target="_blank"
        class="btn btn-sm btn-warning fw-semibold">
        <i class="bi bi-file-earmark-pdf me-1"></i> Print PDF
    </a>

    <a href="<%=ORSView.COURSE_REPORT_CTL%>?type=doc" target="_blank"
        class="btn btn-sm btn-info fw-semibold">
        <i class="bi bi-file-earmark-word me-1"></i> Print DOC
    </a>

</div>

		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.COURSE_LIST_CTL%>" method="post">

			<table>

				<tr>
					<th>Course Name</th>
					<td><input type="text" name="name" value=""
						placeholder="search by courseName"></td>
					<td><input type="submit" name="operation"
						value="<%=CourseCtl.OP_SEARCH%>"></td>

				</tr>

			</table>

			<table border="1px" width="100%">

				<tr style="background-color: skyblue">

					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>

					<th>S No.</th>
					<th>Id</th>
					<th>Course Name</th>
					<th>Course Description</th>
					<th>Course Duration</th>
					<th>Edit</th>

				</tr>


				<%
				while (it.hasNext()) {
					CourseBean bean = it.next();
				%>

				<tr align="center">

					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>

					<td><%=index++%></td>
					<td><%=bean.getId()%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getDescription()%></td>
					<td><%=bean.getDuration()%></td>

					<td><a href="<%=ORSView.COURSE_CTL + "?id=" + bean.getId()%>">Edit</a></td>
				</tr>

				<%
				}
				%>

				<!-- for Next previous delete Operation -->

				<%@ include file="ListFooter.jsp"%>

			</table>

		</form>

	</div>

	<%@ include file="Footer.jsp"%>

</body>
</html>