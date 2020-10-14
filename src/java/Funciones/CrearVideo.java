/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

/**
 *
 * @author Sebastian Oliveros
 */
import static Funciones.Videos.convertToType;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IRational;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.tomcat.jni.Proc;

public class CrearVideo {

    private static final double FRAME_RATE = 50;

    private static final int SECONDS_TO_RUN_FOR = 20;

    private static Dimension pantalla;

    static int i = 0;
        static Calendar c1 = Calendar.getInstance();

    public static void main(String[] args) throws IOException, InterruptedException, AWTException {
        String user = "1";
        IMediaWriter get = Mapa.userVideo.get(user);
        if (get == null) {
            IMediaWriter usuario1 = ToolFactory.makeWriter("C:/Users/Sebastian Oliveros/Downloads/lib/videoUsuario" + user + "adsfv.mp4");

            Mapa.userVideo.put(user, usuario1);
        }
        IMediaWriter get1 = Mapa.userVideo.get(user);
        List<String> imagenes = new ArrayList<>();
        imagenes.add("monitoreo1.png");
        imagenes.add("monitoreo2.png");
        imagenes.add("monitoreo3.png");
        imagenes.add("monitoreo4.png");
        imagenes.add("monitoreo5.png");
        imagenes.add("monitoreo6.png");
        imagenes.add("monitoreo7.png");
        imagenes.add("monitoreo8.png");
        imagenes.add("monitoreo9.png");
        imagenes.add("monitoreo10.png");
        imagenes.add("monitoreo1.png");
        imagenes.add("monitoreo2.png");
        imagenes.add("monitoreo3.png");
        imagenes.add("monitoreo4.png");
        imagenes.add("monitoreo5.png");
        imagenes.add("monitoreo6.png");
        imagenes.add("monitoreo7.png");
        imagenes.add("monitoreo8.png");
        imagenes.add("monitoreo9.png");
        imagenes.add("monitoreo10.png");
        imagenes.add("monitoreo1.png");
        imagenes.add("monitoreo2.png");
        imagenes.add("monitoreo3.png");
        imagenes.add("monitoreo4.png");
        imagenes.add("monitoreo5.png");
        imagenes.add("monitoreo6.png");
        imagenes.add("monitoreo7.png");
        imagenes.add("monitoreo8.png");
        imagenes.add("monitoreo9.png");
        imagenes.add("monitoreo10.png");

        Dimension screenBounds = Toolkit.getDefaultToolkit().getScreenSize();
        get1.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, IRational.make(1 / 1000), screenBounds.width / 2, screenBounds.height / 2);
        
        BufferedImage screen = obtenerImagenes(imagenes.get(1));
        long startTime = System.nanoTime();
            BufferedImage bgrScreen = convertToType(screen,
                    BufferedImage.TYPE_3BYTE_BGR);
            get1.encodeVideo(0, bgrScreen, System.nanoTime() - startTime,
                    TimeUnit.NANOSECONDS);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignore
//                System.out.println("Error : " + e.toString());
            }
        Thread.sleep(1000);
        Mapa.userVideo.put(user, get1);
        
        
        
        
        
        IMediaWriter get2 = Mapa.userVideo.get(user);
        BufferedImage screen1 = obtenerImagenes(imagenes.get(2));
        
            BufferedImage bgrScreen1 = convertToType(screen1,
                    BufferedImage.TYPE_3BYTE_BGR);
            get2.encodeVideo(0, bgrScreen1, System.nanoTime() - startTime,
                    TimeUnit.NANOSECONDS);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignore
//                System.out.println("Error : " + e.toString());
            }
        Thread.sleep(1000);
        Mapa.userVideo.put(user, get2);
        
        
        
        
        IMediaWriter get3 = Mapa.userVideo.get(user);
        BufferedImage screen2 = obtenerImagenes(imagenes.get(3));
        BufferedImage bgrScreen2 = convertToType(screen2,
                    BufferedImage.TYPE_3BYTE_BGR);
            get3.encodeVideo(0, bgrScreen2, System.nanoTime() - startTime,
                    TimeUnit.NANOSECONDS);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignore
//                System.out.println("Error : " + e.toString());
            }
        Mapa.userVideo.put(user, get3);
        Thread.sleep(1000);
        
        
        
        
        
        IMediaWriter get4 = Mapa.userVideo.get(user);
        BufferedImage screen3 = obtenerImagenes(imagenes.get(4));
         BufferedImage bgrScreen3 = convertToType(screen3,
                    BufferedImage.TYPE_3BYTE_BGR);
            get2.encodeVideo(0, bgrScreen3, System.nanoTime() - startTime,
                    TimeUnit.NANOSECONDS);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignore
//                System.out.println("Error : " + e.toString());
            }
        Mapa.userVideo.put(user, get4);
        Thread.sleep(1000);
        
        
        
        
        
        IMediaWriter get5 = Mapa.userVideo.get(user);
        get5.close();
