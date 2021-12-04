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
    <title><fmt:message key="header.cabinet"/></title>
    <link rel="stylesheet" href="style.css">
</head>
    <div>
        <jsp:include page="../header.jsp"/>
    </div>
    <div class="main container">

        <div class="text-center mt-3">
            <h3><fmt:message key="header.cabinet"/></h3>
        </div>
        <div>
            <div>
                <h5><fmt:message key="cabinet.updatePersonalInformation"/></h5>
            </div>
            <div>
                <form method="post" action="/RentCar/cabinet?formType=updateUser">
                    <div>
                        <fmt:message key="user.login"/>:<input value="<c:out value="${customer.login}"/>" type="text" name="login"/>
                    </div>
                    <div>
                        <fmt:message key="cabinet.oldPassword"/>:<input type="password" name="oldPassword"/>
                    </div>
                    <div>
                        <fmt:message key="cabinet.newPassword"/>:<input type="password" name="newPassword"/>
                    </div>
                    <div>
                        <fmt:message key="cabinet.repeatNewPassword"/>:<input type="password" name="repPassword"/>
                    </div>
                    <div>
                        <fmt:message key="user.lastName"/>:<input value="<c:out value="${customer.lastName}"/>" type="text" name="lastName"/>
                    </div>
                    <div>
                        <fmt:message key="user.firstName"/>:<input value="<c:out value="${customer.firstName}"/>" type="text" name="firstName"/>
                    </div>
                    <div>
                        <fmt:message key="user.phoneNumber"/>:<input value="<c:out value="${customer.phoneNum}"/>" type="number" name="phoneNum"/>
                    </div>
                    <div>
                        <fmt:message key="user.email"/>:<input value="<c:out value="${customer.email}"/>" type="email" name="email"/>
                    </div>
                    <div>
                        <fmt:message key="user.documentSeries"/>:<input value="<c:out value="${customer.documentSeries}"/>" type="text" name="documentSeries"/>
                    </div>
                    <div>
                        <fmt:message key="user.documentNumber"/>:<input value="<c:out value="${customer.documentNum}"/>" type="number" name="documentNum"/>
                    </div>
                    <div>
                        <fmt:message key="user.documentDateOfCreation"/>:<input value="${customer.dateIssue}" type="date" name="dateIssue"/>
                    </div>
                    <div>
                        <fmt:message key="user.documentAuthority"/>:<input value="<c:out value="${customer.authority}"/>" type="text" name="authority"/>
                    </div>
                    <div>
                        <button type="submit"><fmt:message key="cabinet.update"/></button>
                    </div>
                </form>
            </div>
        </div>
        <div>
            <jsp:include page="/message_error.jsp"/>
        </div>
        <div>
            <div>
                <h5><fmt:message key="cabinet.addMoney"/></h5>
            </div>
            <div>
                <form method="post" action="/RentCar/cabinet?formType=updateMoney">
                    <input type="number" step=".01" name="money"/>
                    <button type="submit"><fmt:message key="cabinet.addMoney"/></button>
                </form>
            </div>
        </div>
    </div>
</body>
</html>