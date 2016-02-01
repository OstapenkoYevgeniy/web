<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="pch" uri="http://www.john.com/paymentCardNumberHider" %>
>
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
            <c:if test="${not empty sessionScope.user}">
                <t:client_navigation/>
            </c:if>
            <div class="col-md-4 content">
                <div style="text-align: left">
                    <c:forEach items="${paymentCards}" var="paymentCard">
                        <span class="white-text" style="font-size: 18px;">
                            ${paymentCard.typePaymentCard.name}<br>
                            <fmt:message key="text.content.card.expiry.date"/>: ${paymentCard.cardExpiryDate}<br>
                            <fmt:message key="text.content.card.number"/>: ${pch:format(paymentCard.number)}<br>
                            <fmt:message key="option.kzt"/> ${paymentCard.balanceKzt}<br>
                            <fmt:message key="option.rub"/> ${paymentCard.balanceRub}<br>
                            <fmt:message key="option.usd"/> ${paymentCard.balanceUsd}
                        </span>
                        <hr>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>