<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>动态包含,dynamic include,动态包含是运行时包含,凡是涉及到的jsp时都会单独生成servlet</title>
</head>
<body>
	<%
	
	request.getRequestDispatcher("/public/head.jsp").include(request, response);
	%>
	<%
	response.getWriter().write("this is new body!!");
	%>
	<%
	request.getRequestDispatcher("/public/footer.jsp").include(request, response);
	%>
</body>
</html>