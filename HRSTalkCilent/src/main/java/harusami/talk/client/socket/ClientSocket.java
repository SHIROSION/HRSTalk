package harusami.talk.client.socket;
/*
 * @package: harusami.talk.cilent.socket
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/24 下午 08:43
 */

import harusami.serialize.CommandTranser;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @classname: ClientSocket
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/24 下午 08:43
 * @Version 1.0
 */
public class ClientSocket {

    private int port = 8124;
    private String serverAddress = "127.0.0.1";
    private Socket socket;
    private CommandTranser commandTranser = null;

    public ClientSocket() {
        try {
            socket = new Socket(serverAddress, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "无法连接客户端");
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "无法连接客户端");
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * 向服务端发送数据
     * @param commandTranser
     */
    public void sendData(CommandTranser commandTranser) {
        //主要的作用是用于写入对象信息与读取对象信息
        // 对象信息一旦写到文件上那么对象的信息就可以做到持久化了
        ObjectOutputStream objectOutputStream = null;
        this.commandTranser = commandTranser;
        if (socket != null) {
            try {
                objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                objectOutputStream.writeObject(this.commandTranser);
            } catch(UnknownHostException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "服务器端未开启");
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "服务器端未开启");
            }
        }
    }

    /**
     * 接受服务端发送的消息
     * @return
     */
    public CommandTranser getData() {
        ObjectInputStream objectInputStream = null;
        CommandTranser commandTranser = null;

        if (socket != null) {
            try {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                commandTranser = (CommandTranser) objectInputStream.readObject();
            } catch(UnknownHostException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "服务器端未开启");
                return null;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "服务器端未开启");
                return null;
            }
        }
        return commandTranser;
    }
}