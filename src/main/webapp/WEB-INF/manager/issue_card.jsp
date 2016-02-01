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
                <div style="text-align: left;">
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="action" value="ManagerIssueCardAction">
                        <span class="white-text" style="font-size: 18px;">ИИН:</span>
                        <input type="text" name="iin" class="blue-input" style="width: 150px;" maxlength="12">
                        <c:if test="${not empty iinError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${iinError}"/>
                            </div>
                        </c:if>
                        <button type="submit" class="blue-button" style="width: 200px;">Найти</button>
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
