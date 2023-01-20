<%@ page contentType="text/html;charset=utf-8"%>
<%@ page import="com.edu.noticeapp.repository.NoticeDAO"%>
<%@ page import="com.edu.noticeapp.domain.Notice"%> <!-- 지시 영역-->
<%! //선언 영역
	NoticeDAO noticeDAO=new NoticeDAO();
%>
<% //스크립틀릿 영역
	//1) list.jsp 에서 파라미터를 제대로 넘겨야 한다!!
	//2) idx를 파라미터로 넘겨받아 변수에 받으면 된다
	//3) 파라미터명은 notice_idx로 하기!!

	//idx를 파라미터로 넘겨받아 와야함... 
	int notice_idx=Integer.parseInt(request.getParameter("notice_idx"));

	String sql="select * from notice where notice_idx="+notice_idx; //이해를 돕기 위해 남겨놓음
	out.print(sql);

	//한건 가져오기!
	Notice notice=noticeDAO.select(notice_idx);

	//조회수 증가
	noticeDAO.updateHit(notice_idx);

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">   <!-- 추가해줌!!-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }
        * {
            box-sizing: border-box;
        }

        input[type=text],
        select,
        textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-top: 6px;
            margin-bottom: 16px;
            resize: vertical;
        }

        input[type=button] {
            background-color: #04AA6D;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=button]:hover {
            background-color: #45a049;
        }

        .container {
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
        }
    </style>
    <script src="https://cdn.ckeditor.com/ckeditor5/35.4.0/classic/ckeditor.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

    <script>
	$(function(){
			//수정요청
			$($("input[type='button']")[0]).click(function(){ //$(누구를).함수(어떻게) 
				//내용이 많을 뿐만 아니라, form을 이용한 전송이므로 POST방식으로 전송하자!!
				$("form").attr("method","POST");
				$("form").attr("method","notice/update.jsp");
				$("form").submit();
			}); 

			//감싸지 않으면 대괄호꺼임 ()이 자체가 객체이기 때문에 한번더 감싸줘야한다
			$($("input[type='button']")[1]).click(function(){ //$(누구를).함수(어떻게) 
				//삭제요청 	//GET : 링크
				//분홍색이라고 착각 ㄴㄴ
				location.href="/notice/delete.jsp?notice_idx=<%=notice.getNotice_idx()%>";
			}); 

			$($("input[type='button']")[2]).click(function(){ //$(누구를).함수(어떻게) 
				//분홍색이라고 착각 ㄴㄴ
			location.href="/notice/list.jsp>";//GET : 링크
			}); 

		});
    </script>

</head>
<body>
    <h3>상세내용</h3>
    <div class="container">
        <!--HTML 방식|| /를 맨 앞에 넣으면 절대경로(server.xml에서 내가 알려준곳으로 찾아가라) /안넣으면 상대경로(현재위치에서 찾아라)로 지정됨-->
         <form method="post" action="/notice/regist.jsp"> 
            <input type="hidden" name="notice_idx" value="<%=notice.getNotice_idx()%>" style="background:yellow">
            <input type="text" name="title" value="<%=notice.getTitle()%>">
            <input type="text" name="writer" value="<%=notice.getWriter()%>">

            <textarea id="content" name="content" placeholder="Write something.." style="height:200px"><%=notice.getContent()%></textarea>

            <!-- submit 은 현재 form태그의 내용을 주소도 모르고 무조건 전송을 방지하기 위할 일반 버튼 -->
            <input type="button" value="수정">
			<input type="button" value="삭제">
			<input type="button" value="목록">
        </form>
    </div>
</body>
</html>