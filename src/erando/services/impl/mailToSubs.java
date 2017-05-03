/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erando.services.impl;

import com.lowagie.text.DocumentException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author F.Mouhamed
 */
public class mailToSubs {
    
    
    
    
    public void envoyerfacture(String too,String imageName,int id,String titre,float prix,String description) throws IOException, FileNotFoundException, DocumentException{
        String host="smtp.gmail.com";  
        final String user="prohamma555@gmail.com";//change accordingly  
        final String password="foudhili05";//change accordingly  

        String to=too;//change accordingly  
         //Get the session object  
        Properties props = new Properties();  
        props.put("mail.smtp.host",host);  
        props.put("mail.smtp.auth", "true");  
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getDefaultInstance(props,  
         new javax.mail.Authenticator() {  
           protected PasswordAuthentication getPasswordAuthentication() {  
         return new PasswordAuthentication(user,password);  
           }  
         });  
        //Compose the message  
         try {  
          MimeMessage message = new MimeMessage(session);  
          message.setFrom(new InternetAddress(user));  
          message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
          message.setSubject("New Product Added to Store !");  
          message.setText("Morning Sir , New Product has beed added to our stores , go check it  ");  
          MimeBodyPart mbp2 = new MimeBodyPart();
          MimeBodyPart mbp3 = new MimeBodyPart();
          pdfCreation create = new pdfCreation();
          create.genererpdf(titre,prix,imageName,id,description);
          FileDataSource fichier_joint = new FileDataSource("C:\\Users\\F.Mouhamed\\Desktop\\Esprit\\ERandoPi\\"+id+".pdf");
          FileDataSource fichier_joint2 = new FileDataSource("C:\\Users\\F.Mouhamed\\Desktop\\Esprit\\ERandoPi\\userfiles\\"+imageName);
          mbp2.setDataHandler(new DataHandler(fichier_joint));
          mbp2.setFileName(fichier_joint.getName());
          mbp3.setDataHandler(new DataHandler(fichier_joint2));
          mbp3.setFileName(fichier_joint2.getName());
           // Créer un conteneur multipart pour les deux contenus
          Multipart mp = new MimeMultipart();
          mp.addBodyPart(mbp2);
          mp.addBodyPart(mbp3);
            // Ajouter le Multipart au message
          message.setContent(mp);
            //send the message
            // message.setTLS(true);
          Transport.send(message);  
          System.out.println("message sent successfully...");  

          } catch (MessagingException e) {e.printStackTrace();} 
         }
}
