package harusami.talk.client.information;
/*
 * @package: harusami.talk.information
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/19 下午 08:24
 */

/**
 * @classname: LoginInformation
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/19 下午 08:24
 * @Version 1.0
 */
public class LoginInformation {

    private String userEmail;

     public LoginInformation () {
        userEmail = null;
    }

    public LoginInformation (String userEmail) {
         this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

}
