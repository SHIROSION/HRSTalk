package harusami.talk.GUI;
/*
 * @package: harusami.talk.GUI
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/19 上午 02:52
 */

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @classname: LoginWindows
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/19 上午 02:52
 * @Version 1.0
 */
public class LoginWindows extends JDialog {
    JPanel mainJpanel = new JPanel();
    private static final int DIALOG_WIDTH=414;
    private static final int DIALOG_HEIGHT=340;
    private static final int DIALOG_HEIGHT_EXTEND=573;

    public void LoginWindows() {

        setAlwaysOnTop(true);
        setResizable(false);
        setBounds(400, 100, DIALOG_WIDTH,DIALOG_HEIGHT);
        getContentPane().setLayout(new BorderLayout());
        mainJpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(mainJpanel, BorderLayout.CENTER);
        mainJpanel.setLayout(null);

        //设置居中
        setLocation(WindowsCentered.getXY(LoginWindows.this.getSize()));

        JButton signUpButton = new JButton("注 册");
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LoginWindows.this.getHeight() == DIALOG_HEIGHT_EXTEND) {
                    LoginWindows.this.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
                } else {
                    LoginWindows.this.setSize(DIALOG_WIDTH,DIALOG_HEIGHT_EXTEND);
                }
            }
        });

        signUpButton.setBounds(53, 224, 93, 23);
        mainJpanel.add(signUpButton);

        JButton signInButton = new JButton("登 陆");
        signInButton.setBounds(190, 224, 93, 23);
        mainJpanel.add(signInButton);

        JTextField textField = new JTextField();
        textField.setBounds(133, 147, 150, 25);
        mainJpanel.add(textField);
        textField.setColumns(10);

        JTextField pwdTextField = new JTextField();
        pwdTextField.setBounds(133, 182, 150, 25);
        mainJpanel.add(pwdTextField);
        pwdTextField.setColumns(10);

        JLabel emailLable = new JLabel("邮 箱");
        emailLable.setBounds(53, 151, 54, 15);
        mainJpanel.add(emailLable);

        JLabel pwdLable = new JLabel("密 码");
        pwdLable.setBounds(53, 194, 54, 15);
        mainJpanel.add(pwdLable);

        JLabel imageLabel = new JLabel("New label");
        imageLabel.setBounds(0, 0, 360, 136);
        ImageIcon icon = new ImageIcon(
                "D:\\Java project\\HRSTalk\\src\\main\\java\\harusami\\talk\\GUI\\login.png");
        icon = ImageScale.getImage(icon, imageLabel.getWidth(), imageLabel.getHeight());
        imageLabel.setIcon((icon));
        mainJpanel.add(imageLabel);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "\u6CE8\u518C\u7528\u6237",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(12, 259, 336, 221);
        mainJpanel.add(panel);
        panel.setLayout(null);

        JLabel signUpEmailLabel = new JLabel("邮 箱");
        signUpEmailLabel.setBounds(41, 29, 55, 18);
        panel.add(signUpEmailLabel);

        JLabel signUpVerificationCodeLabel = new JLabel("验证码");
        signUpVerificationCodeLabel.setBounds(41, 85, 55, 18);
        panel.add(signUpVerificationCodeLabel);

        JLabel signUpPwdLabel = new JLabel("密 码");
        signUpPwdLabel.setBounds(41, 115, 55, 18);
        panel.add(signUpPwdLabel);

        JLabel signUpConfirmPwdLabel = new JLabel("确认密码");
        signUpConfirmPwdLabel.setBounds(41, 145, 55, 18);
        panel.add(signUpConfirmPwdLabel);

        JTextField signUpEmailText = new JTextField();
        signUpEmailText.setBounds(123, 22, 150, 25);
        panel.add(signUpEmailText);
        signUpEmailText.setColumns(10);

        JTextField signUpVerificationCodeText = new JTextField();
        signUpVerificationCodeText.setBounds(123, 80, 150, 25);
        panel.add( signUpVerificationCodeText);
        signUpVerificationCodeText.setColumns(10);

        JTextField signUpPwdText = new JTextField();
        signUpPwdText.setBounds(123, 113, 150, 25);
        panel.add(signUpPwdText);
        signUpPwdText.setColumns(10);

        JTextField signUpConfirmPwdText = new JTextField();
        signUpConfirmPwdText.setBounds(123, 145, 150, 25);
        panel.add(signUpConfirmPwdText);
        signUpConfirmPwdText.setColumns(10);

        JButton sendVerificationCodeButton = new JButton("发送验证码");
        sendVerificationCodeButton.setBounds(123, 52, 83, 23);
        panel.add(sendVerificationCodeButton);

        JButton cancelButton = new JButton("取 消");
        cancelButton.setBounds(51, 182, 83, 27);
        panel.add(cancelButton);

        JButton cconfirmButton = new JButton("确 认");
        cconfirmButton.setBounds(190, 182, 83, 27);
        panel.add(cconfirmButton);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
