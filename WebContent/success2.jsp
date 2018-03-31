<!-- jsp页面可以引入java类
< % @page import="" %>
引入的类可以是一个，完整的类型，包名+类名，没有后缀
也可以引入整个包，java.util.*
 -->
<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>用户列表</title>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/calendar.css" />
<script type="text/javascript" src="jquery/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="js/calendar.js"></script>
</head>

<body>
<script type="text/javascript">
$(function(){
	Calendar.setup({
		inputField : "createDate",
		ifFormat : "%Y-%m-%d %H:%M:%S",
		showsTime : true,
		timeFormat : "24"
	});	
	
	
});

function review(){
	$.ajax({
		url : "";
		type : "POST";
		data : {"action":""},
		dataType : "JSON",
		success : function(data){
			$("input[name='searchName']").val();
			$("select[name='sex']").val();
			$("input[name='address']").val();
			$("input[name='creatDate']").val();
		}
	});
}

</script>


	<div id="wrap">
		<!-- 13------28 -->
		<%@include file="head.jsp"%>
		<div id="content">
			<p id="whereami"></p>
			<h1>用户列表</h1>
			<form action="<%=request.getContextPath()%>/userQueryServlet?action=search"
				method="post">
				<table>
					<tr>
						<td><label for="searchName">用户名</label></td>
						<td><input type="text" name="searchName" /></td>
						<td><label for="sex">性别</label></td>
						<td><select name="sex">
								<option value="">请选择</option>
								<option value="男">男</option>
								<option value="女">女</option>
						</select></td>
						<td></td>
					</tr>
					<tr>
						<td>
						<label for="address">地址</label></td>
						<td> <input type="text"
							name="address"  /></td>
						<td><label for="time">创建时间</label></td>
						<td><input type="text" name="createDate" id="createDate" readonly="readonly"/></td>
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
					<td>性别</td>
					<td>邮编</td>
					<td>地址</td>
					<td>创建时间</td>
					<td>操作</td>
				</tr>
				<!-- 在jsp中写java代码用<%%>
					遵循java的语法规范
				 -->
				<!-- 
				 jstl需要两个jar包
				 在需要使用jstl技术的页面 taglib,引用jstl库 prefix="c"
				 表示jstl标签的缩写
				 c:forEach可以遍历一个集合，items属性指定要遍历的集合，、
				 引用集合的值$ {}，想遍历这个变量，可以指定每一个对象的名字
				 java:for(User u :userList)
				 通过var属性，起一个变量名
				 $ {对象变量名.属性}引用对象成员变量值
				 
				 varStatus属性记录了每次迭代的迭代状态
				 .index属性，记录了迭代的次数，从0开始
				 -->
				 
				 <!-- 
				 	c:choose可以做条件判断
				 	c:when里有属性test，test的值作为判断条件
				 	如果判断条件成立，则执行c:when
				 	如果条件不成立，执行c:otherwise
				  -->
				<c:choose>
					<c:when test="${page.getList() != null}">
						<c:forEach items="${page.getList()}" var="user" varStatus="status">
							<tr class="row${status.index%2+1}">
								<td>${user.id}</td>
								<td>${user.name}</td>
								<td>${user.pwd}</td>
								<td>${user.role}</td>
								<td>${user.sex}</td>
								<td>${user.email}</td>
								<td>${user.address}</td>
								<td>${user.date}</td>
								<!-- 需要解决2个问题，1.请求地址 2.修改谁 -->
								<td><a href = "<%=request.getContextPath()%>/userUpdateServlet?id=${user.id}">修改</a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class = "row1">
							<td colspan="4">没有相关的用户</td>
						</tr>
					</c:otherwise>
				</c:choose>
					
			</table>
			<div>
			
			<c:if test="${requestScope.page.pageNum <= 1 }">
				<a >首页</a>
				<a >上一页</a>
			</c:if>
			<c:if test="${requestScope.page.pageNum > 1 }">
				<a href="<%=request.getContextPath()%>/userQueryServlet?action=search&name=${param.name }&sex=${param.sex }&address=${param.address }&pageNum=1">
				首页</a>
				<a href="<%=request.getContextPath()%>/userQueryServlet?action=search&name=${param.name }&sex=${param.sex }&address=${param.address }&pageNum=${requestScope.page.pageNum-1 }">
				上一页</a>
			</c:if>
			
			<c:if test="${requestScope.page.maxNum <= requestScope.page.pageNum }">
				<a>下一页</a>
				<a>末页</a>
			</c:if>
			<c:if test="${requestScope.page.maxNum > requestScope.page.pageNum }">
				<a href="<%=request.getContextPath()%>/userQueryServlet?action=search&name=${param.name }&sex=${param.sex }&address=${param.address }&pageNum=${requestScope.page.pageNum+1 }">
				下一页</a>
				<a href="<%=request.getContextPath()%>/userQueryServlet?action=search&name=${param.name }&sex=${param.sex }&address=${param.address }&pageNum=${requestScope.page.maxNum }">
				末页</a>
			</c:if>
				<label>第  ${requestScope.page.pageNum } 页</label>
				<label>共  ${requestScope.page.maxNum } 页</label>
				<label>共  ${requestScope.page.sum } 条数据</label>
			
			
			
			
			
			
			
			
			</div>
			
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





