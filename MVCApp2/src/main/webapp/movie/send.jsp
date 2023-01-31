<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function send(){
	//서버에 폼 전송하기!!
	form1.action="/movie.do"; 
	form1.method="post";
	form1.submit();
}
</script>
</head>
<body>
	<form name="form1">
		<select name="movie">
				<option value="아바타2">아바타2</option>		
				<option value="탑건:매버릭">탑건:매버릭</option>		
				<option value="호빗">호빗</option>		
				<option value="워크래프트">워크래프트</option>		
		</select>
	</form>
	<p>
	<button type="button" onClick="send()">분석요청</button>
	
</body>
</html>