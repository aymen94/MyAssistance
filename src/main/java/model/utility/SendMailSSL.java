package model.utility;

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

    final static Properties props = new Properties();


    final static Session session = Session.getDefaultInstance(props,
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

    final static String username = "myassistance.teamC@gmail.com";
    final static String password = "myassistance";



    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(final String[] args) {
        try {
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            final Message message = new MimeMessage(session);
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


    public static void sendEmail(String recipient,String subject,String msg){

        try {
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            final Message message = new MimeMessage(session);
            message.setFrom(
                    new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(msg);

            Transport.send(message);

            System.out.println("email inviata a\n destinatario : "+ recipient
                    + "\noggetto :" + subject
                    + "\nmessaggio : "+ msg);

        } catch (final MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}
