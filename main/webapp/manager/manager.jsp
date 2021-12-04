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
    <title><fmt:message key="manager.managerPage"/></title>
</head>
<body>
    <div>
        <jsp:include page="../header.jsp"/>
    </div>
    <div>
        <h3><fmt:message key="manager.managerPage"/></h3>
    </div>
    <div>
        <jsp:include page="/message_error.jsp"/>
    </div>
    <div>
        <h5><fmt:message key="header.allOrders"/></h5>
    </div>
    <div>
        <form method="post" action="/RentCar/manager?formType=filterOrder">
            <select name="typeOrder">
                <option value="none" select disable hidden>
                    <fmt:message key="header.allOrders"/>
                </option>
                <c:forEach items="${allStatusOrder}" var="sOrder">
                    <option value="${allStatusOrder.get(sOrder.statusId - 1)}">
                        <fmt:message key="enum.status.${allStatusOrder.get(sOrder.statusId - 1)}"/>
                    </option>
                </c:forEach>
            </select>
            <button type="submit"><fmt:message key="main.filter"/></button>
        </form>
    </div>
    <div>
        <table border="1" cellpadding="2" cellspacing="2">
            <tr>
                <th><fmt:message key="order.orderId"/></th>
                <th><fmt:message key="order.startRent"/></th>
                <th><fmt:message key="order.endRent"/></th>
                <th><fmt:message key="order.needDriver"/></th>
                <th><fmt:message key="order.totalCost"/></th>
                <th><fmt:message key="order.status"/></th>
                <th><fmt:message key="order.costDamage"/></th>
                <th><fmt:message key="order.isPaidDamage"/></th>
                <th><fmt:message key="car.carId"/></th>
                <th><fmt:message key="user.userId"/></th>
                <th><fmt:message key="manager.allInformation"/></th>
            </tr>
            <c:forEach var="order" items="${orders}">
            <tr>
                <th><c:out value="${order.orderId}"/></th>
                <td><c:out value="${order.rentStart}"/></td>
                <td><c:out value="${order.rentEnd}"/></td>
                <td><fmt:message key="${order.needDriver}"/></td>
                <td><c:out value="${order.totalCost}"/></td>
                <td><fmt:message key="enum.status.${order.statusOrder}"/></td>
                <td><c:out value="${order.damagePaid}"/></td>
                <td><fmt:message key="${order.isPaidDamage}"/></td>
                <td><c:out value="${order.car.carId}"/></td>
                <td><c:out value="${order.user.userId}"/></td>
                <td>
                    <a href="/RentCar/manager?orderId=${order.orderId}"><fmt:message key="manager.allInformation"/></a>
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="/RentCar/manager?page=${currentPage - 1}">Previous</a></td>
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
                        <td><a href="/RentCar/manager?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>

    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="/RentCar/manager?page=${currentPage + 1}">Next</a></td>
    </c:if>

</body>
</html>