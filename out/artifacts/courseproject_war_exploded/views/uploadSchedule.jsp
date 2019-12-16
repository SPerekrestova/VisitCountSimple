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
           HashMap<String, HashMap<String, String>> groupList = (HashMap<String, HashMap<String, String>>) request.getAttribute("groupList");
           HashMap<String, String> students = (HashMap<String, String>) groupList.values();

            if (!students.isEmpty()) {
                for (Map.Entry<String, String> entry  : students.entrySet()) {
                    try {
                        out.println(entry.getKey() + " " + entry.getValue() + "<br>");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                out.print("<br>");
            } else out.println("<p>Вы еще не загрузили ни одного файла!</p>");
        %>
    </div>
</div>

<div>
    <button onclick="location.href='/'">Вернуться на главный экран</button>
</div>
</body>
</html>