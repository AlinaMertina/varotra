package com.work.varotra.Entity;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.DataHandler;

public class EmailSender {

    public static void main(String[] args) {

        // Informations sur le serveur de messagerie
        String host = "smtp.gmail.com";
        String port = "587"; // ou "465" pour SSL
        String username = "minoraherinirina72@gmail.com";
        String password = "kods duwe ajin tlau";

        // Paramètres de propriétés
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);

        // Création d'une session avec authentification
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Création du message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("minoraherinirina72@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("raherinirinanomena52@gmail.com"));
            message.setSubject("Sujet de l'e-mail");

            // Partie texte du message
            String messageText = "Contenu de l'e-mail";
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(messageText);

            // Pièce jointe
            String filePath = "C:/Users/itu/Documents/Mr_Tovo/varotra/doc.txt";
            String fileName = "doc.txt";
            DataSource source = new FileDataSource(filePath);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);

            // Création du Multipart
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            // Ajout du Multipart au message
            message.setContent(multipart);

            // Envoi du message
            Transport.send(message);

            System.out.println("E-mail envoyé avec succès!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
