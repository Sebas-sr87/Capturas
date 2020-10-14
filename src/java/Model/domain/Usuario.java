/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.domain;

/**
 *
 * @author Sebastian Oliveros
 */
public class Usuario {
    public static int id_usuario;
    public static String nombre;
    public static String apellido;
    public static String clave;
    public static boolean estado;
    public static Usuario user;
    public static String correo;

    public static String getCorreo() {
        return correo;
    }

    public static void setCorreo(String correo) {
        Usuario.correo = correo;
    }
    

    public static Usuario getUser() {
        return user;
    }

    public static void setUser(Usuario user) {
        Usuario.user = user;
    }

    public static int getId_usuario() {
        return id_usuario;
    }

    public static void setId_usuario(int id_usuario) {
        Usuario.id_usuario = id_usuario;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        Usuario.nombre = nombre;
    }

    public static String getApellido() {
        return apellido;
    }

    public static void setApellido(String apellido) {
        Usuario.apellido = apellido;
    }

    public static String getClave() {
        return clave;
    }

    public static void setClave(String clave) {
        Usuario.clave = clave;
    }

    public static boolean isEstado() {
        return estado;
    }

    public static void setEstado(boolean estado) {
        Usuario.estado = estado;
    }
            
}
