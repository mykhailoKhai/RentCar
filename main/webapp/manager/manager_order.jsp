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
    <title><fmt:message key="managerOrder.managerControlOrder"/></title>
</head>
<body>
<jsp:include page="../header.jsp"/>
    <h3><fmt:message key="managerOrder.managerControlOrder"/></h3>

    <jsp:include page="/message_error.jsp"/>

    <h3><fmt:message key="managerOrder.allInformationAboutOrder"/>: ${order.orderId}</h3>
    <div>
        <div>
            <fmt:message key="order.orderId"/>: <c:out value="${order.orderId}"/><br>
            <fmt:message key="order.startRent"/>: <c:out value="${order.rentStart}"/><br>
            <fmt:message key="order.endRent"/>: <c:out value="${order.rentEnd}"/><br>
            <fmt:message key="order.needDriver"/>: <fmt:message key="${order.needDriver}"/><br>
            <fmt:message key="order.totalCost"/>: <c:out value="${order.totalCost}"/><br>
            <fmt:message key="order.startRent"/>: <fmt:message key="enum.status.${order.statusOrder}"/><br>
            <fmt:message key="order.statusMessage"/>: <c:out value="${order.statusMessage}"/><br>
            <fmt:message key="order.costDamage"/>: <c:out value="${order.damagePaid}"/><br>
            <fmt:message key="order.isPaidDamage"/>: <fmt:message key="${order.isPaidDamage}"/><br>
            <fmt:message key="order.documentSeries"/>: <c:out value="${order.documentSeries}"/><br>
            <fmt:message key="order.documentNumber"/>: <c:out value="${order.documentNum}"/><br>
            <fmt:message key="order.dateIssue"/>: <c:out value="${order.dateIssue}"/><br>
            <fmt:message key="order.authority"/>: <c:out value="${order.authority}"/><br>
        </div>
        <div>
            <h5><fmt:message key="managerOrder.userInOrder"/></h5>
            <fmt:message key="user.userId"/>: <c:out value="${order.user.userId}"/><br>
            <fmt:message key="user.lastName"/>: <c:out value="${order.user.lastName}"/><br>
            <fmt:message key="user.firstName"/>: <c:out value="${order.user.firstName}"/><br>
            <fmt:message key="user.phoneNumber"/>: <c:out value="${order.user.phoneNum}"/><br>
            <fmt:message key="user.email"/>: <c:out value="${order.user.email}"/><br>
            <fmt:message key="user.isActive"/>: <fmt:message key="${order.user.isActive}"/><br>
            <fmt:message key="user.account"/>: <c:out value="${order.user.account}"/><br>
            <fmt:message key="user.documentSeries"/>: <c:out value="${order.user.documentSeries}"/><br>
            <fmt:message key="user.documentNumber"/>: <c:out value="${order.user.documentNum}"/><br>
            <fmt:message key="user.documentDateOfCreation"/>: <c:out value="${order.user.dateIssue}"/><br>
            <fmt:message key="user.documentAuthority"/>: <c:out value="${order.user.authority}"/><br>
        </div>
        <div>
            <h5><fmt:message key="managerOrder.carInOrder"/></h5>
            <fmt:message key="car.carId"/>: <c:out value="${order.car.carId}"/><br>
            <fmt:message key="car.carType"/>: <fmt:message key="enum.carType.${order.car.carType}"/><br>
            <fmt:message key="car.brand"/>: <c:out value="${order.car.brand}"/><br>
            <fmt:message key="car.model"/>: <c:out value="${order.car.model}"/><br>
            <fmt:message key="car.price"/>: <c:out value="${order.car.price}"/><br>
            <fmt:message key="car.isActive"/>: <fmt:message key="${order.car.isActive}"/><br>
        </div>
        <br>
        <div>
            <c:if test="${order.statusOrder == 'NEW_ORDER' or order.statusOrder == 'ISSUE_ORDER'}">
                <form method="post" action="/RentCar/manager?formType=changeStatusOrder&orderId=${order.orderId}">
                    <fmt:message key="managerOrder.changeStatus"/> :
                    <select name="allStatusOrder">
                        <option value="${order.statusOrder}" select disable hidden>
                            <fmt:message key="enum.status.${order.statusOrder}"/>
                        </option>
                        <option value="${allStatusOrder.get(1)}">
                                <fmt:message key="enum.status.${allStatusOrder.get(1)}"/>
                        </option>
                        <c:if test="${order.statusOrder != 'ISSUE_ORDER'}">
                            <option value="${allStatusOrder.get(2)}">
                                <fmt:message key="enum.status.${allStatusOrder.get(2)}"/>
                            </option>
                        </c:if>
                        <c:if test="${order.statusOrder != 'NEW_ORDER'}">
                            <option value="${allStatusOrder.get(3)}">
                                <fmt:message key="enum.status.${allStatusOrder.get(3)}"/>
                            </option>
                        </c:if>
                    </select>
                    <fmt:message key="managerOrder.statusMessage"/> :
                    <input value="${order.statusMessage}" type="text" name="statusMessage">
                    <button type="submit"><fmt:message key="managerOrder.changeStatusAndSendMessage"/></button>
                </form>
                <form method="post" action="/RentCar/manager?formType=changeDamagePaid&orderId=${order.orderId}">
                    <fmt:message key="managerOrder.paymentForDamage"/> :<input type="number" step=".01" name="damagePaid">
                    <button type="submit"><fmt:message key="managerOrder.theCostOfDamage"/></button>
                </form>
            </c:if>
        </div>
    </div>

    <a href="/RentCar/manager"><fmt:message key="header.mainPage"/></a>
</body>
</html>