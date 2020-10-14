/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import java.io.File;
import java.util.List;

/**
 *
 * @author Sebastian Oliveros
 */
public class EliminarImagen {
    public static void eliminarArchivos(List nombre){
            File fichero = new File("C:\\Users\\Sebastian Oliveros\\Documents\\NetBeansProjects\\capturar\\Imagenes\\"+nombre+".png");
            boolean delete = fichero.delete();
            if(delete){
//                System.out.println("Borro archivo: "+nombre);
            }else{
//            System.out.println("NO Borro archivo: "+nombre);
            }
    
    }
}
