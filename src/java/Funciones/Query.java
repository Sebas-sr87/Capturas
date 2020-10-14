/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import Model.domain.Configuracion;
import Model.domain.Imagen;
import Model.domain.Supervisor;
import Model.domain.Usuario;
import Model.domain.Video;
import Servlet.Actividad;
import bd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastian Oliveros
 */
public class Query {

    public static Configuracion CONFIGURACION;

    public static Boolean insertarCaptura(String usuario, String imagen, String nombreImagen, String idusuario) {
        Boolean guardar = Boolean.FALSE;
        String sql = "insert into captura (id_captura,nombre,fecha,imagen,nombre_imagen,id_usuario) values (default,?,now(),?,?,?);";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, imagen);
            ps.setString(3, nombreImagen);
            ps.setInt(4, Integer.valueOf(idusuario));
            boolean execute = ps.execute();
            if (execute == false) {
                guardar = Boolean.TRUE;
            }
        } catch (Exception e) {
            System.out.println("Error consulta insertarCaptura: " + e.toString());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return guardar;
    }

    public static void cambiarEstadoVideos(boolean estado, int id_usuario) {
        String query = "update enviarcorreo set enviado=? where id_usuario=?";
        Connection conexion = null;
        PreparedStatement ps = null;
        try {
            conexion = Conexion.conectar();
            ps = conexion.prepareStatement(query);
            ps.setBoolean(1, estado);
            ps.setInt(2, id_usuario);
            int i = ps.executeUpdate();
            if (i != 0) {
                estado = Boolean.TRUE;
            }
        } catch (Exception e) {
            System.out.println("Error consulta cambiarEstadoVideos: " + e.toString());
        } finally {
            try {
                conexion.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static boolean usuarioEnvioCorreo(int id_usuario) {
        boolean usuario = false;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from enviarcorreo where id_usuario=?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_usuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                usuario = rs.getBoolean("enviado");
            }
        } catch (Exception e) {
            System.out.println("Error usuarioEnvioCorreo: " + e.toString());
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return usuario;
    }

    public static String rutaGuardarImagen() {
        String ruta = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select ruta from ruta";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ruta = rs.getString("ruta");
            }

        } catch (Exception e) {
            System.out.println("Error rutaGuardarImagen: " + e.toString());
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return ruta;
    }

    public static List traerUnaImagen(int id_img) {
        List<Imagen> imagenes = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from captura where id_captura=?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_img);
            rs = ps.executeQuery();
            while (rs.next()) {
                Imagen img = new Imagen();
                img.setId_imagen(rs.getInt("id_captura"));
                img.setImg(rs.getString("imagen"));
                img.setNombre_imagen(rs.getString("nombre_imagen"));
                img.setNombre_usuario(rs.getString("nombre"));
                Timestamp fechadesde = rs.getTimestamp("fecha");
                img.setFecha(new SimpleDateFormat("dd-MM-yyyy hh:mm").format(fechadesde));
                img.setId_usuario(rs.getInt("id_usuario"));
                imagenes.add(img);
            }

        } catch (Exception e) {
            System.out.println("Error rutaGuardarImagen: " + e.toString());
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return imagenes;
    }

    public static String compararFechas(String fechaUltimo) throws ParseException {
        String diferencia = "";
        Calendar c = Calendar.getInstance();
        String fechaAhora = Query.lahora();

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh-mm");

        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaUlti = Calendar.getInstance();

        fechaInicio.setTime(sdf.parse(fechaAhora));
        fechaUlti.setTime(sdf.parse(fechaUltimo.replace(":", "-")));

        long finms = fechaUlti.getTimeInMillis();
        long inicioms = fechaInicio.getTimeInMillis();
        int minutos = (int) (Math.abs((inicioms - finms)) / (1000 * 60));

        return minutos + "";
    }

    public static Usuario traerUsuario(int id_usuario) {
        Usuario user = new Usuario();
Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from usuario where id_usuario=?";
        String img = "";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_usuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                user.setId_usuario(rs.getInt("id_usuario"));
                user.setNombre(rs.getString("nombre"));
                user.setClave(rs.getString("clave"));
                user.setApellido(rs.getString("apellido"));
                user.setEstado(rs.getBoolean("estado"));
                user.setCorreo(rs.getString("correo"));
            }

        } catch (Exception e) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, "Error en traer ultima imagen para comparar", e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, "Cerrar connection", ex);
            }
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, "Cerrar PreparedStatement", ex);
            }
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, "Cerrar ResultSet", ex);
            }
        }
        return user;
    }

    public static List traerImagen() {
        List<Imagen> imagenes = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from ultimosregintrossinimg";
        /*
         SELECT c.id_captura, c.fecha - '01:00:00'::interval AS fecha, c.nombre_imagen, u.nombre, u.apellido, u.id_usuario
         FROM usuario u, captura c
         WHERE u.id_usuario = c.id_usuario AND (c.fecha IN ( SELECT max(c.fecha) AS max
         FROM captura c
         GROUP BY c.id_usuario))
         ORDER BY c.id_usuario;
        
         */

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Imagen img = new Imagen();
                img.setId_imagen(rs.getInt("id_captura"));
//                img.setImg(rs.getString("imagen"));
                img.setNombre_imagen(rs.getString("nombre_imagen"));
                img.setNombre_usuario(rs.getString("nombre"));
                img.setApellido(rs.getString("apellido"));
                Timestamp fechadesde = rs.getTimestamp("fecha");
                String fecha = new SimpleDateFormat("dd-MM-yyyy hh:mm").format(fechadesde);
                img.setFecha(fecha);
                String compararFechas = compararFechas(fecha);
                img.setDiferencia_minutos(compararFechas);
                img.setId_usuario(rs.getInt("id_usuario"));
                imagenes.add(img);
            }

        } catch (Exception e) {
            System.out.println("Error rutaGuardarImagen: " + e.toString());
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return imagenes;
    }

    public static String horario() {
        String hora = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from ultimosregintrossinimg";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                hora = rs.getString("hora");
            }

        } catch (Exception e) {
            System.out.println("Error horario: " + e.toString());
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return hora;

    }

    public static Boolean estadoUsuario() {
        Boolean estado = Boolean.FALSE;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select estado from usuario where id_usuario=1";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                estado = rs.getBoolean("estado");
            }
        } catch (Exception e) {
            System.out.println("Error estadoUsuario: " + e.toString());
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return estado;

    }

    public static String traerUltimaImagen(int id_usuario) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from captura where fecha= (select max(fecha) from captura where id_usuario=?) and id_usuario=?";
        String img = "";

        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_usuario);
            ps.setInt(2, id_usuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                img = rs.getString("imagen");
                System.out.println("Nombre de ultima imagen: " + rs.getString("nombre_imagen"));
            }

        } catch (Exception e) {
            Logger.getLogger(Query.class.getName()).log(Level.SEVERE, "Error en traer ultima imagen para comparar", e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, "Cerrar connection", ex);
            }
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, "Cerrar PreparedStatement", ex);
            }
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, "Cerrar ResultSet", ex);
            }
        }
        return img;
    }

    public static String lahora() {
        Boolean estado = Boolean.FALSE;
        String hora = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select now() as fechahora";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Timestamp fechadesde = rs.getTimestamp("fechahora");
                hora = new SimpleDateFormat("dd-MM-yyyy hh-mm").format(fechadesde);
            }
        } catch (Exception e) {
            System.out.println("Error lahora: " + e.toString());
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return hora;
    }

    public static Boolean guardarVideo(int id_usuario, String nombre) {
        Boolean guardar = Boolean.FALSE;
        String sql = "insert into videos(id_video,nombre,id_usuario,fecha) values (default,?,?,now());";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, id_usuario);
            boolean execute = ps.execute();
            if (execute == false) {
                guardar = Boolean.TRUE;
            }
        } catch (Exception e) {
            System.out.println("Error consulta inicioActividad: " + e.toString());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return guardar;
    }

    public static List nombreVideos(int ciente) {
        List<Video> videos = new ArrayList<>();
        String nombreVideo = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from videos where fecha=(select max(fecha) AS max from videos where id_usuario=?)";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ciente);
            rs = ps.executeQuery();
            while (rs.next()) {
                Video v = new Video();
                v.setId_video(rs.getInt("id_video"));
                v.setNombre(rs.getString("nombre"));
                Timestamp fechadesde = rs.getTimestamp("fecha");
                String fecha = new SimpleDateFormat("dd-MM-yyyy hh:mm").format(fechadesde);
                v.setFecha(fecha);
                v.setId_usuario(rs.getInt("id_usuario"));
                videos.add(v);
                videos.add(v);
            }
        } catch (Exception e) {
            System.out.println("Error Nombre del video: " + e.toString());
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return videos;
    }

    public static String hora() {
        Boolean estado = Boolean.FALSE;
        String hora = "";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT date_part('hours', (SELECT current_timestamp)) as fecha";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                hora = rs.getString("fecha");
            }
        } catch (Exception e) {
            System.out.println("Error lahora: " + e.toString());
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return hora;
    }

    public static Supervisor loginUser(String user, String pass) {
        Boolean valido = Boolean.FALSE;
        Supervisor s = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from supervisor where nombre=? and clave=?";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                s = new Supervisor();
                s.setId_usuario(rs.getInt("id_supervisor"));
                s.setUser(rs.getString("nombre"));
                s.setPass(rs.getString("clave"));
                s.setEstado(rs.getString("estado"));
            }
        } catch (Exception e) {
            System.out.println("Error estadoUsuario: " + e.toString());
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return s;
    }

    public static void sumarActividad(int id_usuario, int actividad) {
        if (!comprobarInicioActividad(id_usuario)) {
            inicioActividad(id_usuario);
        }
        if (comprobarInicioActividad(id_usuario)) {
            actualizarActividad(id_usuario, actividad);
        } else {
            System.out.println("");
        }

    }

    public static Boolean actualizarActividad(int id_usuario, int actividad) {
        Boolean actualizar = Boolean.FALSE;
        String sql = "update actividad ";
        if (actividad == 1) {
            sql += " set actividad=actividad+1 ";
        } else {
            sql += " set inactividad=inactividad+1 ";
        }
        sql += " where id_usuario=? and mes=(SELECT EXTRACT(month FROM now())) and dia=(SELECT EXTRACT(day FROM now()))";
        Connection conexion = null;
        PreparedStatement ps = null;
        try {
            conexion = Conexion.conectar();
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id_usuario);
            int i = ps.executeUpdate();
            if (i != 0) {
                actualizar = Boolean.TRUE;
            }
        } catch (Exception e) {
            System.out.println("Error consulta actualizarActividad: " + e.toString());
        } finally {
            try {
                conexion.close();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return actualizar;
    }

    public static Boolean inicioActividad(int id_usuario) {
        Boolean guardar = Boolean.FALSE;
        String sql = "insert into actividad(id_actividad,mes,dia,id_usuario,actividad,inactividad) values(default,(SELECT EXTRACT(month FROM now())),(SELECT EXTRACT(day FROM now())),?,0,0);";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = Conexion.conectar();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id_usuario);
            boolean execute = ps.execute();
            if (execute == false) {
                guardar = Boolean.TRUE;
            }
        } catch (Exception e) {
            System.out.println("Error consulta inicioActividad: " + e.toString());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return guardar;
    }

    public static Boolean comprobarInicioActividad(int id_usuario) {
        Boolean inicio = Boolean.FALSE;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from actividad where  mes =(SELECT EXTRACT(month FROM now())) and dia = (SELECT EXTRACT(day FROM now())) and id_usuario=?;";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id_usuario);
            rs = ps.executeQuery();
            while (rs.next()) {
                inicio = Boolean.TRUE;
            }
        } catch (Exception e) {
            System.out.println("Error comprobarInicioActividad: " + e.toString());
        } finally {
            try {
                con.close();
                ps.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return inicio;
    }

    public static List Actividad() {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        List actividad = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from actividad where mes=(SELECT EXTRACT(month FROM now())) and dia=(SELECT EXTRACT(days FROM now()))";
        try {
            con = Conexion.conectar();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Actividad a = new Actividad();
                a.setId_actividad(rs.getInt("id_actividad"));
                a.setMes(rs.getInt("mes"));
                a.setDia(rs.getInt("dia"));
                a.setActividad(rs.getInt("actividad"));
                a.setInactividad(rs.getInt("inactividad"));
                a.setId_usuario(rs.getInt("id_usuario"));
                actividad.add(a);
            }
        } catch (Exception e) {
            System.out.println("Error Actividad: " + e.toString());
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, "Cerrar connection", ex);
            }
            try {
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, "Cerrar PreparedStatement", ex);
            }
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Query.class.getName()).log(Level.SEVERE, "Cerrar ResultSet", ex);
            }
        }
        return actividad;
    }
}
