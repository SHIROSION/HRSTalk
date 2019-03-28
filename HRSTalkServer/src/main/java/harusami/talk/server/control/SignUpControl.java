package harusami.talk.server.control;
/*
 * @package: harusami.talk.server.control
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/27 上午 02:58
 */

import harusami.serialize.SignUpInformation;
import harusami.talk.server.database.UserModel;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @classname: SignUpControl
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/27 上午 02:58
 * @Version 1.0
 */
public class SignUpControl {
    private String email;
    private String password;
    private String gender;
    private SignUpInformation signUpInformation;

    public SignUpControl() {
        this.email = null;
        this.password = null;
        this.signUpInformation = null;
    }


    public SignUpControl(SignUpInformation signUpInformation) {
        this.email = signUpInformation.getUserEmail();
        this.password = signUpInformation.getUserPassword();
        this.signUpInformation = signUpInformation;
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        signUpInformation.setSignUpTime(time.format(new Date()));
    }

    public boolean executeSignUp() {
        UserModel userModel = new UserModel();
        if (userModel.getSignUpData(signUpInformation.getUserEmail())) {
            return userModel.insert(signUpInformation) == 1;
        } else {
            return false;
        }
    }
}
