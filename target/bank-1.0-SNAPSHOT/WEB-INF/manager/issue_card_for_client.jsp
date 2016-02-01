<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

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
                        <input type="hidden" name="action" value="IssueCardForClientAction">
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
                            <input type="text" class="blue-input" value="${client.name}" style="width: 200px;"
                                   autocomplete="off" disabled>
                        </div>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.surname"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" value="${client.surname}"
                                   style="width: 200px;" autocomplete="off" disabled>
                        </div>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.patronymic"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" value="${client.patronymic}"
                                   style="width: 200px;" autocomplete="off" disabled>
                        </div>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.birthday"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" value="${client.birthday}"
                                   style="width: 200px;" autocomplete="off" disabled>
                        </div>

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
                        <button type="submit" class="blue-button">Открыть карту</button>
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
