<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>

<h1>${list}</h1><br>

<c:forEach var="mylist" items="${list}">
    <h1>${mylist}</h1>
</c:forEach>

</body>
</html>
