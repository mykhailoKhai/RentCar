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
    <div>
        <jsp:include page="../header.jsp"/>
    </div>
    <div>
        <h3><fmt:message key="managerOrder.managerControlOrder"/></h3>
    </div>
    <div>
        <jsp:include page="/message_error.jsp"/>
    </div>
    <div>
        <h3><fmt:message key="managerOrder.allInformationAboutOrder"/>: ${order.orderId}</h3>
    </div>
    <div>
        <div>
            <div>
                <fmt:message key="order.orderId"/>: <c:out value="${order.orderId}"/>
            </div>
            <div>
                <fmt:message key="order.startRent"/>: <c:out value="${order.rentStart}"/>
            </div>
            <div>
                <fmt:message key="order.endRent"/>: <c:out value="${order.rentEnd}"/>
            </div>
            <div>
                <fmt:message key="order.needDriver"/>: <fmt:message key="${order.needDriver}"/>
            </div>
            <div>
                <fmt:message key="order.totalCost"/>: <c:out value="${order.totalCost}"/>
            </div>
            <div>
                <fmt:message key="order.startRent"/>: <fmt:message key="enum.status.${order.statusOrder}"/>
            </div>
            <div>
                <fmt:message key="order.statusMessage"/>: <c:out value="${order.statusMessage}"/>
            </div>
            <div>
                <fmt:message key="order.costDamage"/>: <c:out value="${order.damagePaid}"/>
            </div>
            <div>
                <fmt:message key="order.isPaidDamage"/>: <fmt:message key="${order.isPaidDamage}"/>
            </div>
            <div>
                <fmt:message key="order.documentSeries"/>: <c:out value="${order.documentSeries}"/>
            </div>
            <div>
                <fmt:message key="order.documentNumber"/>: <c:out value="${order.documentNum}"/>
            </div>
            <div>
                <fmt:message key="order.dateIssue"/>: <c:out value="${order.dateIssue}"/>
            </div>
            <div>
                <fmt:message key="order.authority"/>: <c:out value="${order.authority}"/>
            </div>
        </div>
        <div>
            <div>
                <h5><fmt:message key="managerOrder.userInOrder"/></h5>
            </div>
            <div>
                <fmt:message key="user.userId"/>: <c:out value="${order.user.userId}"/>
            </div>
            <div>
                <fmt:message key="user.lastName"/>: <c:out value="${order.user.lastName}"/>
            </div>
            <div>
                <fmt:message key="user.firstName"/>: <c:out value="${order.user.firstName}"/>
            </div>
            <div>
                <fmt:message key="user.phoneNumber"/>: <c:out value="${order.user.phoneNum}"/>
            </div>
            <div>
                <fmt:message key="user.email"/>: <c:out value="${order.user.email}"/>
            </div>
            <div>
                <fmt:message key="user.isActive"/>: <fmt:message key="${order.user.isActive}"/>
            </div>
            <div>
                <fmt:message key="user.account"/>: <c:out value="${order.user.account}"/>
            </div>
            <div>
                <fmt:message key="user.documentSeries"/>: <c:out value="${order.user.documentSeries}"/>
            </div>
            <div>
                <fmt:message key="user.documentNumber"/>: <c:out value="${order.user.documentNum}"/>
            </div>
            <div>
                <fmt:message key="user.documentDateOfCreation"/>: <c:out value="${order.user.dateIssue}"/>
            </div>
            <div>
                <fmt:message key="user.documentAuthority"/>: <c:out value="${order.user.authority}"/>
            </div>
        </div>
        <div>
            <div>
                <h5><fmt:message key="managerOrder.carInOrder"/></h5>
            </div>
            <div>
                <fmt:message key="car.carId"/>: <c:out value="${order.car.carId}"/>
            </div>
            <div>
                <fmt:message key="car.carType"/>: <fmt:message key="enum.carType.${order.car.carType}"/>
            </div>
            <div>
                <fmt:message key="car.brand"/>: <c:out value="${order.car.brand}"/>
            </div>
            <div>
                <fmt:message key="car.model"/>: <c:out value="${order.car.model}"/>
            </div>
            <div>
                <fmt:message key="car.price"/>: <c:out value="${order.car.price}"/>
            </div>
            <div>
                <fmt:message key="car.isActive"/>: <fmt:message key="${order.car.isActive}"/>
            </div>
        </div>
        <div>
            <c:if test="${order.statusOrder == 'NEW_ORDER' or order.statusOrder == 'ISSUE_ORDER'}">
                <div>
                    <form method="post" action="/RentCar/manager?formType=changeStatusOrder&orderId=${order.orderId}">
                    <div>
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
                    </div>
                    <div>
                        <fmt:message key="managerOrder.statusMessage"/> : <input type="text" name="statusMessage">
                    </div>
                    <div>
                        <button type="submit"><fmt:message key="managerOrder.changeStatusAndSendMessage"/></button>
                    </div>
                </form>
            </div>
            <div>
                <form method="post" action="/RentCar/manager?formType=changeDamagePaid&orderId=${order.orderId}">
                    <fmt:message key="managerOrder.paymentForDamage"/> :<input type="number" step=".01" name="damagePaid">
                    <button type="submit"><fmt:message key="managerOrder.theCostOfDamage"/></button>
                </form>
            </div>
        </c:if>
        </div>
    </div>
    <div>
        <a href="/RentCar/manager"><fmt:message key="header.mainPage"/></a>
    </div>
</body>
</html>