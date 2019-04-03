package harusami.talk.client.gui;
/*
 * @package: harusami.talk.cilent.gui
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/20 下午 03:31
 */

import harusami.serialize.SendInformation;
import harusami.serialize.UserInformation;
import harusami.talk.client.control.TalkControl;
import harusami.serialize.CommandTranser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @classname: TalkWindows
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/20 下午 03:31
 * @Version 1.0
 */
public class TalkWindows extends JDialog {

    private JTextArea textArea;
    public JTextArea textChat;
    private String name;
    private UserInformation userInformation;
    private SendInformation sendInformation;

    public void talkWindows(final String name, final UserInformation userInformation) {

        this.name = name;
        this.userInformation = userInformation;

        JPanel mainJpanel = new JPanel();
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setBounds(100, 100, 700, 600);
        mainJpanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        mainJpanel.setLayout(new BorderLayout(0, 0));
        setContentPane(mainJpanel);

        JPanel panel = new JPanel();
        mainJpanel.add(panel, BorderLayout.NORTH);
        panel.setLayout(new BorderLayout(5, 5));

        JLabel talkerImageLabel = new JLabel("");
        talkerImageLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
        talkerImageLabel.setPreferredSize(new Dimension(64, 64));
        panel.add(talkerImageLabel, BorderLayout.WEST);

        JPanel talkerJpanel = new JPanel();
        panel.add(talkerJpanel, BorderLayout.CENTER);
        talkerJpanel.setLayout(new BorderLayout(0, 0));

        JLabel talkerNameLabel = new JLabel(this.name);
        talkerNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        talkerJpanel.add(talkerNameLabel , BorderLayout.CENTER);

        JLabel talkerDescriptionLabel = new JLabel();
        talkerJpanel.add(talkerDescriptionLabel, BorderLayout.SOUTH);

        JSplitPane splitPane = new JSplitPane();
        splitPane.setResizeWeight(0.9);
        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        mainJpanel.add(splitPane, BorderLayout.CENTER);

        JPanel userJpanel = new JPanel();
        splitPane.setRightComponent(userJpanel);
        userJpanel.setLayout(new BorderLayout(0, 0));

        JPanel selectJpanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) selectJpanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        userJpanel.add(selectJpanel, BorderLayout.NORTH);

        JButton wordButton = new JButton("字体");
        selectJpanel.add(wordButton);

        JButton btnNewButton = new JButton("震屏");
        selectJpanel.add(btnNewButton);

        JPanel mainSendJpanel = new JPanel();
        userJpanel.add(mainSendJpanel, BorderLayout.CENTER);
        mainSendJpanel.setLayout(new BorderLayout(0, 0));

        JPanel sendJpanel = new JPanel();
        mainSendJpanel.add(sendJpanel, BorderLayout.EAST);
        sendJpanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // 该事件为用户聊天信息发送监听事件
        // 当用户点击“发送”按钮的时候，程序会封装发送信息已经发送对象并序列化通过网络发给服务端
        // 服务端接收到信息后，会在session里查找发送对象的连接socket对象
        // 查找成功后会把信息再从封装通过网络发送给发送对象
        JButton sendButton = new JButton("发 送");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CommandTranser commandTranser = new CommandTranser();
                String temp = textArea.getText();
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd a hh:mm:ss:▶");
                String message = simpleDateFormat.format(date) + "\n" + temp + "\n";
                textChat.append(message);
                textArea.setText("");

                SendInformation sendInformation = new SendInformation(userInformation.getEmail(), name, temp);
                commandTranser.setCmd("Talk");
                commandTranser.setData(sendInformation);
                TalkControl talkControl = new TalkControl(commandTranser);
                talkControl.start();
                System.out.println("线程" + talkControl.getId() + "已启动");
            }
        });

        sendJpanel.add(sendButton);

        JPanel mainScrollJpanel = new JPanel();
        mainSendJpanel.add(mainScrollJpanel, BorderLayout.CENTER);
        mainScrollJpanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollJpanel = new JScrollPane();
        mainScrollJpanel.add(scrollJpanel, BorderLayout.CENTER);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        scrollJpanel.setViewportView(textArea);

        textChat = new JTextArea();
        textChat.setEditable(false);
        textChat.add(new JScrollBar(JScrollBar.VERTICAL));
        splitPane.setLeftComponent(textChat);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        try {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e) {
            //TODO exception
            System.out.println("加载炫彩皮肤失败！");
        }

        UserInformation userInformation = new UserInformation();
        userInformation.setEmail("12345");
        new TalkWindows().talkWindows("1234", userInformation);
    }
}
