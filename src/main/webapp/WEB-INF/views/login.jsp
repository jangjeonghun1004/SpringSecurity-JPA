<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Register</title>
  </head>
  <body>
    <form action="/login" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        아이디: <input type="text" name="username" />
        <br>
        비밀번호: <input type"password" name="password" />
        <br>
        <input type="submit" value="전송" />
    </form>
    <br>

    <sec:authorize access="!hasRole('USER')">
        <a href="/register">회원가입</a>
    </sec:authorize>

  </body>
</html>