<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guru Uploading File</title>
</head>
<body>
    <div>
        <h1>Upload file</h1>
    </div>
    <div>
        <form method="post" enctype="multipart/form-data" >
            <input type="file"  name="guru_file" size="50" />
            <input type="submit" value="Upload" />
        </form>
    </div>
</body>
</html>