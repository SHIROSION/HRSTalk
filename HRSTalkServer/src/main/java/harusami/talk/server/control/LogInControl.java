package harusami.talk.server.control;
/*
 * @package: harusami.talk.server.control
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/27 上午 02:38
 */

import harusami.serialize.LoginInformation;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @classname: LogInControl
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/27 上午 02:38
 * @Version 1.0
 */
public class LogInControl {
    private String email;
    private String password;
    private LoginInformation loginInformation;

    public LogInControl() {
        this.email = null;
        this.password = null;
    }

    public LogInControl(LoginInformation loginInformation) {
//        this.email = email;
//        this.password = password;
//        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        this.loginInformation = new LoginInformation();
//        loginInformation.setTime(time.format(new Date()));
    }
}
