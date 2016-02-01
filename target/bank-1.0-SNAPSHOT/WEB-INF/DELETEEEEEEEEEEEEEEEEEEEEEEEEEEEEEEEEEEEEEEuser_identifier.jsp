<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>--%>

<%--<fmt:bundle basename="text">--%>
    <%--<c:choose>--%>
         <%--<c:when test="${not empty sessionScope.user && user.role.name == 'User'}">--%>
            <%--<c:redirect url="/controller?action=DisplayPage&location=user_home"/>--%>
        <%--</c:when>--%>
        <%--<c:when test="${not empty sessionScope.user && user.role.name == 'Manager'}">--%>
            <%--<c:redirect url="/controller?action=DisplayPage&location=manager/manager_home"/>--%>
        <%--</c:when>--%>
        <%--<c:when test="${not empty sessionScope.user && user.role.name == 'Admin'}">--%>
            <%--<c:redirect url="/controller?action=DisplayPage&location=admin_home"/>--%>
        <%--</c:when>--%>
    <%--</c:choose>--%>

    <%--<!DOCTYPE html>--%>
    <%--<html lang="ru">--%>
    <%--<t:head/>--%>
    <%--<body>--%>
    <%--<div class="container">--%>
        <%--<div class="row">--%>
            <%--<c:choose>--%>
                <%--<c:when test="${empty sessionScope.user}">--%>
                    <%--<t:login/>--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <%--<t:user_navigation/>--%>
                <%--</c:otherwise>--%>
            <%--</c:choose>--%>
            <%--<div class="col-md-4 content" style="height: 500px;">--%>
                <%--<div>--%>
                    <%--${identifier}--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</body>--%>
    <%--</html>--%>

<%--</fmt:bundle>--%>