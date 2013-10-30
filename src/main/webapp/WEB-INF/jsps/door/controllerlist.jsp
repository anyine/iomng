<%@ page language="java" contentType="text/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
[
<c:forEach var="controller" varStatus="status"
	items="${controllerList }">
		{"id":${controller.id},"ip":"${controller.ip}","sn":"${controller.sn}"}<c:if
		test="${!status.last }">,</c:if>
</c:forEach>
]
