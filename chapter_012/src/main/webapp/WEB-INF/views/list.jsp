<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>List</title>
</head>
<body>
<h2>Users</h2>
<table border='1'>
    <tr>
        <td>ID</td>
        <td>Login</td>
        <td>Password</td>
        <td>Email</td>
        <td>Date</td>
        <td>Role</td>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.id}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.password}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.createDate}"></c:out></td>
            <td><c:out value="${user.role.role}"></c:out></td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/update" method="GET">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="hidden" name="login" value="${user.login}">
                    <input type="hidden" name="password" value="${user.password}">
                    <input type="hidden" name="email" value="${user.email}">
                    <input type="hidden" name="date" value="${user.createDate}">
                    <input type="hidden" name="role" value="${user.role.role}">
                    <input type="submit" value="Edit">
                </form>
            </td>
            <td>
                <form action="${pageContext.servletContext.contextPath}/delete" method="POST">
                    <input type="hidden" name="id" value="${user.id}">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action="${pageContext.servletContext.contextPath}/create" method="GET">
    <input type="submit" value="Create">
</form>
</body>
</html>
