<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="customtags" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="text"/>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}css/custom.css">

    <title><fmt:message key="admin_menu.head"/></title>
</head>
<body>
<div class="container">
    <jsp:include page="${pageContext.request.contextPath}/jsp/part/header.jsp"/>
    <div class="main-body">
        <nav aria-label="breadcrumb" class="main-breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="controller?command=admin_menu">Admin menu</a></li>
            </ol>
        </nav>

        <div class="row gutters-sm">

            <div class="col-md-12">
                <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                    <li class="nav-item" role="presentation">
                        <a class="nav-link active" id="pills-home-tab" data-toggle="pill" href="#pills-home" role="tab"
                           aria-controls="pills-home" aria-selected="true"><fmt:message
                                key="admin_menu.button.products"/></a>
                    </li>
                    <li class="nav-item" role="presentation">
                        <a class="nav-link" id="pills-profile-tab" data-toggle="pill" href="#pills-profile" role="tab"
                           aria-controls="pills-profile" aria-selected="false"><fmt:message
                                key="admin_menu.button.customers"/></a>
                    </li>
                    <li class="nav-item mr-3" role="presentation">
                        <a class="nav-link" id="pills-orders-tab" data-toggle="pill" href="#pills-profile2" role="tab"
                           aria-controls="pills-contact" aria-selected="false"><fmt:message
                                key="admin_menu.button.orders"/></a>
                    </li>
                    <li class="nav-item mr-3" role="presentation">
                        <a class="nav-link" id="pills-contact-tab" data-toggle="pill" href="#pills-contact" role="tab"
                           aria-controls="pills-contact" aria-selected="false"><fmt:message
                                key="admin_menu.button.users"/></a>
                    </li>
                    <div style="border-left: 1px solid gray;"></div>
                    <form class="form-inline my-2 my-lg-0 ml-auto">
                        <input class="form-control" type="search" placeholder="Search"
                               aria-label="<fmt:message key="admin_menu.button.search"/>">
                        <button class="btn btn-outline-white btn btn-primary my-2 my-sm-0 ml-3" type="submit">
                            <fmt:message key="admin_menu.button.search"/>
                        </button>
                    </form>
                </ul>
                <div class="tab-content" id="pills-tabContent">
                    <!-- Products -->
                    <div class="tab-pane fade show active" id="pills-home" role="tabpanel"
                         aria-labelledby="pills-home-tab">
                        <div class="card card card-bordered mb-3">

                            <div class="card-header">
                                <h5 class="text-center"><fmt:message key="admin_menu.header"/></h5>
                            </div>

                            <div class="card-body">
                                <div id="accordion">
                                    <div class="card">
                                        <div class="card-header px-0 pb-0 pt-0" id="headingThree">
                                            <h5 class="mb-0 mt-0">
                                                <button class="btn btn-link collapsed" data-toggle="collapse"
                                                        data-target="#collapseThree" aria-expanded="false"
                                                        aria-controls="collapseThree">
                                                    <fmt:message key="admin_menu.button.add"/>
                                                </button>
                                            </h5>
                                        </div>

                                        <div id="collapseThree"
                                             class="<c:if test="${add_flag == 'true'}">collapse show</c:if><c:if test="${empty add_flag}">collapse</c:if>"
                                             aria-labelledby="headingThree" data-parent="#accordion">
                                            <form name="productForm" method="POST" action="controller">
                                                <input type="hidden" name="command" value="add_product"/>

                                                <table class="table table-hover table-bordered table-sm table_sort table-hover"
                                                       cellspacing="0"
                                                       width="100%">
                                                    <thead>
                                                    <tr>
                                                        <th class="text-center" width="20px"><fmt:message
                                                                key="admin_menu.table.name"/></th>
                                                        <th class="text-center"><fmt:message
                                                                key="admin_menu.table.decimal"/></th>
                                                        <th class="text-center"><fmt:message
                                                                key="admin_menu.table.customer"/></th>
                                                        </th>
                                                        <th class="text-center"><fmt:message
                                                                key="admin_menu.table.order"/></th>
                                                        </th>
                                                        <th class="text-center"><fmt:message
                                                                key="admin_menu.table.amount"/></th>
                                                        </th>
                                                        <th class="text-center"><fmt:message
                                                                key="admin_menu.table.priority"/></th>
                                                        </th>
                                                        <th class="text-center"><fmt:message
                                                                key="admin_menu.table.manage"/></th>
                                                    </tr>
                                                    </thead>

                                                    <tbody>

                                                    <tr class="table-warning">
                                                        <div class="col-xs-6">
                                                            <td class="text-center">
                                                                <input type="text" name="product_name"
                                                                       class="form-control text-center" required
                                                                       minlength="3"
                                                                       maxlength="20"
                                                                       value="${dataMap['product_name']}">
                                                                <div class="text-danger text-center">
                                                                    <c:if test="${dataMap['product_name'] == ''}">
                                                                        <fmt:message key="product.message.incorrect"/>
                                                                    </c:if>
                                                                </div>
                                                            </td>
                                                            <td class="text-center">
                                                                <input type="text" name="product_decimal_number"
                                                                       required minlength="3"
                                                                       maxlength="30"
                                                                       class="form-control text-center"
                                                                       value="${dataMap['product_decimal']}">
                                                                <div class="text-danger text-center">
                                                                    <c:if test="${datamap['product_decimal'] == ''}">
                                                                        <fmt:message key="product.message.incorrect"/>
                                                                    </c:if>
                                                                </div>
                                                            </td>
                                                        </div>
                                                        <div class="col-xs-6">
                                                            <td class="text-center">
                                                                <select name="customer_name" class="custom-select"
                                                                        style="text-align-last: center"
                                                                        onChange="MkOrderValues(this.selectedIndex)">
                                                                    <c:forEach var="customer" items="${customer_list}">
                                                                        <option style="text-align: left" <c:if
                                                                                test="${dataMap['customer_name'] == customer.name}"> selected </c:if>>
                                                                                ${customer.name}
                                                                        </option>
                                                                    </c:forEach>
                                                                </select>
                                                            </td>
                                                            <td class="text-center">
                                                                <select name="order_name" class="custom-select"
                                                                        style="text-align-last: center"
                                                                        array="priorityList">
                                                                </select>
                                                            </td>
                                                            <td class="text-center" width="20px">
                                                                <input type="text" name="product_amount" required
                                                                       minlength="1"
                                                                       maxlength="4"
                                                                       value="${dataMap['product_amount']}"
                                                                       class="form-control text-center">
                                                                <div class="text-danger text-center">
                                                                    <c:if test="${dataMap['product_amount'] == ''}">
                                                                        <fmt:message key="product.message.incorrect"/>
                                                                    </c:if>
                                                                </div>
                                                            </td>
                                                            <td class="text-center">
                                                                <select name="product_priority" class="custom-select"
                                                                        style="text-align-last: center"
                                                                        array="priorityList">
                                                                    <option
                                                                            <c:if test="${dataMap['product_priority'] == 'A'}">selected</c:if>>
                                                                        A
                                                                    </option>
                                                                    <option
                                                                            <c:if test="${dataMap['product_priority'] == 'B'}">selected</c:if>>
                                                                        B
                                                                    </option>
                                                                    <option
                                                                            <c:if test="${dataMap['product_priority'] == 'C'}">selected</c:if>>
                                                                        C
                                                                    </option>
                                                                </select>
                                                            </td>
                                                            <td class="text-center">
                                                                <button type="submit"
                                                                        class="btn btn-info badge-pill btn-sm mt-1">
                                                                    <fmt:message key="admin_menu.button.create"/>
                                                                </button>
                                                                <div class="text-success text-center">
                                                                    ${dataMap['message']}
                                                                </div>
                                                                <%--<c:if test="${add_flag == 'true'}">
                                                                    <div class="text-success text-center text-muted">
                                                                        <fmt:message key="admin_menu.message.done"/>
                                                                    </div>
                                                                </c:if>--%>
                                                                <hr>
                                                            </td>
                                                        </div>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-wrapper-scroll-y"
                                     style="position: relative;
                                     <c:if test="${product_page == -1}">height: 700px;</c:if>
                                     <c:if test="${product_page != -1}">height: 300px;</c:if> overflow: auto;">

                                    <form name="productForm" action="#">
                                        <table class="table table-hover table-bordered table-sm table_sort table-hover"
                                               cellspacing="0"
                                               width="100%">
                                            <thead>
                                            <tr>
                                                <th class="text-center"><fmt:message key="admin_menu.table.name"/>
                                                    <img class="ml-1"
                                                         src="${pageContext.request.contextPath}/jpg/icons/arrow-down-up.svg"></th>
                                                <th class="text-center"><fmt:message key="admin_menu.table.decimal"/>
                                                    <img class="ml-1"
                                                         src="${pageContext.request.contextPath}/jpg/icons/arrow-down-up.svg"></th>
                                                <th class="text-center"><fmt:message key="admin_menu.table.customer"/>
                                                    <img class="ml-1"
                                                         src="${pageContext.request.contextPath}/jpg/icons/arrow-down-up.svg"></th>
                                                </th>
                                                <th class="text-center"><fmt:message key="admin_menu.table.order"/>
                                                    <img class="ml-1"
                                                         src="${pageContext.request.contextPath}/jpg/icons/arrow-down-up.svg"></th>
                                                </th>
                                                <th class="text-center"><fmt:message key="admin_menu.table.priority"/>
                                                    <img class="ml-1"
                                                         src="${pageContext.request.contextPath}/jpg/icons/arrow-down-up.svg"></th>
                                                </th>
                                                <th class="text-center"><fmt:message key="admin_menu.table.state"/>
                                                    <img class="ml-1"
                                                         src="${pageContext.request.contextPath}/jpg/icons/arrow-down-up.svg"></th>
                                                </th>
                                                <th class="text-center"><fmt:message
                                                        key="admin_menu.table.manage"/></th>
                                            </tr>
                                            </thead>

                                            <tbody>

                                            <ctg:pagination/>
                                            </tbody>
                                        </table>
                                    </form>
                                </div>
                                <div class="row justify-content-center">
                                    <nav aria-label="...">
                                        <ul class="pagination pagination-sm mb-0">
                                            <li class="page-item disable">
                                                <a class="page-link"
                                                   href="controller?command=pagination&product_page=1"
                                                   tabindex="-1">1</a>
                                            </li>
                                            <li class="page-item disable">
                                                <a class="page-link"
                                                   href="controller?command=pagination&product_page=2"
                                                   tabindex="-1">2</a>
                                            </li>
                                            <li class="page-item disable">
                                                <a class="page-link"
                                                   href="controller?command=pagination&product_page=3"
                                                   tabindex="-1">3</a>
                                            </li>
                                            <li class="page-item disable">
                                                <a class="page-link"
                                                   href="controller?command=pagination&product_page=4"
                                                   tabindex="-1">4</a>
                                            </li>
                                            <li class="page-item disable">
                                                <a class="page-link"
                                                   href="controller?command=pagination&product_page=5"
                                                   tabindex="-1">5</a>
                                            </li>
                                            <li class="page-item disable">
                                                <a class="page-link"
                                                   href="controller?command=pagination&product_page=-1"
                                                   tabindex="-1"><fmt:message key="admin_menu.pagination"/></a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                            </form>
                        </div>
                    </div>
                    <!-- Customers -->
                    <div class="tab-pane fade" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab">
                        <div class="card card card-bordered mb-3">
                            <form class="profile-form" name="ProfileForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="main_menu"/>

                                <div class="card-header">
                                    <h5 class="text-center">Мониторинг</h5>
                                </div>

                                <div class="card-body">
                                    <div class="table-wrapper-scroll-y"
                                         style="position: relative; height: 700px; overflow: auto;">
                                        <table id="idtVerticalScrollExample"
                                               class="table table-hover table-bordered table-sm" cellspacing="0"
                                               width="100%">
                                            <thead>
                                            <tr>
                                                <th class="text-center">Наименование</th>
                                                <th class="text-center">Адресс</th>
                                                <th class="text-center">Телефон</th>
                                                <th class="text-center">факс</th>
                                                <th class="text-center">Email</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="customer" items="${customer_list}">
                                                <tr>
                                                    <td class="text-center"><a
                                                            href="controller?command=customer&customer_id=${customer.id}">${customer.name}</a>
                                                    </td>
                                                    <td class="text-center">${customer.address}</td>
                                                    <td class="text-center">${customer.phone}</td>
                                                    <td class="text-center">${customer.fax}</td>
                                                    <td class="text-center">${customer.email}</td>

                                                    <td class="text-center">
                                                        <button class="btn btn-primary badge-pill btn-sm"
                                                                style="width:80px">
                                                            EDIT
                                                        </button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- Orders -->
                    <div class="tab-pane fade" id="pills-profile2" role="tabpanel" aria-labelledby="pills-orders-tab">
                        <div class="card card card-bordered mb-3">
                            <form class="profile-form" name="ProfileForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="main_menu"/>

                                <div class="card-header">
                                    <h5 class="text-center">Мониторинг</h5>
                                </div>

                                <div class="card-body">
                                    <div class="table-wrapper-scroll-y"
                                         style="position: relative; height: 700px; overflow: auto;">
                                        <table id="idtVerticalScrollExample2"
                                               class="table table-hover table-bordered table-sm" cellspacing="0"
                                               width="100%">
                                            <thead>
                                            <tr>
                                                <th class="text-center">Наименование</th>
                                                <th class="text-center">Заказчик</th>
                                                <th class="text-center">Дата заказа</th>
                                                <th class="text-center">Дата отправки</th>
                                                <th class="text-center">Состояние</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="customer" items="${customer_list}">
                                                <tr>

                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- Users -->
                    <div class="tab-pane fade" id="pills-contact" role="tabpanel" aria-labelledby="pills-contact-tab">
                        <div class="card card card-bordered mb-3">
                            <form class="profile-form" name="ProfileForm" method="POST" action="controller">
                                <input type="hidden" name="command" value="main_menu"/>

                                <div class="card-header">
                                    <h5 class="text-center">Мониторинг</h5>
                                </div>

                                <div class="card-body">
                                    <div class="table-wrapper-scroll-y"
                                         style="position: relative; height: 700px; overflow: auto;">
                                        <table id="dtVerticalScrollExample"
                                               class="table table-hover table-bordered table-sm" cellspacing="0"
                                               width="100%">
                                            <thead>
                                            <tr>
                                                <th class="text-center">ФИО</th>
                                                <th class="text-center">Логин</th>
                                                <th class="text-center">Email</th>
                                                <th class="text-center">Department</th>
                                                <th class="text-center">Role</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="user" items="${user_list}">
                                                <tr>
                                                    <td class="text-center"><a
                                                            href="controller?command=profile&userId=${user.id}">${user.name}</a>
                                                    </td>
                                                    <td class="text-center">${user.login}</td>
                                                    <td class="text-center">${user.email}</td>
                                                    <td class="text-center">${user.department.name}</td>
                                                    <td class="text-center">${user.role}</td>

                                                    <td class="text-center">
                                                        <button class="btn btn-primary badge-pill btn-sm"
                                                                style="width:80px">
                                                            EDIT
                                                        </button>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>

                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="${pageContext.request.contextPath}/jsp/part/footer.jsp"/>
    </div>
</div>

<script>
    $('#myList a').on('click', function (e) {
        e.preventDefault()
        $(this).tab('show')
    })
</script>

<!-- Collapse -->
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

<!-- Sort table -->
<script>
    document.addEventListener('DOMContentLoaded', () => {

        const getSort = ({target}) => {
            const order = (target.dataset.order = -(target.dataset.order || -1));
            const index = [...target.parentNode.cells].indexOf(target);
            const collator = new Intl.Collator(['en', 'ru'], {numeric: true});
            const comparator = (index, order) => (a, b) => order * collator.compare(
                a.children[index].innerHTML,
                b.children[index].innerHTML
            );

            for (const tBody of target.closest('table').tBodies)
                tBody.append(...[...tBody.rows].sort(comparator(index, order)));

            for (const cell of target.parentNode.cells)
                cell.classList.toggle('sorted', cell === target);
        };

        document.querySelectorAll('.table_sort thead').forEach(tableTH => tableTH.addEventListener('click', () => getSort(event)));

    });
</script>

<script src="${pageContext.request.contextPath}/js/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/button_block.js"></script>
</body>
</html>