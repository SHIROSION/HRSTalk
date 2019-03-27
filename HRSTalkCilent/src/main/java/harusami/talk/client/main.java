package harusami.talk.client;
/*
 * @package: harusami.talk
 * @program: HRSTalk
 * @description  ${description}
 *
 * @author:  rinne
 * @e-mail:  minami.rinne.me@gmail.com
 * @date: 2019/03/19 上午 03:01
 */

import harusami.talk.client.socket.ClientTread;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @classname: main
 * @description: %{description}
 * @author: rinne
 * @date: 2019/03/19 上午 03:01
 * @Version 1.0
 */
public class main {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(8124);
            Socket socket = null;
            while (true) {
//                socket = ss.accept();
//                ClientTread clientTread = new ClientTread(socket);
//                clientTread.start();
//                System.out.println("线程" + clientTread.getId() + "已启动");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
