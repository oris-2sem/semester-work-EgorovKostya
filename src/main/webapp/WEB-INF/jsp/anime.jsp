<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Информация об аниме</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="/assets/css/main.css">
</head>
<body>
<%@include file="../parts/navbar.jsp"%>

<div class="container-fluid w-80 h-50" style="margin-top: 5%; background-color: #141414; padding: 2%; border-radius: 15px; display: flex">
    <c:if test="${empty anime}">
        <h1><span class="badge bg-primary">this anime is not available on the site</span></h1>
    </c:if>
    <c:if test="${not empty anime}">
        <div class="card mb-3" style="width: 45%; margin-right: 50px">
            <img src="${anime.imageUrl}" class="card-img-top" alt="<c:out value="${anime.id}"/>" style="height: 450px; width: 638px">
            <div class="card-body">
                <hr class="divider">
                <h5 class="card-title"><c:out value="${anime.ruName} ${anime.fullString}"/></h5>
                <p class="card-text"><c:out value="Сезон ${anime.released}"/></p>
            </div>
        </div>
        <div class="card mb-3" style="width: 45%;">
            <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                <div style="margin:0 auto; display: flex">
                    <li class="nav-item" role="presentation" style="padding: 5px">
                        <button class="nav-link active" id="pills-home-tab" data-bs-toggle="pill" data-bs-target="#pills-home" type="button" role="tab" aria-controls="pills-home" aria-selected="true">Информация</button>
                    </li>
                    <li class="nav-item" role="presentation" style="padding: 5px">
                        <button class="nav-link" id="pills-profile-tab" data-bs-toggle="pill" data-bs-target="#pills-profile" type="button" role="tab" aria-controls="pills-profile" aria-selected="false">Полное описание</button>
                    </li>
                    <li class="nav-item" role="presentation" style="padding: 5px">
                        <button class="nav-link" id="pills-contact-tab" data-bs-toggle="pill" data-bs-target="#pills-contact" type="button" role="tab" aria-controls="pills-contact" aria-selected="false">Cмотреть</button>
                    </li>
                </div>
            </ul>
            <div class="tab-content" style="margin: 15px" id="pills-tabContent">
                <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab" tabindex="0">
                    <ul>
                        <p class="fw-light">Тип</p>

                        <p class="fw-semibold"><c:out value="${anime.ruName} ${anime.fullString}"/></p>

                        <p class="fw-light">Избранное</p>

                        <p class="fw-semibold"><c:out value="${anime.favourites}"/> раз</p>

                        <p class="fw-light">Cтатус тайтла</p>

                        <p class="fw-semibold"><c:out value="${anime.status}"/></p>

                        <p class="fw-light">Жанры</p>

                        <p class="fw-semibold">
                            <c:forEach items="${anime.genres}" var="genre">
                                <c:out value="${genre}"/>
                            </c:forEach>
                        </p>
                        <p class="fw-light">Количество серий</p>

                        <p class="fw-semibold"><c:out value="${anime.episodes}"/></p>

                        <p class="fw-light">Альтернативное название</p>

                        <p class="fw-semibold"><c:out value="${anime.enName}"/></p>
                    </ul>
                </div>
                <div class="tab-pane fade" style="margin: 15px" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab" tabindex="0">${anime.description}</div>
                <div class="tab-pane fade" style="margin: 15px" id="pills-contact" role="tabpanel" aria-labelledby="pills-contact-tab" tabindex="0"><a class="btn btn-info" href="/animes/watch/${anime.id}">Удачного просмотра</a></div>
            </div>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script type="text/javascript" src="<c:url value="/assets/js/murder.js"/>"></script>
</body>
</html>