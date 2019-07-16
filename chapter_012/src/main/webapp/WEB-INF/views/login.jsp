<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Log in</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style><%@include file="/WEB-INF/css/login.css"%></style>
    <script>
        $(document).ready(function(){
            $("#signinbutton").click(function validate() {
                var login = $("#login").val();
                var pwd = $("#password").val();
                if (login == "" || pwd == "") {
                    alert( "Invalid data!" );
                    event.preventDefault();
                }
            });
        });
    </script>
</head>
<body>
<div id="content">
    <h2>Login Form</h2>
    <form action="${pageContext.servletContext.contextPath}/signin" method="GET" id="f" onsubmit="validate()">
        <div class="container">

            <label for="login"><b>Login</b></label>
            <input type="text" placeholder="Enter Username" name="login" id="login">

            <label for="password"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" id="password">

            <div>
                <button type="submit" id="signinbutton">Sign in</button>
                <button formaction="${pageContext.servletContext.contextPath}/signup" id="signup-button">Sign up</button>
            </div>

        </div>
    </form>
</div>
</body>
</html>
