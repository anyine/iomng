<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<titl>添加门</title>
</head>
<body>
<c:if test="errorMsg!=null">
	${errorMsg }
</c:if>
<sf:form method="post" modelAttribute="door">
	车辆类型：<sf:input path="name"/>
	<input type="submit" value="保存">
</sf:form>
</body>
</html>