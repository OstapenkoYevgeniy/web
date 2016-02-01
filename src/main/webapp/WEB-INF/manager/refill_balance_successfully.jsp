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
                <span class="white-text" style="font-size: 20px;">
                    <fmt:message key="text.content.refill.balance.successfully"/>
                </span>
            </div>
        </div>
    </div>
    </div>
    </body>
    </html>

</fmt:bundle>
