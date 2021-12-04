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
    <title><fmt:message key="adminUser.managementUserAdmin"/></title>
</head>
<body>
    <div>
        <jsp:include page="../header.jsp"/>
    </div>
    <div>
        <h3><fmt:message key="adminUser.managementUserAdmin"/></h3>
    </div>
    <div class="form">
        <div>
            <h5><fmt:message key="adminUser.createNewManager"/></h5>
        </div>
        <form method="post" action="/RentCar/admin/user?formType=createUser">
            <div>
                <fmt:message key="user.login"/>:<input type="text" name="login"/>
            </div>
            <div>
                <fmt:message key="user.password"/>:<input type="password" name="password"/>
            </div>
            <div>
                <fmt:message key="user.lastName"/>:<input type="text" name="lastName"/>
            </div>
            <div>
                <fmt:message key="user.firstName"/>:<input type="text" name="firstName"/>
            </div>
            <div>
                <fmt:message key="user.phoneNumber"/>:<input type="number" name="phoneNum"/>
            </div>
            <div>
                <fmt:message key="user.email"/>:<input type="email" name="email"/>
            </div>
            <div>
                <fmt:message key="adminUser.managerIsActive"/>:
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
                <fmt:message key="user.documentSeries"/>:<input type="text" name="documentSeries"/>
            </div>
            <div>
                <fmt:message key="user.documentNumber"/>:<input type="number" name="documentNum"/>
            </div>
            <div>
                <fmt:message key="user.documentDateOfCreation"/>:<input type="date" name="dateIssue"/>
            </div>
            <div>
                <fmt:message key="user.documentAuthority"/>:<input type="text" name="authority"/>
            </div>
            <div>
                <button type="submit"><fmt:message key="adminUser.save"/></button>
            </div>
        </form>
    </div>
    <div>
        <h5><fmt:message key="adminUser.allUsers"/></h5>
    </div>
    <div>
        <table border="1" cellpadding="2" cellspacing="2">
            <tr>
                <th><fmt:message key="user.userId"/></th>
                <th><fmt:message key="user.lastName"/></th>
                <th><fmt:message key="user.firstName"/></th>
                <th><fmt:message key="user.phoneNumber"/></th>
                <th><fmt:message key="user.email"/></th>
                <th><fmt:message key="user.role"/></th>
                <th><fmt:message key="user.account"/></th>
                <th><fmt:message key="user.documentSeries"/></th>
                <th><fmt:message key="user.documentNumber"/></th>
                <th><fmt:message key="user.documentDateOfCreation"/></th>
                <th><fmt:message key="user.documentAuthority"/></th>
                <th><fmt:message key="user.isActive"/></th>
                <th><fmt:message key="adminCar.update"/></th>
            </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.userId}"/></td>
                <td><c:out value="${user.lastName}"/></td>
                <td><c:out value="${user.firstName}"/></td>
                <td><c:out value="${user.phoneNum}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><fmt:message key="enum.role.${user.role}"/></td>
                <td><c:out value="${user.account}"/></td>
                <td><c:out value="${user.documentSeries}"/></td>
                <td><c:out value="${user.documentNum}"/></td>
                <td><c:out value="${user.dateIssue}"/></td>
                <td><c:out value="${user.authority}"/></td>
                <td><fmt:message key="${user.isActive}"/></td>
                <td>
                    <form method="post" action="/RentCar/admin/user?formType=createActive&userId=${user.userId}">
                        <select name="isActive">
                            <option value="${user.isActive}" select disable hidden>
                                <fmt:message key="${user.isActive}"/>
                            </option>
                            <option value="${true}">
                                <fmt:message key="${true}"/>
                            </option>
                            <option value="${false}">
                                <fmt:message key="${false}"/>
                            </option>
                        </select>
                        <button type="submit"><fmt:message key="adminUser.save"/></button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </table>
    </div>


    <%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="/RentCar/admin/user?page=${currentPage - 1}"><fmt:message key="previous"/></a></td>
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
                        <td><a href="/RentCar/admin/user?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>

    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="/RentCar/admin/user?page=${currentPage + 1}"><fmt:message key="next"/></a></td>
    </c:if>

</body>
</html>