<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆类型列表</title>
</head>
<body>
	<a href="add">添加车辆类型</a><br>
	<hr>
	<table>
		<thead>
			<tr>
				<td width="100">类型</td>
				<td width="100">操作</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="carType" items="${carTypeList }">
			<tr>
				<td>${carType.name }</td>
				<td><a href="update/${carType.id }">修改</a> <a href="delete/${carType.id }">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>