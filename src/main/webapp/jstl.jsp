<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false"%>
<%@page import="java.util.*,com.amos.model.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jstl标签学习</title>
</head>
<body>
	<br> 从集合中取值:
	<%
		List list = new ArrayList();
		list.add(new Person("amos"));
		list.add(new Person("li"));
		list.add(new Person("amosli"));
		list.add(new Person("hi"));
		list.add(new Person("hi_amos"));
		application.setAttribute("personlist", list);
	%>
	${personlist[0].name }
	<br>
	
	<c:forEach var="list" items="${ personlist}">
		<c:out value="${list.name }"></c:out><br>
	</c:forEach>

	<%
		Map map = new HashMap();
		map.put("aa", new Person("aaaa"));
		map.put("cc", new Person("cccc"));
		map.put("dd", new Person("dddd"));
		map.put("ee", new Person("eeee"));
		map.put("11", new Person("111"));
		request.setAttribute("maps", map);
	%>
	<c:forEach  var="map" items="${maps }">
	 ${map.key}:${map.value.name }<br>
	</c:forEach>

</body>
</html>