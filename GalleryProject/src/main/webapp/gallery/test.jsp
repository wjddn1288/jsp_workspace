<%@page import="java.sql.Connection"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="gallery.util.FileManager"%>
<%@ page contentType="text/html;charset=utf-8"%>

<%
	//설정된 커넥션풀이 제대로 동작할수 있는지 체크해본다
	
	//검색능력
	InitialContext context= new InitialContext();

	//외부자원을 이름으로 검색하기
	DataSource ds= (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	
	//얻어온 커넥션풀로부터 Connection 하나를 대여하자!!
	Connection con= ds.getConnection();
	out.print(con);
	con.close();
%>