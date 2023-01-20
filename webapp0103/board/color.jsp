<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script>
        function change(){
            let c=document.getElementById("c");
            location.href="/board/color.jsp";
        }

    </script>
</head>
<body>
    
</body bgcolor="<%=bg%>">
    <select name="배경색" id="c" size="7">
        <option value="배경색">빨</option>
        <option value="">주</option>
        <option value="">노</option>
        <option value="">초</option>
        <option value="">파</option>
        <option value="">남</option>
        <option value="">보</option>
    </select>
    <button onclick="change()">ok</button>
</html>