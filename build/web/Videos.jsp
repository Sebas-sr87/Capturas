<%-- 
    Document   : Videos
    Created on : 09-10-2020, 16:47:59
    Author     : Sebastian Oliveros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Videos</title>
        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript"  src="js/video.js"></script>
        <script type="text/javascript" src="js/Chart.min.js"></script>
        <script type="text/javascript" src="js/chart-2.7.1.js"></script>
        <link href="css/Pantallas.css" rel="stylesheet">
        <style>
            .vertical-menu {
                width: 200px;
            }

            li {
                cursor: pointer;
                background-color: #eee;
                color: black;
                display: block;
                padding: 12px;
                text-decoration: none;
            }

            li:hover {
                background-color: #ccc;
            }
            #menu{
                width: 20%;
                height: 100%;
                display: inline-block;
            }
        </style>
        
    </head>
    <body>
        <div id='menu'> 
            <nav>
                <ul>
                    <li onclick="crearVideo(1)">Sebastian</li>
                    <li onclick="crearVideo(2)">Camila</li>
                    <li onclick="crearVideo(3)">Felipe</li>
                    <li onclick="crearVideo(4)">Mauricio</li>
                    <li onclick="crearVideo(5)">Jose</li>
                </ul>
            </nav>
        </div>
        <div class="contenedores" id="contenedor_video" style="width: 56%;" >
<!--            <video class="video_contenedor" src="http://200.75.13.14:8082/Capturas/Videos/out.mp4" controls=""></video>-->
            <!--<video class="video_contenedor" src="asdasdasd.mp4"></video>-->
        </div>

    </body>
</html>
