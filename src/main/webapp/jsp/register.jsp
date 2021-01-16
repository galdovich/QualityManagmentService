<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}css/custom.css">

    <title><fmt:message key="title.register"/></title>
</head>
<body>

<div class="container">
    <jsp:include page="${pageContext.request.contextPath}/jsp/part/header.jsp"/>

    <form class="main-form needs-validation" name="sinUpForm" method="POST" action="controller">
        <input type="hidden" name="command" value="register"/>

        <div class="form-group row text-right offset-md-2">
            <label class="label col-sm-3 col-form-label" for="name"><fmt:message key="register.name"/></label>
            <div class="col-sm-5">
                <input type="text" name="name" id="name" class="form-control" value="${register_data['name']}" required
                       minlength="5"
                       <c:if test="${register_data['name'] != ''}"> placeholder="<fmt:message key="register.name.placeholder"/>"
                </c:if>>
                <div class="text-danger text-center">
                    <c:if test="${register_data['name'] == ''}">
                        <fmt:message key="register.name.invalid_message"/>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="form-group row text-right offset-md-2">
            <label class="label col-sm-3 col-form-label" for="login"><fmt:message key="register.login"/></label>
            <div class="col-sm-5"><input type="login" name="login" id="login" value="${register_data['login']}"
                                         class="form-control" required minlength="4"
                                         maxlength="16"
                                         <c:if test="${register_data['login'] != ''}">placeholder="<fmt:message key="register.login.placeholder"/>"
            </c:if>>
                <div class="text-danger text-center">
                    <c:if test="${register_data['login'] == '' && empty register_data['same_login_message']}">
                        <fmt:message key="register.login.invalid_message"/>
                    </c:if>
                </div>
                <div class="text-danger text-center">
                    <c:if test="${not empty register_data['same_login_message']}">
                        ${register_data['same_login_message']}
                    </c:if>
                </div>
            </div>
        </div>

        <div class="form-group row text-right offset-md-2">
            <label class="label col-sm-3 col-form-label" for="password"><fmt:message key="register.password"/></label>
            <div class="col-sm-5">
                <input type="password" name="password" id="password" class="form-control" required minlength="6"
                       maxlength="18"
                       <c:if test="${register_data['password'] != ''}">placeholder="<fmt:message key="register.password.placeholder"/>"
                </c:if>>
                <div class="text-danger text-center">
                    <c:if test="${register_data['password'] == ''}">
                        <fmt:message key="register.password.invalid_message"/>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="form-group row text-right offset-md-2">
            <label class="label col-sm-3 col-form-label" for="password_again"><fmt:message
                    key="register.password_again"/></label>
            <div class="col-sm-5">
                <input type="password" name="password_again" id="password_again" class="form-control" required
                       minlength="4"
                       <c:if test="${register_data['password_again'] != ''}"> placeholder="<fmt:message key="register.password_again.placeholder"/>"
                </c:if>>
            </div>
        </div>

        <div class="form-group row text-right offset-md-2">
            <label class="label col-sm-3 col-form-label" for="email"><fmt:message key="register.email"/></label>
            <div class="col-sm-5">
                <input type="email" name="email" id="email" value="${register_data['email']}" class="form-control"
                       required minlength="1"
                <c:if test="${register_data['email'] != ''}">placeholder="<fmt:message key="register.email.placeholder"/>" </c:if>>
                <div class="text-danger text-center">
                    <c:if test="${register_data['email'] == ''}">
                        <fmt:message key="register.email.invalid_message"/>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="form-group row text-right offset-md-2">
            <label class="label col-sm-3 col-form-label" for="department"><fmt:message
                    key="register.department"/></label>
            <div class="col-sm-5">
                <select name="department" id="department" class="form-control" required>
                    <option value=""></option>
                    <option>ИТР</option>
                    <option>ЦЕХ, ЧПУ</option>
                    <option>Отдел технического контроля</option>
                    <option>Упаковка</option>
                    <option>Лазерная резка</option>
                    <option>Гидроабразивная резка</option>
                    <option>Слесарный участок</option>
                    <option>Сварочный участок</option>
                    <option>Гибка</option>
                </select>
                <div class="valid-feedback">${errorRegisterPassMessage}</div>
                <div class="invalid-feedback">${errorRegisterPassMessage}</div>
            </div>
        </div>


        <div class="form-button row justify-content-center">
            <button type="submit" class="btn btn-outline-primary"><fmt:message key="register.button"/></button>
        </div>

        <c:if test="${user_success_flag == true}">
            <div class="text-success text-center">
                    <fmt:message key="register.valid_message"/>
            </div>
        </c:if>
        <c:if test="${user_fail_flag == true}">
            <div class="text-danger text-center">
                <fmt:message key="register.invalid_message"/>
            </div>
        </c:if>
        <c:if test="${user_same_login == true}">
            <div class="text-danger text-center">
                <fmt:message key="register.same_login"/>
            </div>
        </c:if>

    </form>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <jsp:include page="${pageContext.request.contextPath}/jsp/part/footer.jsp"/>
</div>

<script src="${pageContext.request.contextPath}/js/button_block.js"></script>
</body>
</html>
