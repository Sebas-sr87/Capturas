<%-- 
    Document   : index.jsp
    Created on : 11-06-2020, 11:53:36
    Author     : Sebastian Oliveros
--%>

<%@page import="Servlet.Login"%>
<%@page import="bd.Conexion"%>
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
     String error = (String) request.getSession().getAttribute(Login.PARAM_USER_ERROR);
            if (error != null) {
                out.println("<div class=\"mensaje_error\">" + error + "</div>");
            }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <style>
            form {
                border: 3px solid #f1f1f1;
                margin: 15% 35%;
            }

            input[type=text], input[type=password] {
                width: 100%;
                padding: 12px 20px;
                margin: 8px 0;
                display: inline-block;
                border: 1px solid #ccc;
                box-sizing: border-box;
            }
            button {
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
            }
            button:hover {
                opacity: 0.8;
            }

            .container {
                padding: 16px;
            }

        </style>
    </head>
    <body>
        <form action="Login" method="post">
            <div class="container">
                <label for="user"><b>Username</b></label>
                <input type="text" placeholder="Username" name="user" required autocomplete="off">

                <label for="pass"><b>Password</b></label>
                <input type="password" placeholder="Password" name="pass" required autocomplete="off">
                <label></label>
                <button type="submit">Login</button>
            </div>

        </form>
    </body>
</html>
