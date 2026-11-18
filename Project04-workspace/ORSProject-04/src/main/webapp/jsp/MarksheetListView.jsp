<%@page import="in.co.rays.proj4.controller.MarksheetCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.bean.MarksheetBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MarkSheet List</title>
</head>
<body>

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);

	List<MarksheetBean> list = ServletUtility.getList(request);

	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);

	int index = (pageNo - 1) * pageSize + 1;

	Iterator<MarksheetBean> it = list.iterator();
	%>

	<%@ include file="Header.jsp"%>


	<div align="center">

		<h1>Marksheet List</h1>

		<div class="d-flex gap-2">
			<a href="<%=ORSView.MARKSHEET_REPORT_CTL%>" target="_blank"
				class="btn btn-sm btn-warning fw-semibold"> <i
				class="bi bi-file-earmark-pdf me-1"></i> Print PDF
			</a> <a href="<%=ORSView.MARKSHEET_REPORT_CTL%>?type=doc" target="_blank"
				class="btn btn-sm btn-info fw-semibold"> <i
				class="bi bi-file-earmark-word me-1"></i> Print DOC 
		</div>

		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>


		<form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="post">

			<table>
				<tr>
					<th>Marksheet Name</th>
					<td><input type="text" name="name" value=""
						placeholder="search by name"></td>
					<td><input type="submit" name="operation"
						value="<%=MarksheetCtl.OP_SEARCH%>"></td>
				</tr>
			</table>

			<table border="1" width="100%">

				<tr style="background-color: #6FA7A0;">

					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>
					<th>S No.</th>
					<th>Id</th>
					<th>Roll No</th>
					<th>Student Id</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Maths</th>
					<th>Edit</th>
				</tr>

				<%
				while (it.hasNext()) {

					MarksheetBean bean = it.next();
				%>

				<tr align="center" style="background-color: rgb(192, 192, 192);">

					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>

					<td><%=index++%></td>
					<td><%=bean.getId()%></td>
					<td><%=bean.getRollNo()%></td>
					<td><%=bean.getStudentId()%></td>
					<Td><%=bean.getName()%></td>
					<td><%=bean.getPhysics()%></td>
					<td><%=bean.getChemistry()%></td>
					<td><%=bean.getMaths()%></td>

					<td><a
						href="<%=ORSView.MARKSHEET_CTL + "?id=" + bean.getId()%>">Edit</a></td>

				</tr>

				<%
				}
				%>

				<%@ include file="ListFooter.jsp"%>


			</table>
		</form>
	</div>

	<%@ include file="Footer.jsp"%>

</body>
</html>