/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.imageio.ImageIO;
import org.jcodec.api.SequenceEncoder;

public class Videos {

    private static final double FRAME_RATE = 50;

    private static final int SECONDS_TO_RUN_FOR = 20;

    private static final String outputFilename = "C:/Users/Sebastian Oliveros/Downloads/lib/mydesktop4.mp4";

    private static Dimension screenBounds;
    public static IMediaWriter usuario1 = ToolFactory.makeWriter("C:/Users/Sebastian Oliveros/Downloads/lib/videoUsuario1.mp4");

    public static void main(String[] args) throws IOException, AWTException {

        // let's make a IMediaWriter to write the file.
        final IMediaWriter writer = ToolFactory.makeWriter(outputFilename);
        screenBounds = Toolkit.getDefaultToolkit().getScreenSize();
        // We tell it we're going to add one video stream, with id 0,
        // at position 0, and that it will have a fixed frame rate of FRAME_RATE.
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, IRational.make(1 / 1000), screenBounds.width / 2, screenBounds.height / 2);
        long startTime = System.nanoTime();
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
        for (int index = 0; index < 20; index++) {
// take the screen shot
            BufferedImage screen = obtenerImagenes(imagenes.get(index));
// convert to the right image type
            BufferedImage bgrScreen = convertToType(screen,
                    BufferedImage.TYPE_3BYTE_BGR);
// encode the image to stream #0
            writer.encodeVideo(0, bgrScreen, System.nanoTime() - startTime,
                    TimeUnit.NANOSECONDS);
// sleep for frame rate milliseconds

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignore
                System.out.println("Error : " + e.toString());
            }
        }
        // tell the writer to close and write the trailer if  needed
        writer.close();
      
    }
 private static BufferedImage obtenerImagenes(String nombre) throws IOException, AWTException {

        File imgLoc = new File("C:\\Users\\Sebastian Oliveros\\Downloads\\lib\\" + nombre );
        BufferedImage img;
        img = ImageIO.read(imgLoc);
        return img;

    }
    public static BufferedImage convertToType(BufferedImage sourceImage, int targetType) {

        BufferedImage image;
        // if the source image is already the target type, return the source image
        if (sourceImage.getType() == targetType) {
            image = sourceImage;
        } // otherwise create a new image of the target type and draw the new image
        else {
            image = new BufferedImage(sourceImage.getWidth(),
                    sourceImage.getHeight(), targetType);
            image.getGraphics().drawImage(sourceImage, 0, 0, null);

        }

        return image;

    }

    private static BufferedImage getDesktopScreenshot() {
        try {
            Robot robot = new Robot();
            Rectangle captureSize = new Rectangle(screenBounds);
            return robot.createScreenCapture(captureSize);
        } catch (AWTException e) {
            System.out.println("Error : " + e.toString());

            e.printStackTrace();
            return null;

        }

    }

    public static IMediaWriter agregarImgVideo(IMediaWriter videoUser,BufferedImage imagen, String user) {
        try {
             long startTime = System.nanoTime();
            BufferedImage screen = imagen;
            BufferedImage bgrScreen = convertToType(screen,BufferedImage.TYPE_3BYTE_BGR);
            videoUser.encodeVideo(0, bgrScreen, System.nanoTime() - startTime, TimeUnit.NANOSECONDS);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // ignore
                System.out.println("Error : " + e.toString());
            }
        } catch (Exception e) {
            System.out.println("Error agregarimgvideo: "+e.toString());
        }
       
          return videoUser; 
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
}
