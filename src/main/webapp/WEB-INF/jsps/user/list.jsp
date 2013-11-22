<%@ page language="java" contentType="text/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
[
<c:forEach var="user" varStatus="status" items="${userList }">
		{"id":${user.id},
		"organization":{"id":"${user.organization.id}","name":"${user.organization.name}"},
		"username":"${user.username }",
		"realname":"${user.realname }",
		"email":"${user.email}",
		"phone":"${user.phone }",
		"status":"${user.status }"}<c:if test="${!status.last }">,</c:if>
</c:forEach>
]
