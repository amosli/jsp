<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>pageContext访问其它域</title>
</head>
<body>
	<%
	request.setAttribute("data", "abc");
	String data = pageContext.getAttribute("data",PageContext.REQUEST_SCOPE).toString();
	out.write(data);
	out.write("<br>");
	out.write(pageContext.findAttribute("data").toString());//依次从page request session application四个域中找对应的值
	%>
	
	<%
	pageContext.forward("/out.jsp") ;
	%>
</body>
</html>