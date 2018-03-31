<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>登入系统</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<div id="wrap">
		<div id="top_content">
			<div id="topheader">
				<h1 id="title">
					<a href="#">我爱登陆</a>
				</h1>
			</div>
		<div id="navigation"></div>
		<div id="content">
				<p id="whereami"></p>
				<h1>登入系统</h1>
				<p>&nbsp;</p>
	<!-- 开发人员编辑 -->
	<form action="<%=request.getContextPath()%>/loginServlet" method="post">
	<table>
		<tr><td>用户名：</td>
			<td><input type="text" name="username"/></td></tr>
		<tr><td>密码：</td>
			<td><input type="password" name="password"/></td></tr>
	</table>
	<input type="submit" value="登录" class="button" />
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
