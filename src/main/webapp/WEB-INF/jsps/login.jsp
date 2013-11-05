<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
</head>
<body>
<sf:form method="post" modelAttribute="loginCommand">
	用户名：<sf:input path="username"/><sf:errors path="username"/><br>
	密码:<sf:password path="password"/><sf:errors path="password"/><br>
	记住我<sf:checkbox path="rememberMe"/><br>
	<input type="submit" value="登陆">
</sf:form>

</body>
</html>