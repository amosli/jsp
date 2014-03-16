<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>静态包含,只会包含页头,页脚,只包含一个servlet</title>
</head>
<body>
	<%@include file="/public/head.jsp" %>
	this is body!
	<%@include file="/public/footer.jsp" %>

</body>
</html>