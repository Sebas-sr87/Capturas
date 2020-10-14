/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logs;

import org.apache.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Felipe
 */
public class MensajesLog {

    public static Logger logger;

    public MensajesLog() {

    }

    public static void iniciarLogs() {
        logger = Logger.getLogger(MensajesLog.class.getName());
        try {
            //   LogManager.getLogManager().readConfiguration(new FileInputStream("log.properties"));

            String rutaProjecto = System.getProperty("user.dir") + "/";

            System.setProperty("mi.ruta.log", rutaProjecto);
            System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
            System.setProperty("org.apache.commons.logging.simplelog.showdatetime", "true");
            System.setProperty("org.apache.commons.logging.simplelog.log.httpclient.wire", "ERROR");
            System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http", "ERROR");
            System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.http.headers", "ERROR");
            PropertyConfigurator.configureAndWatch(rutaProjecto + "log.properties", 1000);

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("\nFallo al intentar crear el log, asegurese que el fichero log.properties existe y este en el mismo directorio del demonio");
            System.exit(-1);
        }
    }

}
