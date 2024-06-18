<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
</head>
<body>
<h1><% out.println("MyBatis:UserList");%></h1>
<c:forEach var="user" items="${list}">
    <h1>${user.id} / ${user.name}</h1>
</c:forEach>

</body>
</html>
