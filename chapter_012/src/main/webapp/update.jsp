<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update</title>
</head>
<body>
<h2>Update</h2>
<form action="<%=request.getContextPath()%>/update" method="POST">
    <table>
        <tr>
            <td>Login: </td>
            <td><input type="text" name="login" value=<%=request.getParameter("login")%>></td>
            <input type="hidden" name="id" value=<%=request.getParameter("id")%>>
        </tr>
        <tr>
            <td>Password: </td>
            <td><input type="text" name="password" value=<%=request.getParameter("password")%>></td>
        </tr>
        <tr>
            <td>Email: </td>
            <td><input type="text" name="email" value=<%=request.getParameter("email")%>></td>
        </tr>
        <tr>
            <td>Date: </td>
            <td><%=new Date(Long.parseLong(request.getParameter("date"))).toString()%></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Save"></td>
        </tr>
    </table>
</form>
</body>
</html>
