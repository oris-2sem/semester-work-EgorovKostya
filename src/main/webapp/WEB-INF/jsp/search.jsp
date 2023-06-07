<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>ManKaka</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="/assets/css/main.css">
    <style>
        body {
            padding: 20px;
        }
        .card {
            width: calc(33.33% - 10px);
            margin-right: 10px;
            margin-bottom: 10px;
            float: left;
            background-color: #ffe2c4; /* Измените цвет карточек здесь */
            border: none;
            border-radius: 10px;
            box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.1);
        }
        .card-img-top {
            height: 200px;
            object-fit: cover;
            border-radius: 10px 10px 0 0;
        }
        .card-body {
            padding: 15px;
        }
        .card-title {
            font-size: 18px;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .card-text {
            margin-bottom: 10px;
        }
        .btn-margin {
            margin-top: 5px;
        }
    </style>
</head>
<body>
<%@include file="../parts/navbar.jsp"%>
<div class="container-fluid" style="margin-top: 5%; background-color: #141414; padding: 2%; border-radius: 15px;">
    <c:if test="${titles.size() == 0}">
        <h2 class="text-primary">Ничего не найдено<span class="badge bg-danger"></span></h2>
    </c:if>
    <div class="row">
        <c:forEach items="${titles}" var="title">
            <div class="card">
                <img src="${title.value}" class="card-img-top" alt="${title.key.id}">
                <div class="card-body">
                    <hr class="divider">
                    <h5 class="card-title"><c:out value="${title.key.name}"/></h5>
                    <p class="card-text"><c:out value="${title.key.description}"/></p>
                    <p class="card-text"><small class="text-muted"><c:out value="${title.key.pages}"/> Глав</small></p>
                    <form:form action="/titles/${title.key.id}" method="get">
                        <button class="btn btn-primary btn-margin" type="submit">More information</button>
                    </form:form>
                    <sec:authorize access="isAuthenticated()">
                        <sec:authentication property="principal.id" var="id"/>
                        <div style="padding-top: 2px">
                            <form:form action="/users/${id}/titles/${title.key.id}" method="patch">
                                <c:if test="${!user.favoritesTitles.contains(title.key)}">
                                    <button class="btn btn-primary btn-margin" type="submit">Добавить к себе в избранное</button>
                                </c:if>
                            </form:form>
                            <sec:authorize access="hasRole('ADMIN')">
                                <div style="padding-top: 2px">
                                    <form:form action="/titles/${title.key.id}" method="delete">
                                        <button class="btn btn-danger btn-margin" type="submit">Удалить</button>
                                    </form:form>
                                </div>
                            </sec:authorize>
                        </div>
                    </sec:authorize>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="row justify-content-center mt-3">
        <div class="col-auto">
            <c:if test="${page > 0}">
                <form:form action="/titles" method="get" class="d-inline">
                    <input type="hidden" name="name" value="${name}"/>
                    <input type="hidden" name="page" value="${page - 1}"/>
                    <input type="hidden" name="size" value="${size}"/>
                    <button type="submit" class="btn btn-primary">Предыдущая страница</button>
                </form:form>
            </c:if>
        </div>
        <div class="col-auto">
            <c:if test="${titles.size() == size}">
                <form:form action="/titles" method="get" class="d-inline">
                    <input type="hidden" name="name" value="${name}"/>
                    <input type="hidden" name="page" value="${page + 1}"/>
                    <input type="hidden" name="size" value="${size}"/>
                    <button type="submit" class="btn btn-primary">Следующая страница</button>
                </form:form>
            </c:if>

        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="<c:url value="/assets/js/murder.js"/>"></script>
</body>
</html>