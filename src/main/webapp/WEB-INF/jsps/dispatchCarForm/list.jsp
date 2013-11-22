<%@ page language="java" contentType="text/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	[	<c:forEach var="dispatchCarForm" varStatus="status" items="${dispatchCarFormList }">
				{"id":${dispatchCarForm.id },"car":{"license":"${dispatchCarForm.car.license }",
				"user":{"realname":"${dispatchCarForm.car.user.realname}"}}
				,"startTime":"${dispatchCarForm.startTime}"
				,"endTime":"${dispatchCarForm.endTime}"
				,"reason":"${dispatchCarForm.reason}"
				,"agree":"${dispatchCarForm.agree}",
				"user":"${dispatchCarForm.user}"
				}<c:if test="${!status.last }">,</c:if>
		</c:forEach>
	]