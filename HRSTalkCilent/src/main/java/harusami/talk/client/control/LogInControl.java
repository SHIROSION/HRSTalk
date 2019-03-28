package harusami.talk.client.control;
/*
 * @package: harusami.talk.Control
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/19 下午 08:20
 */

import harusami.talk.client.information.LoginInformation;

/**
 * @classname: LogInControl
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/19 下午 08:20
 * @Version 1.0
 */
public class LogInControl {

    private String email;
    private String password;
    private LoginInformation loginInformation;

    public LogInControl() {
        this.email = null;
        this.password = null;
        this.loginInformation = null;
    }

    public LogInControl(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
