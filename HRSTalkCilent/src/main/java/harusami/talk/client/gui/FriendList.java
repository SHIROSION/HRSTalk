package harusami.talk.client.gui;
/*
 * @package: harusami.talk.cilent.gui
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/20 下午 02:23
 */

import com.alibaba.fastjson.JSONObject;
import harusami.serialize.UserInformation;
import harusami.talk.client.information.FriendNode;
import harusami.talk.client.information.TalkWindowsEntity;
import harusami.talk.client.information.TalkWindowsList;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.*;

/**
 * @classname: FriendList
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/20 下午 02:23
 * @Version 1.0
 */
public class FriendList extends JDialog implements TreeSelectionListener {
    private JTree tree;
    private String friendName;
    private UserInformation userInformation;
    private String email;
    private String getUserInformation;

    public FriendList() {

        this.tree = null;
        this.friendName = null;
        this.userInformation = null;
        this.email = null;
    }

    public FriendList(String email) {
        this.email = email;
    }

    public void friendList() {

        setAlwaysOnTop(true);
        setResizable(false);

        this.userInformation = new UserInformation();
        String filePath  = email + ".json";
        File file = new File(filePath);
        getUserInformation = "";
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(reader);
            String a;
            while ((a = bufferedReader.readLine()) != null) {
                getUserInformation += a;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONObject jsonObject = JSONObject.parseObject(getUserInformation);
        userInformation.setEmail(jsonObject.getString("12345"));
        userInformation.setUid(jsonObject.getInteger("uid"));
        userInformation.setUserName(jsonObject.getString("UserName"));

        FriendNode root=new FriendNode("我的好友",3);
        FriendNode node1=new FriendNode(new ImageIcon ("D:\\Java project\\HRSTalk\\HRSTalkCilent\\src\\main\\image\\picture.jpg"),"12345",1,1);
        FriendNode node2=new FriendNode(new ImageIcon ("D:\\Java project\\HRSTalk\\HRSTalkCilent\\src\\main\\image\\picture.jpg"),"测试2",0,1);
        FriendNode node3=new FriendNode(new ImageIcon ("D:\\Java project\\HRSTalk\\HRSTalkCilent\\src\\main\\image\\picture.jpg"),"测试3",0,1);
        FriendNode node4=new FriendNode(new ImageIcon ("D:\\Java project\\HRSTalk\\HRSTalkCilent\\src\\main\\image\\picture.jpg"),"测试4",0,1);
        FriendNode node5=new FriendNode(new ImageIcon ("D:\\Java project\\HRSTalk\\HRSTalkCilent\\src\\main\\image\\picture.jpg"),"测试5",0,1);


        root.add(node1);
        root.add(node2);
        root.add(node3);
        root.add(node4);
        root.add(node5);

//        for (int i = 1; i < Map.getSize(); i++) {
//            FriendNode node = new FriendNode(new ImageIcon(), map.get("name"),0,1);
//            root.add(node);
//        }

        //将刚才的根节点添加到JTree中
        tree = new JTree(root);
        //将树的前面连接去掉
        tree.putClientProperty("JTree.lineStyle", "Horizontal");
        //设置树的字体大小，样式
        tree.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        //设置树节点的高度
        tree.setRowHeight(70);
        //设置单元描述
        tree.setCellRenderer(new FriendNodeRenderer());

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

        final JPanel friendJpanel = new JPanel();
        tabbedPane.addTab("我的好友", null, friendJpanel, null);
        friendJpanel.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        friendJpanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setViewportView(tree);
        friendJpanel.add(scrollPane);
        //添加树的双击触发事件
        tree.addTreeSelectionListener(this);
        JPanel mainInformationJpanel = new JPanel();
        tabbedPane.addTab("消息列表", null,  mainInformationJpanel, null);

        JLabel information = new JLabel("暂未实现");
        mainInformationJpanel.add(information);

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    /**
     * 当用户点击朋友树中的朋友节点的时候，会生成对应的朋友节点对象聊天窗口
     * 聊天对象的信息会发送报文通过网络从服务端中获取
     * @param e
     */
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode getNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        if (getNode.getLevel() == 1) {
            String friendName = ((FriendNode) getNode).getName();
            TalkWindows talkWindows = TalkWindowsList.getTalkWindows("Talk");
            if (talkWindows == null) {
                talkWindows = new TalkWindows();
                talkWindows.talkWindows(friendName, userInformation);
                TalkWindowsEntity talkWindowsEntity = new TalkWindowsEntity();
                talkWindowsEntity.setName(friendName);
                talkWindowsEntity.setTalkWindows(talkWindows);
                TalkWindowsList.addChatUI(talkWindowsEntity);
            } else {
                talkWindows.show();
            }
        }
    }
}
