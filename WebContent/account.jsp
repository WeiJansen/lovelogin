<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>注册用户</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
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
					<p id="whereami">
					</p>
					<h1>
						注册新用户
					</h1>
					<p>
						&nbsp;
					</p>
					
	<!-- 开发人员编辑 -->
	<!-- 表单第一部分：提交服务器的url地址，及提交方式。 -->
<form action="login.jsp" method="post">
<!-- 表单第二部分：文本域范围，指定文本域。 -->
	<table class="form_table">
		<tr>
			<td>用户名:</td>
			<td><input type="text" name="username" id="id1"/></td>
		</tr>
		<tr><td>密码:</td>
			<td><input type="password" name="mima" id="id2"/></td>
		</tr>		
		<tr><td>权限:</td>
			<td>管理员<input value="1" type="radio" name="role" id="id3"/>
				游客<input value="0" type="radio" name="role" id="id4"/>
			</td>
		</tr>
	</table>
	<!-- 表单第三部分：提交按钮submit。 -->
	<p>
		<input type="submit" class="button" value="注册提交"/>
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
