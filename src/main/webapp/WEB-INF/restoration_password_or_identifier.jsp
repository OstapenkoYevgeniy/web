<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://www.john.com/filler_select" prefix="fs" %>

<fmt:bundle basename="text">
    <!DOCTYPE html>
    <html lang="ru">
    <t:head/>
    <body>
    <div class="container">
        <div class="row">
            <t:login/>
            <div class="col-md-4 content" style="min-height: 500px;">
                <div style="text-align: left;">
                    <c:if test="${not empty remindError}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="${remindError}"/>
                        </div>
                    </c:if>
                    <form action="${pageContext.request.contextPath}/controller" method="post">
                        <input type="hidden" name="action" value="RemindIdentifierAction">

                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.card.number"/>
                            </span>
                        </div>
                        <input type="text" class="blue-input" class="blue-input" name="card_number"
                               style="width: 200px;" value="${cardNumber}" maxlength="16">
                        <c:if test="${not empty cardNumberError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${cardNumberError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.codeword"/>
                            </span>
                        </div>
                        <input type="password" class="blue-input" name="codeword" style="width: 200px;">
                        <c:if test="${not empty codewordError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${codewordError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.card.expiry.date"/> (MMYY)
                            </span>
                        </div>
                        <input type="text" class="blue-input" value="${cardExpiryDate}" name="card_expiry_date"
                               style="width: 200px;" maxlength="4">
                        <c:if test="${not empty cardExpiryDateError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${cardExpiryDateError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.iin"/>
                            </span>
                        </div>
                        <input type="text" class="blue-input" name="iin" value="${iin}" style="width: 200px;"
                               maxlength="12">
                        <c:if test="${not empty iinError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${iinError}"/>
                            </div>
                        </c:if>
                        <hr>
                        <div>
                            <button type="submit" class="blue-button" name="remind_identifier" style="width: 350px;">
                                <fmt:message key="button.text.remind.identifier"/>
                            </button>
                        </div>
                        <hr>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.password"/>
                            </span>
                        </div>
                        <input type="password" class="blue-input" name="password" style="width: 200px;">

                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.retry.password"/>
                            </span>
                        </div>
                        <input type="password" class="blue-input" name="retry_password" style="width: 200px;">
                        <div>
                            <button type="submit" class="blue-button" name="change_password" style="width: 350px;">
                                <fmt:message key="button.text.change.password"/>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    </body>
    </html>

</fmt:bundle></title>
</head>
<body>

</body>
</html>
