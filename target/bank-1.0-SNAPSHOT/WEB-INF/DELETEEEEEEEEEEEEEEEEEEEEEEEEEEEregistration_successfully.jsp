<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<fmt:bundle basename="text">
    <c:choose>
        <c:when test="${user.role.name == 'Client'}">
            <c:redirect url="/controller?action=ClientHomeDisplay"/>
        </c:when>
        <c:when test="${user.role.name == 'Manager'}">
            <c:redirect url="/controller?action=ManagerHomeDisplay"/>
        </c:when>
    </c:choose>

    <!DOCTYPE html>
    <html lang="ru">
    <t:head/>
    <body>
    <div class="container">
        <div class="row">
                    <t:login/>
            <div class="col-md-4 content" style="height: 500px;">
                <div>
                    ${user}
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>

</fmt:bundle>

${dateError}