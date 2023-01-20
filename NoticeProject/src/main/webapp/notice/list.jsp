<!--아래와 같이 DOCTYPE 붙으면 html5 를 의미하고, 차세대 html로써 
		스마트폰 등의 다양한 디바이스에 호환되도록 지원...	-->
<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="com.edu.noticeapp.repository.NoticeDAO"%>
<%@ page import="com.edu.noticeapp.domain.Notice"%>
<%@ page import="java.util.ArrayList"%>

<%! 
	//선언부는 인스턴스 멤버 영역이므로, 여기에 선언한 변수는, 이 jsp의 인스턴스가 생성될때 초기화됨(1번만)
	NoticeDAO noticeDAO = new NoticeDAO();
%>

<%
	ArrayList<Notice> list=noticeDAO.selectAll();

	int totalRecord=list.size(); //총 레코드 수
	int pageSize=10; //페이지당 보여질 레코드 수
	int totalPage=(int)Math.ceil((float)totalRecord/pageSize); //총 페이지 수

	//총 페이지 수
	int blockSize=10; //블럭당=페이지숫자1~10 보여질 페이지 수
	int currentPage=1; //현재 유저가 보고있는 페이지

	//전송되어 온 currnetPage를 jsp의 currentPage 변수에 대입
	//널이 아닐때만, 즉 최조의 접속시엔...
	if(request.getParameter("currentPage")!=null){
		currentPage=Integer.parseInt(request.getParameter("currentPage"));
	}

	int firstPage=currentPage - (currentPage-1)%blockSize; //블럭당 반복문의 시작값
	int lastPage=firstPage + (blockSize-1); //블럭당 반복문의 끝값
	
	int curPos=(currentPage-1)*pageSize; //페이지당 시작 인덱스(ArrayList 에서)
	int num=totalRecord - curPos; //페이지당 시작 게시물 번호
%>
<%="totalRecord="+totalRecord %><br>
<%="pageSize="+pageSize %><br>
<%="totalPage="+totalPage %><br>
<%="currnetPage="+currentPage %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            border: 1px solid #ddd;
            /*left: 500px;*/
            position: relative;
        }

       th,td {
            text-align: left;
            padding: 16px;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        a {
            text-decoration: none;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            //("css의 선택자").() 메서드 -> jquery 골격
            //$("table").animate({ left: '250px' });
			
			//글등록 버튼을 클릭하면...
			$("button").on("click",function(){
				location.href="/notice/write.html";	
			});

        });
    </script> 
</head>
<body>
    <table>
        <tr>
            <th>No</th>
            <th>제목</th>
            <th>작성자</th>
            <th>등록일</th>
            <th>조회수</th>
        </tr>
        <%for(int i=1;i<=pageSize;i++){%>
            <%if(num<1)break; //num이 1보다 작아지면...%>
			<%Notice notice=list.get(curPos++);%>
                <tr>
                    <td><%=num--%></td>
                    <td>
						   <a href="/notice/content.jsp?notice_idx=<%=notice.getNotice_idx()%>"><%=notice.getTitle()%></a>
					</td>
						<td><%=notice.getWriter()%></td>
						<td><%=notice.getRegdate()%></td>
						<td><%=notice.getHit()%></td>
                </tr>
                <%}%>

                    <tr>
                        <td colspan="5" style="text-align:center">

						<%if(firstPage-1>0){%>
							<a href="/notice/list.jsp?currentPage <%=firstPage-1%>">◀</a>
						<%}else{%>
								<a href="javascript:alert('이전페이지가 없습니다');">◀</a>
						<%}%>
							<%for(int i=firstPage;i<=lastPage;i++){%>
								<%if(i>totalPage)break;//가지고 있는 총 페이지수를 넘어서면 빠져나오게%>
									<!-- a태그의 뜻: 지정한 url로 요청을 시도, 이동 아님!! 화면 전환일뿐-->
										<a href="/notice/list.jsp?currentPage=<%=i%>">[<%=i%>]</a>
								<%}%>
									<%if(lastPage+1<=totalPage){ //다음 페이지가 있을때...%>
										<a href="/notice/list.jsp?currentPage=<%=lastPage+1%>">▶</a>
									<%}else{%>
									<a href="javascript:alert('다음페이지가 없어요');">▶</a>
						<%}%>
                        </td>
                    </tr>
                    <tr colspan="5">
                        <td><button>글등록</button></td>
                    </tr>
    </table>
</body>
</html>