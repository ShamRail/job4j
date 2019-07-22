<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <title>Account</title>
</head>
<body>
<div class="container">
    <div class="row pt-3">
        <h3>
            Вы выбрали ряд "${row}" место "${col}", Сумма : 500 рублей.
        </h3>
    </div>
    <div class="row">
        <form action="/chapter_012/account" method="post">
            <div class="form-group">
                <label for="username">ФИО</label>
                <input type="text" class="form-control" name="name" id="username" placeholder="ФИО">
                <input type="hidden" value="${row}" name="row">
                <input type="hidden" value="${col}" name="col">
                <input type="hidden" value="occupy" name="operation">
            </div>
            <div class="form-group">
                <label for="phone">Номер телефона</label>
                <input type="text" class="form-control" name="tel_num" id="phone" placeholder="Номер телефона">
            </div>
            <button type="submit" class="btn btn-success">Оплатить</button>
        </form>
        <form action="/chapter_012/account" method="post">
            <input type="hidden" value="${row}" name="row">
            <input type="hidden" value="${col}" name="col">
            <input type="hidden" value="free" name="operation">
            <button type="submit" class="btn btn-success">Отмена</button>
        </form>
    </div>
</div>
</body>
</html>