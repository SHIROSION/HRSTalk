package harusami.talk.cilent.gui;
/*
 * @package: harusami.talk.cilent.gui
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/20 下午 02:23
 */

import javax.swing.*;
import java.awt.*;

/**
 * @classname: FriendList
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/20 下午 02:23
 * @Version 1.0
 */
public class FriendList extends JDialog {

    public void friendList() {
        setBounds(100, 100, 400, 650);
        getContentPane().setLayout(new BorderLayout());
        JPanel mainJpanel = new JPanel();
        getContentPane().add(mainJpanel, BorderLayout.CENTER);
        mainJpanel.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        mainJpanel.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(10, 10));

        ImageIcon icon = new ImageIcon(
                "D:\\Java project\\HRSTalk\\HRSTalkCilent\\src\\main\\image\\picture.jpg");
        JLabel lblNewLabel = new JLabel(ImageScale.getImage(icon, 64, 64));
        panel.add(lblNewLabel, BorderLayout.WEST);

        JPanel headJpanel= new JPanel();
        panel.add(headJpanel, BorderLayout.CENTER);
        headJpanel.setLayout(new BorderLayout(0, 0));

        JLabel userNameLabel = new JLabel("Rinne");
        userNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        headJpanel.add(userNameLabel, BorderLayout.CENTER);

        JLabel userDescription = new JLabel("该家伙很懒，没有任何简介");
        headJpanel.add(userDescription, BorderLayout.SOUTH);

        JPanel mainSrttingJpanel = new JPanel();
        mainJpanel.add(mainSrttingJpanel, BorderLayout.SOUTH);
        mainSrttingJpanel.setLayout(new BorderLayout(0, 0));

        JPanel settingJpanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        mainSrttingJpanel.add(settingJpanel, BorderLayout.CENTER);

        JButton settingButton = new JButton("设 置");
        settingJpanel.add(settingButton);

        JButton findButton = new JButton("查 找");
        settingJpanel.add(findButton);

        JPanel exitJpanel = new JPanel();
        mainSrttingJpanel.add(exitJpanel, BorderLayout.EAST);

        JButton exitButton = new JButton("退 出");
        exitJpanel.add(exitButton);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        mainJpanel.add(tabbedPane, BorderLayout.CENTER);

        JPanel friendJpanel = new JPanel();
        tabbedPane.addTab("我的好友", null, friendJpanel, null);
        friendJpanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        friendJpanel.add(scrollPane, BorderLayout.CENTER);

        JPanel mainInformationJpanel = new JPanel();
        tabbedPane.addTab("消息列表", null,  mainInformationJpanel, null);

        JLabel information = new JLabel("暂未实现");
        mainInformationJpanel.add(information);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        try {
            FriendList friendList = new FriendList();
            friendList.friendList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
