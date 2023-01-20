<%@page import="com.google.gson.Gson"%>
<%@page import="java.util.List"%>
<%@page import="store.repository.StoreDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	StoreDAO storeDAO= new StoreDAO();
%>
<%
//비동기이던 동기이던 무조건 서버는 응답을 해야한다..
//1) 동기로 들어온 클라이언트: 화면을 다 뒤집어야댐 그래서 html로 응답
//2) 비동기로 들어온 클라이언트 : 화면 전체가 아닌 순수 데이터만 보내면 됨!!
	List storeList = storeDAO.selectAll();

//자바의 자료형을 자동으로 json문자열로 변환해주는 라이브러리!!''
//gson 
Gson gson=new Gson();
String result=gson.toJson(storeList);
out.print(result);
%>