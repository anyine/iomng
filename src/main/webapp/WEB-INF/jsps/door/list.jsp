<%@ page language="java" contentType="text/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
[
<c:forEach var="door" varStatus="status" items="${doorList }">
		{"id":${door.id},"organization":{"id":${door.organization.id},"name":"${door.organization.name}" },
		"name":"${door.name }"}<c:if test="${!status.last }">,</c:if>
</c:forEach>
]
