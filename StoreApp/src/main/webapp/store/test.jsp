<%@ page contentType="text/html;charset=UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");

	String name=request.getParameter("name");
%>
<%="hi"+name%>