<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>修改用户</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

</head>
<body>
	<div id="wrap">
		<div id="top_content">

			<div id="header">
				<div id="rightheader">
					<p>
						2016年5月 <br />
					</p>
				</div>

				<div id="topheader">
					<h1 id="title">
						<a href="#">我爱登陆</a>
					</h1>
				</div>
				<div id="navigation"></div>
			</div>

			<div id="content">
				<p id="whereami"></p>
				<h1>修改用户</h1>
				<p>&nbsp;</p>

				<!-- 开发人员编辑 -->
				<!-- 表单第一部分：提交服务器的url地址，及提交方式。 -->
				<form action="<%=request.getContextPath()%>/userUpdateServlet" method="post">
				<input type= "hidden" name = "id" value = "${user.id}"/>
					<!-- 表单第二部分：文本域范围，指定文本域。 -->
					<table class="form_table">
						<tr>
							<td>用户名:</td>
							<td><input type="text" name="name" id="id1" value="${user.name}" readonly="readonly" /></td>
						</tr>
						<tr>
							<td>密码:</td>
							<td><input type="text" name="pwd" id="id2" value = "${user.pwd}"/></td>
						</tr>
						<tr>
							<td>权限:</td>
							<td>管理员<input value="1" type="radio" name="role" id="id3" <c:if test="${user.role==1}">checked="checked"</c:if> />
								游客<input value="0" type="radio" name="role" id="id4" <c:if test="${user.role==0}">checked="checked"</c:if> />
							</td>
						</tr>
					</table>
					<!-- 表单第三部分：提交按钮submit。 -->
					<p>
						<input type="submit" class="button" value="修改" />
					</p>
				</form>


				<!-- 开发人员编辑 -->

			</div>
		</div>

		<div id="footer">
			<div id="footer_bg">whatisjava@163.com</div>
		</div>
	</div>
</body>
</html>
