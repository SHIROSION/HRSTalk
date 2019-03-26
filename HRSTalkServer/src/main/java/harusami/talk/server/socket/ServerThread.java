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

import java.io.DataInputStream;
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

        while(socket != null) {
            try {
                DataInputStream  in = new DataInputStream(socket.getInputStream());
                String ret = in.readUTF();
                String msgs[] = ret.split("&&##");
                String cmd = msgs[0];
                String msg = msgs[1];
                if (cmd.contains("WorldChat")) {
                    System.out.println(msg);

                }
//                objectInputStream = new ObjectInputStream(socket.getInputStream());
//                CommandTranser commandTranser = (CommandTranser) objectInputStream.readObject();

            } catch (IOException e) {
                e.printStackTrace();
            }
//            catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
        }
    }
}
