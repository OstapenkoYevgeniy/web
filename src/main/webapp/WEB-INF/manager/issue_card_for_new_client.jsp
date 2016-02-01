<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fs" uri="http://www.john.com/filler_select" %>

<fmt:bundle basename="text">
    <c:if test="${empty sessionScope.user}">
        <c:redirect url="/controller?action=DisplayPage&location=index"/>
    </c:if>
    <!DOCTYPE html>
    <html lang="ru">
    <t:head/>
    <body>
    <div class="container">
        <div class="row">
            <t:manager_panel/>
            <div class="col-md-4 content">
                <div style="text-align: left">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="action" value="IssueCardForNewClientAction">
                        <input type="hidden" name="issue_payment_card_id" value="${issuePaymentCard.id}">
                        <input type="hidden" name="iin" value="${issuePaymentCard.iin}">

                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.iin"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" value="${issuePaymentCard.iin}"
                                   style="width: 200px;" disabled>
                        </div>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.name"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" name="name" value="${name}" style="width: 200px;"
                                   autocomplete="off">
                        </div>
                        <c:if test="${not empty nameError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${nameError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.surname"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" name="surname" value="${surname}"
                                   style="width: 200px;"
                                   autocomplete="off">
                        </div>
                        <c:if test="${not empty surnameError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${surnameError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.patronymic"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" name="patronymic" value="${patronymic}"
                                   style="width: 200px;"
                                   autocomplete="off">
                        </div>
                        <c:if test="${not empty patronymicError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${patronymicError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.birthday"/>
                            </span>
                        </div>
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
                        <c:if test="${not empty birthdayError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${birthdayError}"/>
                            </div>
                        </c:if>

                        <hr>

                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.card.number"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" name="card_number" value="${cardNumber}"
                                   style="width: 200px" maxlength="16"
                                   autocomplete="off">
                        </div>
                        <c:if test="${not empty cardNumberError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${cardNumberError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.codeword"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" name="codeword" value="${codeword}"
                                   style="width: 200px"
                                   autocomplete="off">
                        </div>
                        <c:if test="${not empty codewordError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${codewordError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.card.expiry.date"/>(MMYY):
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" name="card_expiry_date" value="${cardExpiryDate}"
                                   style="width: 200px"
                                   maxlength="4" autocomplete="off">
                        </div>
                        <c:if test="${not empty cardExpiryDateError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${cardExpiryDateError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.card.security.code"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" name="security_code" value="${securityCode}"
                                   style="width: 200px"
                                   maxlength="3" autocomplete="off">
                        </div>
                        <c:if test="${not empty securityCodeError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${securityCodeError}"/>
                            </div>
                        </c:if>
                        <button type="submit" class="blue-button">
                            <fmt:message key="text.content.open.card"/>
                        </button>
                        <c:if test="${not empty issueError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${issueError}"/>
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
