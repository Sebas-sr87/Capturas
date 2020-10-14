<%-- 
    Document   : notificacion
    Created on : 12-10-2020, 13:24:23
    Author     : Sebastian Oliveros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="js/push.min.js"></script>
        <script>
            Push.create("Hola mundo",{
			body: "Este es el cuerpo de la notificacion",
			icon: "img/logo.png",
			timeout: 4000,
			onClick: function () {
				window.location="https://nickersoft.github.io/push.js/";
				this.close();
			}
		});
            
        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
