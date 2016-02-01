<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag pageEncoding="UTF-8" %>

<fmt:bundle basename="text">
    <div class="col-md-1 navigation login">
        <div>
            <h1 class="white-h1"><fmt:message key="text.content.navigation"/></h1>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/controller?action=DisplayPage&location=index">
                <button class="blue-button"><fmt:message key="button.text.home"/></button>
            </a>

        </div>

        <hr>

        <div>
            <a href="${pageContext.request.contextPath}/controller?action=IssueCardDisplay">
                <button class="blue-button"><fmt:message key="button.text.issue.card"/></button>
            </a>
        </div>

        <hr>

        <div>
            <a href="${pageContext.request.contextPath}/controller?action=LogoutAction">
                <button class="blue-button" type="submit"><fmt:message key="button.text.exit"/></button>
            </a>
        </div>
    </div>
</fmt:bundle>