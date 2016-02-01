<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<c:choose>
    <c:when test="${user.role.name == 'Client'}">
        <c:redirect url="/controller?action=ClientHomeDisplay"/>
    </c:when>
    <c:when test="${user.role.name == 'Manager'}">
        <c:redirect url="/controller?action=ManagerHomeDisplay"/>
    </c:when>
</c:choose>

<fmt:bundle basename="text">
    <!DOCTYPE html>
    <html lang="ru">
    <t:head/>
    <body>
    <div class="container">
        <div class="row">
            <t:login/>
            <div class="col-md-4 content" style="min-height: 500px;">
                <div>
                    <div>
                        <span class="white-text" style="font-size: 18px;">
                            <fmt:message key="text.content.one"/>
                        </span>
                    </div>
                    <a href="${pageContext.request.contextPath}/controller?action=IssueCardDisplay">
                        <button class="blue-button" style="width: 300px; ">
                            <fmt:message key="button.text.issue.paymentcard"/>
                        </button>
                    </a>
                    <hr>
                    <div>
                        <span class="white-text" style="font-size: 16px;">
                           <b><fmt:message key="text.content.two"/></b>
                        </span>
                    </div>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="action" value="MoneyTransferCardToCardAction">
                        <table border="1" cladding="7" callipering="0">
                            <tr>
                                <td>
                                    <div class="card_from">
                                        <div>
                                            <fmt:message key="text.content.card.from"/>
                                        </div>
                                        <div>
                                            <b><fmt:message key="text.content.card.name.patronymic"/></b>
                                        </div>
                                        <div>
                                            <input type="text" style="width: 100%;" name="name_from"
                                                   value="${nameFrom} ${surnameFrom}">
                                        </div>
                                        <div>
                                            <b><fmt:message key="text.content.card.number"/></b>
                                        </div>
                                        <div>
                                            <input type="text" class="card-number" name="card_number_from_1"
                                                   maxlength="4" value="${cardNumberFrom1}">
                                            <input type="text" class="card-number" name="card_number_from_2"
                                                   maxlength="4" value="${cardNumberFrom2}">
                                            <input type="text" class="card-number" name="card_number_from_3"
                                                   maxlength="4" value="${cardNumberFrom3}">
                                            <input type="text" class="card-number" name="card_number_from_4"
                                                   maxlength="4" value="${cardNumberFrom4}">
                                        </div>
                                        <div style="display: inline-block">
                                            <div>
                                                <b><fmt:message key="text.content.card.expiry.date"/></b>
                                            </div>
                                            <div>
                                                <input type="text" class="card-number" name="card_expiry_date"
                                                       maxlength="4" value="${cardExpiryDate}">
                                            </div>
                                        </div>
                                        <div style="display: inline-block">&nbsp;&nbsp;&nbsp;</div>
                                        <div style="display: inline-block">
                                            <div>
                                                <b><fmt:message key="text.content.card.security.code"/></b>
                                            </div>
                                            <div>
                                                <input type="password" class="card-number" name="security_code"
                                                       maxlength="3">
                                            </div>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <div class="card_to">
                                        <div>
                                            <fmt:message key="text.content.card.to"/>
                                        </div>
                                        <div>
                                            <b><fmt:message key="text.content.card.name.patronymic"/></b>
                                        </div>
                                        <div>
                                            <input type="text" style="width: 100%;" name="name_to" value="${nameTo} ${surnameTo}">
                                        </div>
                                        <div>
                                            <b><fmt:message key="text.content.card.number"/></b>
                                        </div>
                                        <div>
                                            <input type="text" class="card-number" name="card_number_to_1"
                                                   maxlength="4" value="${cardNumberTo1}">
                                            <input type="text" class="card-number" name="card_number_to_2"
                                                   maxlength="4" value="${cardNumberTo2}">
                                            <input type="text" class="card-number" name="card_number_to_3"
                                                   maxlength="4" value="${cardNumberTo3}">
                                            <input type="text" class="card-number" name="card_number_to_4"
                                                   maxlength="4" value="${cardNumberTo4}">
                                            |
                                            <select name="currency" required>
                                                <option value="kzt"><fmt:message key="option.kzt"/></option>
                                                <option value="rub"><fmt:message key="option.rub"/></option>
                                                <option value="usd"><fmt:message key="option.usd"/></option>
                                            </select>
                                        </div>
                                        <div style="text-align: left; margin-top: 15px;">
                                            <b><fmt:message key="text.content.sum"/> </b>
                                            <input type="text" name="amount" value="${amount}">
                                            <button type="submit" class="blue-button" style="width: auto;">
                                                <fmt:message key="button.text.send"/>
                                            </button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>
                    <c:if test="${not empty transfer}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="${transfer}"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty transferError}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="${transferError}"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty nameError}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="${nameError}"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty surnameError}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="${surnameError}"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty cardNumberError}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="${cardNumberError}"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty cardExpiryDateError}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="${cardExpiryDateError}"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty securityCodeError}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="${securityCodeError}"/>
                        </div>
                    </c:if>
                    <c:if test="${not empty amountError}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="${amountError}"/>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>