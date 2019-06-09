<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create</title>
</head>
<body>
<h2>Create</h2>
<form action="<%=request.getContextPath()%>/create" method="POST">
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
            <td></td>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>
</form>
</body>
</html>
