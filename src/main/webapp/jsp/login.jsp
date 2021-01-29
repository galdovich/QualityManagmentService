<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">

    <title><fmt:message key="title.login"/></title>
</head>
<body>

<div class="container">
    <jsp:include page="${pageContext.request.contextPath}/jsp/part/header.jsp"/>
    <div class="row-login justify-content-center">
        <div class="col-md-8">

            <form class="login-form" name="LoginForm" method="POST" action="controller">
                <input type="hidden" name="command" value="login"/>

                <div class="form-group row">
                    <label for="login" class="col-md-4 col-form-label text-md-right"><fmt:message key="login.login"/></label>
                    <div class="col-md-6">
                        <input type="text" id="login" class="form-control" name="login" required
                               autofocus placeholder="<fmt:message key="login.login.placeholder"/>">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="password" class="col-md-4 col-form-label text-md-right"><fmt:message key="login.password"/></label>
                    <div class="col-md-6">
                        <input type="password" id="password" class="form-control" name="password" required
                               placeholder="<fmt:message key="login.password.placeholder"/>"><br>
                        <c:if test="${errorLoginPassMessage == true}">
                            <div class="text-danger">
                                <fmt:message key="message.login_error"/>
                            </div>
                        </c:if>
                        <hr>
                    </div>
                </div>

                <div class="form-group row">
                    <div class="col-md-6 offset-md-4">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox" name="remember"> <fmt:message key="login.remember_me"/>
                            </label>
                        </div>
                    </div>
                </div>

                <div class="col-md-6 offset-md-4">
                    <button type="submit" class="btn btn-primary">
                        <fmt:message key="login.button"/>
                    </button>
                    <a href="#" class="btn btn-link">
                        <fmt:message key="login.forgot.password"/>
                    </a>
                </div>
             </form>

        </div>
    </div>
    <jsp:include page="${pageContext.request.contextPath}/jsp/part/footer.jsp"/>
</div>
</body>
</html>