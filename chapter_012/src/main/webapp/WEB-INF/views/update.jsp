<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update</title>
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
                    if (cntrs[i] == "${country}") {
                        $("#country").append(
                            "<option value= " + cntrs[i] + " selected>" + cntrs[i] + "</option>"
                        );
                    } else {
                        $("#country").append(
                            "<option value= " + cntrs[i] + ">" + cntrs[i] + "</option>"
                        );
                    }
                }
                var query = '/chapter_012/towns?type=towns' + '&country=' + "${country}";
                $.get(query, function (d, status) {
                    var twns = d.split("|");
                    for (var i = 0; i < twns.length; i++) {
                        if (twns[i] == "${town}") {
                            $("#town").append(
                                "<option value= " + twns[i] + " selected>" + twns[i] + "</option>"
                            );
                        } else {
                            $("#town").append(
                                "<option value= " + twns[i] + ">" + twns[i] + "</option>"
                            );
                        }
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
    <form action="${pageContext.servletContext.contextPath}/update" method="POST" id="f">
        <div class="container">
            <h1>Update user</h1>
            <p>Please fill in this form to update a user.</p>
            <hr>
            <input type="hidden" name="id" value="${id}">
            <label for="login"><b>Login</b></label>
            <input type="text" placeholder="Enter Password" name="login" value="${login}">

            <label for="password"><b>Password</b></label>
            <input type="password" placeholder="Enter Password" name="password" value="${password}">

            <label for="country"><b>Country</b></label>
            <br>
            <select id="country" name="country" form="f">

            </select>

            <label for="town"><b>Town</b></label>
            <br>
            <select id="town" name="town" form="f">

            </select>


            <label><b>Date</b></label>
            <p>${date}</p>

            <label for="roles"><b>Role</b></label>
            <br>
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

            <div class="clearfix">
                <button formaction="${pageContext.servletContext.contextPath}/list" formmethod="get" class="cancelbtn">Cancel</button>
                <button type="submit" class="signupbtn">Update</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
