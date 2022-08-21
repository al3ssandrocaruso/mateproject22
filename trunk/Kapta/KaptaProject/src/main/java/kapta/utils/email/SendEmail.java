package kapta.utils.email;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    private SendEmail(){
        //ignored
    }

    public static void send(String receiver,String emailSubject,String toWrite) {

        String from = "mateproject22@virgilio.it"; //mail dalla quale inviamo
        String host = "smtp.virgilio.it";
        Properties properties = System.getProperties();

        // Setup
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {return new PasswordAuthentication(from, "Lollo.2001");}
        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));

            // set subject
            message.setSubject(emailSubject);

            // set message
            message.setText(toWrite);

            // Send message
            Transport.send(message);

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
}
