<%@ page contentType="text/html;charset=UTF-8"%>
<%
	//로그아웃이란, 서버측의 현재 클라이언트가 사용중인 세션 객체를 무효화 시키는 것이다.
	//무효화 이후엔 더이상 세션을 사용할 수 없다. 객체 자체를 죽이지는 못한다. 
	//자바에선 객체의 소멸은 가비지컬렉터가 담당하므로..
	session.invalidate();
%>
<script>
	alert("로그아웃 되었습니다.");
	location.href="/gallery/list.jsp";
</script>