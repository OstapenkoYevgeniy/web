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
                <div style="text-align: left;">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="action" value="RefillBalanceAction">
                        <input type="hidden" name="action" value="UserRegistrationAction">

                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.iin"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" name="iin" value="${iin}" style="width: 200px;" maxlength="12">
                        </div>
                        <c:if test="${not empty iinError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${iinError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                 <fmt:message key="text.content.name"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" name="name" style="width: 200px;" value="${name}">
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
                            <input type="text" class="blue-input" name="surname" style="width: 200px;" value="${surname}">
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
                            <input type="text" class="blue-input" name="patronymic" style="width: 200px;" value="${patronymic}">
                        </div>
                        <c:if test="${not empty patronymicError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${patronymicError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                 <fmt:message key="text.content.card.number"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" name="card_number" style="width: 200px;" value="${cardNumber}" maxlength="16">
                        </div>
                        <c:if test="${not empty cardNumberError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${cardNumberError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                 <fmt:message key="text.content.card.expiry.date"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" name="card_expiry_date" style="width: 200px;" maxlength="4" value="${cardExpiryDate}">
                        </div>
                        <c:if test="${not empty cardExpiryDateError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${cardExpiryDateError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                 <fmt:message key="text.content.amount"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" name="amount" style="width: 200px;" value="${amount}">
                        </div>
                        <c:if test="${not empty amountError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${amountError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.currency"/>
                            </span>
                        </div>
                        <div>
                            <select name="currency" class="blue-input" style="width: 200px;">
                                <option value="kzt"><fmt:message key="option.kzt"/></option>
                                <option value="rub"><fmt:message key="option.rub"/></option>
                                <option value="usd"><fmt:message key="option.usd"/></option>
                            </select>
                        </div>
                        <div>
                            <button type="submit" class="blue-button" style="width: 200px;">
                                <fmt:message key="button.text.refill"/>
                            </button>
                        </div>
                        <c:if test="${not empty refillError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${refillError}"/>
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

${issue_payment_card}