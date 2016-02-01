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
            <c:choose>
                <c:when test="${empty user}">
                    <t:login/>
                </c:when>
                <c:when test="${not empty user && user.role.name == 'Client'}">
                    <t:client_navigation/>
                </c:when>
                <c:otherwise>
                    <c:redirect url="/controller?action=DisplayPage&location=index"/>
                </c:otherwise>
            </c:choose>
            <div class="col-md-4 content" style="text-align: left;">
                <form action="${pageContext.request.contextPath}/controller" method="post">
                    <c:if test="${not empty user}">
                        <c:set value="${user.name}" var="name"/>
                        <c:set value="${user.iin}" var="iin"/>
                        <c:set value="disabled" var="disabled"/>
                        <input type="hidden" name="name" value="${name}">
                        <input type="hidden" name="iin" value="${iin}">
                    </c:if>
                    <input type="hidden" name="action" value="IssueCardAction">

                    <div><span class="white-text"><fmt:message key="text.content.name"/></span></div>
                    <div>
                        <input class="blue-input" type="text" name="name" value="${name}"
                               style="width: 250px;" ${disabled}>
                    </div>
                    <c:if test="${not empty nameError}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="${nameError}"/>
                        </div>
                    </c:if>
                    <div><span class="white-text"><fmt:message key="text.content.iin"/></span></div>
                    <div><input class="blue-input" type="text" name="iin" value="${iin}"
                                style="width: 250px;" ${disabled}></div>

                    <c:if test="${not empty iinError}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="${iinError}"/>
                        </div>
                    </c:if>

                    <div><span class="white-text"><fmt:message key="text.content.telephone.number"/></span></div>
                    <div>
                        <span class="white-h1">+7</span>
                        <input class="blue-input" type="text" name="phone" value="${phone}" style="width: 227px;">
                    </div>

                    <c:if test="${not empty phoneError}">
                        <div class="alert alert-danger" role="alert">
                            <fmt:message key="${phoneError}"/>
                        </div>
                    </c:if>

                    <div><span class="white-text"><fmt:message key="text.content.choose.package.offer"/></span></div>
                    <div>
                        <select class="blue-input" name="card_type" style="width: 250px;">
                            <c:forEach items="${typesPaymentCards}" var="typePaymentCard">
                                <c:choose>
                                    <c:when test="${(cardType != null) && (cardType == typePaymentCard.id)}">
                                        <option selected="selected"
                                                value="${typePaymentCard.id}">${typePaymentCard.name}</option>
                                    </c:when>
                                    <c:otherwise>
                                             <option value="${typePaymentCard.id}">${typePaymentCard.name}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <button class="blue-button" type="submit" style="width: 250px;"><fmt:message
                                key="button.text.send"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    </body>
    </html>
</fmt:bundle>
