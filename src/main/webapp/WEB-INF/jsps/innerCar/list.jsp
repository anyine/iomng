<%@ page language="java" contentType="text/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

[
<c:forEach var="car" varStatus="status" items="${innerCarList }">
	{"id":${car.id },
	"license":"${car.license }",
	"card":{"id":"${car.card.id}","number":"${car.card.number }"},
	"user":{"id":${car.user.id},"realname":"${car.user.realname }"},
	"type":{"id":${car.type.id},"name":"${car.type.name }"},
	"organization":{"id":${car.organization.id},"name":"${car.organization.name}"},
	"status":"${car.status}",
	"level":"${car.level}",
	"notify":{"id":${car.notify.id},"name":"${car.notify.name}"}}
	<c:if test="${!status.last }">,</c:if>
</c:forEach>
]
