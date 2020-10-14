/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Base64;

/**
 *
 * @author Sebastian Oliveros
 */
public class SizeImagen {
     public static BufferedImage resize(BufferedImage img) {
        Image tmp = img.getScaledInstance(683, 384, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(683, 384, img.getType());

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return dimg;
    }
     public static byte[] imagenBajaCalidad(String imagentexto){
         BufferedImage transImagen = Comparar.transImagen(imagentexto);
         BufferedImage resize = resize(transImagen);
         String transImagen1 = Comparar.transImagen(resize);
         byte[] barr = Base64.getDecoder().decode(transImagen1);
         
         
         return barr;
     
     }
}
