<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>

<form action="create">
    작성자 : <input type="text" name="writer" value="${dto.writer}" /> <br />
    내용 : <input type="text" name="content" value="${dto.content}" /> <br />
    <input type="submit" value="전송" /> <br />
    ${message}
</form>

</body>
</html>
