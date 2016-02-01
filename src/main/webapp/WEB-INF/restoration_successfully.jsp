<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="text">
    <!DOCTYPE html>
    <html lang="ru">
    <t:head/>
    <body>
    <div class="container">
        <div class="row">
            <t:login/>
            <div class="col-md-4 content" style="min-height: 500px;">
                <c:choose>
                    <c:when test="${not empty remindUser}">
                        <span class="white-text" style="font-size: 18px;">
                            <fmt:message key="text.content.your.identifier"/>: ${remindUser.identifier}
                        </span>
                    </c:when>
                    <c:when test="${not empty changePassword}">
                        <span class="white-text" style="font-size: 18px;">
                            <fmt:message key="text.content.your.identifier"/>: ${remindUser.identifier}<br>
                            <fmt:message key="text.content.change.password"/>
                        </span>
                    </c:when>
                    <c:otherwise>
                        <c:redirect url="/controller?action=DisplayPage&location=index"/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    </div>
    </body>
    </html>
</fmt:bundle>