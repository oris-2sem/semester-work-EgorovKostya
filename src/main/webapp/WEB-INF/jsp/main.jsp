<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: egoro
  Date: 26.05.2023
  Time: 9:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>ManKaka</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    <link rel="stylesheet" href="/assets/css/main.css">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Icons">
</head>
</head>
<body>
    <%@include file="../parts/navbar.jsp"%>

    <div class="wrap">
        <div class="container-slider-video" style="margin-top: 6%;padding: 1%;">
        <div id="carouselExampleCaptions" class="carousel slide">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">

                <div class="carousel-item active">
                    <video class="d-block w-100" width="100%" height="80%" src="../../assets/videos/naruto.webm" loop autoplay></video>
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Сотни манг, которые ты даже не читал, cо ссылкой на источник</h5>
                        <p>«Люди становятся сильнее, потому что у них есть воспоминания, которые они не могут забыть».
                            — Цунаде (Naruto)</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <video class="d-block w-100" width="100%" height="80%" src="../../assets/videos/onepiece.webm" loop ></video>
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Лучший информативный сайт для хикканов</h5>
                        <p>«Забывание похоже на рану. Рана может зажить, но шрам от нее уже остался».
                            — Монки Д. Луффи (One Piece)</p>
                    </div>

                </div>
                <div class="carousel-item" >
                    <video class="d-block w-100" width="100%" height="80%" src="../../assets/videos/demonslayer.webm" loop></video>
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Также ты можешь посмотреть анимэхи</h5>
                        <p>«Истинная сила относится не только к физическому телу».
                            — Кёдзюро Ренгоку (Клинок рассекающий демонов)</p>
                    </div>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
            </div>
            <button class="mute-button" onclick="toggleMute()">
                <i class="material-icons">volume_off</i>
            </button>
        </div>
    </div>
</body>
<script>
    var videos = document.getElementsByTagName("video");
    var muteButton = document.querySelector(".mute-button");

    function toggleMute() {
        for (var i = 0; i < videos.length; i++) {
            if (videos[i].muted) {
                videos[i].muted = false;
                muteButton.innerHTML = '<i class="material-icons">volume_off</i>';
            } else {
                videos[i].muted = true;
                muteButton.innerHTML = '<i class="material-icons">volume_up</i>';
            }
        }
    }
    $(document).ready(function() {
        $('.carousel').on('slide.bs.carousel', function() {
            var currentVideo = $(this).find('.carousel-item.active video')[0];
            currentVideo.pause();
        });

        $('.carousel').on('slid.bs.carousel', function() {
            var currentVideo = $(this).find('.carousel-item.active video')[0];
            currentVideo.play();
        });
    });

</script>
<script type="text/javascript" src="<c:url value="/assets/js/murder.js"/>"></script>

</html>
