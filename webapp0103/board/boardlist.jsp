<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.sql.*"%>
<%!
     //선언 영역 멤버 변수,메서드 영역
     Connection con;
     PreparedStatement pstmt;
     ResultSet rs;

    String url="jdbc:oracle:thin:@localhost:1521:XE";
    String user="javase";
    String pass="1234";
%>

<%
    Class.forName("oracle.jdbc.driver.OracleDriver");
    con=DriverManager.getConnection(url,user,pass);
    String sql="select * from board order by board_idx desc";

    pstmt=con.prepareStatement(sql);
    rs=pstmt.executeQuery();
%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">   <!-- 추가해줌!!-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            border: 1px solid #ddd;
        }

        th,
        td {
            text-align: left;
            padding: 16px;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }
    </style>
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
        <%while(rs.next()){%>
        <tr>
            <td>Adam</td>
            <td><%=rs.getString("title")%>></td>
            <td><%=rs.getString("writer")%></td>
            <td><%=rs.getString("regdate")%></td>
            <td><%=rs.getString("hit")%></td>
        </tr>
        <%}%>
        <tr>
            <td colspan="5">
                <button onclick="location.href='/board/regist.html';">글등록</button>
            </td>
        </tr>
    </table>

</body>

</html>
<%
    rs.close();
    pstmt.close();
    con.close();
%>