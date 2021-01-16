<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>

<!-- Footer -->
<footer class="page-footer border-top shadow-sm">

    <div class="row text-center">
        <div class="col-lg-6 col-md-6 col-sm-6">
            <fmt:message key="footer.text"/>
        </div>

        <div class="col-lg-6 col-md-6 col-sm-6">
            <a href="controller?command=locale&locale=ru_ru"><fmt:message key="locale.ru"/></a>
            |
            <a href="controller?command=locale&locale=en_us"><fmt:message key="locale.en"/></a>
            |
            <a href="todo"><fmt:message key="locale.de"/></a>
        </div>
    </div>

</footer>