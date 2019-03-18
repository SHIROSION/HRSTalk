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

import javax.swing.*;
import java.awt.*;

/**
 * @classname: LoginWindows
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/19 上午 02:52
 * @Version 1.0
 */
public class LoginWindows {
    int width = 500, hight = 350;

    public void login() {
        JFrame jFrame = new JFrame("LogIn");
        jFrame.setSize(width, hight);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jPanel = new JPanel();
        jFrame.setContentPane(jPanel);
        JTextPane jTextPane = new JTextPane();
        jTextPane.setPreferredSize(new Dimension( 200, 25 ));
        jPanel.add(jTextPane);
        jFrame.setVisible(true);
    }
}
