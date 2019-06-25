<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h2>Registration</h2>
<form action="${pageContext.servletContext.contextPath}/signup" method="POST" id="f">
    <table>
        <tr>
            <td>Login: </td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td>Password: </td>
            <td><input type="text" name="password"></td>
        </tr>
        <tr>
            <td>Email: </td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td>Role: </td>
            <td>
                <label>
                    <select name="roles" form="f">
                        <option value="user">User</option>
                        <option value="admin">Admin</option>
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