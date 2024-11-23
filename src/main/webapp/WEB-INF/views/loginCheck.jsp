<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>index page</title>
  </head>
  <body>
    <h1>Welcome, <c:out value="${pageContext.request.userPrincipal.name}" /></h1>

    <h2>User Details:</h2>
    <ul>
        <li>Username: ${pageContext.request.userPrincipal.name}</li>
        <li>Roles:
            <c:forEach var="role" items="${pageContext.request.userPrincipal.authorities}">
                ${role.authority}
            </c:forEach>
        </li>
    </ul>
    <br>

    <c:if test="${not empty pageContext.request.userPrincipal.name}">
    <form action="/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button>Logout</button>
    </form>
    </c:if>

  </body>
</html>