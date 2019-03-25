package harusami.talk.cilent.gui;
/*
 * @package: harusami.talk.cilent.gui
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/20 下午 03:31
 */

import harusami.talk.cilent.information.CommandTranser;
import harusami.talk.cilent.socket.ClientSocket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @classname: TalkWindows
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/20 下午 03:31
 * @Version 1.0
 */
public class TalkWindows extends JDialog {

    private JTextArea textArea;
    private JTextArea textChat;
    private ClientSocket clientSocket = new ClientSocket();

    public void talkWindows() {
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

        JLabel talkerNameLabel = new JLabel("testName");
        talkerNameLabel.setFont(new Font("Dialog", Font.BOLD, 14));
        talkerJpanel.add(talkerNameLabel , BorderLayout.CENTER);

        JLabel talkerDescriptionLabel = new JLabel("埋下一座城，关了所有灯");
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

        JButton sendButton = new JButton("发 送");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String temp = textArea.getText();
                Date date = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss a");
                String message = temp + simpleDateFormat.format(date);
                textChat.append(message);
                textArea.setText("");
                CommandTranser commandTranser = new CommandTranser();
                commandTranser.setData(temp);
                commandTranser.setCmd("message");
                clientSocket.sendData(commandTranser);
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

        JScrollPane scrollPane = new JScrollPane();

        //splitPane.setLeftComponent(scrollPane);
        textChat = new JTextArea();
        textChat.setEditable(false);
        textChat.add(new JScrollBar(JScrollBar.VERTICAL));
        splitPane.setLeftComponent(textChat);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        try
        {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
            System.out.println("加载炫彩皮肤失败！");
        }

        TalkWindows talkWindows = new TalkWindows();
        talkWindows.talkWindows();
    }
}
