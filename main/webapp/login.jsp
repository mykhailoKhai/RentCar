<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>

<fmt:setLocale value="en" />
<c:if test="${locale != null}">
    <fmt:setLocale value="${locale}" />
</c:if>
<fmt:setBundle basename="MyBundle"/>

<html>
<head>
    <title><fmt:message key="login.pageLogin"/></title>
</head>
<body>
    <jsp:include page="header.jsp"/>

<h3><fmt:message key="login.pageLogin"/></h3>

    <div class="form">
        <form method="post" action="login">
            <label><fmt:message key="user.login"/>:</label><input type="text" name="login"/><br>
            <label><fmt:message key="user.password"/>:</label><input type="password" name="password"/><br>
            <input type="submit" value="<fmt:message key="login.enter"/>"/><br>
        </form>
        <jsp:include page="message_error.jsp"/>
        <br>
        <a href="/RentCar/registration"><fmt:message key="login.registration"/></a>
    </div>
</body>
</html>