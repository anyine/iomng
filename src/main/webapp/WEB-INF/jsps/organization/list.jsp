<%@ page language="java" contentType="text/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
[
<c:forEach var="organization" varStatus="status" items="${organizationList }">
		{"id":${organization.id},
		"text":"${organization.name }",
		"leaf":${empty organization.children}}<c:if test="${!status.last }">,</c:if>
</c:forEach>
]
