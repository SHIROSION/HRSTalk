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
import harusami.talk.client.information.UserInformation;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @classname: ClientTalkTread
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/24 下午 10:31
 * @Version 1.0
 */
public class ClientTalkTread extends Thread {

    private ClientSocket clientSocket;
    private UserInformation userInformation;
    private String name;
    private Boolean isOnline;

    private ClientTalkTread(ClientSocket clientSocket, UserInformation userInformation) {
        this.clientSocket = clientSocket;
        this.userInformation = userInformation;
        this.name = userInformation.getUserName();
    }

    /**
     * 此方法日后实现
     * @return
     */
    public boolean isOnline() {
        return isOnline;
    }

    /**
     * 此方法日后实现
     * @return
     */
    public void setOnline(boolean isOnline) {
        this.isOnline = true;
    }

    @Override
    public void run() {
        if (!isOnline) {
            JOptionPane.showMessageDialog(null, "对方处于离线状态");
        } else {
            while (isOnline) {
                CommandTranser commandTranser = clientSocket.getData();
                //与服务器端相同处理接收到的消息(命令)
                //这里处理来自服务器的消息(命令)
                if (commandTranser != null) {
                    execute(commandTranser);
                }
            }
        }
    }

    public void execute (CommandTranser commandTranser) {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd hh:mm:ss a");
        String message = commandTranser.getData() + simpleDateFormat.format(date);
    }
}
