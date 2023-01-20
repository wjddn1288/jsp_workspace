<%@page import="repository.NoticeDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	NoticeDAO noticeDAO = new NoticeDAO();
%>
<%request.setCharacterEncoding("utf-8");%>
<jsp:useBean id="notice" class="domain.Notice"/>
<jsp:setProperty property="*" name="notice"/>
<%
	int result  = noticeDAO.update(notice);
	out.print(result);
%>

