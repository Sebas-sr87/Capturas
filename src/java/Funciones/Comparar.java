/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import static Funciones.Query.comprobarInicioActividad;
import static Funciones.Query.inicioActividad;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Sebastian Oliveros
 */
public class Comparar {

    public static BufferedImage imagen1 = null;
    public static BufferedImage imagen2 = null;
    private static final int factor1 = 1;
    private static final int factor2 = 2;

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Sebastian Oliveros\\Pictures\\AnyDesk\\ad.jpg");
        BufferedImage bf = ImageIO.read(file);
        BufferedImage resize = resize(bf);
        File outputfile = new File("C:\\Users\\Sebastian Oliveros\\Pictures\\AnyDesk\\acg.jpg");
        ImageIO.write(resize, "jpg", outputfile);
    }

    public static BufferedImage resize(BufferedImage img) {
        Image tmp = img.getScaledInstance(337, 192, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(337, 192, img.getType());

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }

    private static BufferedImage resizeImageWithHint(BufferedImage originalImage, int type) {
        int IMG_WIDTH = (originalImage.getWidth() * factor1) / factor2;
        int IMG_HEIGHT = (originalImage.getHeight() * factor1) / factor2;
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        g.setComposite(AlphaComposite.Src);

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        return resizedImage;
    }

    public static void eliminarArchivos() {
        try {
            String comando1 = "rm -rf Imagenes";
            String comando2 = "mkdir Imagenes";
            String comando3 = "mkdir Hola";

            comandoLinux(comando1);
            Thread.sleep(300000);
            comandoLinux(comando2);
             comandoLinux(comando3);
        } catch (Exception e) {
        }

    }

    public static void comandoLinux(String comando) {
        try {

            System.out.println("Start");
            Process p = Runtime.getRuntime().exec(comando);
            System.out.println("End");
            InputStream in = p.getErrorStream();
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
            }

            in.close();
        } catch (Exception e) {

        }

    }
    public static void comparar(String imgNueva, String nombre, int id_usuario) {
        String imagenOld = Query.traerUltimaImagen(id_usuario);
        try {
            BufferedImage imgOld = transImagen(imagenOld);
            BufferedImage imgNew = transImagen(imgNueva);
            imgOld = recortarImagen(imgOld);
            imgNew = recortarImagen(imgNew);
            compararImagenes(imgOld, imgNew, id_usuario);
        } catch (Exception e) {
            System.out.println("Error comparar: " + e.toString());
        }

    }

    public static BufferedImage recortarImagen(BufferedImage imagen) {
        BufferedImage subimage = null;
        try {
            BufferedImage img1 = imagen;
            int height = img1.getHeight();
            int width = img1.getWidth();
            int comienzox = 50;
            int comienzoy = 50;
            subimage = img1.getSubimage(comienzox, comienzoy, (width - 100), (height - 100));

        } catch (Exception ex) {
            Logger.getLogger(Comparar.class.getName()).log(Level.SEVERE, "Error en recortar Imagen", ex);
        }
        return subimage;
    }

    public static BufferedImage transImagen(String img) {
        BufferedImage imagenTransformada = null;
        try {
            byte[] imagenbyte = Base64.getDecoder().decode(img);
            ByteArrayInputStream bais = new ByteArrayInputStream(imagenbyte);
            imagenTransformada = ImageIO.read(bais);
        } catch (Exception e) {
            System.out.println("Error en cambiar de String a BufferedImage: " + e.toString());
        }
        return imagenTransformada;
    }
    public static String transImagen(BufferedImage img) {
        String imagentexto = "";
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            byte src[] = baos.toByteArray();
            imagentexto = Base64.getEncoder().encodeToString(src);
        } catch (Exception e) {
        }
        return imagentexto;
    }

    public static void compararImagenes(BufferedImage imgOld, BufferedImage imgNew, int id_usuario) {
        int widthOld = imgOld.getWidth();
        int heightOld = imgOld.getHeight();
        int widthNew = imgNew.getWidth();
        int heightNew = imgNew.getHeight();

        long diferencia = 0;
        for (int y = 0; y < heightOld; y++) {
            for (int x = 0; x < widthOld; x++) {
                int rgbA = imgOld.getRGB(x, y);
                int rgbB = imgNew.getRGB(x, y);
                int redA = (rgbA >> 16) & 0xff;
                int greenA = (rgbA >> 8) & 0xff;
                int blueA = (rgbA) & 0xff;
                int redB = (rgbB >> 16) & 0xff;
                int greenB = (rgbB >> 8) & 0xff;
                int blueB = (rgbB) & 0xff;
                diferencia += Math.abs(redA - redB);
                diferencia += Math.abs(greenA - greenB);
                diferencia += Math.abs(blueA - blueB);
            }
        }
//
        double total_pixels = widthOld * heightOld * 3;
        double avg_different_pixels = diferencia / total_pixels;
        double porcentaje = (avg_different_pixels / 255) * 100;
        if (porcentaje > 0) {
            Query.sumarActividad(id_usuario, 1);
        } else if (porcentaje == 0) {
            Query.sumarActividad(id_usuario, 0);
        }

    }

}
