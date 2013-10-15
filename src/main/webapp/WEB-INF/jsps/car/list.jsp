<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆列表</title>
</head>
<body>
	<a href="add">添加车辆</a><br>
	<hr>
	<table>
		<thead>
			<tr>
				<td width="100">牌号</td>
				<td width="100">RFID</td>
				<td width="100">类型</td>
				<td width="100">状态</td>
				<td width="150">操作</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="car" items="${carList }">
			<tr>
				<td><a href="${car.id }">${car.license }</a></td>
				<td>${car.rfid }</td>
				<td>${car.type.name }</td>
				<td>${car.status }</td>
				<td><a href="update/${car.id }">修改</a> <a href="delete/${car.id }">删除</a> <a href="changeStatus/${car.id }">改变状态</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>