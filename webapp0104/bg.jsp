<%@ page contentType="text/html;charset=UTF-8"%>
<%
    //색상 배열 선언
    String [] bgArray={"red","orange","yellow","green","blue","navy","purple"};

    //클라이언트의 파라미터 넘겨받기
    //웹상으로 전송된 모든 파라미터는 문자열이다!!
    //심지어 숫자형으로 남겨도 문자로 취급..
    String bg=request.getParameter("bg");
    out.print("넘겨온  bg값은"+bg);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script>
        //서버에 bg.jsp 라는 JSP에 파라미터 전송 
        //get 방식으로 보내도 된다.. 보안상 중요하지 않고 양이 얼마 안되기 때문에!
        //마치 편지봉투에 간단한 메시지 적는것돠 같다..(봉투는 노출되므로..)
        //header를 타고 전송한다고 표현함.
        function setBg(){
            //선택한 셀렉트박스 의 값
            let sel = document.querySelector("select");
            location.href="/bg.jsp?bg="+sel.value;
        }
    </script>
</head>
<body bgcolor="<%=bg%>"> 
    //넘겨 받은 파라미터 값
    <select name="" id="">
        <%for(int i=0; i<bgArray.length; i++){%>
        <option value="<%=bgArray[i]%>"><%=bgArray[i]%></option>
        <%}%>
    </select>
    <button onclick="setBg()">배경변경</button>

</body>
</html>