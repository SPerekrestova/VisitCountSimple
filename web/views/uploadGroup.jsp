<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Расписание</title>
</head>
<body>
<div>
    <div>
        <div>
            <h2>Files</h2>
        </div>

        <%
           if (request.getAttribute("groupList") != null){
               out.println("<p>Список групп загружен успешно!</p>");

           } else {
               out.println("<p>Вы еще не загрузили ни одного файла!</p>");
           }
        %>
    </div>
</div>

<div>
    <button onclick="location.href='/'">Вернуться на главный экран</button>
</div>
</body>
</html>