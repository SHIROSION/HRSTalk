package harusami.talk.server.socket;
/*
 * @package: harusami.talk.server.socket
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/25 上午 10:51
 */

import harusami.talk.server.information.CommandTranser;
import harusami.talk.server.information.SocketList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @classname: ServerThread
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/25 上午 10:51
 * @Version 1.0
 */
public class ServerThread extends Thread {

    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        ObjectOutputStream objectOutputStream1 = null;
        CommandTranser commandTranser = new CommandTranser();

        while(socket != null) {
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                if ("message".equals(commandTranser.getCmd())) {
                    //如果 msg.ifFlag即 服务器处理成功 可以向朋友发送信息 如果服务器处理信息失败 信息发送给发送者本人
                    if (commandTranser.isFlag()) {
                        objectOutputStream = new ObjectOutputStream(
                                SocketList.getSocket(commandTranser.getReceiver()).getOutputStream());
                    } else {
                        objectOutputStream1 = new ObjectOutputStream(socket.getOutputStream());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if ("WorldChat".equals(commandTranser.getCmd())) {
                    HashMap<String, Socket> map = SocketList.getMap();
                    Iterator<Map.Entry<String, Socket>> it = map.entrySet().iterator();
                    while(it.hasNext()) {
                        Map.Entry<String, Socket> entry = it.next();
                        if(!entry.getKey().equals(commandTranser.getSender())) {
                            objectOutputStream = new ObjectOutputStream(entry.getValue().getOutputStream());
                            objectOutputStream1.writeObject(commandTranser);
                        }
                    }
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
