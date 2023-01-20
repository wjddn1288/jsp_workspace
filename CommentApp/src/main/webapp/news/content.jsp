<%@page import="news.domain.Comments"%>
<%@page import="news.domain.News"%>
<%@page import="news.repository.NewsDAO"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%! 
	NewsDAO newsDAO = new NewsDAO();
%>
<%
	String news_idx = request.getParameter("news_idx");
	News news=newsDAO.select(Integer.parseInt(news_idx));
	out.print(news);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/inc/header_link.jsp"%>
<script type="text/javascript">

$(function(){
	$($("button")[0]).click(function(){ //등록버튼
		$("#form1").attr({
			"action":"/news/regist", 
			"method":"post"
		});
		$("#form1").submit();
	});	
	
	$($("button")[1]).click(function(){//목록버튼
		$(location).attr("href","/news/list.jsp");
	});
	
	//댓글 등록 버튼 이벤트 연결 
	$("#form2 button").click(function(){
		$("#form2").attr({
			"action":"/comments/regist",
			"method":"post"
		});
		$("#form2").submit();
	});
	
	$($("button")[2]).click(function(){ //삭제버튼
		if(confirm("삭제하시겠어요?")){
			$("#form1").attr({
				"action" : "/news/delete",
				"method" : "get",
			});
			$("#form1").submit();
		}	
	});
	$($("button")[2]).click(function(){ //삭제버튼
		$(location).attr("href","/news/list.jsp");
	});
	
});
</script>
</head>
<body>
	<div class="container mt-3 border">
		<h2 class="text-center">뉴스기사 상세보기</h2>
		<form id="form1">
			<input type="hidden" name="news_idx">
			<div class="form-group">
				<input type="text" class="form-control" value="<%=news.getTitle() %>" name="title">
			</div>
			
			<div class="form-group">
				<input type="text" class="form-control" value="<%=news.getWriter() %>" name="writer">
			</div>
			
			<div class="form-group">
				<textarea class="form-control" name="content"><%=news.getContent() %></textarea>
			</div>
			
			<div class="form-group text-center">
				<button type="button" class="btn btn-secondary">등록</button>
				<button type="button" class="btn btn-secondary">목록</button>
				<button type="button" class="btn btn-secondary">삭제</button>
			</div>
		</form>
		
	<form id="form2">
			<input type="hidden" name="news_idx" value="<%=news.getNews_idx()%>">
			
			<div class="form-group row">
				<div class="col-md-6">
					<input type="text" class="form-control" placeholder="Enter title" name="msg">
				</div>
				<div class="col-md-4">
					<input type="text" class="form-control" placeholder="Enter writer" name="author">
				</div>
				<div class="col-md-2">
					<button type="button" class="btn btn-secondary">댓글등록</button>					
				</div>				
			</div>
		</form>
			
		<table class="table">
			<thead>
				<tr>
		        <th>댓글메시지</th>
		        <th>작성자</th>
		        <th>등록일</th>
		      </tr>
		    </thead>
		    <tbody>
		    	<%for(int i=0;i<news.getCommentsList().size(); i++){ %>
		    	<%Comments comments=news.getCommentsList().get(i); %>
				<tr>
					<td><%=comments.getMsg() %></td>
			        <td><%=comments.getAuthor() %></td>
			        <td><%=comments.getRegdate() %></td>
				</tr>
		      <%} %>
		    </tbody>
		</table>			
	</div>
</body>
</html>


