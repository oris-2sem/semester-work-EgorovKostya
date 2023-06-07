<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edit</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
  <link rel="stylesheet" href="/assets/css/main.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />

</head>
<body>
<%@include file="../parts/navbar.jsp"%>

<div class="container-fluid w-50 h-52" style="margin-top: 5%; background-color: #141414; padding: 2%; border-radius: 15px;">
  <sec:authorize access="isAuthenticated()">
    <sec:authentication property="principal.id" var="id"/>
  </sec:authorize>
  <form:form action="/users/${id}" method="put" modelAttribute="user">
    <div class="mb-3">
      <label for="username" class="form-label text-primary">Username</label>
      <t:input type="text" class="form-control" path="username" required="true"/>
      <div style="padding-top: 2px">
        <button type="submit" class="btn btn-primary" >Update username</button>
      </div>
    </div>
  </form:form>
  <form:form action="/users/${id}" method="post" modelAttribute="pass">
    <div class="mb-3">
      <label for="exampleInputPassword1" class="form-label text-primary">Old password</label>
      <t:input type="password" class="form-control" id="exampleInputPassword1" path="oldPassword" required="true"/>
      <label for="exampleInputPassword2" class="form-label text-primary">Password</label>
      <t:input type="password" class="form-control" id="exampleInputPassword2" path="password" required="true"/>
      <div class="form-text">We'll never share your password with anyone else.</div>
    </div>
    <button type="submit" class="btn btn-primary">Update password</button>
  </form:form>
  <c:if test="${inc != null}">
    <p class="text-danger">Такой тайтл уже есть</p>
  </c:if>
  <div style="padding-top: 2px">
    <form:form action="/users/${id}" method="delete">
      <button type="submit" class="btn btn-danger">Delete account</button>
    </form:form>
  </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<script type="text/javascript" src="<c:url value="/assets/js/murder.js"/>"></script>
</html>
