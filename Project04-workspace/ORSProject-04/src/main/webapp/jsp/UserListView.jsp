<%@page import="in.co.rays.proj4.controller.BaseCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.proj4.bean.RoleBean"%>
<%@page import="java.util.List"%>
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

	<%
	String succ = ServletUtility.getSuccessMessage(request);
	String error = ServletUtility.getErrorMessage(request);

	List<UserBean> list = ServletUtility.getList(request);
	
/* 	List<UserBean> nextList = (List<UserBean>) request.getAttribute("nextList"); */

 	int pageNo = ServletUtility.getPageNo(request);
	int pageSize = ServletUtility.getPageSize(request);
	
	int index = (pageNo - 1) * pageSize + 1;
	
	Iterator<UserBean> it = list.iterator();
	%>

	<%@ include file="Header.jsp"%>

	<div align="center">

		<h1>User List</h1>

		<h3 style="color: green"><%=succ%></h3>
		<h3 style="color: red"><%=error%></h3>

		<form action="<%=ORSView.USER_LIST_CTL%>" method="post">

			<table border="1px" width="100%">

				<tr style="background: skyblue">
				
					<th><input type="checkbox"
						onclick="document.querySelectorAll('input[name=ids]').forEach(c=>c.checked=this.checked)">Select
						All</th>
						
					<th>S.No</th>
					<th>Id</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Login</th>
					<th>DOB</th>
					<th>Role ID</th>
					<th>Gender</th>
					<th>Edit</th>

				</tr>

				<%
				while (it.hasNext()) {
					UserBean bean = it.next();
				%>

				<tr align="center">

					<td><input type="checkbox" name="ids"
						value="<%=bean.getId()%>"></td>
						
					<td><%=index++%></td>
					<td><%=bean.getId()%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getLogin()%></td>
					<td><%=bean.getDob()%></td>
					<td><%=bean.getRoleId()%></td>
					<td><%=bean.getGender()%></td>
					
 					<td><a href="<%=ORSView.USER_CTL + "?id=" + bean.getId()%>">Edit</a></td>  

				</tr>

				<%
				}
				%>

			</table>

			 	<%@ include file="ListFooter.jsp"%>
 

			<%-- <table width="100%">

				<input type="hidden" name="pageNo" value="<%=pageNo%>">

				<tr>

					<td><input type="submit" name="operation"
						<%=pageNo == 1 ? "disabled" : ""%>
						value="<%=BaseCtl.OP_PREVIOUS%>"></td>
					<td align="center"><input type="submit" name="operation"
						value="<%=BaseCtl.OP_DELETE%>"></td>
					<td align="right"><input type="submit" name="operation"
						<%=nextList.size() == 0 ? "disabled" : ""%>
						value="<%=BaseCtl.OP_NEXT%>"></td>

				</tr>

			</table> --%>

		</form>

	</div>

	<%@ include file="Footer.jsp"%>

</body>
</html>