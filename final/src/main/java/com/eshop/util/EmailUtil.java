package com.eshop.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtil {

    public static void sendEmail(String recipient, String subject, String message) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.163.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("huangrz2001@163.com", "YLFHJVGUZHJHHJVE");
            }
        };

        Session session = Session.getInstance(props, auth);

        Message email = new MimeMessage(session);
        email.setFrom(new InternetAddress("huangrz2001@163.com"));
        email.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        email.setSubject(subject);
        email.setText(message);

        Transport.send(email);
    }
}
