<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="t" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Redactor</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="/assets/css/main.css">

</head>
<body>
<%@include file="../parts/navbar.jsp"%>
<div class="container-fluid w-50 h-52" style="margin-top: 5%; background-color: #141414; padding: 2%; border-radius: 15px;">
    <form:form action="/titles" method="post" enctype="multipart/form-data" modelAttribute="title">
        <div class="mb-3">
            <label for="formFile" class="form-label text-primary">Выберите превьюшку</label>
            <t:input class="form-control" type="file" id="formFile" path="file" required="true"/>
        </div>
        <div class="mb-3">
            <label for="exampleFormControlInput1" class="form-label text-primary">Название тайтла</label>
            <t:input type="text" class="form-control" path="name" id="exampleFormControlInput1" placeholder="Название" required="true"/>
            <c:if test="${errorName != null}">
                <p class="text-danger">Такой тайтл уже есть</p>
            </c:if>
        </div>
        <div class="mb-3">
            <label for="exampleFormControlTextarea1" class="form-label text-primary">Краткое описание</label>
            <t:input type="textarea" class="form-control" path="description" id="exampleFormControlTextarea1" rows="3" placeholder="Краткое описание" required="true"/>
        </div>
        <div class="mb-3">
            <label for="exampleFormControlInput2" class="form-label text-primary">Количество глав</label>
            <t:input type="text" class="form-control" path="pages" id="exampleFormControlInput2" placeholder="Количество глав" required="true"/>
            <c:if test="${not empty errorPageCount}">
                <p class="text-danger">Неправильный формат</p>
            </c:if>
        </div>
        <div class="mb-3">
            <label for="exampleFormControlInput3" class="form-label text-primary">Тип</label>
            <t:input type="text" class="form-control" path="type" id="exampleFormControlInput3" placeholder="example: Манхва" required="true"/>
        </div>
        <div class="mb-3">
            <label for="exampleFormControlInput4" class="form-label text-primary">Дата Релиза</label>
            <t:input type="date" class="form-control" path="released" id="exampleFormControlInput4" placeholder="example: 2002" required="true"/>
        </div>
        <div class="mb-3">
            <label for="exampleFormControlInput5" class="form-label text-primary">Статус тайтла</label>
            <t:input type="text" class="form-control" path="status" id="exampleFormControlInput5" placeholder="example: Онгоинг" required="true"/>
        </div>
        <div class="mb-3">
        <form:select multiple="true" path="authorIds" aria-label="multiple select example">
            <c:forEach items="${authors}" var="author">
                <form:option value="${author.id}" label="${author.name}" />
            </c:forEach>
        </form:select>
        </div>
        <div class="mb-3">
            <label for="exampleFormControlInput7" class="form-label text-primary">Альтернативное название</label>
            <t:input type="text" class="form-control" path="alternativeName" id="exampleFormControlInput7" placeholder="Альтернативное название" required="true"/>
        </div>
        <div class="mb-3">
            <label for="exampleFormControlInput9" class="form-label text-primary">Ссылка для чтения</label>
            <t:input type="text" class="form-control" path="link" id="exampleFormControlInput9" placeholder="Ссылка для просмотра" required="true"/>
        </div>
        <div class="mb-3">
            <label for="exampleFormControlInput8" class="form-label text-primary">Полное описание</label>
            <t:input type="textarea" class="form-control" path="fullDescription" id="exampleFormControlInput8" placeholder="Полное описание" required="true"/>
        </div>
        <button type="submit" class="btn btn-primary">Добавить тайтл</button>
    </form:form>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script type="text/javascript" src="<c:url value="/assets/js/murder.js"/>"></script>
</html>