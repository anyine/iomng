<%@ page language="java" contentType="text/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

[
<c:forEach var="record" varStatus="status"
	items="${innerCarRecordList }">
	{"type":"${record.type }",
	"date":"${record.date}",
	"car":{"license":"${record.car.license}"},
	"status":"${record.status}"
	}
	<c:if test="${!status.last }">,</c:if>
</c:forEach>
]
