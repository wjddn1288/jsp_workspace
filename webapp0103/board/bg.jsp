<%@ page contentType="text/html;charset=UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <script>
            //사용자가 입력한 배경색을 서버에 전송하되, 헤더를 타고 전송되는 Get방식으로..
            function send(){
                //a href="네이버" 와 동일
                let t=document.getElementById("t");
                location.href="/board/bg.jsp?bg="+t.value;
                // href에 있는 /는 우리 웹사이트의 루트를 의미 
                // ? 이후에는 주소가 아니라 변수라고 말해주는거임!
            }
        </script>
    </head>
    <%
        //클라이언트가 전송한 문자열 
        //서버 입장에서는 클라이언트가 전송한 데이터를 받으려면, 요청과 관련된 객체를 이용해야 한다..
        //jsp는 개발자가 생성하지 않더라도 이미 시스템인 서버가 객체를 메로리에 자동으로 올려주는 내장된
        //객체들이 지원된다... 약 총 11개 정도..
        //그 중 '요청' 정보를 가진 객체인request를 사용하면 클라이언트가 서버에게 요청한 모든 내용을 분석할수 있다.
        String bg = request.getParameter("bg");
    %>

        <body bgcolor="<%=bg%>">
            <input type="text" id="t">
            <button onclick="send()">배경색적용</button>
        </body>

    </html>