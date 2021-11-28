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
</head>
<body>
    <div>
<%--        <form method="post" action=<my:replaceParam name='language' value='ua' />>--%>
<%--            <button type="submit"><fmt:message key="header.ua"/></button>--%>
<%--        </form>--%>
<%--        <form method="post" action=<my:replaceParam name='language' value='en' />>--%>
<%--            <button type="submit"><fmt:message key="header.en"/></button>--%>
<%--        </form>--%>
<%--        <a href="/RentCar/language?language=ua&cur=<my:replaceParam name='language' value='en' />"><fmt:message key="header.ua"/>--%>
<%--        <a href="/RentCar/language?locale=en&cur=${pageContext.request.requestURI}"><fmt:message key="header.en"/>--%>

    <a href=<my:replaceParam name='language' value='ua' />><fmt:message key="header.ua"/></a>
    <a href=<my:replaceParam name='language' value='en' />><fmt:message key="header.en"/></a><br>


        <c:if test="${sessionScope.customer == null and sessionScope.manager == null and sessionScope.admin == null}">
            <a href="/RentCar/login"><fmt:message key="user.login"/></a><br>
            <a href="/RentCar/registration"><fmt:message key="login.registration"/></a>
        </c:if>
        <c:if test="${sessionScope.customer != null}">
            <fmt:message key="header.hello"/> ${customer.firstName} ${customer.lastName}<br>
            <fmt:message key="header.yourAccount"/>: ${customer.account}<br>
            <a href="/RentCar/cabinet"><fmt:message key="header.cabinet"/></a><br>
            <a href="/RentCar/logout"><fmt:message key="header.logout"/></a><br>
            <a href="/RentCar/main"><fmt:message key="header.mainPage"/></a><br>
            <a href="/RentCar/customerOrder"><fmt:message key="header.allOrders"/></a><br>
        </c:if>
        <c:if test="${sessionScope.manager != null}">
            <fmt:message key="header.hello"/> ${manager.firstName} ${manager.lastName}<br>
            <a href="/RentCar/logout"><fmt:message key="header.logout"/></a><br>
            <a href="/RentCar/manager"><fmt:message key="header.mainPage"/></a>
        </c:if>
        <c:if test="${sessionScope.admin != null}">
            <fmt:message key="header.hello"/> ${admin.firstName} ${admin.lastName}<br>
            <a href="/RentCar/logout"><fmt:message key="header.logout"/></a><br>
            <a href="/RentCar/admin/user"><fmt:message key="header.managementUsers"/></a>
            <form method="post" action="/RentCar/admin/car?formType=mainCar">
                <button type="submit"><fmt:message key="header.managementCars"/></button>
            </form>
        </c:if>
    </div>
</body>
</html>