package model.segnalazione;

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
     * The Constant props.
     */
    private static final Properties PROPS = new Properties();

    /**
     * The Constant session.
     */
    private static final Session SESSION = Session.getDefaultInstance(PROPS,
            new javax.mail.Authenticator() {
                /**
                 * (non-Javadoc)
                 *
                 * @see javax.mail.Authenticator#getPasswordAuthentication()
                 */
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USERNAME, PASSWORD);
                }
            });

    /**
     * The Constant username.
     */
    private static final String USERNAME = "myassistance.teamC@gmail.com";

    /**
     * The Constant password.
     */
    private static final String PASSWORD = "myassistance";

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(final String[] args) {
        try {
            PROPS.put("mail.smtp.host", "smtp.gmail.com");
            PROPS.put("mail.smtp.socketFactory.port", "465");
            PROPS.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            PROPS.put("mail.smtp.auth", "true");
            PROPS.put("mail.smtp.port", "465");
            final Message message = new MimeMessage(SESSION);
            message.setFrom(
                    new InternetAddress("myassistance.teamC@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("myassistance.teamC@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText(
                    "Dear Mail Crawler," + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (final MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Send email.
     *
     * @param recipient the recipient
     * @param subject   the subject
     * @param msg       the msg
     */
    public static void sendEmail(final String recipient, final String subject,
            final String msg) {

        try {
            PROPS.put("mail.smtp.host", "smtp.gmail.com");
            PROPS.put("mail.smtp.socketFactory.port", "465");
            PROPS.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            PROPS.put("mail.smtp.auth", "true");
            PROPS.put("mail.smtp.port", "465");
            final Message message = new MimeMessage(SESSION);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);

            System.out.println("email inviata a\n destinatario : " + recipient
                    + "\noggetto :" + subject + "\nmessaggio : " + msg);

        } catch (final MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
