<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="pf" uri="http://www.john.com/phoneFormatter" %>


<c:if test="${issuePaymentCard == null}">
    <c:redirect url="/controller?action=DisplayPage&location=index"/>
</c:if>

<fmt:bundle basename="text">
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
            <div class="col-md-4 content" style="text-align: left;">
                <span class="white-text" style="font-size: 18px;">
                    <fmt:message key="text.content.thanks"/> ${issuePaymentCard.clientName}!<br/>

                </span>
                <span class="white-text" style="font-size: 18px;">
                    <fmt:message
                            key="text.content.issue.payment.card"/> "${issuePaymentCard.typePaymentCard.name}" <fmt:message
                        key="text.content.successfully"/> <br>
                </span>
                <span class="white-text" style="font-size: 18px;">
                    <fmt:message
                            key="text.content.callback"/> ${pf:format(issuePaymentCard.phone, "+7(ddd)ddd-dd-dd")}
                </span>
            </div>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>
