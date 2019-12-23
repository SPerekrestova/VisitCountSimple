<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Файлы</title>
</head>

<body>
<div>
    <h1>Учет посещений!</h1>
</div>

<div>
    <div>
        <div>
            <h2>Files</h2>
        </div>
        <%
            String text = (String) request.getAttribute("result");
            out.println("<p>"+ text +"</p>");
        %>
    </div>
</div>

<div>
    <button onclick="location.href='/'">Вернуться на главный экран</button>
</div>
</body>
</html>