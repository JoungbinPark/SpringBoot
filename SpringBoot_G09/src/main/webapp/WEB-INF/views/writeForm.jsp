<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
<form action="write" method="post">
    <table width="700" cellpadding="0" cellspacing="0" border="1">
        <tr>
            <td width="80">작성자</td>
            <td><input type="text" name="writer" value="${dto.writer}"/></td>
        </tr>
        <tr>
            <td width="80">제목</td>
            <td><input type="text" name="title" value="${dto.title}"/></td>
        </tr>
        <tr>
            <td width="80">내용</td>
            <td><input type="text" name="content" value="${dto.content}"/></td>
        </tr>
        <tr>
            <td>
                ${message}
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="입력"/> &nbsp;&nbsp;
                <a href="/">목록보기</a>
            </td>
        </tr>
    </table>
</form>


</body>
</html>
