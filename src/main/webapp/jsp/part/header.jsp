<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>

<html>
<head>

</head>
<body>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <h5 class="col-md-2 font-weight-normal">
        <a href="controller?command=welcome">
            <img src="${pageContext.request.contextPath}/jpg/icons/logo_main.jpg" alt="" width="85" height="60"
                 title="Bootstrap">
        </a>
    </h5>
    <nav class="col-md-7">
        <c:if test="${user_role == 'ADMIN'}">
            <a class="p-2 text-dark" href="controller?command=admin_menu">ADMIN MENU</a>
        </c:if>
        <c:if test="${user_role == 'WORKER'}">
            <a class="p-2 text-dark" href="controller?command=client_page">WORKER MENU</a>
        </c:if>
        <a class="p-2 text-dark" href="#"><fmt:message key="header.about_us"/></a>
        <a class="p-2 text-dark" href="#"><fmt:message key="header.feedback"/></a>
    </nav>

    <c:if test="${isAuthorized != true}">
        <div class="col-md-1">
            <a href="controller?command=login_page" class="btn btn-success" role="button" aria-pressed="true">
                <fmt:message key="header.button_enter"/></a>
        </div>
        <div class="col-md-3">
            <a href="controller?command=register_page" class="btn btn-primary" role="button" aria-pressed="true">
                <fmt:message key="header.button_register"/></a>
        </div>
    </c:if>
    <c:if test="${isAuthorized == true}">
        <div class="row">
            <div class="col-md-10 text-center">
                <a href="controller?command=profile">${user_name}</a>
            </div>
            <div class="col-md-1">
                <a href="controller?command=logout">
                    <img src="${pageContext.request.contextPath}/jpg/icons/arrow-left-square.svg" alt="" width="32"
                         height="32" title="Bootstrap">
                </a>
            </div>
        </div>
    </c:if>

</div>
</body>
</html>