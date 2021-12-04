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
    <title><fmt:message key="adminCar.managementCarAdmin"/></title>
</head>
<body>
    <div>
        <jsp:include page="../header.jsp"/>
    </div>
    <div>
        <h3><fmt:message key="adminCar.managementCarAdmin"/></h3>
    </div>
    <div>
        <jsp:include page="/message_error.jsp"/>
    </div>
    <div class="form">
        <div>
            <h5><fmt:message key="adminCar.createNewCar"/></h5>
        </div>
        <div>
            <form method="post" action="/RentCar/admin/car?formType=createCar">
                <div>
                    <fmt:message key="car.carType"/> :
                    <select name="carTypeName">
                        <c:forEach items="${carTypes}" var="carType">
                            <option value="${carTypes.get(carType.carTypeId - 1)}">
                                <fmt:message key="enum.carType.${carTypes.get(carType.carTypeId - 1)}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <fmt:message key="car.brand"/>:<input type="text" name="brand"/>
                </div>
                <div>
                    <fmt:message key="car.model"/>:<input type="text" name="model"/>
                </div>
                <div>
                    <fmt:message key="car.bodyType"/> :
                    <select name="bodyTypeName">
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
                        <c:forEach items="${colors}" var="color">
                            <option value="${colors.get(color.colorId - 1)}">
                                <fmt:message key="enum.color.${colors.get(color.colorId - 1)}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <fmt:message key="car.yearCreation"/> :
                    <input type="number" name="yearCreation"/>
                </div>
                <div>
                    <fmt:message key="car.transmission"/> :
                    <select name="transmissionTypeName">
                        <c:forEach items="${transmissionTypes}" var="transmissionType">
                            <option value="${transmissionTypes.get(transmissionType.transmissionId - 1)}">
                                <fmt:message key="enum.transmissionType.${transmissionTypes.get(transmissionType.transmissionId - 1)}"/>
                                       </option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <fmt:message key="car.sizeEngine"/> :
                    <input type="number" step=".01" name="engineSize"/>
                </div>
                <div>
                    <fmt:message key="car.powerEngine"/> :
                    <input type="number" name="enginePower"/>
                </div>
                <div>
                    <fmt:message key="car.fuelType"/> :
                    <select name="fuelTypeName">
                        <c:forEach items="${fuelTypes}" var="fuelType">
                            <option value="${fuelTypes.get(fuelType.fuelTypeId - 1)}">
                                <fmt:message key="enum.fuelType.${fuelTypes.get(fuelType.fuelTypeId - 1)}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <fmt:message key="car.price"/> :
                    <input type="number" step=".01" name="price"/>
                </div>
                <div>
                    <fmt:message key="car.isActive"/> :
                        <select name="isActive">
                            <option value="${true}">
                                <fmt:message key="true"/>
                            </option>
                            <option value="${false}">
                                <fmt:message key="false"/>
                            </option>
                        </select>
                </div>
                <div>
                    <button type="submit"><fmt:message key="adminCar.saveCar"/></button>
                    </div>
                </form>
            </div>
    </div>
    <div>
        <h5><fmt:message key="main.allCars"/></h5>
    </div>
    <div>
        <table border="1" cellpadding="2" cellspacing="2">
            <tr>
                <th><fmt:message key="car.carId"/></th>
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
                <th><fmt:message key="car.isActive"/></th>
                <th><fmt:message key="adminCar.delete"/></th>
                <th><fmt:message key="adminCar.update"/></th>
            </tr>
        <c:forEach var="car" items="${cars}">
            <tr>
                <td><c:out value="${car.carId}"/></td>
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
                <td><fmt:message key="${car.isActive}"/></td>
                <td>
                    <form method="post" action="/RentCar/admin/car?formType=deleteCar&carId=${car.carId}">
                        <button type="submit"><fmt:message key="adminCar.delete"/></button>
                    </form>
                </td>
                <td>
                    <form method="post" action="/RentCar/admin/car?formType=updateCar&carId=${car.carId}">
                        <button type="submit"><fmt:message key="adminCar.update"/></button>
                    </form>
                </td>
            </tr>
            </c:forEach>
        </table>
    </div>

 <%--For displaying Previous link except for the 1st page --%>
     <c:if test="${currentPage != 1}">
         <td><a href="/RentCar/admin/car?page=${currentPage - 1}"><fmt:message key="previous"/></a></td>
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
                         <td><a href="/RentCar/admin/car?page=${i}">${i}</a></td>
                     </c:otherwise>
                 </c:choose>
             </c:forEach>
         </tr>
     </table>

     <%--For displaying Next link --%>
     <c:if test="${currentPage lt noOfPages}">
         <td><a href="/RentCar/admin/car?page=${currentPage + 1}"><fmt:message key="next"/></a></td>
     </c:if>

</body>
</html>