<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Загрузка файлов</title>
</head>
<body>
    <div>
        <h1>Загрузка</h1>
    </div>
    <%
        if (request.getAttribute("gurumessage") != null) {
            out.println("<p> " + request.getAttribute("gurumessage") + "</p>");
        }
    %>
    <div>
        <form method="post" enctype="multipart/form-data" >
            <input type="file"  name="file" size="50" />
            <input type="submit" value="Upload" />
        </form>
    </div>
    <div>
        <button onclick="location.href='/'">Вернуться на главный экран</button>
    </div>
</body>
</html>