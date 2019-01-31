package model.utente;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * The Class SendMailSSL.
 */
public final class SendMailSSL {

    /**
     * Instantiates a new send mail SSL.
     */
    private SendMailSSL() {

    }

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(final String[] args) {
        final String username = "myassistance.teamC@gmail.com";
        final String password = "myassistance";

        final Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        final Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    /* (non-Javadoc)
                     * @see javax.mail.Authenticator#getPasswordAuthentication()
                     */
                    @Override
                    protected PasswordAuthentication
                    getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            final Message message = new MimeMessage(session);
            message.setFrom(
                    new InternetAddress("myassistance.teamC@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("tanosk8.95@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText(
                    "Dear Mail Crawler," + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (final MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
