/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import Funciones.EnviarEmail;
import Funciones.Query;
import Model.domain.Configuracion;
import Model.domain.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.mail.MessagingException;

/**
 *
 * @author Sebastian Oliveros
 */
public class Conexion {

    public static String host = "192.168.100.144";
    public static String database = "Capturar";
    public static String user = "postgres";
    public static String pass = "adp2019";
    public static String port = "5432";
//    public static String host ="192.168.100.132";
//    public static String database ="Capturar";
//    public static String user ="postgres";
//    public static String pass ="adp2015";
//    public static String port ="5432";

    public static Connection conectar() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://" + host + ":5432/" + database, user, pass);
        } catch (Exception e) {
            System.out.println("Error en conectar de ConexionPostgres: " + e);
        }
        return con;
    }

    public static Connection conectar(Configuracion conf) {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:postgresql://" + conf.getHost() + ":5432/" + conf.getDatabase(), conf.getUser(), conf.getPass());
        } catch (Exception e) {
            System.out.println("Error en conectar de ConexionPostgres: " + e);
        }
        return con;
    }

    public static void main(String[] args) throws ParseException, MessagingException {
        Usuario traerUsuario = Query.traerUsuario(1);
        System.out.println("traerUsuario = " + traerUsuario);
    }
}
