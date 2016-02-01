<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag pageEncoding="UTF-8" %>

<fmt:bundle basename="text">
    <div class="col-md-1 navigation login">
        <div>
            <div>
                <a href="${pageContext.request.contextPath}/controller?action=DisplayPage&location=index">
                    <button class="blue-button">
                        <fmt:message key="button.text.index"/>
                    </button>
                </a>
            </div>
        </div>
        <hr>
        <div>
            <h1 class="white-h1">
                <fmt:message key="text.login"/>
            </h1>
        </div>
        <div>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <input type="hidden" name="action" value="LoginAction">

                <div>
                    <span class="white-text">
                        <fmt:message key="input.identifier"/>
                    </span>
                    <input class="blue-input" type="text" name="identifier" value="${identifier}" maxlength="12">
                </div>
                <c:if test="${not empty identifierError}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="${identifierError}"/>
                    </div>
                </c:if>
                <div>
                    <span class="white-text">
                        <fmt:message key="input.password"/>
                    </span>
                    <input class="blue-input" type="password" name="password">
                </div>
                <c:if test="${not empty passwordError}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="${passwordError}"/>
                    </div>
                </c:if>
                <div>
                    <button class="blue-button" type="submit">
                        <fmt:message key="button.text.enter"/>
                    </button>
                </div>
                <c:if test="${not empty error}">
                    <div class="alert alert-danger" role="alert">
                        <fmt:message key="${error}"/>
                    </div>
                </c:if>
            </form>
        </div>
        <hr>
        <div>
            <a href="${pageContext.request.contextPath}/controller?action=DisplayPage&location=restoration_password_or_identifier">
                <button class="blue-button">
                    <fmt:message key="button.text.forgot.password.or.identifier"/>
                </button>
            </a>
        </div>
        <div>
            <a href="${pageContext.request.contextPath}/controller?action=DisplayPage&location=registration">
                <button class="blue-button">
                    <fmt:message key="button.text.registration"/>
                </button>
            </a>
        </div>
    </div>
</fmt:bundle>