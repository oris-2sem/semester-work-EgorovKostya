<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="t" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <title>Title</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
  <link rel="stylesheet" href="/assets/css/main.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<%@include file="../parts/navbar.jsp"%>


<div class="container-fluid w-80 h-50" style="margin-top: 5%; background-color: #141414; padding: 2%; border-radius: 15px; display: flex">
  <c:if test="${empty title}">
    <h1><span class="badge bg-primary">this manga is not available on the site</span></h1>
  </c:if>
  <c:if test="${not empty title}">
    <div class="card mb-3" style="width: 45%; margin-right: 50px">
      <img src="${title.value}" class="card-img-top" alt="<c:out value="${title.key.id}"/>" style="height: 450px; width: 638px">
      <div class="card-body">
        <hr class="divider">
        <h5 class="card-title"><c:out value="${title.key.name}"/></h5>
        <p class="card-text"><c:out value="${title.key.description}"/></p>
        <p class="card-text"><small class="text-muted"><c:out value="${title.key.pages}"/> Глав</small></p>
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
            <button class="nav-link" id="pills-contact-tab" data-bs-toggle="pill" data-bs-target="#pills-contact" type="button" role="tab" aria-controls="pills-contact" aria-selected="false">Читать</button>
          </li>
        </div>
      </ul>
      <div class="tab-content" style="margin: 15px" id="pills-tabContent">
        <div class="tab-pane fade show active" id="pills-home" role="tabpanel" aria-labelledby="pills-home-tab" tabindex="0">
          <ul>
            <p class="fw-light">Тип</p>

            <p class="fw-semibold"><c:out value="${title.key.type}"/></p>

            <p class="fw-light">Год релиза</p>

            <p class="fw-semibold"><c:out value="${title.key.released}"/></p>

            <p class="fw-light">Cтатус тайтла</p>

            <p class="fw-semibold"><c:out value="${title.key.status}"/></p>

            <p class="fw-light">Авторы</p>

            <p class="fw-semibold">
                <c:forEach items="${title.key.authors}" var="author">
                  <c:out value="${author.name} "/>
                </c:forEach>
            </p>
             <p class="fw-light">Загружено глав</p>

            <p class="fw-semibold"><c:out value="${title.key.pages}"/></p>

            <p class="fw-light">Альтернативное название</p>

            <p class="fw-semibold"><c:out value="${title.key.alternativeName}"/></p>
          </ul>
        </div>
        <div class="tab-pane fade" style="margin: 15px" id="pills-profile" role="tabpanel" aria-labelledby="pills-profile-tab" tabindex="0"><c:out value="${title.key.fullDescription}"/></div>
        <div class="tab-pane fade" style="margin: 15px" id="pills-contact" role="tabpanel" aria-labelledby="pills-contact-tab" tabindex="0"><a class="btn btn-info" href="<c:out value="${title.key.link}"/>">Прощай</a></div>
      </div>
    </div>
  </c:if>

</div>
<div class="card mb-3" style="width: 100%; margin-top: 20px;">
  <h5 class="card-header">Комментарии</h5>
  <div class="card-body">
      <c:if test="${title.key.comments.size() == 0}">
        <p class="card-text">Нет комментариев.</p>
      </c:if>
      <ul class="list-group" id="comment-list">
        <c:forEach items="${title.key.comments}" var="comment">
          <li class="list-group-item"><c:out value="${comment.value}"/></li>
        </c:forEach>
      </ul>

  </div>
  <s:authorize access="isAuthenticated()">
    <div class="card mb-3" style="width: 100%; margin-top: 20px;">
      <h5 class="card-header">Добавить комментарий</h5>
      <div class="card-body">
        <form:form id="comment-form" action="/comments/${title.key.id}" method="post">
          <div class="mb-3">
            <label for="comment" class="form-label">Текст комментария:</label>
            <textarea class="form-control" id="comment" name="value" rows="3"></textarea>
          </div>
          <button id="commentForm" onclick="la()" class="btn btn-primary">Добавить</button>
        </form:form>
      </div>
    </div>
  </s:authorize>
</div>
<script type="text/javascript">
  function la() {
    $('#comment-form').submit(function(event) {
      event.preventDefault();
      let comment = $('#comment').val();
      let ul=document.querySelector("#comment-list")
      console.log(comment);
      $.ajax({
        url: '/comments/${title.key.id}?value=' + comment,
        type: 'POST',
        success: function(response) {
          console.log('Комментарий успешно добавлен!');
          const li=document.createElement("li")
          li.textContent= "${username}: " + comment
          li.className="list-group-item"
          ul.append(li)
          comment=""
        },
        error: function(xhr) {
          console.log('Ошибка при добавлении комментария: ' + xhr.statusText);
        }
      });
      $.ajax({

      });
    });
  }
</script>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script type="text/javascript" src="<c:url value="/assets/js/murder.js"/>"></script>
</html>
