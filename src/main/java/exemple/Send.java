package exemple;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Send extends Authenticator {

    private String username;
    private String password;
    private Properties prop;

    public Send(String username, String password) {
        this.username = username;
        this.password = password;

        prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
    }

    public String send(String subject, String text, final String fromEmail, String toEmail){

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);


            Transport.send(message);
            System.out.println("Done");
            return "true";

        } catch (MessagingException e) {
            e.printStackTrace();
            return "false";
        }
    }
}
