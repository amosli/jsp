<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jsp tags</title>
</head>
<body>
	<%-- <jsp:include page="public/head.jsp"></jsp:include> --%>
	<%String name="amosli"; %>
	<jsp:forward page="/HomeServlet">
		<jsp:param value="<%= name %>" name="username"/>
	</jsp:forward>
</body>
</html>