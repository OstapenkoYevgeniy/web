<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="f" uri="http://www.john.com/phoneFormatter" %>

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
                    <c:forEach items="${issuePaymentCards}" var="issuePaymentCard">
                        <span style="font-size: 18px;">
                                ${issuePaymentCard.typePaymentCard.name} (${f:format(issuePaymentCard.phone, "+7(ddd)ddd-dd-dd")} - ${issuePaymentCard.clientName})
                        </span>
                        <hr>
                    </c:forEach>
                    <t:pagination pages="${pages}" offset="${offset}"
                                  prefix="/controller?action=ManagerHomeDisplay&limit=${limit}&offset="/>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>