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
    <title><fmt:message key="customer.customerOrders"/></title>
</head>
<body>
    <div>
        <jsp:include page="../header.jsp"/>
    </div>
    <div class="main container">
        <div class="text-center mt-3">
            <h3><fmt:message key="customer.customerOrders"/></h3>
        </div>
        <div>
            <jsp:include page="/message_error.jsp"/>
        </div>
        <div class="mt-3 mb-3">
            <table class="table table-bordered table-hover">
                <tr>
                    <th><fmt:message key="order.orderId"/></th>
                    <th><fmt:message key="order.status"/></th>
                    <th><fmt:message key="order.statusMessage"/></th>
                    <th><fmt:message key="order.startRent"/></th>
                    <th><fmt:message key="order.endRent"/></th>
                    <th><fmt:message key="order.needDriver"/></th>
                    <th><fmt:message key="order.totalCost"/></th>
                    <th><fmt:message key="car.carId"/></th>
                    <th><fmt:message key="car.brand"/></th>
                    <th><fmt:message key="car.model"/></th>
                    <th><fmt:message key="car.color"/></th>
                    <th><fmt:message key="car.price"/></th>
                    <th><fmt:message key="order.costDamage"/></th>
                    <th><fmt:message key="order.isPaidDamage"/></th>
                    <th><fmt:message key="customer.pay"/></th>
                </tr>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td><c:out value="${order.orderId}"/></td>
                        <td><fmt:message key="enum.status.${order.statusOrder}"/></td>
                        <td><c:out value="${order.statusMessage}"/></td>
                        <td><c:out value="${order.rentStart}"/></td>
                        <td><c:out value="${order.rentEnd}"/></td>
                        <td><fmt:message key="${order.needDriver}"/></td>
                        <td><c:out value="${order.totalCost}"/></td>
                        <td><c:out value="${order.car.carId}"/></td>
                        <td><c:out value="${order.car.brand}"/></td>
                        <td><c:out value="${order.car.model}"/></td>
                        <td><fmt:message key="enum.color.${order.car.color}"/></td>
                        <td><c:out value="${order.car.price}"/></td>
                        <td><c:if test="${order.damagePaid > 0.01}"><c:out value="${order.damagePaid}"/></c:if></td>
                        <td><c:if test="${order.damagePaid > 0.01}"><fmt:message key="${order.isPaidDamage}"/></c:if></td>
                        <td>
                            <c:if test="${order.damagePaid > 0.01 and order.isPaidDamage == false}">
                                <form method="post" action="/RentCar/customerOrder?formType=paidDamage&orderId=${order.orderId}">
                                    <button type="submit"><fmt:message key="customer.pay"/></button>
                                </form>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <%--For displaying Previous link except for the 1st page --%>
        <c:if test="${currentPage != 1}">
            <td><a href="/RentCar/customerOrder?page=${currentPage - 1}"><fmt:message key="previous"/></a></td>
        </c:if>

        <%--For displaying Page numbers.
        The when condition does not display a link for the current page--%>
        <table border="1" cellpadding="1" cellspacing="1">
            <tr>
                <c:forEach begin="1" end="${noOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage eq i}">
                            <td>${i}</td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="/RentCar/customerOrder?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>

        <%--For displaying Next link --%>
        <c:if test="${currentPage lt noOfPages}">
            <td><a href="/RentCar/customerOrder?page=${currentPage + 1}"><fmt:message key="next"/></a></td>
        </c:if>
    </div>
</body>
</html>