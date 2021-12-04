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
    <div>
        <jsp:include page="header.jsp"/>
    </div>
    <div class="text-center">
        <div>
            <h3 class="mb-3 mt-3 font-weight-normal"><fmt:message key="registration.pageRegistration"/></h3>
        </div>
        <form method="post" action="registration" style="max-width:400px;margin: auto">
            <div class="mt-1">
                <input type="text" class="form-control" placeholder="<fmt:message key="user.login"/>" name="login"/>
            </div>
            <div class="mt-1">
                <input type="password" class="form-control" placeholder="<fmt:message key="user.password"/>" name="password"/>
            </div>
            <div class="mt-1">
                <input type="password" class="form-control" placeholder="<fmt:message key="registration.confirmPassword"/>" name="conPassword"/>
            </div>
            <div class="mt-1">
                <input type="text" class="form-control" placeholder="<fmt:message key="user.firstName"/>" name="firstName"/>
            </div>
            <div class="mt-1">
                <input type="text" class="form-control" placeholder="<fmt:message key="user.lastName"/>" name="lastName"/>
            </div>
            <div class="mt-1">
                <input type="email" class="form-control" placeholder="<fmt:message key="user.email"/>" name="email"/>
            </div>
            <div class="mt-1">
                <input type="tel" class="form-control" placeholder="<fmt:message key="user.phoneNumber"/>" name="phoneNumber"/>
            </div>
            <div class="mt-3">
                <input class="btn btn-lg btn-primary btn-block" type="submit" value="<fmt:message key="registration.register"/>"/>
            </div>
        </form>
    </div>
    <div class="text-center">
        <jsp:include page="message_error.jsp"/>
    </div>
    <div class="text-center mt-5">
        <a href="/RentCar/login"><fmt:message key="user.login"/></a>
    </div>
</body>
</html>