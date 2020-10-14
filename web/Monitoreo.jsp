<%-- 
    Document   : Monitoreo
    Created on : 10-06-2020, 10:57:29
    Author     : Sebastian Oliveros
--%>

<%@page import="bd.Conexion"%>
<%@page import="Servlet.Login"%>
<%@page import="Model.domain.Supervisor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
    public void jspInit() {
        Conexion.host = getServletContext().getInitParameter("host-database");
        Conexion.database = getServletContext().getInitParameter("name-database");
        Conexion.user = getServletContext().getInitParameter("user-database");
        Conexion.pass = getServletContext().getInitParameter("pass-database");
    }
%>

<%
    Supervisor s = (Supervisor) request.getSession().getAttribute(Login.PARAM_USER);
    if (s == null) {
        response.sendRedirect("index.jsp");
    }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="refresh" content="60">
        <title>Monitoreo</title>
        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <script type="text/javascript"  src="js/Pantallas.js"></script>
        <script type="text/javascript" src="js/Chart.min.js"></script>
        <script type="text/javascript" src="js/chart-2.7.1.js"></script>
        <link href="css/Pantallas.css" rel="stylesheet"> 
    </head>
    <body>
                <div id="myModal" class="modal">
                    <span class="close" onclick="cerrar(this)">&times;</span>
                    <img class="modal-content" id="img01">
                    <div id="caption"></div>
                </div>
                <div style="width: 100%;height: 20px;">
                    <p style="margin: 5px 0px 0px 90%;cursor: pointer;" onclick="cerrarsesion();">Cerrar sesion</p>
                </div>
<!--        <table>
            <tbody>
                <tr>
                    <td>
                        <table>
                            <tr><img src="http://192.168.100.144:8080/Capturas/Imagenes/Horatres1-sebastian -19-06-2020 04-20.jpg"</tr>
                            <tr></tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table></table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table></table>
                    </td>
                </tr>
            </tbody>>
        </table>--> 
    </body>
    
</html>
