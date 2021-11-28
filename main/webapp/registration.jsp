<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="en" />
<c:if test="${locale != null}">
    <fmt:setLocale value="${locale}" />
</c:if>
<fmt:setBundle basename="MyBundle"/>

<html>
<head>
    <title><fmt:message key="registration.pageRegistration"/></title>
</head>
<body>
<jsp:include page="header.jsp"/>

    <h3><fmt:message key="registration.pageRegistration"/></h3>
    <div class="form">
        <form method="post" action="registration">
            <label><fmt:message key="user.login"/>:</label><input type="text" name="login"/><br>
            <label><fmt:message key="user.password"/>:</label><input type="password" name="password"/><br>
            <label><fmt:message key="registration.confirmPassword"/>:</label><input type="password" name="conPassword"/><br>
            <label><fmt:message key="user.firstName"/>:</label><input type="text" name="firstName"/><br>
            <label><fmt:message key="user.lastName"/>:</label><input type="text" name="lastName"/><br>
            <label><fmt:message key="user.email"/>:</label><input type="email" name="email"/><br>
            <label><fmt:message key="user.phoneNumber"/>:</label><input type="tel" name="phoneNumber"/><br>
            <input type="submit" value="<fmt:message key="registration.register"/>"/><br>
        </form>
    </div>
    <jsp:include page="message_error.jsp"/>
    <br>
    <a href="/RentCar/login"><fmt:message key="user.login"/></a>
</body>
</html>