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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/custom.css">

    <title><fmt:message key="product.title"/></title>
</head>
<body>
<div class="container">
    <jsp:include page="${pageContext.request.contextPath}/jsp/part/header.jsp"/>
    <div class="main-body">
        <nav aria-label="breadcrumb" class="main-breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="controller?command=admin_menu">Admin menu</a></li>
                <li class="breadcrumb-item active" aria-current="product">Product</li>
            </ol>
        </nav>

        <div class="row gutters-sm">
            <div class="col-md-5">
                <div class="card card card-bordered">
                    <div class="card-header text-center p-1" style="height: 35px">
                        <h5>Информация</h5>
                    </div>
                    <!-- product form -->
                    <form class="profile-form" name="productForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="edit_product"/>

                        <div class="card-body p-0 pr-2 pl-2">
                            <div class="row mt-2">
                                <div class="col-sm-5 pr-0 text-center">
                                    <h6 class="mb-0">Изделие</h6>
                                </div>
                                <div class="col-sm-7 pl-0 text-center" style="white-space: nowrap">
                                    <input class="form-control text-center" type="text" name="product_name"
                                           value="${product_name}" readonly>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-5 pr-0 text-center">
                                    <h6 class="mb-0">Заказчик</h6>
                                </div>
                                <div class="col-sm-7 pl-0 text-center">
                                    <c:if test="${user_role == 'WORKER'}">
                                        <input class="form-control text-center" type="text" name="customer_name"
                                               value="${customer_name}" readonly>
                                    </c:if>
                                    <c:if test="${user_role == 'ADMIN'}">
                                        <select name="customer_name" class="custom-select"
                                                style="text-align-last: center"
                                                onChange="MkOrderValues(this.selectedIndex)">
                                            <c:forEach var="customer" items="${customer_list}">
                                                <option style="text-align: left" <c:if
                                                        test="${customer_name == customer.name}"> selected </c:if>>
                                                    <c:out value="${customer.name}"/>
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-5 pr-0 text-center">
                                    <h6 class="mb-0">Заказ</h6>
                                </div>
                                <div class="col-sm-7 pl-0 text-center">
                                    <c:if test="${user_role == 'WORKER'}">
                                        <input class="form-control text-center" type="text" name="order_name"
                                               value="${order_name}" readonly>
                                    </c:if>
                                    <c:if test="${user_role == 'ADMIN'}">
                                        <select name="order_name" class="custom-select" style="text-align-last: center"
                                                array="priorityList">
                                            <c:forEach var="order" items="${orders_list}">
                                                <option style="text-align: left" <c:if
                                                        test="${order_name == order.name}"> selected </c:if>>
                                                    <c:out value="${order.name}"/>
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </c:if>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-5 pr-0 text-center">
                                    <h6 class="mb-0">Состояние</h6>
                                </div>
                                <div class="col-sm-7 pl-0">
                                    <c:if test="${state == true}">
                                        <div class="form-control text-center alert-success border border-success">
                                            Выполнено!
                                        </div>
                                    </c:if>
                                    <c:if test="${empty state}">
                                        <div class="form-control text-center alert-warning border border-warning">
                                            Выполнение!
                                        </div>
                                    </c:if>

                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-5 pr-0 text-center">
                                    <h6 class="mb-0">Модель</h6>
                                </div>
                                <div class="col-sm-7 pl-0">
                                    <div class="form-control text-center">
                                        <a href="${pageContext.request.contextPath}/uploads/model/${product_model_link}">${product_model_link}</a>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-5 pr-0 text-center">
                                    <h6 class="mb-0">Дата отправки</h6>
                                </div>
                                <div class="col-sm-7 pl-0">
                                    <input class="form-control text-center" type="date" name="product_shipped_date"
                                           value="${product_shipped_date}">
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-5 pr-0 text-center">
                                    <h6 class="mb-0">Количество</h6>
                                </div>
                                <div class="col-sm-7 pl-0">
                                    <c:if test="${user_role == 'ADMIN'}">
                                        <input class="form-control text-center" type="text" name="product_amount"
                                               value="${product_amount}">
                                    </c:if>
                                    <c:if test="${user_role == 'WORKER'}">
                                        <input class="form-control text-center" type="text" name="product_amount"
                                               value="${product_amount}" readonly>
                                    </c:if>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-5 pr-0 text-center">
                                    <h6 class="mb-0">Статус</h6>
                                </div>
                                <div class="col-sm-7 pl-0">
                                    <c:if test="${user_role == 'WORKER'}">
                                        <input class="form-control text-center" type="text" name="product_priority"
                                               value="${product_priority}" readonly>
                                    </c:if>
                                    <c:if test="${user_role == 'ADMIN'}">
                                        <select name="product_priority" class="custom-select" style="text-align-last: center"
                                                array="priorityList">
                                            <option
                                                    <c:if test="${product_priority == 'A'}"> selected </c:if> >
                                                A
                                            </option>
                                            <option <c:if test="${product_priority == 'B'}"> selected </c:if>>
                                                B
                                            </option>
                                            <option <c:if test="${product_priority == 'C'}"> selected </c:if>>
                                                C
                                            </option>
                                        </select>
                                    </c:if>
                                </div>
                            </div>
                            <div class="row">
                                <c:if test="${success_adding == true}">
                                    <div class="col-md-12 text-center text-success mt-2">
                                        <fmt:message key="product.valid_message"/>
                                    </div>
                                </c:if>

                                <c:if test="${fail_adding == true}">
                                    <div class="col-md-12 text-center text-danger mt-2">
                                        <fmt:message key="product.invalid_message"/>
                                    </div>
                                </c:if>

                                <c:if test="${same_values == true}">
                                    <div class="col-md-12 text-center text-danger mt-2">
                                        <fmt:message key="product.same_values_message"/>
                                    </div>
                                </c:if>
                                <div class="col-sm-12 pr-0 mt-3 mb-3 text-center">
                                    <c:if test="${user_role == 'ADMIN'}">
                                        <button type="submit" class="btn btn-sm btn-dark">Обновить</button>
                                    </c:if>
                                </div>
                            </div>
                        </div>

                    </form>

                </div>

                <!-- icon upload -->
                <div class="card mt-2 ">

                    <div class="card-body pt-1">
                        <div class="d-flex flex-column align-items-center text-center">
                            <div>
                                <h6>Изображение</h6>
                            </div>
                            <hr>

                            <img src="${pageContext.request.contextPath}/uploads/icon/${product_model_icon}" alt="Admin"
                                 class="rounded" width="250">

                            <c:if test="${user_role == 'ADMIN'}">
                                <div class="row mt-3">
                                    <form action="upload" enctype="multipart/form-data" method="POST">
                                        <input type="hidden" name="upload_type" value="icon"/>
                                        <div class="input-group">
                                            <div class="custom-file">
                                                <input type="file" class="custom-file-input" name="file"
                                                       id="imageURL1"
                                                       aria-describedby="inputGroupFileAddon01">
                                                <label class="custom-file-label text-left" for="imageURL1">Выберете
                                                    изображение</label>
                                            </div>
                                            <div class="input-group-append">
                                                <button class="btn btn-outline-secondary" name="icon-submit" type="submit">
                                                    UPLOAD
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </c:if>

                            <c:if test="${user_role == 'ADMIN'}">
                                <div>
                                    <h6>Модель</h6>
                                </div>
                                <div class="row mt-3">
                                    <form action="upload" enctype="multipart/form-data" method="POST">
                                        <input type="hidden" name="upload_type" value="model"/>
                                        <div class="input-group">
                                            <div class="custom-file">
                                                <input type="file" class="custom-file-input" name="file" id="imageURL2"
                                                       aria-describedby="inputGroupFileAddon01">
                                                <label class="custom-file-label text-left" for="imageURL2">Выберете
                                                    модель</label>
                                            </div>
                                            <div class="input-group-append">
                                                <button class="btn btn-outline-secondary" name="model-submit" type="submit">
                                                    UPLOAD
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>

            </div>

            <div class="col-md-7">
                <div class="card card card-bordered mb-3">
                    <form class="profile-form" name="ProfileForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="update_route_map"/>

                        <div class="card-header">
                            <div class="row">
                                <div class="col-sm-4"></div>
                                <div class="col-sm-5 text-center">
                                    <h5>Маршрутная карта</h5>
                                </div>
                                <div class="col-sm-3 text-right">
                                    <a href="#">
                                        <img src="${pageContext.request.contextPath}/jpg/icons/printer-fill.svg">
                                    </a>
                                </div>
                            </div>
                        </div>

                        <div class="card-body">
                            <c:if test="${not empty production_map}">
                                <table data-toggle="table" class="table" style="width: 100%">
                                    <thead>
                                    <tr>
                                        <th class="text-center">№</th>
                                        <th class="text-center">Операция</th>
                                        <th class="text-center">Фамилия</th>
                                        <th class="text-center">Дата</th>
                                        <th class="text-center">Статус</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach items="${production_map}" var="production_map">
                                        <tr>
                                            <th scope="row" class="text-center px-0"
                                                width="20px">${production_map.key.queue}</th>
                                            <td class="text-center px-1">${production_map.key.process}</td>
                                            <c:if test="${production_map.value.user.name != null}">
                                                <td class="text-center px-1">${production_map.value.user.name}</td>
                                                <td class="text-center px-1"
                                                    width="100px">${production_map.value.date}</td>
                                                <td class="text-center px-1"><img
                                                        src="${pageContext.request.contextPath}/jpg/icons/check-square.svg"></td>
                                            </c:if>
                                            <c:if test="${production_map.value.user.name == null}">
                                                <td class="text-center px-1">
                                                    <div class="input-group input-group-sm">
                                                        <input type="text" class="form-control text-center"
                                                               placeholder="Сканируйте пропуск" name="user_name">
                                                    </div>
                                                </td>
                                                <td class="text-center px-1">${production_map.value.date}</td>
                                                <td></td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>

                            <c:if test="${empty production_map}">
                                <div class="col-md-12 text-center mt-5 mb-5">
                                    <h6>Маршрутная карта ещё не создана!</h6>
                                </div>
                                <div class="col-md-12 text-center">
                                    <button type="submit" href="#">Создать</button>
                                </div>
                            </c:if>
                            <c:if test="${not empty production_map}">
                                <div class="row justify-content-center">
                                    <div class="col-md-12 text-center">

                                        <a class="text-success">${validMessage}</a>

                                        <a class="text-danger">${invalidMessage}</a>

                                    </div>
                                    <hr>
                                    <div class="col-md-5 text-center">
                                        <button type="submit" class="btn btn-sm btn-dark">Обновить</button>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                    </form>
                </div>

                <div class="chat mt-1">
                    <div class="card card-bordered">
                        <div class="card-header">
                            <h4 class="card-title">Chat</h4>
                        </div>

                        <div class="card-body">
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <jsp:include page="${pageContext.request.contextPath}/jsp/part/footer.jsp"/>
    </div>
