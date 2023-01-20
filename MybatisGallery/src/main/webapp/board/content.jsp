<%@page import="board.domain.Board"%>
<%@page import="board.repository.BoardDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%!
	BoardDAO boardDAO= new BoardDAO();
%>
<%
	String board_idx=request.getParameter("board_idx");
	Board board=boardDAO.select(Integer.parseInt(board_idx));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글등록</title>
 <%@ include file="/inc/header_link.jsp"%>
  <script type="text/javascript">
  
  function edit(){
	  $("#form1").attr({
		  "action": "/board/update",
		  "method":"post",
		  //application/x-www-form=urlencoded 디폴트
		  "enctype":"multipart/form-data"
	  });
	  $("#form1").submit();
  }
  
  function del(){
	$(#form1).attr({
		"action" : "/board/delete",
		"method" : "post"
		"enctype" : "multipart/form-data"
	});
	$("#form1").submit();
  }
  
  //미리보기 구현(파일정보를 가진 obj 받기)
  function preview(obj){
	//넘겨받은 파일객체를 스트림으로 읽어, src에 대입 
	//실행 순서 ACB 
	let reader=new FileReader();
	//console.log("A");
	reader.onload=function(e){ //파일을 다 읽으면
		//console.log("B");
		console.log(e.target.result);
		$("#pic").attr("src",e.target.result);
	};
	reader.readAsDataURL(obj);
	//console.log("C");
  }
  
  $(function(){
  	    ClassicEditor
  	    .create( document.querySelector( "#content" ) )
  	    .catch( error => {
         console.error( error );
        } );

  //버튼에 이벤트 연결
  	$($("button")[0]).click(function(){
  		edit();
  	});
  
	$($("button")[1]).click(function(){
		del();
  	});
  
  	$($("button")[2]).click(function(){
  		location.href="/board/list.jsp";
  	});
  	
  	$("#file").on("change",function(){
  		console.log(this.files[0]);	
  		preview(this.files[0]);
  	});
  	
 });
  </script>
</head>
<body>
    <div class="container mt-2">
    	<form id="form1">
    	<input type="hidden" name="board_idx" value="<%=board.getBoard_idx()%>">
    	<input type="hidden" name="filename" value="<%=board.getFilename()%>">
    	
        <div class="row text-center">
            <h3>글내용보기</h3>
        </div>
        <div class="row mt-2">
            <input type="text" class="form-control" value="<%=board.getTitle() %>"
                name="title">
        </div>
        <div class="row mt-2">
            <input type="text" class="form-control" value="<%=board.getWriter() %>"
                name="writer">
        </div>
        <div class="row mt-2">
            <textarea style="width:100%" id="content" name="content"><%=board.getContent() %></textarea>
        </div>
        
        <div class="row mt-2">
            <img src="/data/<%=board.getFilename()%>" width="150"px id="pic">
        </div>
        
        <div class="row mt-2">
            <input type="file" class="form-control" name="file" id="file">
        </div>
        <div class="row my-2">
            <div class="col text-center">
                <button type="button" class="btn btn-warning">수정</button>
                <button type="button" class="btn btn-warning">삭제</button>
                <button type="button" class="btn btn-warning">목록</button> 
            </div>
        </div>
        </form>
    </div>
</body>
</html>