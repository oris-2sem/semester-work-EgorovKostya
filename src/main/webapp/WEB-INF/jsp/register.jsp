<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="/assets/css/main.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

</head>
<body>
    <div class="container-fluid w-50 h-52 position-absolute top-50 start-50 translate-middle" style="background-color: blanchedalmond">
        <p class="fw-semibold">Введите свои данные</p>
        <form:form class="form-horizontal" method="post" action="/register" modelAttribute="user">
            <div class="mb-3 row">
                <label class="col-sm-2 col-form-label">Username</label>
                <div class="col-sm-10">
                    <t:input type="text" class="form-control" path="username" required="true" value="${username}"/>
                    <c:if test="${incorrectUsername != null}">
                        <p class="text-danger">this username already used</p>
                    </c:if>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
                <div class="col-sm-10">
                    <t:password class="form-control" id="inputPassword" path="password" required="true"/>
                </div>
            </div>
            <div class="mb-3 row">
                <label for="inputPassword" class="col-sm-2 col-form-label">Password Repeat</label>
                <div class="col-sm-10">
                    <t:password class="form-control" id="inputPassword" label="Password Repeat" path="passwordRepeat" required="true"/>
                </div>
            </div>
            <div class="col-sm-10">
                <c:if test="${diffPassword != null}">
                    <p class="text-danger">Different passwords! Try again</p>
                </c:if>
            </div>
            <button type="submit" class="btn btn-primary mb-3">Register</button>
            <p class="text-muted">
                Уже зареган? жми <a href="<c:url value="/signin"/>" class="text-reset">сюда</a>.
            </p>
        </form:form>
    </div>
    <div class="baton" >
        <a href="#" class="btn btn-primary themeToggle"><span class="material-symbols-outlined">bedtime</span></a>
    </div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script type="text/javascript" src="<c:url value="/assets/js/murder.js"/>"></script>
</html>
