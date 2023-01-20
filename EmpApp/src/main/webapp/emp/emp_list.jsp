<%@page import="com.google.gson.Gson"%>
<%@page import="empapp.domain.Emp"%>
<%@page import="java.util.List"%>
<%@page import="empapp.repository.EmpDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	EmpDAO empDAO = new EmpDAO();
%>
<%
	String deptno = request.getParameter("deptno");
	List<Emp> empList=empDAO.selectByFkey(Integer.parseInt(deptno));  //반환형이 인트라서
	Gson gson = new Gson();
	String jsonList = gson.toJson(empList);
	out.print(jsonList);
%>