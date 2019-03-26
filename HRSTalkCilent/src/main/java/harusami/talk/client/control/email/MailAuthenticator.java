package harusami.talk.client.control.email;
/*
 * @package: harusami.talk.Control.email
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/20 上午 01:54
 */


import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @classname: MailAuthenticator
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/20 上午 01:54
 * @Version 1.0
 */
public class MailAuthenticator extends Authenticator {
    public static String USERNAME = "";
    public static String PASSWORD = "";

    public MailAuthenticator() {
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(USERNAME, PASSWORD);
    }

}
