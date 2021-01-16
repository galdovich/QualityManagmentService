<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}css/custom.css">

    <title><fmt:message key="title.main"/></title>
</head>
<body>
<div class="container">
    <jsp:include page="${pageContext.request.contextPath}/jsp/part/header.jsp"/>
    <div class="row-main">
        <table class="table table-striped">
            <thread>
                <tr>
                    <th><fmt:message key="main.customers"/></th>
                    <th><fmt:message key="main.phone"/></th>
                    <th><fmt:message key="main.email"/></th>
                </tr>
            </thread>
            <tbody>
            <c:forEach items="${customer_list}" var="customer">
                <tr>
                    <td>${customer.name}</td>
                    <td>${customer.phone}</td>
                    <td>${customer.email}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>

        <table class="table table-striped">
            <thread>
                <tr>
                    <th><fmt:message key="main.user_amount"/> ${userAmount}</th>
                </tr>
            </thread>
        </table>
    </div>
    <jsp:include page="${pageContext.request.contextPath}/jsp/part/footer.jsp"/>
</div>
</body>
</html>