/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Funciones.EnviarEmail;
import Funciones.Query;
import Model.domain.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.JSONObject;

/**
 *
 * @author Sebastian Oliveros
 */
@WebServlet(name = "correo", urlPatterns = {"/correo"})
public class correo extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            JSONObject rj = new JSONObject();

            Boolean correo = request.getParameter("correo") == null ? Boolean.FALSE : Boolean.TRUE;
            if (correo) {
                try {
                    String cliente = request.getParameter("cliente");
                    String usuario = request.getParameter("usuario");
                    int minutos = Integer.parseInt(request.getParameter("minutos"));
                    String receptor = "";
                    String mensaje = "";
                    if (minutos == 1) {
                        Usuario user = Query.traerUsuario(Integer.parseInt(usuario));
                        receptor = user.getCorreo();
                        mensaje = "No has enviado capturas";
                    } else if (minutos == 2) {
//                    receptor="srodriguez@adportas.cl";
                        receptor = "daniel@adportas.cl";
                        mensaje = "El cliente " + cliente + " no ha enviado sus capturas hace mas de 10 minutos";
                    }

                    EnviarEmail e = new EnviarEmail();
                    Object[] a = new Object[1];
                    a[0] = receptor;//para quien
                    String subject = "Informacion sobre las capturas";
                    String message = mensaje;
                    String from = "daniel@adportas.cl";//quien lo manda
                    e.postMail(a, subject, message, 18399, 164, from);
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error: " + e.toString());;

                }
            }
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
