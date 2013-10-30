<%@ page language="java" contentType="text/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
[
<c:forEach var="reader" varStatus="status"
	items="${readerList }">
		{"id":${reader.id},"sn":"${reader.controller.sn}","number":"${reader.number}","gateNumber":"${reader.gateNumber}","type":"${reader.type}"}<c:if
		test="${!status.last }">,</c:if>
</c:forEach>
]
