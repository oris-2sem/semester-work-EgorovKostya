<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<nav class="navbar navbar-expand-lg navbar-dark fixed-top bg-danger">
    <div class="container">
        <a class="navbar-brand" href="<c:url value="/"/>">
            <span class="material-symbols-outlined inline" style="padding-top: 3px">home</span>
            Mankaka
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<c:url value="/titles"/>">
                        <span class="material-symbols-outlined" style="vertical-align: middle;">movie_filter</span>
                        <span style="vertical-align: middle;">Titles</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="<c:url value="/animes"/>">
                        <span class="material-symbols-outlined" style="vertical-align: middle;">theaters</span>
                        <span style="vertical-align: middle;">Anime</span>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active themeToggle" href="#">
                        <span class="material-symbols-outlined" style="vertical-align: middle;">bedtime</span>
                    </a>
                </li>
            </ul>
            <form:form class="d-flex" role="search" method="get" action="/titles">
                <span class="material-symbols-outlined" style="padding-top: 7px; vertical-align: middle;">search</span>
                <input class="form-control me-2" type="search" name="name" id="exampleDataList" placeholder="Type to search manga">
                <button class="btn btn-outline-light" type="submit">Search</button>
            </form:form>
            <p class="badge bg-primary text-wrap fst-italic fs-6 mx-auto d-flex justify-content-center" style="height: 3%">The best site with a description of manga</p>
            <sec:authorize access="!isAuthenticated()">
                <ul class="nav justify-content-end">
                    <li class="btn-group mx-auto" role="group" aria-label="Basic outlined example">
                        <a class="btn btn-primary" href="<c:url value="/signin"/>" role="button">Sign In</a>
                        <a class="btn btn-dark" href="<c:url value="/register"/>" role="button">Sign Up</a>
                    </li>
                </ul>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <sec:authentication property="principal.username" var="username"/>
                <sec:authentication property="principal.id" var="id"/>
                <div class="dropdown">
                    <button class="btn btn-primary dropdown-toggle" type="button" data-bs-toggle="dropdown" id="dropdownMenuButton" aria-haspopup="true" aria-expanded="false">
                            ${username}
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
                            <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"></path>
                            <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"></path>
                        </svg>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                        <li><a class="dropdown-item" href="<c:url value="/users/${id}"/>">Profile</a></li>
                        <li><a class="dropdown-item" href="<c:url value="/edit"/>">Edit</a></li>
                        <sec:authorize access="hasRole('ADMIN')">
                            <li><a class="dropdown-item" href="<c:url value="/redact"/>">Redact</a></li>
                        </sec:authorize>
                        <li><hr class="dropdown-divider"></li>
                        <li>
                            <form:form action="/logout" method="post">
                                <button class="dropdown-item btn-primary" name="logout" value="out">Log out</button>
                            </form:form>
                        </li>
                    </ul>
                </div>
            </sec:authorize>
        </div>
    </div>
</nav>






