<%@ page language="java" contentType="text/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	[	<c:forEach var="carType" varStatus="status" items="${carTypeList }">
				{"id":${carType.id },"name":"${carType.name }"}<c:if test="${!status.last }">,</c:if>
		</c:forEach>
	]