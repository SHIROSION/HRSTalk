package harusami.talk.client.gui;
/*
 * @package: harusami.talk.GUI
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/19 上午 02:52
 */

import harusami.serialize.CommandTranser;
import harusami.talk.client.control.PasswordEncryption;
import harusami.talk.client.control.SignUpControl;
import harusami.serialize.SignUpInformation;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

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

    private JTextField emailTextField;
    private JTextField pwdTextField;
    private JTextField signUpEmailText;
    private JTextField signUpVerificationCodeText;
    private JTextField signUpPwdText;
    private JTextField signUpConfirmPwdText;


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

        JButton signInButton = new JButton("登 录");
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getUserEmail = emailTextField.getText();
                String getUserPwd = pwdTextField.getText();
                new FriendList().friendList();
                setVisible(false);
            }
        });

        signInButton.setBounds(190, 224, 93, 23);
        mainJpanel.add(signInButton);

        emailTextField = new JTextField();
        emailTextField.setBounds(133, 147, 150, 25);
        mainJpanel.add(emailTextField);
        emailTextField.setColumns(10);

        pwdTextField = new JPasswordField();
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
                "D:\\Java project\\HRSTalk\\HRSTalkCilent\\src\\main\\image\\login.png");
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

        final JLabel signUpVerificationCodeLabel = new JLabel("验证码");
        signUpVerificationCodeLabel.setBounds(41, 85, 55, 18);
        panel.add(signUpVerificationCodeLabel);

        JLabel signUpPwdLabel = new JLabel("密 码");
        signUpPwdLabel.setBounds(41, 115, 55, 18);
        panel.add(signUpPwdLabel);

        final JLabel signUpConfirmPwdLabel = new JLabel("确认密码");
        signUpConfirmPwdLabel.setBounds(41, 145, 55, 18);
        panel.add(signUpConfirmPwdLabel);

        signUpEmailText = new JTextField();
        signUpEmailText.setBounds(123, 22, 150, 25);
        panel.add(signUpEmailText);
        signUpEmailText.setColumns(10);

        signUpVerificationCodeText = new JTextField();
        signUpVerificationCodeText.setBounds(123, 80, 150, 25);
        panel.add( signUpVerificationCodeText);
        signUpVerificationCodeText.setColumns(10);

        signUpPwdText = new JPasswordField();
        signUpPwdText.setBounds(123, 113, 150, 25);
        panel.add(signUpPwdText);
        signUpPwdText.setColumns(10);

        signUpConfirmPwdText = new JPasswordField();
        signUpConfirmPwdText.setBounds(123, 145, 150, 25);
        panel.add(signUpConfirmPwdText);
        signUpConfirmPwdText.setColumns(10);

        JButton sendVerificationCodeButton = new JButton("发送验证码");
        sendVerificationCodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        sendVerificationCodeButton.setBounds(123, 52, 83, 23);
        panel.add(sendVerificationCodeButton);

        JButton cancelButton = new JButton("取 消");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LoginWindows.this.getHeight() == DIALOG_HEIGHT_EXTEND) {
                    LoginWindows.this.setSize(DIALOG_WIDTH, DIALOG_HEIGHT);
                } else {
                    LoginWindows.this.setSize(DIALOG_WIDTH,DIALOG_HEIGHT_EXTEND);
                }
            }
        });

        cancelButton.setBounds(51, 182, 83, 27);
        panel.add(cancelButton);

        JButton confirmButton = new JButton("确 认");
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getEmail = signUpEmailText.getText();

                if (signUpConfirmPwdText.getText().equals(signUpPwdText.getText())) {
                    SignUpInformation signUpInformation = new SignUpInformation();

                    signUpInformation.setUserEmail(getEmail);
                    try {
                        signUpInformation.setUserPassword(PasswordEncryption.getEncryptedPwd(signUpPwdText.getText()));
                    } catch (NoSuchAlgorithmException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedEncodingException e1) {
                        e1.printStackTrace();
                    }
                    //userInformation.setGender();
                    CommandTranser commandTranser = new CommandTranser();
                    commandTranser.setCmd("SignUp");
                    commandTranser.setData(signUpInformation);
                    SignUpControl signUpControl = new SignUpControl(commandTranser);
                    signUpControl.run();
                } else {
                    JOptionPane.showMessageDialog(
                            LoginWindows.this,"密码不一致或验证码错误",
                            "消息对话框",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        confirmButton.setBounds(190, 182, 83, 27);
        panel.add(confirmButton);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            BeautyEyeLNFHelper.launchBeautyEyeLNF();
        } catch (Exception e) {
            e.printStackTrace();
        }

        LoginWindows loginWindows = new LoginWindows();
        loginWindows.LoginWindows();
    }
}
