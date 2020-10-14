/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Funciones.Query;
import Model.domain.Imagen;
import Model.domain.Supervisor;
import Model.domain.Video;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static logs.MensajesLog.logger;
import twitter4j.JSONException;
import twitter4j.JSONObject;

/**
 *
 * @author Sebastian Oliveros
 */
@WebServlet(name = "Logindos", urlPatterns = {"/Logindos"})
public class crearvideo extends HttpServlet {

    public static final String PARAM_USER_ERROR = "error_login";
    public static final String PARAM_USER = "Usuario";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json");
        String user = request.getParameter("user") == null ? "" : request.getParameter("user");
        String pass = request.getParameter("pass") == null ? "" : request.getParameter("pass");
        int cliente = Integer.valueOf(request.getParameter("cliente"));
        JSONObject rj = new JSONObject();
        PrintWriter out = response.getWriter();
        String clienteAd = "";
        if (cliente == 1) {
            clienteAd = "sebastian";
        } else if (cliente == 2) {
            clienteAd = "camila";
        } else if (cliente == 3) {
            clienteAd = "felipe";
        } else if (cliente == 4) {
            clienteAd = "mauricio";
        } else if (cliente == 5) {
            clienteAd = "jose";
        }
        Calendar fecha = Calendar.getInstance();
        int ano = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String fechacompleta = "-" + ano + "-" + mes + "-" + dia + "-" + hora + "-" + minuto + "-" + segundo + ".mp4";
        String nombreVideo = Query.hora();
        try {
//            String comando2 = " ffmpeg -framerate 1 -i C:\\Users\\img%3d.jpg -r 60 -c:v libx264 -profile:v high -crf 20 -pix_fmt yuv420p -vf pad=ceil(iw/2)*2:ceil(ih/2)*2 C:\\Users\\sadsadasdsad.mp4";
            String comando2 = " ffmpeg -framerate 1 -i /opt/apache-tomcat-9.0.0.M3/webapps/Capturas/Imagenes/" + clienteAd + "%1d.jpg -r 60 -c:v libx264 -profile:v high -crf 20 -pix_fmt yuv420p -vf pad=ceil(iw/2)*2:ceil(ih/2)*2 /opt/apache-tomcat-9.0.0.M3/webapps/Capturas/Videos/" + clienteAd + fechacompleta;

            System.out.println("Start");
            Process p = Runtime.getRuntime().exec(comando2);
            System.out.println("End");
            InputStream in = p.getErrorStream();
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
            }

            in.close();
            Boolean guardarVideo = Query.guardarVideo(cliente, clienteAd + fechacompleta);
            List<Video> nombreVideos = Query.nombreVideos(cliente);
             if (nombreVideos == null) {
                        throw new Exception();
                    } else {
                        try {
                            rj.put("imagen", nombreVideos);
                        } catch (JSONException ex) {
                            out.println("rrrrrrrrrr" + ex.toString());
                        }
                        out.write(rj.toString());
                    }
            System.out.println("terminado");
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error: " + e.toString());
            logger.error("Error Crear : " + e + " " + e.getStackTrace()[0].getLineNumber());
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
