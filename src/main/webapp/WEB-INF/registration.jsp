<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fs" uri="http://www.john.com/filler_select" %>

<fmt:bundle basename="text">
    <c:choose>
        <c:when test="${user.role.name == 'Client'}">
            <c:redirect url="/controller?action=ClientHomeDisplay"/>
        </c:when>
        <c:when test="${user.role.name == 'Manager'}">
            <c:redirect url="/controller?action=ManagerHomeDisplay"/>
        </c:when>
    </c:choose>
    <!DOCTYPE html>
    <html lang="ru">
    <t:head/>
    <body>
    <div class="container">
        <div class="row">
            <c:choose>
                <c:when test="${empty sessionScope.user}">
                    <t:login/>
                </c:when>
                <c:otherwise>
                    <t:client_navigation/>
                </c:otherwise>
            </c:choose>
            <div class="col-md-4 content" style="min-height: 500px;">
                <div style="text-align: left;">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="action" value="RegistrationAction">

                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.your.identifier"/>
                            </span>
                        </div>
                        <input type="text" class="blue-input" name="identifier" style="width: 200px;"
                               value="${identifier}">
                        <c:if test="${not empty identifierError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${identifierError}"/>
                            </div>
                        </c:if>
                        <div><span class="white-text" style="font-size: 18px;">
                            <fmt:message key="text.content.name"/>
                        </span></div>
                        <input type="text" class="blue-input" name="name" style="width: 200px;" value="${name}">
                        <c:if test="${not empty nameError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${nameError}"/>
                            </div>
                        </c:if>
                        <div><span class="white-text" style="font-size: 18px;">
                            <fmt:message key="text.content.surname"/>
                        </span></div>
                        <input type="text" class="blue-input" name="surname" style="width: 200px;" value="${surname}">
                        <c:if test="${not empty surnameError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${surnameError}"/>
                            </div>
                        </c:if>
                        <div><span class="white-text" style="font-size: 18px;">
                             <fmt:message key="text.content.patronymic"/>
                        </span></div>
                        <input type="text" class="blue-input" name="patronymic" style="width: 200px;"
                               value="${patronymic}">
                        <c:if test="${not empty patronymicError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${patronymicError}"/>
                            </div>
                        </c:if>
                        <div><span class="white-text" style="font-size: 18px;">
                            <fmt:message key="text.content.birthday"/>
                        </span></div>
                        <div>
                            <select name="day" class="blue-input" style="width: 65px;">
                                <c:forEach items="${fs:getDays()}" var="day">
                                    <c:choose>
                                        <c:when test="${day == returnDay}">
                                            <option selected="selected" value="${day}">${day}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${day}">${day}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <select name="month" class="blue-input" style="width: 150px;">
                                <c:forEach items="${fs:getMonths()}" var="month">
                                    <c:choose>
                                        <c:when test="${month == returnMonth}">
                                            <option selected="selected" value="${month}"><fmt:message
                                                    key="month.${month}"/></option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${month}"><fmt:message key="month.${month}"/></option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <select name="year" class="blue-input" style="width: 85px;">
                                <c:forEach items="${fs:getYears(fs:getCurrentYear()-18, 82)}"
                                           var="year">
                                    <c:choose>
                                        <c:when test="${year == returnYear}">
                                            <option selected="selected" value="${year}">${year}</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${year}">${year}</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </div>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="input.password"/>
                            </span>
                        </div>
                        <input type="password" class="blue-input" name="password" style="width: 200px;">
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.retry.password"/>
                            </span>
                        </div>
                        <div>
                            <input type="password" class="blue-input" name="retry_password" style="width: 200px;">
                        </div>
                        <c:if test="${not empty passwordError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${passwordError}"/>
                            </div>
                        </c:if>
                        <div>
                            <button type="submit" class="blue-button" style="width: 200px;">
                                <fmt:message key="button.text.registration"/>
                            </button>
                        </div>
                        <c:if test="${not empty registrationError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${registrationError}"/>
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>
