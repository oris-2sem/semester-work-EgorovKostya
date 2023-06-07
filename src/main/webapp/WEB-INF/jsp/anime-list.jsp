<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Anime Search</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
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
<c:choose>
    <c:when test="${empty animes}">
        <div class="container text-center" style="margin-top: 100px">
        </div>
            <div class="row justify-content-center mt-5">
                    <div class="card">
                        <div class="card-body">
                            <form:form action="/animes" method="get" modelAttribute="animeFilter">
                                <div class="form-group">
                                    <label for="animeName">Enter Anime Name:</label>
                                    <t:input type="text" class="form-control" id="animeName" path="name" placeholder="e.g. Attack on Titan"/>
                                </div>
                                <div class="form-group mb-3" style="margin-top: 2%">
                                    <button type="button" class="btn btn-secondary" data-bs-toggle="collapse" data-bs-target="#filtersCollapse" aria-expanded="false" aria-controls="filtersCollapse">
                                        Additional Filters
                                    </button>
                                </div>
                                <div class="collapse mt-2" id="filtersCollapse">
                                    <div class="form-group">
                                        <label for="minYear">Year Range:</label>
                                        <div class="row">
                                            <div class="col">
                                                <t:input type="number" class="form-control" path="minYear" id="minYear" placeholder="From" value="0"/>
                                            </div>
                                            <div class="col">
                                                <t:input type="number" class="form-control" path="maxYear" id="maxYear" placeholder="To"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="minYear">Chapters Range:</label>
                                        <div class="row">
                                            <div class="col">
                                                <t:input type="number" class="form-control" path="minChapters" id="minChapters" placeholder="From" value="0"/>
                                            </div>
                                            <div class="col">
                                                <t:input type="number" class="form-control" path="maxChapters" id="maxChapters" placeholder="To"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="genres">Genres:</label>
                                        <form:select multiple="true" class="form-control" path="genres">
                                            <c:forEach items="${genres}" var="genre">
                                                <form:option value="${genre.name}" label="${genre.name}" />
                                            </c:forEach>
                                        </form:select>
                                    </div>
                                    <div class="form-group">
                                        <label for="status">Status</label>
                                        <t:input type="text" class="form-control" path="status" id="status"/>
                                    </div>
                                </div>
                                <button type="submit" class="btn btn-primary btn-block mt-3">Search</button>
                            </form:form>
                        </div>
                </div>
            </div>
    </c:when>
    <c:otherwise>
        <div class="container-fluid" style="margin-top: 5%; background-color: #141414; padding: 2%; border-radius: 15px;">
            <div class="row">
                <c:forEach items="${animes}" var="anime">
                    <div class="card">
                        <img src="${anime.imageUrl}" class="card-img-top" alt="<c:out value="${anime.id}"/>">
                        <div class="card-body">
                            <hr class="divider">
                            <h5 class="card-title"><c:out value="${anime.ruName}"/></h5>
                            <p class="card-text"><c:out value="Сезон: ${anime.released} ${anime.fullString}"/></p>
                            <p class="card-text"><small class="text-muted"><c:out value="${anime.episodes}"/> Серий</small></p>
                            <form:form action="/animes/${anime.id}" method="get">
                                <button class="btn btn-primary btn-margin" type="submit">More information</button>
                            </form:form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>



        <button type="button" class="btn btn-secondary position-fixed bottom-0 end-0 mb-3 me-3" data-bs-toggle="modal" data-bs-target="#searchModal">
            Open Search
        </button>
        <!-- Modal -->
        <div class="modal fade" id="searchModal" tabindex="-1" aria-labelledby="searchModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="searchModalLabel">Search Anime</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form:form action="/animes" method="get" modelAttribute="animeFilter">
                            <div class="form-group">
                                <label for="animeName">Enter Anime Name:</label>
                                <t:input type="text" class="form-control" id="animeName" path="name" placeholder="e.g. Attack on Titan"/>
                            </div>
                            <div class="form-group mb-3" style="margin-top: 2%">
                                <button type="button" class="btn btn-secondary" data-bs-toggle="collapse" data-bs-target="#filtersCollapse" aria-expanded="false" aria-controls="filtersCollapse">
                                    Additional Filters
                                </button>
                            </div>
                            <div class="collapse mt-2" id="filtersCollapse">
                                <div class="form-group">
                                    <label for="minYear">Year Range:</label>
                                    <div class="row">
                                        <div class="col">
                                            <t:input type="number" class="form-control" path="minYear" id="minYear" placeholder="From" value="0"/>
                                        </div>
                                        <div class="col">
                                            <t:input type="number" class="form-control" path="maxYear" id="maxYear" placeholder="To"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="minYear">Chapters Range:</label>
                                    <div class="row">
                                        <div class="col">
                                            <t:input type="number" class="form-control" path="minChapters" id="minChapters" placeholder="From" value="0"/>
                                        </div>
                                        <div class="col">
                                            <t:input type="number" class="form-control" path="maxChapters" id="maxChapters" placeholder="To"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="genres">Genres:</label>
                                    <form:select multiple="true" class="form-control" path="genres">
                                        <c:forEach items="${genres}" var="genre">
                                            <form:option value="${genre.name}" label="${genre.name}" />
                                        </c:forEach>
                                    </form:select>
                                </div>
                                <div class="form-group">
                                    <label for="status">Status</label>
                                    <t:input type="text" class="form-control" path="status" id="status"/>
                                </div>
                            </div>
                            <button type="submit" class="btn btn-primary btn-block mt-3">Search</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script type="text/javascript" src="<c:url value="/assets/js/murder.js"/>"></script>
</body>
</html>