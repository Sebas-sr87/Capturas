/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sebastian Oliveros
 */
public class Procesos {


    public static void main(String[] args) {

        try {
            String line;
            List nombre = new ArrayList<>();
            String[] comando = {"powershell.exe", "Get-Process | Where {$_.mainWindowTitle}"};

            Process p = Runtime.getRuntime().exec(comando);
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
//                System.out.println(line); //<-- Parse data here.
                String[] procesosArray = line.split(",");
                if (!nombre.contains(procesosArray[0])) {
                    nombre.add(procesosArray[0]);
                }
            }
            input.close();
//            System.out.println(nombre);
        } catch (Exception err) {
            err.printStackTrace();
        }
//         try {
//                    String[] cmd = {"PowerShell.exe"};
//                    Runtime.getRuntime().exec(cmd);
//                } catch (Exception e) {
//                    System.out.println("Error en comando: "+e.toString());
//                }

    }
}
