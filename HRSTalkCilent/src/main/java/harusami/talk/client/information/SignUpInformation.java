package harusami.talk.client.information;
/*
 * @package: harusami.talk.information
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/20 上午 10:11
 */

/**
 * @classname: SignUpInformation
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/20 上午 10:11
 * @Version 1.0
 */
public class SignUpInformation {

    private String userEmail;
    private String userPassword;
    private String signUpTime;

    public SignUpInformation() {
        userEmail = null;
        userPassword = null;
        signUpTime = null;
    }

    public SignUpInformation(String userEmail, String userPassword, String signUpTime) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.signUpTime = signUpTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getSignUpTime() {
        return signUpTime;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setSignUpTime(String signUpTime) {
        this.signUpTime = signUpTime;
    }
}
