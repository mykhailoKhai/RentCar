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
</head>
<body>

    <jsp:include page="../header.jsp"/>
    <h3><fmt:message key="header.cabinet"/></h3>

    <h5><fmt:message key="cabinet.update"/></h5>
    <div class="form">
            <form method="post" action="/RentCar/cabinet?formType=updateUser">
                <div>
                    <label><fmt:message key="user.login"/></label>
                    <label>
                        <input value=<c:out value="${customer.login}"/> type="text" name="login"/>
                    </label>
                </div>
                <div>
                    <label><fmt:message key="cabinet.oldPassword"/></label>
                    <label>
                        <input type="password" name="oldPassword"/>
                    </label>
                </div>
                <div>
                    <label><fmt:message key="cabinet.newPassword"/></label>
                    <label>
                        <input type="password" name="newPassword"/>
                    </label>
                </div>
                <div>
                    <label><fmt:message key="cabinet.repeatNewPassword"/></label>
                    <label>
                        <input type="password" name="repPassword"/>
                    </label>
                </div>
                <div>
                    <label><fmt:message key="user.lastName"/></label>
                    <label>
                        <input value=<c:out value="${customer.lastName}"/> type="text" name="lastName"/>
                    </label>
                </div>
                <div>
                    <label><fmt:message key="user.firstName"/></label>
                    <label>
                        <input value=<c:out value="${customer.firstName}"/> type="text" name="firstName"/>
                    </label>
                </div>
                <div>
                    <label><fmt:message key="user.phoneNumber"/></label>
                    <label>
                        <input value=<c:out value="${customer.phoneNum}"/> type="number" name="phoneNum"/>
                    </label>
                </div>
                <div>
                    <label><fmt:message key="user.email"/></label>
                    <label>
                        <input value=<c:out value="${customer.email}"/> type="email" name="email"/>
                    </label>
                </div>
                <div>
                    <label><fmt:message key="user.documentSeries"/></label>
                    <label>
                        <input value=<c:out value="${customer.documentSeries}"/> type="text" name="documentSeries"/>
                    </label>
                </div>
                <div>
                    <label><fmt:message key="user.documentNumber"/></label>
                    <label>
                        <input value=<c:out value="${customer.documentNum}"/> type="number" name="documentNum"/>
                    </label>
                </div>
                <div>
                    <label><fmt:message key="user.documentDateOfCreation"/></label>
                    <label>
                        <input value="${customer.dateIssue}" type="date" name="dateIssue"/>
                    </label>
                </div>
                <div>
                    <label><fmt:message key="user.documentAuthority"/></label>
                    <label>
                        <input value=<c:out value="${customer.authority}"/> type="text" name="authority"/>
                    </label>
                </div>
                <div>
                    <button type="submit"><fmt:message key="cabinet.update"/></button>
                </div>
            </form>
        </div>
    <jsp:include page="/message_error.jsp"/>
    <div>
        <h5><fmt:message key="cabinet.addMoney"/></h5>
             <form method="post" action="/RentCar/cabinet?formType=updateMoney">
                  <input type="number" step=".01" name="money"/>
                  <button type="submit"><fmt:message key="cabinet.addMoney"/></button>
             </form>
    </div>
</body>
</html>