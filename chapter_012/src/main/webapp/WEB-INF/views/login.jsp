<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<h2>Log in</h2>
<form action="${pageContext.servletContext.contextPath}/signin" method="GET">
    <table>
        <tr>
            <td>Login: </td>
            <td><input type="text" name="login"></td>
        </tr>
        <tr>
            <td>Password: </td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button formaction="${pageContext.servletContext.contextPath}/signup">Sign up</button>
                <input type="submit" value="Sign in">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
