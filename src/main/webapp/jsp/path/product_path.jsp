<%@ page contentType="text/html;charset=UTF-8"%>
<html>

<body>
<jsp:forward page="${pageContext.request.contextPath}/controller?command=product&product_id=${product_id}"/>
</body>

</html>