//        Runtime runtime = Runtime.getRuntime();
//        try {
//            Process process = runtime.exec("powershell.exe get-process");
//            InputStream errorStream = process.getErrorStream();
//            System.out.println(errorStream);
//            process.getOutputStream().close();
//            
//            String line;
//            BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            while ((line = stdout.readLine()) != null) {
//                System.out.println(line);
//            }
//
//            TimeUnit.SECONDS.sleep(2);
//        } catch (IOException ex) {
//            System.out.println("Error");
//            System.exit(-1);
//        }
    }

    public static IMediaWriter agregarImagenVideo(IMediaWriter videoUser, BufferedImage imagen, String user) {
        try {
            long startTime = System.nanoTime();
            BufferedImage bgrScreen = convertToType(imagen,
                    BufferedImage.TYPE_3BYTE_BGR);
            videoUser.encodeVideo(0, bgrScreen, System.nanoTime() - startTime,
                    TimeUnit.NANOSECONDS);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignore
//                System.out.println("Error : " + e.toString());
            }

        } catch (Exception e) {
//            System.out.println("Error agregarImagenVideo: " + e.toString());
        }
        return videoUser;
    }

    public static IMediaWriter agregarImgVideo(IMediaWriter videoUser, BufferedImage imagen, String user) {
        try {
            long startTime = System.nanoTime();
            Dimension screenBounds = Toolkit.getDefaultToolkit().getScreenSize();
            videoUser.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, IRational.make(1 / 1000), imagen.getWidth() / 2, imagen.getHeight() / 2);
            BufferedImage screen = imagen;
            BufferedImage bgrScreen = convertToType(screen, BufferedImage.TYPE_3BYTE_BGR);
            videoUser.encodeVideo(0, bgrScreen, System.nanoTime() - startTime, TimeUnit.NANOSECONDS);

        } catch (Exception e) {
//            System.out.println("Error agregarimgvideo: " + e.toString());
        }

        return videoUser;
    }

    public static BufferedImage convertToType(BufferedImage sourceImage, int targetType) {

        BufferedImage image;
        if (sourceImage.getType() == targetType) {
            image = sourceImage;
        } else {
            image = new BufferedImage(sourceImage.getWidth(),
                    sourceImage.getHeight(), targetType);
            image.getGraphics().drawImage(sourceImage, 0, 0, null);
        }
        return image;
    }

    private static BufferedImage getDesktopScreenshot() {
        try {
            Robot robot = new Robot();
            Rectangle captureSize = new Rectangle(pantalla);
            return robot.createScreenCapture(captureSize);
        } catch (AWTException e) {
            e.printStackTrace();
            return null;

        }
    }

    public void crearVideo(String numerovideo, String ruta) {
        Map<String, byte[]> imagenes = Mapa.listaImagenes;
        Iterator it = imagenes.keySet().iterator();
        List<byte[]> lista_imagenes = new ArrayList<>();
        List<String> lista_ids = new ArrayList<>();
        while (it.hasNext()) {
            String key = (String) it.next();
            lista_imagenes.add(imagenes.get(key));
            lista_ids.add(key);
        }

        int mes = c1.get(Calendar.MONTH);
        int anho = c1.get(Calendar.YEAR);
        int dia = c1.get(Calendar.DAY_OF_MONTH);
        String fecha = dia + "-" + mes + "-" + anho + "";
        String destino = ruta + fecha + "-Parte" + numerovideo + ".mp4";

        final IMediaWriter writer = ToolFactory.makeWriter(destino);
        pantalla = Toolkit.getDefaultToolkit().getScreenSize();
//        System.out.println("Se agregar parametros al video y se inicia");
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, pantalla.width, pantalla.height);

        long startTime = System.nanoTime();
        try {
            for (int index = 0; index < lista_imagenes.size(); index++) {

//                System.out.println("Se agregara nueva imagen al video: imagen" + index);
//                BufferedImage screen = obtenerImagenes("Horauno" + index);
                BufferedImage screen = crearImagenByte(lista_imagenes.get(index));
//                BufferedImage img = convertToType(screen, BufferedImage.TYPE_3BYTE_BGR);
                writer.encodeVideo(0, screen, System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
                try {
                    Thread.sleep(950);
                } catch (InterruptedException e) {
//                    System.out.println("Error en sleep: " + e.toString());
                }
            }
        } catch (Exception e) {
//            System.out.println("Error crearVideo: " + e.toString());
        }

        writer.close();
        removerIds(lista_ids);
//        System.out.println("El video ha terminado de ser procesado");
    }

    public static void removerIds(List idsImagenes) {
        Map listaImagenes = Mapa.listaImagenes;
        try {
            for (int j = 0; j < idsImagenes.size(); j++) {
                listaImagenes.remove(idsImagenes.get(j));
            }
//            System.out.println("Se ha limpiado el Map");
        } catch (Exception e) {
//            System.out.println("Error en limiar Map: " + e.toString());
        }
    }

    private static BufferedImage obtenerImagenes(String nombre) throws IOException, AWTException {

        File imgLoc = new File("C:\\Users\\Sebastian Oliveros\\Downloads\\lib\\" + nombre);
        BufferedImage img;
        img = ImageIO.read(imgLoc);
        return img;

    }

    private BufferedImage crearImagenByte(byte[] imageData) {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageData);
        try {
            return ImageIO.read(bais);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
