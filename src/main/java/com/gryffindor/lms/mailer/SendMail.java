package com.gryffindor.lms.mailer;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * class description :
 *
 * @version 1.0
 * @author Md. Amdadul Bari
 */

public class SendMail {
    public void send(String email,int approvalCode){
        final String username = "griffyndor.lms@gmail.com";
        final String password = "#imad#imad#";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Gryffindor"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Griffyndor LMS Registration");
            message.setText("Dear User,"
                    + "\n\nYour Registration Code is "+approvalCode+"\n\n\nThanks");

            Transport.send(message);

            System.out.println("mail sent");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}