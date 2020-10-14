/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import static Funciones.Query.traerImagen;
import com.xuggle.mediatool.IMediaWriter;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;

/**
 *
 * @author Sebastian Oliveros
 */
public class Mapa {

    public static Map<String, byte[]> listaImagenes = new LinkedHashMap<String, byte[]>();
    public static Map<String, IMediaWriter> userVideo = new LinkedHashMap<>();
   public static Map<String, Integer> numero = new HashMap<String, Integer>();

    public static Map<String, Integer> getNumero() {
        return numero;
    }

    public static void setNumero(Map<String, Integer> numero) {
        Mapa.numero = numero;
    }

    

    
    public static Map<String, byte[]> getLinkedHashMap() {
        return listaImagenes;
    }

    public static void setLinkedHashMap(Map<String, byte[]> linkedHashMap) {
        Mapa.listaImagenes = linkedHashMap;
    }

    public static void main(String[] args) throws ParseException {
        String rutaImagenes="C:\\Users\\img%03d.jpg";
        String sdasdas="C:\\Users\\Sebastian Oliveros\\Downloads\\lib>ffmpeg -framerate 1 -i sebastian%1d.jpg -r 60 -c:v libx264 -profile:v high -crf 20 -pix_fmt yuv420p -vf \"pad=ceil(iw/2)*2:ceil(ih/2)*2\" outpuooooosadsadat.mp4";
        String rutaVideo="";
        try {
            System.out.println("Start");
            String comando ="ffmpeg -framerate 1 -i "+rutaImagenes+" -r 60 -c:v libx264 -profile:v high -crf 20 -pix_fmt yuv420p  "+rutaVideo+"sadfsdfdsfsoot.mp4";
            Process p = Runtime.getRuntime().exec(comando);
            System.out.println("End");
            InputStream in = p.getErrorStream();
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
            }
            in.close();
            System.out.println("sadgashdgashd");
        } catch (Exception e) {
            System.out.println("Error :" + e.toString());
        }

//        try {
//
//            EnviarEmail e = new EnviarEmail();
//            Object[] a = new Object[1];
//            a[0] = "daniel@adportas.cl";
//            String subject = "Información sobre las capturas";
//            String message = "Probar si envio el email";
//            String from = "srodriguez@adportas.cl";
//            e.postMail(a, subject, message, 18399, 164, from);
//        } catch (MessagingException ex) {
//            System.out.println(ex);
//        }
    }

}
