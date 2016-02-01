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
                        <input type="hidden" name="action" value="BlockCardAction">
                        <input type="hidden" name="block" value="false">

                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.iin"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" maxlength="12" class="blue-input" name="iin" value="${iin}" style="width: 200px;">
                        </div>
                        <c:if test="${not empty iinError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${iinError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.name"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" name="name" style="width: 200px;" value="${name}">
                        </div>
                        <c:if test="${not empty nameError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${nameError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.surname"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" name="surname" style="width: 200px;" value="${surname}">
                        </div>
                        <c:if test="${not empty surnameError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${surnameError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.patronymic"/>
                            </span>
                        </div>
                        <div>
                            <input type="text" class="blue-input" name="patronymic" style="width: 200px;" value="${patronymic}">
                        </div>
                        <c:if test="${not empty patronymicError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${patronymicError}"/>
                            </div>
                        </c:if>
                        <div>
                            <span class="white-text" style="font-size: 18px;">
                                <fmt:message key="text.content.card.number"/>
                            </span>
                        </div>
                        <div>
                            <span class="white-text" style="font-size: 20px;">
                                <fmt:message key="text.content.all.unblock"/>
                            </span>
                        </div>
                        <div>
                            <input maxlength="16" type="text" class="blue-input" name="card_number" style="width: 200px;" value="${cardNumber}">
                        </div>
                        <c:if test="${not empty cardNumberError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${cardNumberError}"/>
                            </div>
                        </c:if>
                        <div>
                            <button type="submit" class="blue-button" style="width: 200px;">
                                <fmt:message key="button.text.unblock"/>
                            </button>
                        </div>
                        <c:if test="${not empty blockError}">
                            <div class="alert alert-danger" role="alert">
                                <fmt:message key="${blockError}"/>
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

