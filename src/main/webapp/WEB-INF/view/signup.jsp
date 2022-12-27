<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
         isELIgnored="false" %>



<html>
<head>
    <title>Sign Up Page</title>
</head>
<body>


    <div align="center">
        <h1 align="center">Sign Up here</h1>
        <%--@elvariable id="signupdto" type="java"--%>
        <form:form action="process-signup"
                   method="POST"
                   modelAttribute="signupdto">

            Username: <form:input type="text" path="username"/>
            <br/>
            Password: <form:input path="password" type="password"/>
            <br/>
            <input type="submit" value="signup">
        </form:form>
    </div>



</body>
</html>
