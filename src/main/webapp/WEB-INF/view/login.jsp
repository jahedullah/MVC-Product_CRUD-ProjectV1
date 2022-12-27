<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login page</title>
</head>
<body>
    <c:if test="${param.error != null}">
        <div align="center">
            <b><i style="color: red">Invalid Username and/or Password</i></b>
        </div>
    </c:if>

    <c:if test="${param.logout != null}">
        <div align="center">
            <b><i style="color: rebeccapurple">Logged out successfully. Log Back In??</i></b>
        </div>
    </c:if>
    <div align="center">
        <h1> Custom Login</h1>

            <form:form method="POST">
                Username : <input type="text" name="username">
                <br/>
                Password : <input type="password" name="password">
                <br/>
                <input type="submit" value="Login">
            </form:form>
    </div>


</body>

</html>