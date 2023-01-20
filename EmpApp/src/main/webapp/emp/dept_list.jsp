<%@page import="com.google.gson.Gson"%>
<%@page import="empapp.domain.Dept"%>
<%@page import="java.util.List"%>
<%@page import="empapp.repository.DeptDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	DeptDAO deptDAO=new DeptDAO();
%>
<%
	List<Dept> deptList=deptDAO.selectAll(); //gson으로  json 바꿈
	Gson gson=new Gson(); //자바객체 <-서로변환-> json
	String jsonList=gson.toJson(deptList);
	out.print(jsonList);
%>
