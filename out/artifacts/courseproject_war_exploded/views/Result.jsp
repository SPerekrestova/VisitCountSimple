<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Расписание</title>
</head>
<body>
<% String msg = (String)request.getAttribute("message");
    out.println(msg);
%>

<div>
    <button onclick="location.href='/'">Вернуться на главный экран</button>
</div>
</body>
</html>