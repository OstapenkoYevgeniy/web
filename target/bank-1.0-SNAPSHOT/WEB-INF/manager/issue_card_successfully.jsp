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
                <div>
                    <span class="white-text" style="font-size: 18px;">
                        <fmt:message key="text.content.isssue.card.successfully"/><br>
                        <fmt:message key="text.content.client.identifier"/>: ${paymentCard.user.identifier}
                    </span>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>

</fmt:bundle>

${issue_payment_card}