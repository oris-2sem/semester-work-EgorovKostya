<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="<c:url value="/assets/css/main.css"/>">

</head>
<body>
<%@include file="../parts/navbar.jsp"%>
<div class="container-fluid w-50 h-52" style="margin-top: 5%; background-color: #141414; padding: 2%; border-radius: 15px;">
    <sec:authorize access="isAuthenticated()" >
        <sec:authentication property="principal.id" var="id"/>
    </sec:authorize>
    <h2 class="text-danger">${user.username}'s favourite titles<span class="badge bg-danger"><c:out value="${usersFavourites.size()}"/></span></h2>
    <c:forEach items="${usersFavourites}" var="title">
        <div class="card mb-3">
            <img src="${title.value}" class="card-img-top" alt="<c:out value="${title.key.id}"/>" style="height: 400px;">
            <div class="card-body">
                <hr class="divider">
                <h5 class="card-title"><c:out value="${title.key.name}"/></h5>
                <p class="card-text"><c:out value="${title.key.description}"/></p>
                <p class="card-text"><small class="text-muted"><c:out value="${title.key.pages}"/> Глав</small></p>
                <form:form action="/titles/${title.key.id}" method="get">
                    <button class="btn btn-primary" type="submit">More information</button>
                </form:form>
                <div style="padding-top: 2px">
                    <c:if test="${userId == id}">
                        <form:form action="/users/${id}/titles/${title.key.id}" method="delete">
                            <button class="btn btn-danger" type="submit">Удалить</button>
                        </form:form>
                    </c:if>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script type="text/javascript" src="<c:url value="/assets/js/murder.js"/>"></script>
</html>
