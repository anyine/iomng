<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆人员出入管理平台系统</title>
<link rel="stylesheet" type="text/css" href="public/login.css" />
</head>
<body>
<table style="width: 100%; margin-top:10%;" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="20%"></td>
			<td>
			<div class="msg" align="center">${msg }</div>
			<div id="container">
			  <sf:form method="post" modelAttribute="loginCommand" id="myForm">
				<div id="loginForm">
					<div id="namepwd">
						<div id="userName">
							<font>用户名</font>
							<sf:input path="username" id="name"/><sf:errors path="username" cssClass="msg"/>
						</div>
						<div id="password">
							<font>密<span class="bank13"></span>码</font>
							<sf:password path="password" id="pwd"/><sf:errors path="password" cssClass="msg"/>
						</div>
					</div>
					<div id="checkboxlgBtn">
						<div id="remerber">
						<sf:checkbox path="rememberMe" id="rememberPwd"/>
							记住我的密码<font>(?)</font>
						</div>
						<input type="submit" value="" id="loginBtn" >
					</div>				
				</div>
			</sf:form>
			</div>
			</td>
		</tr>

		<tr>
			<td colspan="3"></td>
		</tr>
</table>

</body>
</html>