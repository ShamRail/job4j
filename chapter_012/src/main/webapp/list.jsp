<<<<<<< HEAD
<%@ page import="ru.job4j.crud.persistent.User" %>
<%@ page import="ru.job4j.crud.logic.ValidateService" %><%@ page import="ru.job4j.crud.logic.ValidationException"%>
=======
<%@ page import="ru.job4j.crud.logic.ValidateService" %>
<%@ page import="ru.job4j.crud.logic.ValidationException" %><%@ page import="ru.job4j.crud.persistent.User"%>
>>>>>>> 1. Перенести все виды из предыдущего задания на JSP
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! private final ValidateService validateService = ValidateService.getInstance();%>
<html>
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
    </tr>
    <% try {
        for (User user : validateService.findAll()) { %>
<<<<<<< HEAD
        <%String date = new Date(user.getCreateDate()).toString();%>
        <tr>
            <td><%=user.getId()%></td>
            <td><%=user.getLogin()%></td>
            <td><%=user.getPassword()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=date%></td>
            <td>
                <form action="<%=request.getContextPath()%>/update.jsp" method="GET">
                    <input type="hidden" name="id" value=<%=user.getId()%>>
                    <input type="hidden" name="login" value=<%=user.getLogin()%>>
                    <input type="hidden" name="password" value=<%=user.getPassword()%>>
                    <input type="hidden" name="email" value=<%=user.getEmail()%>>
                    <input type="hidden" name="date" value=<%=user.getCreateDate()%>>
                    <input type="submit" value="Edit">
                </form>
            </td>
            <td>
                <form action="<%=request.getContextPath()%>/delete" method="POST">
                    <input type="hidden" name="id" value=<%=user.getId()%>>
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
        <% }
=======
    <%String date = new Date(user.getCreateDate()).toString();%>
    <tr>
        <td><%=user.getId()%></td>
        <td><%=user.getLogin()%></td>
        <td><%=user.getPassword()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=date%></td>
        <td>
            <form action="<%=request.getContextPath()%>/update.jsp" method="GET">
                <input type="hidden" name="id" value=<%=user.getId()%>>
                <input type="hidden" name="login" value=<%=user.getLogin()%>>
                <input type="hidden" name="password" value=<%=user.getPassword()%>>
                <input type="hidden" name="email" value=<%=user.getEmail()%>>
                <input type="hidden" name="date" value=<%=user.getCreateDate()%>>
                <input type="submit" value="Edit">
            </form>
        </td>
        <td>
            <form action="<%=request.getContextPath()%>/delete" method="POST">
                <input type="hidden" name="id" value=<%=user.getId()%>>
                <input type="submit" value="Delete">
            </form>
        </td>
    </tr>
    <% }
>>>>>>> 1. Перенести все виды из предыдущего задания на JSP
    } catch (ValidationException e) {

    }%>
</table>
<form action="<%=request.getContextPath()%>/create.jsp" method="GET">
    <input type="submit" value="Create">
</form>
</body>
</html>
