<%@page import="mvc.model.blood.BloodAdvisor"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//여기서의 request는 새로운 요청에 의해서 생성된 request가 아니라
	//BloodController에게 요청을 시도할때 생성된 예전의 request객체이다
	//즉 죽지 않고 여기까지 살아온 것이다..
	String msg=(String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	혈액형에 대한 판단 결과 :
	<p>
	<%=msg %>
</body>
</html>