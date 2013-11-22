<%@ page language="java" contentType="text/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
[
<c:forEach var="person" varStatus="status" items="${innerPersonList }">
		{"id":${person.id},
		"organization":{"id":${person.organization.id},"name":"${person.organization.name}"},
		"name":"${person.name }",
		"phone":"${person.phone }",
		"notify":{"id":"${person.notify.id}","name":"${person.notify.name}"},
		"card":{"id":"${person.card.id}","number":"${person.card.number}"},
		"certificate":"${person.certificate }",
		"userIds":[<c:forEach var="user" varStatus="s" items="${person.users }">${user.id}<c:if test="${!s.last }">,</c:if></c:forEach>]
		}<c:if test="${!status.last }">,</c:if>
</c:forEach>
]
