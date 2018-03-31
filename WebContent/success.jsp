<!-- jsp页面可以引入java类
< % @page import="" %>
引入的类可以是一个，完整的类型，包名+类名，没有后缀
也可以引入整个包，java.util.*
 -->
<%@page import="com.hp.po.User"%>
<%@page import="java.util.*" contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>用户列表</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>

<body>
	<div id="wrap">
		<!-- 13------28 -->
		<%@include file="head.jsp"%>
		<div id="content">
			<p id="whereami"></p>
			<h1>用户列表</h1>
			<form action="<%=request.getContextPath()%>/userQueryServlet"
				method="get">
				<table>
					<tr>
						<td><input type="text" name="searchName" placeholder="输入用户名" /></td>
						<td><input type="submit" value="查询" /></td>
					</tr>
				</table>
			</form>
			<!-- 开发人员编辑 -->
			<table class="table">
				<tr class="table_header">
					<td>编号</td>
					<td>用户名</td>
					<td>密码</td>
					<td>权限</td>
				</tr>
				<!-- 在jsp中写java代码用<%%>
					遵循java的语法规范
				 -->
				<%
					List userList = (List) request.getAttribute("userList");
				%>
				<!-- html start -->

				<%
					if (null != userList) {
						for (int i = 0; i < userList.size(); i++) {
							User u2 = (User) userList.get(i);
				%>
				<tr class="row<%=i % 2 + 1%>">
					<td><%=u2.getId()%></td>
					<td><%=u2.getName()%></td>
					<td><%=u2.getPwd()%></td>
					<td><%=u2.getRole()%></td>
				</tr>
				<%
					}
					}
				%>
				<!-- html stop -->
			</table>
			<!-- 开发人员编辑 -->
		</div>
	</div>
	<!-- 51------55 -->
	<%-- 静态包含 <%@include file="footer.jsp" %> --%>

	<%-- 动态包含 --%>
	<jsp:include page="footer.jsp" />
	</div>
</body>
</html>





