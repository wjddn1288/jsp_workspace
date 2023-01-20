<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="com.edu.noticeapp.repository.NoticeDAO"%>
<%@ page import="com.edu.noticeapp.domain.Notice"%>
<%! //멤버영역
	NoticeDAO noticeDAO=new NoticeDAO(); //인스턴스 올라갈때 한번만 올라가게 하려고!!
%>
<%
//잠시 키보드 체험하고 가여...와 개좋다...잔잔한 타자감이 너무 좋아
	//누가 주석 달았는지 모르겠지?ㅋㅋㅋ
	//근데 진짜 좋긴하다....이야 비싼값 하는 것 같아...이여여아아아아아 계속 쓰고싶다
	//내꺼 너무 시끄럽거든... 정우야 담배는 몸에 안좋으니 쪼금씩만 피렴~
	//끊기에는 힘들 수도 있으니까~~ㅎㅎ 알게찌?
	//내가 누구인지는 비밀이얌~~ 

	//스크립틀릿은 요청이 들어올때마다 실행되는 영역

	//클라이언트로 부터 폼의 파라미터들을 넘겨받아 오라클에 넣기
	//드라이버로드, 접속, 쿼리, 닫기 
	Notice notice=new Notice();

	request.setCharacterEncoding("utf-8");
	notice.setTitle(request.getParameter("title"));
	notice.setWriter(request.getParameter("writer"));
	notice.setContent(request.getParameter("content"));

	int result=noticeDAO.insert(notice);
	out.print("result is"+result);

	//목록 페이지 나오게...
	if(result>0){
	%>
	<script>
		alert("등록완료");
		location.href="/notice/list.jsp";
	</script>
	<%}else{%>
	<script>
		alert("등록실패");
		history.back(); //방문한 페이지 기록
	</script>
	<%}%>