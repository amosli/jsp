<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	当前时间:
	<%
		Date date = new Date();
		String time=date.toLocaleString();
		int x = 100;
	%>
	<%= time %>
	<br>
	<% out.print("x:"+x); %>
	
	<%!
	public void run(){
		System.out.println("this is run !");
	};
	static {
	 System.out.println("this is static!");
	}
	private static String abc = "ad" ;
	static {
		System.out.println("abc:"+abc);
	}
	
	%>
	
	<%!
static 
{ 
	System.out.println("loading Servlet!"); 
}
private int globalVar = 0;
public void jspInit()
{
	System.out.println("initializing jsp!");
}
%>
<%!
public void jspDestroy()
{
	System.out.println("destroying jsp!");
}
%>
<%-- <%
out.print("abc");
%> --%>
	<!-- hello -->
	
	
	
</body>
</html>