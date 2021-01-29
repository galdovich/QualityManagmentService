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

    <title><fmt:message key="title.profile"/></title>
</head>
<body>

<div class="container">
    <jsp:include page="${pageContext.request.contextPath}/jsp/part/header.jsp"/>
    <div class="main-body">

        <!-- Breadcrumb -->
        <nav aria-label="breadcrumb" class="main-breadcrumb">
            <ol class="breadcrumb">
                <c:if test="${role=ADMIN}">
                    <li class="breadcrumb-item"><a href="controller?command=admin_menu">Admin menu</a></li>
                </c:if>

                <li class="breadcrumb-item active" aria-current="page">User Profile</li>
            </ol>
        </nav>
        <!-- /Breadcrumb -->

        <div class="row gutters-sm">
            <div class="col-md-4 mb-3">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="/jpg/profile/22625793_3e75d287ec1bf1f3653cc16f965c1dbe_800.jpg" alt="Admin"
                                 class="rounded-circle" width="150">
                            <div class="mt-3">
                                <h4>${user_full_name}</h4>
                                <p class="text-secondary mb-1">Full Stack Developer</p>
                                <p class="text-muted font-size-sm">Bay Area, San Francisco, CA</p>
                                <%--   <form action="upload" enctype="multipart/form-data" method="POST">
                                       Upload File: <input type="file" name="content" height="130">
                                       <input type="submit" value="Upload File">
                                   </form>--%>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="card mt-3">
                    <div class="card-header text-center p-1" style="height: 35px">
                        <h5>Информация</h5>
                    </div>
                    <%--<ul class="list-group list-group-flush">
                        <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                            <h6 class="mb-0">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-globe mr-2 icon-inline">
                                    <circle cx="12" cy="12" r="10"></circle>
                                    <line x1="2" y1="12" x2="22" y2="12"></line>
                                    <path d="M12 2a15.3 15.3 0 0 1 4 10 15.3 15.3 0 0 1-4 10 15.3 15.3 0 0 1-4-10 15.3 15.3 0 0 1 4-10z"></path>
                                </svg>
                                Website
                            </h6>
                            <span class="text-secondary">https://bootdey.com</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                            <h6 class="mb-0">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-github mr-2 icon-inline">
                                    <path d="M9 19c-5 1.5-5-2.5-7-3m14 6v-3.87a3.37 3.37 0 0 0-.94-2.61c3.14-.35 6.44-1.54 6.44-7A5.44 5.44 0 0 0 20 4.77 5.07 5.07 0 0 0 19.91 1S18.73.65 16 2.48a13.38 13.38 0 0 0-7 0C6.27.65 5.09 1 5.09 1A5.07 5.07 0 0 0 5 4.77a5.44 5.44 0 0 0-1.5 3.78c0 5.42 3.3 6.61 6.44 7A3.37 3.37 0 0 0 9 18.13V22"></path>
                                </svg>
                                Github
                            </h6>
                            <span class="text-secondary">bootdey</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                            <h6 class="mb-0">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round" class="feather feather-twitter mr-2 icon-inline text-info">
                                    <path d="M23 3a10.9 10.9 0 0 1-3.14 1.53 4.48 4.48 0 0 0-7.86 3v1A10.66 10.66 0 0 1 3 4s-4 9 5 13a11.64 11.64 0 0 1-7 2c9 5 20 0 20-11.5a4.5 4.5 0 0 0-.08-.83A7.72 7.72 0 0 0 23 3z"></path>
                                </svg>
                                Twitter
                            </h6>
                            <span class="text-secondary">@bootdey</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                            <h6 class="mb-0">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round"
                                     class="feather feather-instagram mr-2 icon-inline text-danger">
                                    <rect x="2" y="2" width="20" height="20" rx="5" ry="5"></rect>
                                    <path d="M16 11.37A4 4 0 1 1 12.63 8 4 4 0 0 1 16 11.37z"></path>
                                    <line x1="17.5" y1="6.5" x2="17.51" y2="6.5"></line>
                                </svg>
                                Instagram
                            </h6>
                            <span class="text-secondary">bootdey</span>
                        </li>
                        <li class="list-group-item d-flex justify-content-between align-items-center flex-wrap">
                            <h6 class="mb-0">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24"
                                     fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round"
                                     stroke-linejoin="round"
                                     class="feather feather-facebook mr-2 icon-inline text-primary">
                                    <path d="M18 2h-3a5 5 0 0 0-5 5v3H7v4h3v8h4v-8h3l1-4h-4V7a1 1 0 0 1 1-1h3z"></path>
                                </svg>
                                Facebook
                            </h6>
                            <span class="text-secondary">bootdey</span>
                        </li>
                    </ul>--%>
                </div>
            </div>
            <div class="col-md-8">
                <div class="card mb-3">
                    <form class="profile-form" name="ProfileForm" method="POST" action="controller">
                        <input type="hidden" name="command" value="edit_profile"/>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-3 text-center">
                                    <h6 class="mb-0"><fmt:message key="profile.full_name"/></h6>
                                </div>
                                <input class="col-sm-8 form-control" type="text" name="name" id="name"
                                       value="${user_name}">
                            </div>
                            <div class="text-danger text-center">
                                ${editData['name_message']}
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3 text-center">
                                    <h6 class="mb-0"><fmt:message key="profile.login"/></h6>
                                </div>
                                <input class="col-sm-8 form-control" value="${user_login}" readonly>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3 text-center">
                                    <h6 class="mb-0"><fmt:message key="profile.email"/></h6>
                                </div>
                                <input class="col-sm-8 form-control" type="email" name="email" id="email"
                                       value="${user_email}">
                            </div>
                            <div class="text-danger text-center">
                                ${editData['email_message']}
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3 text-center">
                                    <h6 class="mb-0"><fmt:message key="profile.department"/></h6>
                                </div>
                                <select type="text" name="department" id="department" class="col-sm-8 form-control"
                                        required>
                                    <option selected>${user_department}</option>
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
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-3 text-center">
                                    <h6 class="mb-0"><fmt:message key="profile.role"/></h6>
                                </div>
                                <input class="col-sm-8 form-control" value="${user_role}" readonly>
                            </div>
                            <hr>
                            <div class="row justify-content-center">
                                <c:if test="${validMessage == true}">
                                    <div class="col-md-12 text-center text-success">
                                        <fmt:message key="profile.valid.message"/>
                                    </div>
                                </c:if>

                                <c:if test="${invalidMessage == true}">
                                    <div class="col-md-12 text-center text-danger">
                                        <fmt:message key="profile.invalid.message"/>
                                    </div>
                                </c:if>

                                <hr>
                                <c:if test="${user_role == 'ADMIN'}">
                                    <div class="col-md-12 text-center">
                                        <button type="submit" class="btn btn-sm btn-primary"><fmt:message
                                                key="profile.save.button"/></button>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="card col-md-12 p-0">

                    <div class="card-header text-center" style="height: 40px">
                        <h5>Информация</h5>
                    </div>
                    <div class="card-body">

                    </div>
                </div>
                <%-- <div class="col-sm-6 mb-3">
                     <div class="card h-100">
                         <div class="card-body">
                             <h6 class="d-flex align-items-center mb-3"><i class="material-icons text-info mr-2">assignment</i>Project
                                 Status</h6>
                             <small>Web Design</small>
                             <div class="progress mb-3" style="height: 5px">
                                 <div class="progress-bar bg-primary" role="progressbar" style="width: 80%"
                                      aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"></div>
                             </div>
                             <small>Website Markup</small>
                             <div class="progress mb-3" style="height: 5px">
                                 <div class="progress-bar bg-primary" role="progressbar" style="width: 72%"
                                      aria-valuenow="72" aria-valuemin="0" aria-valuemax="100"></div>
                             </div>
                             <small>One Page</small>
                             <div class="progress mb-3" style="height: 5px">
                                 <div class="progress-bar bg-primary" role="progressbar" style="width: 89%"
                                      aria-valuenow="89" aria-valuemin="0" aria-valuemax="100"></div>
                             </div>
                             <small>Mobile Template</small>
                             <div class="progress mb-3" style="height: 5px">
                                 <div class="progress-bar bg-primary" role="progressbar" style="width: 55%"
                                      aria-valuenow="55" aria-valuemin="0" aria-valuemax="100"></div>
                             </div>
                             <small>Backend API</small>
                             <div class="progress mb-3" style="height: 5px">
                                 <div class="progress-bar bg-primary" role="progressbar" style="width: 66%"
                                      aria-valuenow="66" aria-valuemin="0" aria-valuemax="100"></div>
                             </div>
                         </div>
                     </div>
                 </div>--%>
            </div>
        </div>
    </div>
    <jsp:include page="${pageContext.request.contextPath}/jsp/part/footer.jsp"/>
</div>
<script src="${pageContext.request.contextPath}/js/button_block.js"></script>
</body>
</html>
