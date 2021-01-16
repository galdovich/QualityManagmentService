<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}css/custom.css">

    <title><fmt:message key="client_menu.title"/></title>
</head>
<body>

<div class="container">
    <jsp:include page="${pageContext.request.contextPath}/jsp/part/header.jsp"/>
    <div class="row-login justify-content-center">
        <div class="col-md-6">

            <div class="form-group row mb-0">
                <form class="form-finding" name="clientMainPage" method="POST" action="controller"/>
                    <input type="hidden" name="command" value="client_product"/>
                    <input type="text" id="product_decimal" name="product_decimal" class="form-control mb-3"
                           placeholder="<fmt:message key="client.main"/>" required=""/>

                    <div class="text-danger text-center mt-2">
                        <c:if test="${productErrorMessage == true}">
                            <fmt:message key="product.error.message"/>
                        </c:if>
                    </div>
                    <hr>
            </div>

            <div class="form-group row justify-content-center">

                    <button class="btn btn-primary" style="width: 130px" type="submit"><fmt:message
                            key="client.button.search"/></button>

            </div>
        </div>

    </div>

    <jsp:include page="${pageContext.request.contextPath}/jsp/part/footer.jsp"/>
</div>
</body>
</html>
