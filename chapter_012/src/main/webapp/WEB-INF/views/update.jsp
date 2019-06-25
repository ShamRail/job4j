<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update</title>
</head>
<body>
<h2>Update</h2>
<form action="${pageContext.servletContext.contextPath}/update" method="POST" id="f">
    <table>
        <tr>
            <td>Login: </td>
            <td><input type="text" name="login" value="${login}"></td>
            <input type="hidden" name="id" value="${id}">
        </tr>
        <tr>
            <td>Password: </td>
            <td><input type="text" name="password" value="${password}"></td>
        </tr>
        <tr>
            <td>Email: </td>
            <td><input type="text" name="email" value="${email}"></td>
        </tr>
        <tr>
            <td>Date: </td>
            <td>"${date}"</td>
        </tr>
        <tr>
            <td>Role: </td>
            <td>
                <label>
                    <select name="roles" form="f">
                        <c:if test="${role == 'user'}">
                            <option value="user" selected>User</option>
                            <option value="admin">Admin</option>
                        </c:if>
                        <c:if test="${role == 'admin'}">
                            <option value="user">User</option>
                            <option value="admin" selected>Admin</option>
                        </c:if>
                    </select>
                </label>
            </td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>
</form>
</body>
</html>
