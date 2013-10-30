<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改车辆${innerCar.license }</title>
</head>
<body>
	${errorMsg }
<sf:form method="post" modelAttribute="innerCar">
		<sf:hidden path="id" />
	号牌：<sf:input path="license" readonly="true"/><br>
	RFID：<sf:input path="rfid" /><sf:errors path="rfid"/><br>
	车辆类型：<sf:select path="type.id">
				<c:forEach var="type" items="${carTypeList }">
					<sf:option value="${type.id }" >${type.name }</sf:option>
				</c:forEach>
			</sf:select><br>
	<input type="submit" value="保存">
</sf:form>
</body>
</html>