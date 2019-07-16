<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style><%@include file="/WEB-INF/css/signup.css"%></style>
    <script>
        $("#signupbtn").click(function validate() {
            var login = $("#login").val();
            var pwd = $("#password").val();
            if (login == "" || pwd == "") {
                alert( "Invalid data!" );
                event.preventDefault();
            }
        });
        $(document).ready(function() {
            $.get('/chapter_012/towns?type=""', function(data, status) {
                var cntrs = data.split("|");
                for (var i = 0; i < cntrs.length; i++) {
                    $("#country").append(
                        "<option value= " + cntrs[i] + ">" + cntrs[i] + "</option>"
                    );
                }
                var query = '/chapter_012/towns?type=towns' + '&country=' + cntrs[0];
                $.get(query, function (d, status) {
                    var twns = d.split("|");
                    for (var i = 0; i < twns.length; i++) {
                        $("#town").append(
                            "<option value= " + twns[i] + ">" + twns[i] + "</option>"
                        );
                    }
                });
            });
        });

        function load() {
            var sltd = $("#country").val();
            var query = '/chapter_012/towns?type=towns' + '&country=' + sltd;
            $("#town").empty();
            $.get(query, function (d, status) {
                var twns = d.split("|");
                for (var i = 0; i < twns.length; i++) {
                    $("#town").append(
                        "<option value= " + twns[i] + ">" + twns[i] + "</option>"
                    );
                }
            });
        }

    </script>
</head>
<body>
<div id="content">
    <form action="${pageContext.servletContext.contextPath}/create" method="POST" id="f">
        <div class="container">
            <h1>Create user</h1>
            <p>Please fill in this form to create a user.</p>
            <hr>
            <label for="login"><b>Login</b></label>
            <input type="text" placeholder="Enter Password" name="login">

            <label for="password"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password">

            <label for="email"><b>Email</b></label>
            <input type="text" placeholder="Enter Email" name="email">

            <label for="country"><b>Country</b></label>
            <br>
            <select id="country" name="country" form="f">
            </select>

            <label for="town"><b>Town</b></label>
            <br>
            <select id="town" name="town" form="f">

            </select>

            <label for="roles"><b>Role</b></label>
            <br>
            <select id="role" name="roles" form="f">
                <option value="user">User</option>
                <option value="admin">Admin</option>
            </select>

            <div class="clearfix">
                <button formaction="${pageContext.servletContext.contextPath}/list" formmethod="get" class="cancelbtn">Cancel</button>
                <button type="submit" class="signupbtn">Create</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
