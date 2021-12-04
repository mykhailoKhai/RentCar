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
    <title><fmt:message key="header.mainPage"/></title>
</head>
<body>
    <div>
        <jsp:include page="/header.jsp"/>
    </div>
    <div class="main container">
        <div class="text-center mt-3">
            <h3><fmt:message key="header.mainPage"/></h3>
        </div>
        <div class="mt-3 mb-3" style="background-color: ghostwhite">
            <div>
                <h5><fmt:message key="main.filter"/></h5>
            </div>
            <div>
                <form method="post" action="/RentCar/main?formType=filter">
                    <select name="brand">
                        <option value="none" select disable hidden>
                            <fmt:message key="main.filter.chooseByBrand"/>
                        </option>
                        <c:forEach items="${brands}" var="brand">
                            <option value="${brand}">
                                    ${brand}
                            </option>
                        </c:forEach>
                    </select>
                    <select name="carType">
                        <option value="none" select disable hidden>
                            <fmt:message key="main.filter.chooseByClass"/>
                        </option>
                        <c:forEach items="${carTypes}" var="carType">
                            <option value="${carType}">
                                <fmt:message key="enum.carType.${carType}"/>
                            </option>
                        </c:forEach>
                    </select>
                    <select name="sortPrice">
                        <option value="false" select disable hidden>
                            <fmt:message key="main.filter.sortByPrice"/>
                        </option>
                        <option value="true"><fmt:message key="true"/></option>
                        <option value="false"><fmt:message key="false"/></option>
                    </select>
                    <select name="sortBrand">
                        <option value="false" select disable hidden>
                            <fmt:message key="main.filter.sortByBrand"/>
                        </option>
                        <option value="true"><fmt:message key="true"/></option>
                        <option value="false"><fmt:message key="false"/></option>
                    </select>
                    <fmt:message key="main.filter.freeWith"/>: <input value="${startRent}" type="date" name="startRent"/>
                    <fmt:message key="main.filter.to"/>: <input value="${endRent}" type="date" name="endRent"/>
                    <div>
                        <button type="submit" class="btn btn-sm btn-outline-secondary"><fmt:message key="main.filter.filter"/></button>
                    </div>
                </form>
            </div>
            <div>
                <a href="/RentCar/main?formType=filterDel" class="btn btn-sm btn-outline-secondary"><fmt:message key="main.filter.deleteFilter"/></a>
            </div>
        </div>
        <div>
            <jsp:include page="message_error.jsp"/>
        </div>
        <div class="mb-3 mt-3">
            <div>
                <h5><fmt:message key="main.allCars"/></h5>
            </div>
            <table class="table table-bordered table-hover">
                <tr>
                    <th><fmt:message key="car.carType"/></th>
                    <th><fmt:message key="car.brand"/></th>
                    <th><fmt:message key="car.model"/></th>
                    <th><fmt:message key="car.bodyType"/></th>
                    <th><fmt:message key="car.color"/></th>
                    <th><fmt:message key="car.yearCreation"/></th>
                    <th><fmt:message key="car.transmission"/></th>
                    <th><fmt:message key="car.sizeEngine"/></th>
                    <th><fmt:message key="car.powerEngine"/></th>
                    <th><fmt:message key="car.fuelType"/></th>
                    <th><fmt:message key="car.price"/></th>
                    <c:if test="${sessionScope.customer != null}">
                        <td><fmt:message key="main.rentCar"/></td>
                    </c:if>
                </tr>

                <c:forEach var="car" items="${cars}">
                    <tr>
                        <td><fmt:message key="enum.carType.${car.carType}"/></td>
                        <td><c:out value="${car.brand}"/></td>
                        <td><c:out value="${car.model}"/></td>
                        <td><fmt:message key="enum.bodyType.${car.bodyType}"/></td>
                        <td><fmt:message key="enum.color.${car.color}"/></td>
                        <td><c:out value="${car.yearCreation}"/></td>
                        <td><fmt:message key="enum.transmissionType.${car.transmissionType}"/></td>
                        <td><c:out value="${car.engineSize}"/></td>
                        <td><c:out value="${car.enginePower}"/></td>
                        <td><fmt:message key="enum.fuelType.${car.fuelType}"/></td>
                        <td><c:out value="${car.price}"/></td>
                        <c:if test="${sessionScope.customer != null}">
                            <td><a href="/RentCar/rent?carId=${car.carId}" class="btn btn-sm btn-outline-secondary"><fmt:message key="main.rentCar"/></a></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </div>

        <%--For displaying Previous link except for the 1st page --%>
        <c:if test="${currentPage != 1}">
            <td><a href="/RentCar/main?page=${currentPage - 1}"><fmt:message key="previous"/></a></td>
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
                            <td><a href="/RentCar/main?page=${i}">${i}</a></td>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </tr>
        </table>

        <%--For displaying Next link --%>
        <c:if test="${currentPage lt noOfPages}">
            <td><a href="/RentCar/main?page=${currentPage + 1}"><fmt:message key="next"/></a></td>
        </c:if>
    </div>
</body>
</html>