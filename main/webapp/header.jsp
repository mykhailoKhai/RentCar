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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <div>
            <a href=<my:replaceParam name='language' value='ua' />><fmt:message key="header.ua"/></a>
            <a href=<my:replaceParam name='language' value='en' />><fmt:message key="header.en"/></a>
        </div>
        <c:if test="${sessionScope.customer == null and sessionScope.manager == null and sessionScope.admin == null}">
            <div>
                <a class="btn btn-sm btn-outline-secondary" href="/RentCar/registration"><fmt:message key="login.registration"/></a>
                <a class="btn btn-sm btn-outline-secondary" href="/RentCar/login"><fmt:message key="user.login"/></a>
            </div>
        </c:if>
        <c:if test="${sessionScope.customer != null}">
            <div>
                <fmt:message key="header.hello"/> ${customer.firstName} ${customer.lastName}
            </div>
            <div>
                <fmt:message key="header.yourAccount"/>: ${customer.account}
            </div>
            <div>
                <a class="btn btn-sm btn-outline-secondary" href="/RentCar/main"><fmt:message key="header.mainPage"/></a>
                <a class="btn btn-sm btn-outline-secondary" href="/RentCar/customerOrder"><fmt:message key="header.allOrders"/></a>
                <a class="btn btn-sm btn-outline-secondary" href="/RentCar/cabinet"><fmt:message key="header.cabinet"/></a>
            </div>
            <div>
                <a class="btn btn-sm btn-outline-secondary" href="/RentCar/logout"><fmt:message key="header.logout"/></a>
            </div>
        </c:if>
        <c:if test="${sessionScope.manager != null}">
            <div>
                <fmt:message key="header.hello"/> ${manager.firstName} ${manager.lastName}
            </div>
            <div>
                <a class="btn btn-sm btn-outline-secondary" href="/RentCar/manager"><fmt:message key="header.mainPage"/></a>
            </div>
            <div>
                <a class="btn btn-sm btn-outline-secondary" href="/RentCar/logout"><fmt:message key="header.logout"/></a>
            </div>
        </c:if>
        <c:if test="${sessionScope.admin != null}">
            <div>
                <fmt:message key="header.hello"/> ${admin.firstName} ${admin.lastName}
            </div>
            <div>
                <a class="btn btn-sm btn-outline-secondary" href="/RentCar/admin/user"><fmt:message key="header.managementUsers"/></a>
                <a class="btn btn-sm btn-outline-secondary" href="/RentCar/admin/car"><fmt:message key="header.managementCars"/></a>
            </div>
            <div>
                <a class="btn btn-sm btn-outline-secondary" href="/RentCar/logout"><fmt:message key="header.logout"/></a>
            </div>
        </c:if>
    </div>
</nav>
</body>
</html>