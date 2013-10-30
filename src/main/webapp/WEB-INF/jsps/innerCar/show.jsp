<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>车辆${innerCar.license }</title>
</head>
<body>
	号牌：${innerCar.license }<br>
	RFID：${innerCar.rfid }<br>
	车辆类型：${innerCar.type.name }<br>
	状态：${"in" eq innerCar.status?"未外出":"外出" }<br>
</body>
</html>