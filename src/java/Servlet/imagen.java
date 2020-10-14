/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Funciones.Query;
import Model.domain.Imagen;
import static Servlet.Login.PARAM_USER_ERROR;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import twitter4j.JSONException;
import twitter4j.JSONObject;

/**
 *
 * @author Sebastian Oliveros
 */
@WebServlet(name = "imagen", urlPatterns = {"/imagen"})
public class imagen extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            Boolean imagen = request.getParameter("imagen") == null ? Boolean.FALSE : Boolean.TRUE;
            Boolean estado = request.getParameter("estado") == null ? Boolean.FALSE : Boolean.TRUE;
            Boolean unaImagen = request.getParameter("unaImagen") == null ? Boolean.FALSE : Boolean.TRUE;
            Boolean cerrarsesion = request.getParameter("cerrar") == null ? Boolean.FALSE : Boolean.TRUE;
            JSONObject rj = new JSONObject();
            if (imagen) {
                try {
                    List<Imagen> traerImagen = Query.traerImagen();
                    if (traerImagen == null) {
                        throw new Exception();
                    } else {
                        try {
                            rj.put("imagen", traerImagen);
                        } catch (JSONException ex) {
                            out.println("rrrrrrrrrr" + ex.toString());
                        }
                        out.write(rj.toString());
                    }
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error: " + e.toString());;

                }
            } else if (unaImagen) {
                try {
                    int id_img = Integer.parseInt(request.getParameter("id_img"));
                    List<Imagen> img = Query.traerUnaImagen(id_img);
                    if (img == null) {
                        throw new Exception();
                    } else {
                        try {
                            rj.put("imagen", img);
                        } catch (JSONException ex) {
                            out.println("rrrrrrrrrr" + ex.toString());
                        }
                        out.write(rj.toString());
                    }
                } catch (Exception e) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal server error: " + e.toString());;

                }

            } else if (cerrarsesion) {
                try {
                    request.getSession().invalidate();
                    rj.put("cierre", true);
                    out.write(rj.toString());

                } catch (Exception e) {
                    System.out.println("Error: " + e.toString());
                }

            } else if (estado) {
                try {
                    Boolean estadoUsuario = Query.estadoUsuario();
                    if (Objects.equals(estadoUsuario, Boolean.FALSE)) {
                        throw new Exception();
                    } else {
                        try {
                            rj.put("estado", estadoUsuario);
                        } catch (JSONException ex) {
                            out.println("rrrrrrrrrr" + ex.toString());
                        }
                    }
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
