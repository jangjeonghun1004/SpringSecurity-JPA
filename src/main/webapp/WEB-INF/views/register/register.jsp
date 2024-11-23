<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Register</title>
  </head>
  <body>
    <form action="/register" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        아이디: <input type="text" name="userid" />
        <br>
        비밀번호: <input type"password" name="password" />
        <br>
        <input type="submit" value="전송" />
    </form>
  </body>
</html>