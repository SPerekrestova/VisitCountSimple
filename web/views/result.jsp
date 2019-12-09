<%@ page import="java.util.List" %>
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
            List<String> names = (List<String>) request.getAttribute("fileNames");

            if (names != null && !names.isEmpty()) {
                out.println("<ui>");
                for (String s : names) {
                    out.println("<li>" + s + "</li>");
                }
                out.println("</ui>");
            } else out.println("<p>Вы еще не загрузили ни одного файла!</p>");
        %>
    </div>
</div>

<div>
    <button onclick="location.href='/'">Вернуться на главный экран</button>
</div>
</body>
</html>