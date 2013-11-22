<%@ page language="java" contentType="text/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	[	<c:forEach var="leave" varStatus="status" items="${leaveList }">
				{"id":${leave.id}
				,"startTime":"${leave.startTime}"
				,"endTime":"${leave.endTime}"
				,"person":{"id":${leave.person.id},"name":"${leave.person.name}"}
				,"reason":"${leave.reason}"
				,"agree":${leave.agree}
				}<c:if test="${!status.last }">,</c:if>
		</c:forEach>
	]