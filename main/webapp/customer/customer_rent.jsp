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
    <div>
        <jsp:include page="../header.jsp"/>
    </div>
    <div class="text-center mt-3">
        <h3><fmt:message key="customerRent.carRentPage"/></h3>
    </div>
    <div class="main container row" style="margin-left: 150px">
        <div class="mt-3 mb-3 col-md-4">
            <div>
                <div>
                    <h5><fmt:message key="customerRent.rentInformation"/></h5>
                </div>
                <div>
                    <div>
                        <fmt:message key="order.totalCost"/>: ${totalCost}
                    </div>
                    <div>
                        <form method="post" action="/RentCar/rent">
                            <div>
                                <fmt:message key="order.startRent"/>: ${startR}
                            </div>
                            <div>
                                <fmt:message key="order.endRent"/>: ${endR}
                            </div>
                            <div>
                                <fmt:message key="customerRent.carWithDriver"/>:
                                <select name="needDriver">
                                    <option value="true"><fmt:message key="true"/></option>
                                    <option value="false"><fmt:message key="false"/></option>
                                </select>
                            </div>
                            <div>
                                <button type="submit"><fmt:message key="main.rentCar"/></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div>
                <div>
                    <h5><fmt:message key="customerRent.changeInformation"/></h5>
                </div>
                <div>
                    <form method="post" action="/RentCar/rent?formType=changeInf">
                        <div>
                            <fmt:message key="order.startRent"/>:<input value="${startR}" type="date" name="startRent"/>
                        </div>
                        <div>
                            <fmt:message key="order.endRent"/>:<input value="${endR}" type="date" name="endRent"/>
                        </div>
                        <div>
                            <button type="submit"><fmt:message key="cabinet.update"/></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="mt-3 mb-3 col-md-4">
            <div>
                <h5><fmt:message key="customerRent.carInformation"/></h5>
            </div>
            <c:if test="${car != null}">
                <div>
                    <fmt:message key="car.carType"/>:<fmt:message key="enum.carType.${car.carType}"/>
                </div>
                <div>
                    <fmt:message key="car.brand"/>:${car.brand}
                </div>
                <div>
                    <fmt:message key="car.model"/>:${car.model}
                </div>
                <div>
                    <fmt:message key="car.bodyType"/>:<fmt:message key="enum.bodyType.${car.bodyType}"/>
                </div>
                <div>
                    <fmt:message key="car.color"/>:<fmt:message key="enum.color.${car.color}"/>
                </div>
                <div>
                    <fmt:message key="car.yearCreation"/>:${car.yearCreation}
                </div>
                <div>
                    <fmt:message key="car.transmission"/>:<fmt:message key="enum.transmissionType.${car.transmissionType}"/>
                </div>
                <div>
                    <fmt:message key="car.sizeEngine"/>:${car.engineSize}
                </div>
                <div>
                    <fmt:message key="car.powerEngine"/>:${car.enginePower}
                </div>
                <div>
                    <fmt:message key="car.fuelType"/>:<fmt:message key="enum.fuelType.${car.fuelType}"/>
                </div>
                <div>
                    <fmt:message key="car.price"/>:${car.price}
                </div>
            </c:if>
        </div>
        <div class="mt-3 mb-3 col-md-4">
            <div>
                <h5><fmt:message key="customerRent.yourInformation"/></h5>
            </div>
            <div>
                <c:if test="${sessionScope.customer != null}">
                    <div>
                        <fmt:message key="user.lastName"/>:${customer.lastName}
                    </div>
                    <div>
                        <fmt:message key="user.firstName"/>:${customer.firstName}
                    </div>
                    <div>
                        <fmt:message key="user.phoneNumber"/>:${customer.phoneNum}
                    </div>
                    <div>
                        <fmt:message key="user.email"/>:${customer.email}
                    </div>
                    <div>
                        <fmt:message key="user.documentSeries"/>:${customer.documentSeries}
                    </div>
                    <div>
                        <fmt:message key="user.documentNumber"/>:${customer.documentNum}
                    </div>
                    <div>
                        <fmt:message key="user.documentDateOfCreation"/>:${customer.dateIssue}
                    </div>
                    <div>
                        <fmt:message key="user.documentAuthority"/>:${customer.authority}
                    </div>
                </c:if>
            </div>
        </div>
        <div>
            <jsp:include page="/message_error.jsp"/>
        </div>
    </div>
</body>
</html>