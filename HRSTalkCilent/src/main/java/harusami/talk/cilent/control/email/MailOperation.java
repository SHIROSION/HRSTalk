package harusami.talk.cilent.control.email;
/*
 * @package: harusami.talk.Control.email
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/20 上午 02:31
 */

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * @classname: MailOperation
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/20 上午 02:31
 * @Version 1.0
 */
public class MailOperation {

    public String sendEmail(String user, String password, String host, String sendFrom, String sendTo, String subject, String content)
        throws Exception {
        if (sendTo != null) {
            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.auth", "true");
            MailAuthenticator mailAuthenticator = new MailAuthenticator();
            MailAuthenticator.USERNAME = user;
            MailAuthenticator.PASSWORD = password;
            Session session = Session.getInstance(properties, mailAuthenticator);

            try {
                MimeMessage mimeMessage = new MimeMessage(session);
                mimeMessage.setFrom(new InternetAddress(sendFrom));
                if (!sendTo.trim().equals("")) {
                    mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(sendTo.trim()));
                }

                mimeMessage.setSubject(subject);
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                mimeBodyPart.setContent(content, "text/html;charset=utf-8");
                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(mimeBodyPart);
                mimeMessage.setContent(multipart);
                mimeMessage.setSentDate(new Date());
                mimeMessage.saveChanges();

                Transport transport = session.getTransport("smtp");
                Transport.send(mimeMessage);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "Success";
        } else {
            return "false";
        }
    }
}
