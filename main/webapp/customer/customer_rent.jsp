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
    <title><fmt:message key="customerRent.carRentPage"/></title>
</head>
<body>

    <jsp:include page="../header.jsp"/>

<h3><fmt:message key="customerRent.carRentPage"/></h3>
<h5><fmt:message key="customerRent.yourInformation"/></h5>
    <c:if test="${sessionScope.customer != null}">
         <div>
            <fmt:message key="user.lastName"/>:
                ${customer.lastName}<br>
            <fmt:message key="user.firstName"/>:
                ${customer.firstName}<br>
            <fmt:message key="user.phoneNumber"/>:
                ${customer.phoneNum}<br>
            <fmt:message key="user.email"/>:
                ${customer.email}<br>
            <fmt:message key="user.documentSeries"/>:
                ${customer.documentSeries}<br>
            <fmt:message key="user.documentNumber"/>:
                ${customer.documentNum}<br>
            <fmt:message key="user.documentDateOfCreation"/>:
                ${customer.dateIssue}<br>
            <fmt:message key="user.documentAuthority"/>:
                ${customer.authority}<br>
         </div>
    </c:if>
    <h5><fmt:message key="customerRent.carInformation"/></h5>
    <c:if test="${car != null}">
        <div>
            <fmt:message key="car.carType"/>:
                <fmt:message key="enum.carType.${car.carType}"/><br>
            <fmt:message key="car.brand"/>:
                ${car.brand}<br>
            <fmt:message key="car.model"/>:
                ${car.model}<br>
            <fmt:message key="car.bodyType"/>:
                <fmt:message key="enum.bodyType.${car.bodyType}"/><br>
            <fmt:message key="car.color"/>:
                <fmt:message key="enum.color.${car.color}"/><br>
            <fmt:message key="car.yearCreation"/>:
                ${car.yearCreation}<br>
            <fmt:message key="car.transmission"/>:
                <fmt:message key="enum.transmissionType.${car.transmissionType}"/><br>
            <fmt:message key="car.sizeEngine"/>:
                ${car.engineSize}<br>
            <fmt:message key="car.powerEngine"/>:
                ${car.enginePower}<br>
            <fmt:message key="car.fuelType"/>:
                <fmt:message key="enum.fuelType.${car.fuelType}"/><br>
            <fmt:message key="car.price"/>:
                ${car.price}<br>
        </div>
    </c:if>
    <h5><fmt:message key="customerRent.rentInformation"/></h5>
    <div>
        <fmt:message key="order.totalCost"/> : ${totalCost}<br>
<%--            <div>--%>
<%--                <fmt:message key="order.startRent"/> : ${startR}<br>--%>
<%--                <fmt:message key="order.endRent"/> : ${endR}<br>--%>
<%--                <fmt:message key="customerRent.carWithDriver"/> : ${needDriver}<br>--%>
<%--            </div>--%>
        <form method="post" action="/RentCar/rent">
<%--                <fmt:message key="order.startRent"/> :--%>
<%--                    <input value="${startR}" type="date" name="startRent"/>--%>
<%--                <fmt:message key="order.endRent"/> :--%>
<%--                    <input value="${endR}" type="date" name="endRent"/>--%>
            <fmt:message key="order.startRent"/> : ${startR}<br>
            <fmt:message key="order.endRent"/> : ${endR}<br>
            <fmt:message key="customerRent.carWithDriver"/> :
                <select name="needDriver">
                    <option value="true"><fmt:message key="true"/></option>
                    <option value="false"><fmt:message key="false"/></option>
                </select>
            <br>
            <button type="submit"><fmt:message key="main.rentCar"/></button>
        </form>
    </div>

    <h5><fmt:message key="customerRent.changeInformation"/></h5>
    <div>
        <form method="post" action="/RentCar/rent?formType=changeInf">
                <fmt:message key="order.startRent"/> :
                    <input value="${startR}" type="date" name="startRent"/>
                <fmt:message key="order.endRent"/> :
                    <input value="${endR}" type="date" name="endRent"/>
            <button type="submit"><fmt:message key="cabinet.update"/></button>
        </form>
    </div>

    <jsp:include page="/message_error.jsp"/>

</body>
</html>