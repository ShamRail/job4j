<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>List</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<h2>Users</h2>
<div id="table-container">
    <table class="table table-bordered">
        <tr>
            <td>ID</td>
            <td>Login</td>
            <td>Password</td>
            <td>Email</td>
            <td>Date</td>
            <td>Role</td>
            <td>Country</td>>
            <td>Town</td>
            <td colspan="2">Actions</td>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>
                <td><c:out value="${user.password}"></c:out></td>
                <td><c:out value="${user.email}"></c:out></td>
                <td><c:out value="${user.createDate}"></c:out></td>
                <td><c:out value="${user.role.role}"></c:out></td>
                <td><c:out value="${user.country}"></c:out></td>
                <td><c:out value="${user.town}"></c:out></td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/update" method="GET">
                        <input type="hidden" name="id" value="${user.id}">
                        <input type="hidden" name="login" value="${user.login}">
                        <input type="hidden" name="password" value="${user.password}">
                        <input type="hidden" name="email" value="${user.email}">
                        <input type="hidden" name="date" value="${user.createDate}">
                        <input type="hidden" name="role" value="${user.role.role}">
                        <input type="hidden" name="country" value="${user.country}">
                        <input type="hidden" name="town" value="${user.town}">
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
</div>
<div id="form-input">
    <form action="${pageContext.servletContext.contextPath}/create" method="GET">
        <div id="from-group">
            <input type="submit" class="form-control" value="Create">
        </div>
    </form>
</div>
</body>
</html>
