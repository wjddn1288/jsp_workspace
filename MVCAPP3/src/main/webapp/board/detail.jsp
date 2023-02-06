<%@page import="com.mvc3.domain.Board"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
//포워딩에 의해 이 페이지가 호출되기 때문에, 유지된 요청 정보로부터 dto를 꺼내자
	Board board = (Board)request.getAttribute("board");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
 <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<script type="text/javascript">
function del(){
	if(confirm("삭제하시겠습니까?")){
		$("#form1").attr({
			action:"/board/delete.do",
			method:"post"
		});
		$("#form1").submit();
	}
}

function edit(){
	if(confirm("수정하시겠습니까?")){
		$("#form1").attr({
			action:"/board/edit.do",
			method:"post"
		});
		$("#form1").submit();		
	}
}

$(function(){
	$("#bt_del").click(function(){
		del();
	});
	
	$("#bt_edit").click(function(){
		edit();	
	});	
});
</script>
<body>
	<div class="container m-5">
	     <form id="form1">
	     	<input type="hidden" name="board_idx" value="<%=board.getBoard_idx()%>">
	          <div class="form-group">
	          	 <input type="text" class="form-control" value="<%=board.getTitle() %>" name="title">
              </div>
	          <div class="form-group">
	         	  <input type="text" class="form-control" value="<%=board.getWriter() %>" name="writer">
             </div>
	          <div class="form-group">
	          	<textarea class="form-control" placeholder="Enter content" name="content"><%=board.getContent() %></textarea>
             </div>
        </form>
        <div class="form-group">
	        <button type="button" class="btn btn-success" id="bt_edit" >수정</button>
	        <button type="button" class="btn btn-success" id="bt_del" >삭제</button>
	        <button type="button" class="btn btn-success" id="bt_list" >목록</button>
        </div>
	</div>
</body>
</html>