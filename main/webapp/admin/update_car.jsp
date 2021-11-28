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
    <title><fmt:message key="adminCar.updateCarAdmin"/></title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h3><fmt:message key="adminCar.updateCarAdmin"/></h3>
    <div class="form">
        <form method="post" action="/RentCar/admin/car?formType=update">
            <div>
                <fmt:message key="car.carType"/> :
                <select name="carTypeName">
                    <c:forEach items="${carTypes}" var="carType">
                        <option value="${car.carType}" select disable hidden>
                            <fmt:message key="enum.carType.${car.carType}"/>
                        </option>
                        <option value="${carTypes.get(carType.carTypeId - 1)}">
                            <fmt:message key="enum.carType.${carTypes.get(carType.carTypeId - 1)}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <fmt:message key="car.brand"/> :
                <input value=<c:out value="${car.brand}"/> type="text" name="brand"/>
            </div>
            <div>
                <fmt:message key="car.model"/> :
                <input value=<c:out value="${car.model}"/> type="text" name="model"/>
            </div>
            <div>
                <fmt:message key="car.bodyType"/> :
                <select name="bodyTypeName">
                    <option value="${car.bodyType}" select disable hidden>
                        <fmt:message key="enum.bodyType.${car.bodyType}"/>
                    </option>
                    <c:forEach items="${bodyTypes}" var="bodyType">
                        <option value="${bodyTypes.get(bodyType.bodyTypeId - 1)}">
                            <fmt:message key="enum.bodyType.${bodyTypes.get(bodyType.bodyTypeId - 1)}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <fmt:message key="car.color"/> :
                <select name="color">
                    <option value="${car.color}" select disable hidden>
                        <fmt:message key="enum.color.${car.color}"/>
                    </option>
                    <c:forEach items="${colors}" var="color">
                        <option value="${colors.get(color.colorId - 1)}">
                            <fmt:message key="enum.color.${colors.get(color.colorId - 1)}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <fmt:message key="car.yearCreation"/> :
                <input value=<c:out value="${car.yearCreation}"/> type="number" name="yearCreation"/>
            </div>
            <div>
                <fmt:message key="car.transmission"/> :
                <select name="transmissionTypeName">
                    <option value="${car.transmissionType}" select disable hidden>
                        <fmt:message key="enum.transmissionType.${car.transmissionType}"/>
                    </option>
                    <c:forEach items="${transmissionTypes}" var="transmissionType">
                        <option value="${transmissionTypes.get(transmissionType.transmissionId - 1)}">
                                <fmt:message key="enum.transmissionType.${transmissionTypes.get(transmissionType.transmissionId - 1)}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <fmt:message key="car.sizeEngine"/> :
                <input value=<c:out value="${car.engineSize}"/> step=".01" type="number" name="engineSize"/>
            </div>
            <div>
                <fmt:message key="car.powerEngine"/> :
                <input value=<c:out value="${car.enginePower}"/> type="number" name="enginePower"/>
            </div>
            <div>
                <fmt:message key="car.fuelType"/> :
                <select name="fuelTypeName">
                    <option value="${car.fuelType}" select disable hidden>
                        <fmt:message key="enum.fuelType.${car.fuelType}"/>
                    </option>
                    <c:forEach items="${fuelTypes}" var="fuelType">
                        <option value="${fuelTypes.get(fuelType.fuelTypeId - 1)}">
                            <fmt:message key="enum.fuelType.${fuelTypes.get(fuelType.fuelTypeId - 1)}"/>
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div>
                <fmt:message key="car.price"/> :
                <input value=<c:out value="${car.price}"/> step=".01" type="number" name="price"/>
            </div>
            <div>
                <fmt:message key="car.isActive"/> :
                <select name="isActive">
                    <option value="${car.isActive}" select disable hidden>
                        <fmt:message key="${car.isActive}"/>
                    </option>
                    <option value="${true}">
                        <fmt:message key="${true}"/>
                    </option>
                    <option value="${false}">
                        <fmt:message key="${false}"/>
                    </option>
                </select>
            </div>
            <div>
                <button type="submit"><fmt:message key="adminCar.update"/></button>
            </div>
        </form>
        <form method="post" action="/RentCar/admin/car?formType=mainCar">
            <button type="submit"><fmt:message key="header.mainPage"/></button>
        </form>
    </div>
</body>
</html>