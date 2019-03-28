package harusami.talk.client.socket;
/*
 * @package: harusami.talk.cilent.socket
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/24 下午 10:31
 */

import harusami.serialize.CommandTranser;
import harusami.talk.client.control.LogInControl;
import harusami.talk.client.control.PasswordEncryption;
import harusami.talk.client.gui.FriendList;
import harusami.talk.client.gui.LoginWindows;
import harusami.talk.client.gui.TalkWindows;
import harusami.talk.client.information.TalkWindowsEntity;
import harusami.talk.client.information.TalkWindowsList;
import harusami.talk.client.information.UserInformation;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @classname: ClientTread
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/24 下午 10:31
 * @Version 1.0
 */
public class ClientTread extends Thread {
    private Socket socket;
    private boolean isOnline = true;
    private ClientSocket clientSocket;
    private int port = 8124;
    private String serverAddress = "127.0.0.1";

    public ClientTread(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        if (!isOnline) {
            return;
        }

        while (isOnline) {
            CommandTranser commandTranser = clientSocket.getData();
            if (commandTranser != null) {
                execute(commandTranser);
            } else {
                break;
            }
        }
    }

    private void execute(CommandTranser commandTranser) {
        if (commandTranser.getCmd().equals("Talk")) {
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd a hh:mm:ss:▶");
            String friendName = commandTranser.getSender();
            TalkWindows talkWindows = TalkWindowsList.getTalkWindows(friendName);
            if (TalkWindowsList.getTalkWindows(friendName) == null) {
                talkWindows = new TalkWindows();
                talkWindows.talkWindows(friendName);
                TalkWindowsEntity talkWindowsEntity = new TalkWindowsEntity();
                talkWindowsEntity.setName(friendName);
                talkWindowsEntity.setTalkWindows(talkWindows);
                String message = simpleDateFormat.format(date) + "\n" + commandTranser.getData() + "\n";
                talkWindows.textChat.append(message);
            } else {
                talkWindows.show();
            }
        }

        if (commandTranser.getCmd().equals("SignInError")) {
            JOptionPane.showMessageDialog(LoginWindows.mainJpanel, "该用户已注册",
                    "消息对话框",JOptionPane.WARNING_MESSAGE);
        }

        if (commandTranser.getCmd().equals("SignInSuccessful")) {
            JOptionPane.showMessageDialog(LoginWindows.mainJpanel, "注册成功",
                    "消息对话框",JOptionPane.PLAIN_MESSAGE);
        }

        if (commandTranser.getCmd().equals("LogInInformation")) {
            LogInControl.setServerPassword((String) commandTranser.getData());
        }
    }
}
