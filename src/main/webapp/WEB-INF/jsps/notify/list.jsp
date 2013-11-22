<%@ page language="java" contentType="text/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	[	<c:forEach var="notify" varStatus="status" items="${notifyList }">
				{"id":${notify.id },"name":"${notify.name }","startTime":"<fmt:formatDate value="${notify.startTime}" pattern="HH:mm:ss"/>","endTime":"<fmt:formatDate value="${notify.endTime}" pattern="HH:mm:ss"/>"}<c:if test="${!status.last }">,</c:if>
		</c:forEach>
	]