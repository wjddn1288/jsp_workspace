<%@ page contentType="text/html;charset=UTF-8" %>
    <!DOCTYPE html>
    <html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
        <script>
            function send(){
                //서버에 있는 gugu.jsp에게,입력한 단수를 파라미터로 전송하자!!
                //post,get 두 방식 중 보안상 중요하게 없으니 get으로 가겠다 일반주소임
                let dan=document.getElementById("dan");
                location.href="/board/gugu.jsp?dan="+dan.value;
            }
        </script>
    </head>

    <body>
        <input type="text" id="dan">
        <button onclick="send()">서버에 있는 jsp에게 단 요청</button>
        <%
            String param=request.getParameter("dan");
            out.print("넘어온 dan 변수 값은"+param);

            //만약 최초에 페이지에 접속한 경우 param이 전송되지 않으므로,null상태이므로
            //아래의 정수화 시키는 코드는 null이 아닐때만 수행하자
            int dan=0;

            if(param !=null){ //파라미터가 전송된 경우만...
                dan=Integer.parseInt(param); //웹에 있는 데이터는 숫자여도 모두 문자 취급함 
            }
        %>
        <table width="500px" border="1px">
            <tr>
                <td>구구단 <%=dan%>단</td>
            </tr>
                <%for(int i=1; i<10; i++){%>
            <tr>
                <td> <%=dan+"*"+i+"="+(dan*i)%></td>
            </tr>
            <%}%>
        </table>
    </body>

    </html>