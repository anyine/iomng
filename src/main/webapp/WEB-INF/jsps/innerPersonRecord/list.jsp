<%@ page language="java" contentType="text/json; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

[
<c:forEach var="record" varStatus="status"
	items="${innerPersonRecordList }">
	{"type":"${record.type }",
	"date":"${record.date}",
	"person":{"name":"${record.person.name}"},
	"status":"${record.status}"
	}
	<c:if test="${!status.last }">,</c:if>
</c:forEach>
]