</div>

<script>
    var aHouseValues = new Array(
        "ЛД-294",
        "22",
        "181, 182"
    );

    function getOrderValuesByCustomer(index) {
        var sOrderValues = aHouseValues[index];
        return sOrderValues.split(","); // преобразуем строку в массив домов
    }

    function MkOrderValues(index) {
        var aCurrOrderValues = getOrderValuesByCustomer(index);
        var nCurrOrderValuesCnt = aCurrOrderValues.length;
        var oOrderList = document.forms["productForm"].elements["order_name"];
        var oOrderListOptionsCnt = oOrderList.options.length;
        oOrderList.length = 0; // удаляем все элементы из списка домов
        for (i = 0; i < nCurrOrderValuesCnt; i++) {
            // далее мы добавляем необходимые дома в список
            if (document.createElement) {
                var newHouseListOption = document.createElement("OPTION");
                newHouseListOption.text = aCurrOrderValues[i];
                newHouseListOption.value = aCurrOrderValues[i];
                // тут мы используем для добавления элемента либо метод IE, либо DOM, которые, alas, не совпадают по параметрам…
                (oOrderList.options.add) ? oOrderList.options.add(newHouseListOption) : oOrderList.add(newHouseListOption, null);
            } else {
                // для NN3.x-4.x
                oOrderList.options[i] = new Option(aCurrOrderValues[i], aCurrOrderValues[i], false, false);
            }
        }
    }

    MkOrderValues(document.forms["productForm"].elements["customer_name"].selectedIndex);
</script>

<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/tether.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/hidden_buttons.js"></script>
<script src="${pageContext.request.contextPath}/js/button_block.js"></script>

</body>
</html>
