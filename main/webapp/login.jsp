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
    <div>
        <jsp:include page="header.jsp"/>
    </div>
    <div class="text-center">
        <div>
            <h3 class="mb-3 mt-3 font-weight-normal"><fmt:message key="login.pageLogin"/></h3>
        </div>
        <form method="post" action="login" style="max-width:300px;margin: auto">
            <div class="mb-3">
                <input type="text" class="form-control" placeholder="<fmt:message key="user.login"/>" name="login"/>
            </div>
            <div>
                <input type="password" class="form-control" placeholder="<fmt:message key="user.password"/>" name="password"/>
            </div>
            <div class="mt-3">
                <input class="btn btn-lg btn-primary btn-block" type="submit" value="<fmt:message key="login.enter"/>"/>
            </div>
        </form>
    </div>
    <div class="text-center">
        <jsp:include page="message_error.jsp"/>
    </div>
    <div class="text-center mt-5">
        <a href="/RentCar/registration"><fmt:message key="login.registration"/></a>
    </div>
</body>
</html>