<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jsp:setproperty</title>
</head>
<body>
	<jsp:useBean id="person" class="com.amos.model.Person"></jsp:useBean>
	<br>----------------------------<br>
	<!-- 手工为bean属性赋值 -->
	<jsp:setProperty property="name" name="person" value="hi_amos" />
	<%=person.getName()%>
	<br>

	<br>----------------------------<br>
	<!-- 用请求参数给bean赋值 -->

	<!-- http://localhost:8080/jsp/setproperty.jsp?username=abc -->
	<jsp:setProperty property="name" name="person" param="username" />
	<%=person.getName()%><br>

	<!-- 支持将字符串转换成8种基本常用的数据类型 
	  http://localhost:8080/jsp/setproperty.jsp?username=abc&userage=30
	  -->
	<jsp:setProperty property="age" name="person" param="userage" />
	<%=person.getAge()%>

	<jsp:setProperty property="birthday" name="person"
		value="<%=new Date()%>" />
	<%=person.getBirthday()%>


	<br>----------------------------<br>
	<!-- 用所有的请求参数为Bean赋值 -->
	<jsp:setProperty property="*" name="person" />
	<%=person.getAge()%>
	<%=person.getName() %>
	<br>----------------------------
	<br>
	<jsp:getProperty property="name" name="person"/>
	<jsp:getProperty property="age" name="person"/>
	<jsp:getProperty property="birthday" name="person"/>	
	
</body>
</html>