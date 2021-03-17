package helPet.util;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.MediaType;
import java.util.Properties;

public class EmailSender {

    // TODO: Extract from config
    private static final String USERNAME = "helpetoffice@gmail.com";
    private static final String PASSWORD = "kpgnatgopegboyfq";

    public void sendMail(String to,
                         String subject,
                         String content) throws Exception {
        Properties prop = System.getProperties();
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.debug", "true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.transport.protocol", "smtp");
        prop.put("mail.smtp.ssl.enable", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });
        MimeMessage msg = new MimeMessage(session);

        try {

            // from
            msg.setFrom(new InternetAddress(USERNAME));

            // to
            msg.addRecipients(Message.RecipientType.TO, to);

            // subject
            msg.setSubject(subject);

            // content
            msg.setText(content);
            Transport.send(msg);

            System.out.println("Sent");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new Exception("Cannot send email: " + e.getMessage());
        }
    }

}
