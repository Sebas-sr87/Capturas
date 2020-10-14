<%-- 
    Document   : index.jsp
    Created on : 08-06-2020, 9:39:31
    Author     : Sebastian Oliveros
--%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="Funciones.Query.*"%>
<%@page import="Funciones.SizeImagen"%>
<%@page import="Funciones.Comparar"%>
<%@page import="Model.domain.Configuracion"%>
<%@page import="bd.Conexion"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="Funciones.CrearVideo"%>
<%@page import="Funciones.Mapa"%>
<%@page import="Funciones.Mapa.*"%>
<%@page import="java.nio.file.Paths"%>
<%@page import="java.nio.file.Files"%>
<%@page import="java.util.Base64"%>
<%@page import="Funciones.Query"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="com.sun.org.apache.xerces.internal.parsers.DOMParser"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="org.w3c.dom.Element"%>
<%@page import="org.w3c.dom.NodeList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    BufferedReader tttt = request.getReader();
    String h = "";
    String q = "";
    while ((h = tttt.readLine()) != null) {
        String j = h;
        q += j;
    }
    String xml = q;
    if (!xml.isEmpty()) {
        DOMParser parser = new DOMParser();
        parser.parse(new org.xml.sax.InputSource(new java.io.StringReader(q)));
        Document doc = parser.getDocument();
        String nombre = doc.getElementsByTagName("nombre").item(0).getTextContent();
        String imagen = doc.getElementsByTagName("imagen").item(0).getTextContent();
        String nombreImagen = doc.getElementsByTagName("nombreimagen").item(0).getTextContent();
        String numero_video = doc.getElementsByTagName("horavideo").item(0).getTextContent();
        String idusuario = doc.getElementsByTagName("idusuario").item(0).getTextContent();
        int hora = Integer.valueOf(Query.hora());
        if (hora >= 8 && hora <= 20) {
            if (Mapa.numero.get(nombre) == null) {
                Mapa.numero.put(nombre, 0);
            }
            int numero = Mapa.numero.get(nombre);
            String nombreCompleto = nombre + numero + ".jpg";
            Mapa.numero.put(nombre, numero + 1);
            Comparar.comparar(imagen, nombreImagen, Integer.valueOf(idusuario));
            byte[] barr = SizeImagen.imagenBajaCalidad(imagen);
            String ruta = Query.rutaGuardarImagen();
           
//        String ruta = "C:\\Users\\Sebastian Oliveros\\Downloads\\lib\\";
//        String ruta="/opt/apache-tomcat-9.0.0.M3/webapps/Sapeo/Imagenes/";
            Files.write(Paths.get(ruta + nombreCompleto), barr);
            Boolean guardo = Query.insertarCaptura(nombre, imagen, nombreCompleto, idusuario);
             Query.cambiarEstadoVideos(true, Integer.parseInt(idusuario));
        } else if (hora == 1) {
            Comparar.eliminarArchivos();
        } else {
            Mapa.numero.put("jose", 0);
            Mapa.numero.put("sebastian", 0);
            Mapa.numero.put("felipe", 0);
            Mapa.numero.put("camila", 0);
            Mapa.numero.put("mauricio", 0);
        }
//        if (guardo) {
//            int ultimo = nombreImagen.indexOf("4");
//            Mapa.listaImagenes.put(nombreImagen, barr);
//            if (ultimo != -1) {
//                //lo que pasara cuando sea la ultima imagen de la hora, creara el video
//                CrearVideo v = new CrearVideo();
//                v.crearVideo(numero_video, ruta);
//            }
//
//        }
    }


%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-1.11.1.min.js"></script>
        <title>Capturas</title>

    </head>
    <body>

        <h1>Bienvenido XML de captura!</h1>
    </body>
</html>
