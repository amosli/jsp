<%@page import="java.util.*"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.amos.model.*"%>
<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>el表达式学习</title>
</head>
<body>
	第一种,简单取值:
	<%
	String data = "hi,amos!";
	request.setAttribute("data", data);
%>
	${data}
	<br> 第二种,从bean中取值:
	<%
		Person p = new Person();
		p.setName("amosli");
		session.setAttribute("person", p);
	%>
	${person.name}
	<br>
	<%=((Person) session.getAttribute("person")).getName()%><br>
	第三种:从复杂bean中取值:
	<%
		Person person1 = new Person();
		Address a = new Address();
		a.setCity("上海");
		person1.setAddress(a);
		application.setAttribute("p1", person1);
	%>
	${p1.address.city}
	<br> 第四种:从集合中取值:
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

	<%
		Map map = new HashMap();
		map.put("aa", new Person("aaaa"));
		map.put("cc", new Person("cccc"));
		map.put("dd", new Person("dddd"));
		map.put("ee", new Person("eeee"));
		map.put("11", new Person("111"));

		request.setAttribute("map", map);
	%>
	${map.dd.name }<br> 
	<%-- ${map.11.name } 一般情况下用点号进行取值,如果点号取不出来值,那么可以用[]来代替. --%>
	${map['11'].name }<br>
	
	获取当前应用的名称:
	${pageContext.request.contextPath }
	
</body>
</html>