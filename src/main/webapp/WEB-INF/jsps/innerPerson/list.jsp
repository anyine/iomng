<%@ page language="java" contentType="text/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
[
<c:forEach var="person" varStatus="status" items="${innerPersonList }">
		{"id":${person.id},
		"organization":{"id":${person.organization.id},"name":"${person.organization.name}"},
		"name":"${person.name }",
		"card":{"number":"${person.card.number}"},
		"certificate":"${person.certificate }"
		}<c:if test="${!status.last }">,</c:if>
</c:forEach>
]
