<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function send(){
	//서버에 폼 전송하기!!
	//분명 한페이지 이지만 나눠져 있다.창피하게 나한테 보낸다고 하지 말자
	form1.action="/blood.do"; //form앞에 winodw.document가 생략되어있다!
	form1.method="post";
	form1.submit();
}
</script>
</head>
<body>
	<form name="form1">
		<select name="blood">
				<option value="A">A형</option>		
				<option value="B">B형</option>		
				<option value="O">O형</option>		
				<option value="AB">AB형</option>		
		</select>
	</form>
	<p>
	<button type="button" onClick="send()">분석요청</button>
	
</body>
</html>