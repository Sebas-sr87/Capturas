/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Funciones;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Maxbel
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.*;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
//import org.apache.log4j.Logger;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 *
 * @author felipe
 */
public class EnviarEmail {

    /**
     * Envia el mail en modo post.
     *
     * @param recipients array en donde vienen los destinatarios
     * @param subject asunto
     * @param message cuerpo del mail
     * @param from emisor del mail
     * @throws MessagingException .
     */
    public final void postMail(
            final Object[] recipients,
            final String subject,
            final String message,
            int agente, int cliente,
            final String from) throws MessagingException {
        try {
            //Set the host smtp address
            final Properties props = new Properties();
            props.put("mail.smtp.host", "192.168.100.71");
        props.put("mail.smtp.host", "200.75.13.10");
//        props.put("mail.smtp.host", "152.139.2.253");
            // create some properties and get the default Session
            final Session session = Session.getDefaultInstance(props, null);
//            Session session = setupSMTP("agente1adp@gmail.com", "Adportas2017");
//            session.setDebug(false);

            // create a message
            final MimeMessage msg = new MimeMessage(session);

            // set the from and to address
            final InternetAddress addressFrom = new InternetAddress(from);
            msg.setFrom(addressFrom);

            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i
                    < recipients.length; i++) {
                addressTo[i] = new InternetAddress((String) recipients[i]);
            }
            msg.setRecipients(Message.RecipientType.TO, addressTo);

            // Optional :
            //You can also set your custom headers in the Email if you Want
            msg.addHeader(
                    "MyHeaderName", "myHeaderValue");
            String html = message;

            Multipart alternative = new MimeMultipart("alternative");
            MimeBodyPart bodyMixed = new MimeBodyPart();
            Multipart mixed = new MimeMultipart("mixed");
            // create the message part 
//        final String fileAttachment = "revision.xls";
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            MimeBodyPart bodyHtml = new MimeBodyPart();
            bodyHtml.setContent(html, "text/html;charset=UTF-8");
            alternative.addBodyPart(bodyHtml);

            bodyMixed.setContent(alternative);
            mixed.addBodyPart(bodyMixed);
            Multipart multipart = new MimeMultipart();
            messageBodyPart.setText(message);
//        DataSource source =
//                new FileDataSource(fileAttachment);
//        messageBodyPart.setDataHandler(
//                new DataHandler(source));
//        messageBodyPart.setFileName(fileAttachment);

            multipart.addBodyPart(messageBodyPart);

            // Setting the Subject and Content Type
            msg.setSubject(subject);
            msg.setContent(mixed);
            // Logger.getLogger(Mailenviar.class.getName()).info(message);
            Transport.send(msg);
            // Logger.getLogger(Mailenviar.class.getName()).
            //  info("ya se envio el mensaje via mail");
//        Mail.recipiente.clear();

        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error al enviar correo: " + ex.toString());
        }
    }

    private Session setupSMTP(final String usuario, final String password) {
        Session session1;
        final Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
//        //props.put("mail.ssl.enable", "true");
////        props.put("mail.smtp.starttls.enable", "true");
////        props.put("mail.smtp.tls", "true");
//        props.put("mail.smtp.ssl.checkserveridentity", "true");

        final javax.mail.Authenticator auth = new javax.mail.Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario, password);
            }
        };
        session1 = Session.getInstance(props, auth);
        return session1;
    }

}
